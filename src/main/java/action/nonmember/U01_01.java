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
		MemberDAO memberDAO = MemberDAO.getInstance();
		DoctorDAO doctorDAO = DoctorDAO.getInstance();
		PharmacyDAO pharmacyDAO = PharmacyDAO.getInstance();
		HospitalDAO hospitalDAO = HospitalDAO.getInstance();
		AdminDAO adminDAO = AdminDAO.getInstance();
		XssFilter xssFilter = XssFilter.getInstance();
		
		String patternNum = "^[0-9]*$";
		String patternEmail = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
		String patternPw = "^[A-Za-z0-9]{8,64}$";
		
		
		String email = request.getParameter("email");
		String[] pw = request.getParameterValues("pw");
		String frist_name = request.getParameter("frist_name");
		String last_name = request.getParameter("last_name");
		String frist_kana = request.getParameter("frist_kana");
		String last_kana = request.getParameter("last_kana");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		String insurance_mark = request.getParameter("insurance_mark");
		String[] check_birth = request.getParameterValues("birth");
		String[] check_insurance_expiry_date = request.getParameterValues("insurance_expiry_date");
		
		String[] tel_ = request.getParameterValues("tel");
		String[] zip_code = request.getParameterValues("zip_code");
		String[] insurance_num = request.getParameterValues("insurance_num");
		
		
		if("".equals(email) || email == null) {
			forward.setErrorMsg("メールアドレスを入力してください。");
			return forward;
		}
		if (!email.matches(patternEmail)) {
			forward.setErrorMsg("正しいメールアドレスを入力してください。");
			return forward;
		}
		
		if (doctorDAO.isDuplicateEmail(email) || memberDAO.isDuplicateEmail(email) || pharmacyDAO.isDuplicateEmail(email)
				|| hospitalDAO.isDuplicateEmail(email) || adminDAO.isDuplicateEmail(email)) {
			forward.setErrorMsg("既に存在しているメールアドレスです。\\n別のメールアドレスを入力してください。");
			return forward;
		}
		
		if(frist_name == null || "".equals(frist_name.replaceAll("\\s", ""))) {
			forward.setErrorMsg("苗字を入力してください。");
			return forward;
		}
		
		if(last_name == null || "".equals(last_name.replaceAll("\\s", ""))) {
			forward.setErrorMsg("名前を入力してください。");
			return forward;
		}
		String name = frist_name + " " + last_name;
		if(name.length() > 50) {
			forward.setErrorMsg("「名前」が50文字以上のため、登録できません。");
			return forward;
		}
		
		if(frist_kana == null || "".equals(frist_kana.replaceAll("\\s", ""))) {
			forward.setErrorMsg("苗字のふりがなを入力してください。");
			return forward;
		}
		
		if(last_kana == null || "".equals(last_kana.replaceAll("\\s", ""))) {
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
		map.put("email", email);
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
		

		/* 問診票 */
		Map<String, String> q_map = new HashMap<String, String>();
		q_map.put("blood_type", request.getParameter("blood_type"));
		q_map.put("medical_history", request.getParameter("medical_history").replaceAll("\r\n", "<br>"));
		q_map.put("medication", request.getParameter("medication").replaceAll("\r\n", "<br>"));
		q_map.put("drink", request.getParameter("drink"));
		q_map.put("smoke", request.getParameter("smoke"));
		q_map.put("pregnancy", request.getParameter("pregnancy"));
		q_map.put("allergy", request.getParameter("allergy").replaceAll("\r\n", "<br>"));

		
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
		member.setM_i_mark(insurance_mark);
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
