package action.hospital;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.Electronic_prescriptionDAO;

public class U12_02 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		
		String ep_num_ = request.getParameter("ep_num");
		
		if(ep_num_ == null || "".equals(ep_num_)) {
			forward.setErrorMsg("電子処方箋番号がみつかりませんでした。");
			return forward;
		}
		int ep_num = Integer.parseInt(ep_num_);
		if(!Electronic_prescriptionDAO.getInstance().updateAuth(ep_num)) {
			forward.setErrorMsg("データーベースエラー:承認ができませんでした。");
			return forward;
		}
		forward.setMsg("承認しました。");
		forward.setPath("u12_01");
		forward.setRedirectToAction(true);
		return forward;
	}

}
