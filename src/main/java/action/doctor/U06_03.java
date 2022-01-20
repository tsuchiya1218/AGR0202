package action.doctor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.DoctorDAO;
import model.DoctorBean;
import util.Gmail;
import util.XssFilter;

public class U06_03 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);

		String frist_name_ = request.getParameter("frist_name");
		String last_name_ = request.getParameter("last_name");
		String frist_kana_ = request.getParameter("frist_kana");
		String last_kana_ = request.getParameter("last_kana");
		String birth = request.getParameterValues("birth")[0] + "-" + request.getParameterValues("birth")[1] + "-"
				+ request.getParameterValues("birth")[2];
		String tel = request.getParameterValues("tel")[0] + "-" + request.getParameterValues("tel")[1] + "-"
				+ request.getParameterValues("tel")[2];
		String gender_ = request.getParameter("gender");
		String d_department_ = request.getParameter("d_department");

		if (frist_name_ == null || "".equals(frist_name_)) {
			forward.setErrorMsg("苗字を入力してください。");
			return forward;
		}
		if (last_name_ == null || "".equals(last_name_)) {
			forward.setErrorMsg("名前を入力してください。");
			return forward;
		} 
		if (frist_kana_ == null || "".equals(frist_kana_)) {
			forward.setErrorMsg("ふりがなを入力してください。");
			return forward;
		} 
		if (last_kana_ == null || "".equals(last_kana_)) {
			forward.setErrorMsg("ふりがなを入力してください。");
			return forward;
		}
		if (birth == null || "".equals(birth.replaceAll("-", ""))) {
			forward.setErrorMsg("生年月日を入力してください。");
			return forward;
		}
		if (tel == null || "".equals(tel.replaceAll("-", ""))) {
			forward.setErrorMsg("電話番号を入力してください。");
			return forward;
		}
		if (gender_ == null || "".equals(gender_)) {
			forward.setErrorMsg("性別を選択してください。");
			return forward;
		}
		if (d_department_ == null || "".equals(d_department_)) {
			forward.setErrorMsg("診察科を入力してください。");
			return forward;
		}
		XssFilter xssFilter = XssFilter.getInstance();

		String[] check_datetype_birth = birth.split("-");
		String name = xssFilter.stripTagAll(frist_name_.replaceAll("\\ts", "")) + " "
				+ xssFilter.stripTagAll(last_name_.replaceAll("\\s", ""));
		String kana = xssFilter.stripTagAll(frist_kana_.replaceAll("\\s", "")) + " "
				+ xssFilter.stripTagAll(last_kana_.replaceAll("\\s", ""));
		String gender = xssFilter.stripTagAll(gender_);
		String[] pw = xssFilter.stripTagAll(request.getParameterValues("pw"));
		String pw_original = xssFilter.stripTagAll(request.getParameter("pw_original"));

		String patternNum = "^[0-9]*$";
		String patternPw = "^[A-Za-z0-9]{8,64}$";
		
		if (name.length() > 50) {
			forward.setErrorMsg("名前が50文字超えています。");
			return forward;
		}
		if (kana.length() > 50) {
			forward.setErrorMsg("ふりがなは50文字までです。");
			return forward;
			
		}
		if (!birth.replaceAll("-", "").matches(patternNum) 
				|| check_datetype_birth[0].length() != 4
				|| Integer.parseInt(check_datetype_birth[1]) < 1 
				|| Integer.parseInt(check_datetype_birth[1]) > 12
				|| Integer.parseInt(check_datetype_birth[2]) < 1 
				|| Integer.parseInt(check_datetype_birth[2]) > 31
				|| check_datetype_birth[1].length() != 2
				|| check_datetype_birth[2].length() != 2) {
			forward.setErrorMsg("正しい生年月日を入力してください。 \\n例)1998 01 05");
			return forward;
		}
		
		boolean isChangePw = false;
		DoctorDAO doctorDAO = DoctorDAO.getInstance();
		DoctorBean doctor = (DoctorBean) session.getAttribute("doctor");
		if (pw[0] != null && pw[1] != null && !"".equals(pw[0]) && !"".equals(pw[1])) {
			if (!pw[0].equals(pw[1])) {
				forward.setErrorMsg("確認パスワードと一致してください。");
				return forward;
			}
			if (!pw[0].matches(patternPw) || !pw[1].matches(patternPw)) {
				forward.setErrorMsg("パスワードは英数字のみ・8桁以上を入力してください。");
				return forward;
			}
			if (pw_original == null || "".equals(pw_original)) {
				forward.setErrorMsg("現在のパスワードを入力してください。");
				return forward;
			}
			if (!doctorDAO.checkPassword(doctor.getD_email(), pw_original)) {
				forward.setErrorMsg("現在のパスワードが一致しません。");
				return forward;
			}
			isChangePw = true;
		}
		doctor.setD_name(name);
		doctor.setD_kana(kana);
		doctor.setD_gender(gender);
		doctor.setD_birth(birth);
		doctor.setD_tel(tel);

		if (isChangePw) {
			doctor.setD_pw(pw[0]);
			if (!doctorDAO.updateDoctorWithPassword(doctor)) {
				forward.setErrorMsg("個人情報変更が失敗しました。");
				return forward;
			}
		} else {
			if (!doctorDAO.updateDoctor(doctor)) {
				forward.setErrorMsg("個人情報変更が失敗しました。");
				return forward;
			}
		}
		Gmail gmail = Gmail.getInstance();

		if (!gmail.sendUpdateNotice((String) session.getAttribute("beforeHashEmail"), doctor.getD_name())) {
			forward.setErrorMsg("個人情報変更のお知らせメールの送信が失敗しました。\\n個人情報は変更されましたのでご安心してください。");
			return forward;
		}

		session.setAttribute("doctor", doctor);
		forward.setRedirectToAction(true);
		forward.setMsg("個人情報が変更されました。");
		forward.setPath("u06_01");
		return forward;
	}

}
