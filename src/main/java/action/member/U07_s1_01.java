package action.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.ChildDAO;
import model.ChildBean;

public class U07_s1_01 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		HttpSession session = request.getSession(true);
		ActionForward forward = new ActionForward();
		
		@SuppressWarnings("unchecked")
		List<ChildBean> list = (List<ChildBean>) session.getAttribute("child");
		
		int index = 0;
		for(ChildBean c : list) {
			if(c.getC_num() == Integer.parseInt(request.getParameter("c_num"))) {
				break;
			}
			index++;
		}
		
		request.setAttribute("index", index);
		forward.setPath("u07_s1");
		return forward;
	}

}
