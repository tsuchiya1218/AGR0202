package action.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.MemberDAO;
import model.MemberBean;
import util.Gmail;
import util.SHA256;
import util.XssFilter;

public class U06 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);

		String brith = request.getParameterValues("brith")[0] + "-" + request.getParameterValues("brith")[1] + "-"
				+ request.getParameterValues("brith")[2];
		String tel = request.getParameterValues("tel")[0] + "-" + request.getParameterValues("tel")[1] + "-"
				+ request.getParameterValues("tel")[2];
		String zip_code = request.getParameterValues("zip_code")[0] + "-" + request.getParameterValues("zip_code")[1];
		String insurance_num = request.getParameterValues("insurance_num")[0] + "-"
				+ request.getParameterValues("insurance_num")[1];
		String insurance_expiry_date = request.getParameterValues("insurance_expiry_date")[0] + "-"
				+ request.getParameterValues("insurance_expiry_date")[1] + "-"
				+ request.getParameterValues("insurance_expiry_date")[2];
		
		String[] check_datetype_brith = request.getParameterValues("brith");
		String[] check_datetype_insurance_expiry_date = request.getParameterValues("insurance_expiry_date");
		
		/* 会員情報 */
		Map<String, String> map = new HashMap<String, String>();
		map.put("frist_name", request.getParameter("frist_name").replaceAll("\\t", ""));
		map.put("last_name", request.getParameter("last_name").replaceAll("\\t", ""));
		map.put("frist_kana", request.getParameter("frist_kana").replaceAll("\\t", ""));
		map.put("last_kana", request.getParameter("last_kana").replaceAll("\\t", ""));
		map.put("brith", brith);
		map.put("tel", tel);
		map.put("gender", request.getParameter("gender"));
		map.put("zip_code", zip_code);
		map.put("address", request.getParameter("address"));
		map.put("insurance_num", insurance_num);
		map.put("insurance_expiry_date", insurance_expiry_date);
		map.put("insurance_mark", request.getParameter("insurance_mark"));

		String patternNum = "^[0-9]*$";
		String patternPw = "^[A-Za-z0-9]{8,64}$";
		XssFilter xssFilter = XssFilter.getInstance();
		
		String[] pw = xssFilter.stripTagAll(request.getParameterValues("pw"));
		String pw_original = xssFilter.stripTagAll(request.getParameter("pw_original"));
		
		map = xssFilter.stripTagAll(map);

		// input text type
		for (String ary : map.keySet()) {
			if ("".equals(map.get(ary)) || map.get(ary) == null || "".equals(map.get(ary).replaceAll("\\t", ""))) {
				forward.setErrorMsg("入力欄には空白・スペース禁止です。");
				return forward;
			}
		}
		
		if(!map.get("brith").replaceAll("-", "").matches(patternNum)) {
			forward.setErrorMsg("生年月日は数字のみです。");
			return forward;
		}
		if(!map.get("insurance_num").replaceAll("-", "").matches(patternNum)) {
			forward.setErrorMsg("保険証番号は数字のみです。");
			return forward;
		}
		
		if(!map.get("insurance_expiry_date").replaceAll("-", "").matches(patternNum)) {
			forward.setErrorMsg("保険証 有効期限は数字のみです。");
			return forward;
		}

		try {
			if (map.get("brith").length() != 10 || Integer.parseInt(check_datetype_brith[1]) < 1 || Integer.parseInt(check_datetype_brith[1]) > 12) {
				forward.setErrorMsg("正しい生年月日を入力してください。 \\n例)1998 01 05");
				return forward;
			}
			if (map.get("insurance_expiry_date").length() != 10 || Integer.parseInt(check_datetype_insurance_expiry_date[2]) < 1
					|| Integer.parseInt(check_datetype_insurance_expiry_date[2]) > 31) {
				forward.setErrorMsg("正しい保険証有効期限を入力してください。 \\n例)2024 04 01");
				return forward;
			}
		}catch(NumberFormatException e) {
			forward.setErrorMsg("生年月日・保険証有効期限に入力してください。");
			return forward;
		}
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberBean member = (MemberBean) session.getAttribute("member");
		MemberBean updateMemberBean = new MemberBean();
		/* 会員情報 */
		updateMemberBean.setM_email(member.getM_email());
		updateMemberBean.setM_pw(pw[0]);
		updateMemberBean.setM_name(map.get("frist_name") + " " + map.get("last_name"));
		updateMemberBean.setM_kana(map.get("frist_kana") + " " + map.get("last_kana"));
		updateMemberBean.setM_gender(map.get("gender"));
		updateMemberBean.setM_address(map.get("address"));
		updateMemberBean.setM_i_mark(map.get("insurance_mark"));
		updateMemberBean.setM_brith(map.get("brith"));
		updateMemberBean.setM_tel(map.get("tel"));
		updateMemberBean.setM_zip_code(map.get("zip_code"));
		updateMemberBean.setM_i_num(map.get("insurance_num"));
		updateMemberBean.setM_i_expiry_date(map.get("insurance_expiry_date"));
		
		// if update with password
		if (pw[0] != null && pw[1] != null && !"".equals(pw[0]) && !"".equals(pw[1])) {
			if (!pw[0].equals(pw[1])) {
				forward.setErrorMsg("確認パスワードと一致してください。");
				return forward;
			} 
			if (!pw[0].matches(patternPw) || !pw[1].matches(patternPw) || pw[0].length() < 8
					|| pw[1].length() < 8) {
				forward.setErrorMsg("パスワードは英数字のみ入力してください。");
				return forward;
			} 
			if (pw[0].indexOf(" ") != -1 || pw[1].indexOf(" ") != -1) {
				forward.setErrorMsg("パスワードにはスペース禁止です。");
				return forward;
			}
			if(pw_original == null || "".equals(pw_original)) {
				forward.setErrorMsg("現在のパスワードを入力してください。");
				return forward;
			}
			
			if(!member.getM_pw().equals(SHA256.getEncrypt(pw_original))) {
				forward.setErrorMsg("現在のパスワードが一致しません。");
				return forward;
			}
			if(!memberDAO.updateMemberBeanWithPassword(updateMemberBean)) {
				forward.setErrorMsg("個人情報変更に失敗しました。問い合わせしてください。");
				return forward;
			}
		} else {
			if(!memberDAO.updateMemberBean(updateMemberBean)) {
				forward.setErrorMsg("個人情報変更に失敗しました。問い合わせしてください。");
				return forward;
			}
		}
		Gmail gmail = Gmail.getInstance();
		
		if(!gmail.sendUpdateNotice((String)session.getAttribute("beforeHashEmail"), updateMemberBean.getM_name())) {
			forward.setErrorMsg("個人情報変更のお知らせメールの送信が失敗しました。\\n個人情報は変更されましたのでご安心してください。");
			return forward;
		}
		
		session.setAttribute("member", updateMemberBean);
		forward.setPath("u06_01");
		return forward;
	}

}
