package action.nonmember;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.AdminDAO;
import dao.DoctorDAO;
import dao.HospitalDAO;
import dao.MemberDAO;
import dao.PharmacyDAO;
import model.MemberBean;
import model.QuestionnaireBean;
import util.XssFilter;

public class U01_01 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		
		String[] check_datetype_birth = request.getParameterValues("birth");
		String[] check_datetype_insurance_expiry_date = request.getParameterValues("insurance_expiry_date");


		String birth = request.getParameterValues("birth")[0] + "-" + request.getParameterValues("birth")[1] + "-"
				+ request.getParameterValues("birth")[2];
		String tel = request.getParameterValues("tel")[0] + "-" + request.getParameterValues("tel")[1] + "-"
				+ request.getParameterValues("tel")[2];
		String zip_code = request.getParameterValues("zip_code")[0] + "-" + request.getParameterValues("zip_code")[1];
		String insurance_num = request.getParameterValues("insurance_num")[0] + "-"
				+ request.getParameterValues("insurance_num")[1];
		String insurance_expiry_date = request.getParameterValues("insurance_expiry_date")[0] + "-"
				+ request.getParameterValues("insurance_expiry_date")[1] + "-"
				+ request.getParameterValues("insurance_expiry_date")[2];

		/* 会員情報 */
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", request.getParameter("email"));
		map.put("name", request.getParameter("frist_name").replaceAll("\\t", "") + " "
				+ request.getParameter("last_name").replaceAll("\\t", ""));
		map.put("kana", request.getParameter("frist_kana").replaceAll("\\t", "") + " "
				+ request.getParameter("last_kana").replaceAll("\\t", ""));
		map.put("birth", birth);
		map.put("tel", tel);
		map.put("gender", request.getParameter("gender"));
		map.put("zip_code", zip_code);
		map.put("address", request.getParameter("address"));
		map.put("insurance_num", insurance_num);
		map.put("insurance_expiry_date", insurance_expiry_date);
		map.put("insurance_mark", request.getParameter("insurance_mark"));

		/* 問診票 */
		Map<String, String> q_map = new HashMap<String, String>();
		q_map.put("blood_type", request.getParameter("blood_type"));
		q_map.put("medical_history", request.getParameter("medical_history").replaceAll("\r\n", "<br>"));
		q_map.put("medication", request.getParameter("medication").replaceAll("\r\n", "<br>"));
		q_map.put("drink", request.getParameter("drink"));
		q_map.put("smoke", request.getParameter("smoke"));
		q_map.put("pregnancy", request.getParameter("pregnancy"));
		q_map.put("allergy", request.getParameter("allergy").replaceAll("\r\n", "<br>"));

		String patternNum = "^[0-9]*$";
		String patternEmail = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
		String patternPw = "^[A-Za-z0-9]{8,64}$";
		String[] pw = request.getParameterValues("pw");

		// email
		if (!map.get("email").matches(patternEmail)) {
			forward.setErrorMsg("正しいメールアドレスを入力してください。'");
			return forward;
		}
		MemberDAO memberDAO = MemberDAO.getInstance();
		DoctorDAO doctorDAO = DoctorDAO.getInstance();
		PharmacyDAO pharmacyDAO = PharmacyDAO.getInstance();
		HospitalDAO hospitalDAO = HospitalDAO.getInstance();
		AdminDAO adminDAO = AdminDAO.getInstance();
		

		if (doctorDAO.isDuplicateEmail(map.get("email")) || memberDAO.isDuplicateEmail(map.get("email")) || pharmacyDAO.isDuplicateEmail(map.get("email"))
				|| hospitalDAO.isDuplicateEmail(map.get("email")) || adminDAO.isDuplicateEmail(map.get("email"))) {
			forward.setErrorMsg("既に存在しているメールアドレスです。\\n別のメールアドレスを入力してください。");
			return forward;
		}
		if(map.get("name").length() > 50) {
			forward.setErrorMsg("「名前」が50文字以上のため、登録できません。");
			return forward;
		}
		if(map.get("kana").length() > 50) {
			forward.setErrorMsg("「ふりがな」が50文字以上のため、登録できません。");
			return forward;
		}

		// input text type
		for (String ary : map.keySet()) {
			if ("".equals(map.get(ary)) || map.get(ary) == null || "".equals(map.get(ary).replaceAll("\\t", ""))) {
				forward.setErrorMsg("入力欄には空白・スペース禁止です。");
				return forward;
			}
		}
		
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

		try {
			if (map.get("birth").length() != 10 || Integer.parseInt(check_datetype_birth[1]) < 1 || Integer.parseInt(check_datetype_birth[1]) > 12) {
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

		// password
		if (pw[0] == null || pw[1] == null || "".equals(pw[0]) || "".equals(pw[1])) {
			forward.setErrorMsg("パスワードは8文字以上を入力してください。");
			return forward;
		} else {
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
		}
		
		XssFilter xssFilter = XssFilter.getInstance();
		pw = xssFilter.stripTagAll(pw);
		map = xssFilter.stripTagAll(map);
		q_map = xssFilter.stripTagAll(q_map);

		/* 会員情報 */
		MemberBean member = new MemberBean();
		member.setM_email(map.get("email"));
		member.setM_pw(pw[0]);
		member.setM_name(map.get("name"));
		member.setM_kana(map.get("kana"));
		member.setM_gender(map.get("gender"));
		member.setM_address(map.get("address"));
		member.setM_i_mark(map.get("insurance_mark"));
		member.setM_birth(map.get("birth"));
		member.setM_tel(map.get("tel"));
		member.setM_zip_code(map.get("zip_code"));
		member.setM_i_num(map.get("insurance_num"));
		member.setM_i_expiry_date(map.get("insurance_expiry_date"));

		/* 問診票情報 */
		QuestionnaireBean questionnaire = new QuestionnaireBean();
		questionnaire.setQ_blood_type(q_map.get("blood_type"));
		questionnaire.setQ_medical_history(q_map.get("medical_history"));
		questionnaire.setQ_medication(q_map.get("medication"));
		questionnaire.setQ_drink("1".equals(q_map.get("drink")) ? true : false);
		questionnaire.setQ_smoke("1".equals(q_map.get("smoke")) ? true : false);
		questionnaire.setQ_pregnancy("1".equals(q_map.get("pregnancy")) ? true : false);
		questionnaire.setQ_allergy(q_map.get("allergy"));
		
		//一時的に使うからbeanにする、
		session.setAttribute("questionnaireBean", questionnaire);
		session.setAttribute("memberBean", member);
		forward.setPath("u01_02");
		return forward;

	}

}
