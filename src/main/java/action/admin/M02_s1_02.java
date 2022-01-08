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
import model.PharmacyBean;
import util.XssFilter;

public class M02_s1_02 implements Action {

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

		String p_num_ = request.getParameter("p_num");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
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
			forward.setErrorMsg("薬局名をを入力してください。");
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
		
		if(p_num_ == null || "".equals(p_num_)) {
			forward.setErrorMsg("該当の薬局番号がみつかりませんでした。");
			return forward;
		}
		int p_num = Integer.parseInt(p_num_);

		String tel = tel_[0] + "-" + tel_[1] + "-" + tel_[2];
		PharmacyBean pharmacy = new PharmacyBean();
		XssFilter xssFilter = XssFilter.getInstance();
		pharmacy.setP_num(p_num);
		pharmacy.setP_name(xssFilter.stripTagAll(name));
		pharmacy.setP_address(xssFilter.stripTagAll(address));
		pharmacy.setP_tel(xssFilter.stripTagAll(tel));

		if (isModifyPassword && isModifyEmail) {
			pharmacy.setP_email(xssFilter.stripTagAll(email));
			pharmacy.setP_pw(xssFilter.stripTagAll(password));
			if (!adminDAO.updatePharmacyWithEmailAndPassword(pharmacy)) {
				forward.setErrorMsg("変更が失敗しました。");
				return forward;
			}
		} else {
			if (!adminDAO.updatePharmacy(pharmacy)) {
				forward.setErrorMsg("変更が失敗しました。");
				return forward;
			}
		}

		forward.setMsg("変更が完了しました。");
		forward.setPath("m02_01");
		return forward;
	}

}
