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
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberBean member = (MemberBean) session.getAttribute("member");
		MemberBean updateMemberBean = new MemberBean();
		String patternNum = "^[0-9]*$";
		String patternPw = "^[A-Za-z0-9]{8,64}$";
		XssFilter xssFilter = XssFilter.getInstance();
		
		String[] pw = xssFilter.stripTagAll(request.getParameterValues("pw"));
		String frist_name = xssFilter.stripTagAll(request.getParameter("frist_name").replaceAll("\\t", ""));
		String last_name = xssFilter.stripTagAll(request.getParameter("last_name").replaceAll("\\t", ""));
		String frist_kana = xssFilter.stripTagAll(request.getParameter("frist_kana").replaceAll("\\t", ""));
		String last_kana = xssFilter.stripTagAll(request.getParameter("last_kana").replaceAll("\\t", ""));
		String gender = xssFilter.stripTagAll(request.getParameter("gender"));
		String address = xssFilter.stripTagAll(request.getParameter("address"));
		String insurance_mark = xssFilter.stripTagAll(request.getParameter("insurance_mark"));
		
		String[] check_birth = xssFilter.stripTagAll(request.getParameterValues("birth"));
		String[] check_insurance_expiry_date = xssFilter.stripTagAll(request.getParameterValues("insurance_expiry_date"));
		
		String[] tel_ = xssFilter.stripTagAll(request.getParameterValues("tel"));
		String[] zip_code = xssFilter.stripTagAll(request.getParameterValues("zip_code"));
		String[] insurance_num = xssFilter.stripTagAll(request.getParameterValues("insurance_num"));
		
		
		if(frist_name == null || "".equals(frist_name)) {
			forward.setErrorMsg("苗字を入力してください。");
			return forward;
		}
		
		if(last_name == null || "".equals(last_name)) {
			forward.setErrorMsg("名前を入力してください。");
			return forward;
		}
		String name = frist_name + " " + last_name;
		if(name.length() > 50) {
			forward.setErrorMsg("「名前」が50文字以上のため、登録できません。");
			return forward;
		}
		
		if(frist_kana == null || "".equals(frist_kana)) {
			forward.setErrorMsg("苗字のふりがなを入力してください。");
			return forward;
		}
		
		if(last_kana == null || "".equals(last_kana)) {
			forward.setErrorMsg("名前のふりがなを入力してください。");
			return forward;
		}
		
		String kana = frist_kana + " " + last_kana;
		if(kana.length() > 50) {
			forward.setErrorMsg("「ふりがな」が50文字以上のため、登録できません。");
			return forward;
		}
		if(gender == null || "".equals(gender)) {
			forward.setErrorMsg("性別を選択してください。");
			return forward;
		}
		
		if(check_birth != null) {
			for(String birth1 : check_birth) {
				if(birth1 == null || "".equals(birth1)) {
					forward.setErrorMsg("生年月日を入力してください。");
					return forward;
				}
			}
			if (check_birth[0].length() != 4
					|| Integer.parseInt(check_birth[1]) < 1
					|| Integer.parseInt(check_birth[1]) > 12 
					|| Integer.parseInt(check_birth[2]) < 1
					|| Integer.parseInt(check_birth[2]) > 31
					|| check_birth[1].length() != 2
					|| check_birth[2].length() != 2) {
				forward.setErrorMsg("正しい生年月日を入力してください。 \\n例)1998 01 05");
				return forward;
			}
		}else {
			forward.setErrorMsg("生年月日を入力してください。");
			return forward;
		}
		String birth = check_birth[0] + "-" + check_birth[1] + "-"+ check_birth[2];
		
		if(tel_ != null) {
			for(String tel1 : tel_) {
				if(tel1 == null || "".equals(tel1)) {
					forward.setErrorMsg("電話番号を入力してください。");
					return forward;
				}
			}
		}else {
			forward.setErrorMsg("電話番号を入力してください。");
			return forward;
		}
		String tel = tel_[0] + "-" + tel_[1] + "-"+ tel_[2];
		
		if(zip_code != null) {
			for(String zipcode : zip_code) {
				if(zipcode == null || "".equals(zipcode)) {
					forward.setErrorMsg("郵便番号を入力してください。");
					return forward;
				}
			}
		}else {
			forward.setErrorMsg("郵便番号を入力してください。");
			return forward;
		}
		
		if(address == null || "".equals(address)) {
			forward.setErrorMsg("住所を入力してください。");
			return forward;
		}
		
		if(insurance_num[0] == null || "".equals(insurance_num[0])
				|| insurance_num[1] == null || "".equals(insurance_num[1])) {
			forward.setErrorMsg("保険証番号を入力してください。");
			return forward;
		}
		
		if(insurance_mark == null || "".equals(insurance_mark)) {
			forward.setErrorMsg("保険証記号を入力してください。");
			return forward;
		}
		
		if(check_insurance_expiry_date != null) {
			for(String insurance_expiry_date1 : check_insurance_expiry_date) {
				if(insurance_expiry_date1 == null || "".equals(insurance_expiry_date1)) {
					forward.setErrorMsg("保険証有効期限を入力してください。");
					return forward;
				}
				
			}
			if (check_insurance_expiry_date[0].length() != 4
					|| Integer.parseInt(check_insurance_expiry_date[1]) < 1
					|| Integer.parseInt(check_insurance_expiry_date[1]) > 12
					|| Integer.parseInt(check_insurance_expiry_date[2]) < 1
					|| Integer.parseInt(check_insurance_expiry_date[2]) > 31
					|| check_insurance_expiry_date[1].length() != 2
					|| check_insurance_expiry_date[2].length() != 2) {
				forward.setErrorMsg("正しい保険証有効期限を入力してください。 \\n例)2024 04 01");
				return forward;
			}
		}else {
			forward.setErrorMsg("保険証有効期限を入力してください。");
			return forward;
		}
		
		String insurance_expiry_date = check_insurance_expiry_date[0] + "-"
				+ check_insurance_expiry_date[1] + "-"
				+ check_insurance_expiry_date[2];
		
		
		
		/* 会員情報 */
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", frist_name+ " "+ last_name);
		map.put("kana", frist_kana + " "+ last_kana);
		map.put("birth", birth);
		map.put("tel", tel);
		map.put("gender", gender);
		map.put("zip_code", zip_code[0] + "-"+zip_code[1]);
		map.put("address", address);
		map.put("insurance_num", insurance_num[0] + "-" + insurance_num[1]);
		map.put("insurance_expiry_date", insurance_expiry_date);
		map.put("insurance_mark", insurance_mark);
		
		if(!map.get("birth").replaceAll("-", "").matches(patternNum)) {
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

		
		String pw_original = xssFilter.stripTagAll(request.getParameter("pw_original"));
		
		map = xssFilter.stripTagAll(map);

		if(!map.get("birth").replaceAll("-", "").matches(patternNum)) {
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

		/* 会員情報 */
		updateMemberBean.setM_email(member.getM_email());
		updateMemberBean.setM_pw(pw[0]);
		updateMemberBean.setM_name(map.get("name"));
		updateMemberBean.setM_kana(map.get("kana"));
		updateMemberBean.setM_gender(map.get("gender"));
		updateMemberBean.setM_address(map.get("address"));
		updateMemberBean.setM_i_mark(map.get("insurance_mark"));
		updateMemberBean.setM_birth(map.get("birth"));
		updateMemberBean.setM_tel(map.get("tel"));
		updateMemberBean.setM_zip_code(map.get("zip_code"));
		updateMemberBean.setM_i_num(map.get("insurance_num"));
		updateMemberBean.setM_i_expiry_date(map.get("insurance_expiry_date"));
		updateMemberBean.setAge(memberDAO.countBirth(map.get("birth")));
		
		// if update with password
		if (pw[0] != null && pw[1] != null && !"".equals(pw[0]) && !"".equals(pw[1])) {
			if (!pw[0].equals(pw[1])) {
				forward.setErrorMsg("確認パスワードと一致してください。");
				return forward;
			} 
			if (!pw[0].matches(patternPw) || !pw[1].matches(patternPw)) {
				forward.setErrorMsg("パスワードは英数字のみ・8桁以上を入力してください。");
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
		
		updateMemberBean.setM_pw(SHA256.getEncrypt(pw[0]));
		session.setAttribute("member", updateMemberBean);
		forward.setPath("u06_01");
		return forward;
	}

}
