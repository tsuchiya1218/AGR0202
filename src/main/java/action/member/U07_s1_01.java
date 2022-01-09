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
		String c_num_ = request.getParameter("c_num");
		
		if(c_num_ == null || "".equals(c_num_)) {
			forward.setErrorMsg("子ども番号がみつかりませんでした。");
			return forward;
		}
		
		int c_num = Integer.parseInt(c_num_);
		ChildBean child = ChildDAO.getInstance().getChildBeanByC_num(c_num);
		
		
		request.setAttribute("child", child);
		forward.setPath("u07_s1");
		return forward;
	}

}
