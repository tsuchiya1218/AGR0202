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

public class M02_01 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);
		session.removeAttribute("pharmacyBean");
		ActionForward forward = new ActionForward();
		
		String patternNum = "^[0-9]*$";
		String patternEmail = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
		String patternPw = "^[A-Za-z0-9]{8,64}$";
		
		String email = request.getParameter("email").replaceAll("\\s", "");
		String password = request.getParameter("password").replaceAll("\\s", "");
		String name = request.getParameter("name");
		String[] tel_ = request.getParameterValues("tel");
		String address = request.getParameter("address");
		
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
		if(name == null || "".equals(name)) {
			forward.setErrorMsg("薬局名を入力してください。");
			return forward;
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
		if(address == null || "".equals(address)) {
			forward.setErrorMsg("住所を入力してください。");
			return forward;
		}
		
		String tel = tel_[0]+"-"+tel_[1]+"-"+tel_[2];
		PharmacyBean pharmacy = new PharmacyBean();
		
		XssFilter xssFilter = XssFilter.getInstance();
		pharmacy.setP_email(xssFilter.stripTagAll(email));
		pharmacy.setP_pw(xssFilter.stripTagAll(password));
		pharmacy.setP_name(xssFilter.stripTagAll(name));
		pharmacy.setP_address(xssFilter.stripTagAll(address));
		pharmacy.setP_tel(xssFilter.stripTagAll(tel));
		
		session.setAttribute("pharmacyBean", pharmacy);
		
		forward.setPath("m02_03");
		return forward;
	}

}
