package action.pharmacy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import util.XssFilter;

public class U10_s1 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		
		int selected = Integer.parseInt(request.getParameter("selected"));

		if(selected == 0) {
			forward.setPath("u10_02_pharmacy");
			return forward;
		}
		
		
		request.setAttribute("selected", selected);
		forward.setPath("u10_s1_pharmacy");
		return forward;
	}

}
