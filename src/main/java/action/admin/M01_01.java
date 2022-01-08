package action.admin;

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
import model.DoctorBean;
import util.XssFilter;

public class M01_01 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);
		session.removeAttribute("doctorBean");
		ActionForward forward = new ActionForward();
		
		String patternNum = "^[0-9]*$";
		String patternEmail = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
		String patternPw = "^[A-Za-z0-9]{8,64}$";
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String frist_name = request.getParameter("frist_name");
		String last_name = request.getParameter("last_name");
		String frist_kana = request.getParameter("frist_kana");
		String last_kana = request.getParameter("last_kana");
		String[] birth_ = request.getParameterValues("birth");
		String[] tel_ = request.getParameterValues("tel");
		String gender = request.getParameter("gender");
		String department = request.getParameter("department");
		String hospital_name = request.getParameter("hospital_name");
		if(email == null || "".equals(email)) {
			forward.setErrorMsg("メールアドレスを入力してください。");
			return forward;
		}
		if(!email.matches(patternEmail)) {
			forward.setErrorMsg("正しいメールアドレスを入力してください。");
			return forward;
		}
		if(AdminDAO.getInstance().isDuplicateEmail(email) || PharmacyDAO.getInstance().isDuplicateEmail(email) ||
				MemberDAO.getInstance().isDuplicateEmail(email) || DoctorDAO.getInstance().isDuplicateEmail(email) ||
				HospitalDAO.getInstance().isDuplicateEmail(email)) {
			forward.setErrorMsg("既に登録されているメールアドレスです。");
			return forward;
		}
		if(password == null || "".equals(password)) {
			forward.setErrorMsg("パスワードをを入力してください。");
			return forward;
		}
		if(!password.matches(patternPw)) {
			forward.setErrorMsg("パスワードは英数字のみ８桁以上にしてください。");
			return forward;
		}
		if(frist_name == null || "".equals(frist_name)) {
			forward.setErrorMsg("苗字をを入力してください。");
			return forward;
		}
		if(last_name == null || "".equals(last_name)) {
			forward.setErrorMsg("名前を入力してください。");
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
		if(birth_[0] == null || "".equals(birth_[0]) || birth_[1] == null || "".equals(birth_[1]) ||
				birth_[2] == null || "".equals(birth_[2])) {
			forward.setErrorMsg("生年月日を入力してください。");
			return forward;
		}
		if(birth_[0].length() < 4 || birth_[1].length() < 2 || birth_[2].length() < 2 ||
				!birth_[0].matches(patternNum) || !birth_[1].matches(patternNum)  || !birth_[2].matches(patternNum) ) {
			forward.setErrorMsg("生年月日は　2022-01-02のように桁数を合わせてください。");
		}
		if(tel_[0] == null || "".equals(tel_[0]) || tel_[1] == null || "".equals(tel_[1]) ||
				tel_[2] == null || "".equals(tel_[2])) {
			forward.setErrorMsg("電話番号を入力してください。");
			return forward;
		}
		if(!tel_[0].matches(patternNum) || !tel_[1].matches(patternNum) || !tel_[2].matches(patternNum)) {
			forward.setErrorMsg("電話番号は数字のみにしてください。");
			return forward;
		}
		if(gender == null || "".equals(gender)) {
			forward.setErrorMsg("性別を選択してください。");
			return forward;
		}
		if(department == null || "".equals(department)) {
			forward.setErrorMsg("診察科を	入力してください。");
			return forward;
		}
		if(hospital_name == null || "".equals(hospital_name)) {
			forward.setErrorMsg("病院名を	入力してください。");
			return forward;
		}
		int h_num = AdminDAO.getInstance().findByH_nameToH_num(hospital_name);
		if(h_num == 0) {
			forward.setErrorMsg("該当の病院は登録されていません。");
			return forward;
		}
		
		String birth = birth_[0]+"-"+birth_[1]+"-"+birth_[2];
		String tel = tel_[0]+"-"+tel_[1]+"-"+tel_[2];
		String name = frist_name+" "+last_name;
		String kana = frist_kana+" "+last_kana;
		DoctorBean doctor = new DoctorBean();
		
		XssFilter xssFilter = XssFilter.getInstance();
		doctor.setD_email(xssFilter.stripTagAll(email));
		doctor.setD_pw(xssFilter.stripTagAll(password));
		doctor.setD_name(xssFilter.stripTagAll(name));
		doctor.setD_kana(xssFilter.stripTagAll(kana));
		doctor.setD_birth(xssFilter.stripTagAll(birth));
		doctor.setD_tel(xssFilter.stripTagAll(tel));
		doctor.setD_gender(xssFilter.stripTagAll(gender));
		doctor.setD_department(xssFilter.stripTagAll(department));
		doctor.setD_h_num(h_num);
		
		session.setAttribute("doctorBean", doctor);
		request.setAttribute("hospital_name", hospital_name);
		
		forward.setPath("m01_03");
		return forward;
	}

}
