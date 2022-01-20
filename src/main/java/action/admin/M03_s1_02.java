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
import model.HospitalBean;
import util.XssFilter;

public class M03_s1_02 implements Action {

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

		String h_num_ = request.getParameter("h_num");
		String email = request.getParameter("email").replaceAll("\\s", "");
		String password = request.getParameter("password").replaceAll("\\s", "");
		String name = request.getParameter("name"); 
		String[] tel_ = request.getParameterValues("tel");
		String address = request.getParameter("address");
		
		AdminDAO adminDAO = AdminDAO.getInstance();
		boolean isModifyEmail = false;
		boolean isModifyPassword = false;

		if (email != null && !"".equals(email)) {
			if (!email.matches(patternEmail)) {
				forward.setErrorMsg("正しいメールアドレスを入力してください。");
				return forward;
			}
			isModifyEmail = true;
		}

		if(AdminDAO.getInstance().isDuplicateEmail(email) || PharmacyDAO.getInstance().isDuplicateEmail(email) ||
				MemberDAO.getInstance().isDuplicateEmail(email) || DoctorDAO.getInstance().isDuplicateEmail(email) ||
				HospitalDAO.getInstance().isDuplicateEmail(email)) {
			forward.setErrorMsg("既に登録されているメールアドレスです。");
			return forward;
		}
		if (password != null && !"".equals(password)) {
			if (!password.matches(patternPw)) {
				forward.setErrorMsg("パスワードは英数字のみ８桁以上にしてください。");
				return forward;
			}
			isModifyPassword = true;
		}

		if (name == null || "".equals(name)) {
			forward.setErrorMsg("病院名をを入力してください。");
			return forward;
		}
		if (tel_[0] == null || "".equals(tel_[0]) || tel_[1] == null || "".equals(tel_[1]) || tel_[2] == null
				|| "".equals(tel_[2])) {
			forward.setErrorMsg("電話番号を入力してください。");
			return forward;
		}
		if (!tel_[0].matches(patternNum) || !tel_[1].matches(patternNum) || !tel_[2].matches(patternNum)) {
			forward.setErrorMsg("電話番号は数字のみにしてください。");
			return forward;
		}
		if (address == null || "".equals(address)) {
			forward.setErrorMsg("住所を入力してください。");
			return forward;
		}
		
		if(h_num_ == null || "".equals(h_num_)) {
			forward.setErrorMsg("該当の病院番号がみつかりませんでした。");
			return forward;
		}
		int h_num = Integer.parseInt(h_num_);

		String tel = tel_[0] + "-" + tel_[1] + "-" + tel_[2];
		HospitalBean hospital = new HospitalBean();
		XssFilter xssFilter = XssFilter.getInstance();
		hospital.setH_num(h_num);
		hospital.setH_name(xssFilter.stripTagAll(name));
		hospital.setH_address(xssFilter.stripTagAll(address));
		hospital.setH_tel(xssFilter.stripTagAll(tel));

		if (isModifyPassword && isModifyEmail) {
			hospital.setH_email(xssFilter.stripTagAll(email));
			hospital.setH_pw(xssFilter.stripTagAll(password));
			if (!adminDAO.updateHospitalWithEmailAndPassword(hospital)) {
				forward.setErrorMsg("変更が失敗しました。");
				return forward;
			}
		} else {
			if (!adminDAO.updateHospital(hospital)) {
				forward.setErrorMsg("変更が失敗しました。");
				return forward;
			}
		}

		forward.setMsg("変更が完了しました。");
		forward.setPath("m03_01");
		return forward;
	}

}
