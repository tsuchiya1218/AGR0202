package action.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.ChildDAO;
import model.ChildBean;
import model.MemberBean;

public class U07_01 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		
		MemberBean member = (MemberBean) session.getAttribute("member");
		
		ChildDAO childDAO = ChildDAO.getInstance();
		List<ChildBean> children = childDAO.getChildList(member.getM_num());
		int countChild = 0;
		
		if(!children.isEmpty()) {
			for(ChildBean c : children) {
				c.setAge(childDAO.countBirth(c.getC_birth()));
				countChild++;
			}
			request.setAttribute("children", children);
		}
		member.setChildren_count(countChild);
		
		session.setAttribute("member", member);
		forward.setPath("u07_01");
		return forward;
	}

}
