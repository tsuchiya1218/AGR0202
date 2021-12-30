package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;

public class U07_s1 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		ActionForward forward = new ActionForward();
		
		request.setAttribute("c_num", request.getParameter("c_num"));
		forward.setPath("u07_s1");
		return forward;
	}

}
