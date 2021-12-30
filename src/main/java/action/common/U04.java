package action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;

public class U04 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("index");
		HttpSession session = request.getSession();
        session.invalidate();
		return forward;
	}

}
