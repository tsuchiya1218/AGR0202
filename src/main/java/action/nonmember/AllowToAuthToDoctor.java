package action.nonmember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.DoctorDAO;
import dao.MemberDAO;
import util.XssFilter;

public class AllowToAuthToDoctor implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		XssFilter xssFilter = XssFilter.getInstance();
		String email_ = request.getParameter("email");
		String email = xssFilter.stripTagAll(email_.replaceAll("\\s", ""));
		DoctorDAO doctorDAO = DoctorDAO.getInstance();
		
		if(doctorDAO.findByEmail(email)) {
			if(doctorDAO.isAuth(email)) {
				forward.setErrorMsg("既にメールアドレスの認証がされています。");
				return forward;
			}
			if(!doctorDAO.updateAuth(email)) {
				forward.setErrorMsg("メール認証のエラーが発生しました。\\nもう一度、認証URLにアクセスしてください。");
				return forward;
			}
		}
		
		session.invalidate();
		forward.setPath("u01_04");
		return forward;
	}

}
