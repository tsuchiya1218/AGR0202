package action.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.ChildDAO;
import model.ChildBean;
import model.MemberBean;
import util.XssFilter;

public class U07_02 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		XssFilter xssFilter = XssFilter.getInstance();
		
		String[] check_datetype_birth = xssFilter.stripTagAll(request.getParameterValues("birth"));
		String[] check_null_insurance_expiry_date = xssFilter
				.stripTagAll(request.getParameterValues("insurance_expiry_date"));

		String birth = request.getParameterValues("birth")[0] + "-" + request.getParameterValues("birth")[1] + "-"
				+ request.getParameterValues("birth")[2];
		String insurance_num = request.getParameterValues("insurance_num")[0] + "-"
				+ request.getParameterValues("insurance_num")[1];
		String insurance_expiry_date = "";
		for (String i_expiry_date : check_null_insurance_expiry_date) {
			if (i_expiry_date != null || "".equals(i_expiry_date)) {
				insurance_expiry_date += i_expiry_date + "-";
			}
		}
		insurance_expiry_date = insurance_expiry_date.replaceFirst(".$", "");

		Map<String, String> map = new HashMap<String, String>();
		map.put("medical_num", request.getParameter("medical_num"));
		map.put("insurance_num", insurance_num);
		map.put("insurance_expiry_date", insurance_expiry_date);
		map.put("insurance_mark", request.getParameter("insurance_mark"));
		map.put("name", request.getParameter("frist_name").replaceAll("\\t", "") + " "
				+ request.getParameter("last_name").replaceAll("\\t", ""));
		map.put("kana", request.getParameter("frist_kana").replaceAll("\\t", "") + " "
				+ request.getParameter("last_kana").replaceAll("\\t", ""));
		map.put("birth", birth);
		map.put("gender", request.getParameter("gender"));
		map.put("blood_type", request.getParameter("blood_type"));
		map.put("medical_history", request.getParameter("medical_history").replaceAll("\r\n", "<br>"));
		map.put("medication", request.getParameter("medication").replaceAll("\r\n", "<br>"));
		map.put("allergy", request.getParameter("allergy").replaceAll("\r\n", "<br>"));

		String patternNum = "^[0-9]*$";
		map = xssFilter.stripTagAll(map);
		
		if(map.get("name").length() > 50) {
			forward.setErrorMsg("「名前」が50文字以上のため、登録できません。");
			return forward;
		}
		if(map.get("kana").length() > 50) {
			forward.setErrorMsg("「ふりがな」が50文字以上のため、登録できません。");
			return forward;
		}
		
		if (!map.get("birth").replaceAll("-", "").matches(patternNum)) {
			forward.setErrorMsg("生年月日は数字のみです。");
			return forward;
		}

		try {
			if (map.get("birth").length() != 10 || Integer.parseInt(check_datetype_birth[1]) < 1
					|| Integer.parseInt(check_datetype_birth[1]) > 12) {
				forward.setErrorMsg("正しい生年月日を入力してください。 \\n例)1998 01 05");
				return forward;
			}
		} catch (NumberFormatException e) {
			forward.setErrorMsg("生年月日を入力してください。");
			return forward;
		}

		MemberBean member = (MemberBean) session.getAttribute("member");

		ChildDAO childDAO = ChildDAO.getInstance();
		ChildBean childBean = new ChildBean();

		if (childDAO.isDuplicateMedical_num(map.get("medical_num"))) {
			forward.setErrorMsg("既に登録されている子ども医療費番号です。");
			return forward;
		}
		if (childDAO.isDuplicateC_i_num(map.get("insurance_num"))) {
			forward.setErrorMsg("既に登録されている保険証番号です。");
			return forward;
		}

		childBean.setC_m_num(member.getM_num());
		childBean.setC_medical_num(map.get("medical_num"));
		childBean.setC_i_num(map.get("insurance_num"));
		childBean.setC_i_expiry_date(map.get("insurance_expiry_date"));
		childBean.setC_i_mark(map.get("insurance_mark"));
		childBean.setC_name(map.get("name"));
		childBean.setC_kana(map.get("kana"));
		childBean.setC_birth(map.get("birth"));
		childBean.setC_gender(map.get("gender"));
		childBean.setC_blood_type(map.get("blood_type"));
		childBean.setC_medical_history(map.get("medical_history"));
		childBean.setC_medication(map.get("medication"));
		childBean.setC_allergy(map.get("allergy"));

		session.setAttribute("childBean", childBean);
		forward.setPath("u07_03");

		return forward;
	}

}
