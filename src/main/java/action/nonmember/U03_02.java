package action.nonmember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import util.XssFilter;

public class U03_02 implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		XssFilter xssFilter = XssFilter.getInstance();
		String m_email = xssFilter.stripTagAll(request.getParameter("email").replaceAll("\\s", ""));
		
		forward.setPath("u03_03");
		request.setAttribute("email", m_email);
		return forward;
	}
}
