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

public class U07_03 implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		ChildBean childBean = (ChildBean) session.getAttribute("childBean");
		ChildDAO childDAO = ChildDAO.getInstance();
		
		if(!childDAO.addChild(childBean)) {
			forward.setErrorMsg("子供情報登録に失敗しました。問い合わせしてください。");
			return forward;
		}
		MemberBean member = (MemberBean) session.getAttribute("member");
		List<ChildBean> child = childDAO.getChild(member.getM_num());
		
		int countChild = 0;
		if(!child.isEmpty()) {
			for(ChildBean c : child) {
				c.setAge(childDAO.countBirth(c.getC_birth()));
				countChild++;
			}
			session.setAttribute("child", child);
		}
		
		member.setM_children(String.valueOf(countChild));
		session.setAttribute("member", member);
		
		forward.setMsg("子ども情報が登録されました。");
		forward.setPath("u07_01");
		return forward;
	}

}
