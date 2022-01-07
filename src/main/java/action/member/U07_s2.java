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

public class U07_s2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		
		ChildDAO childDAO = ChildDAO.getInstance();
		if(!childDAO.deleteChild(c_num)) {
			forward.setErrorMsg("子供の情報削除ができませんでした。");
			return forward;
		}
		childDAO.sortC_Num();
		MemberBean member = (MemberBean) session.getAttribute("member");
		
		int countChild = 0;
		List<ChildBean> child = childDAO.getChildList(member.getM_num());
		if(!child.isEmpty()) {
			for(ChildBean c : child) {
				c.setAge(childDAO.countBirth(c.getC_birth()));
				countChild++;
			}
			session.setAttribute("child", child);
		}
		member.setChildren_count(countChild);
		session.setAttribute("member", member);
		forward.setMsg("子供の情報が削除されました。");
		forward.setPath("u07_01");
		return forward;
	}

}
