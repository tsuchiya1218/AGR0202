package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.AdminDAO;

public class M03_s2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);
		ActionForward forward = new ActionForward();
		AdminDAO adminDAO = AdminDAO.getInstance();
		String h_num_ = request.getParameter("h_num");
		
		if("".equals(h_num_) || h_num_ == null) {
			forward.setErrorMsg("病院番号がみつかりませんでした。");
			return forward;
		}
		int h_num = Integer.parseInt(h_num_);
		
		if(!adminDAO.deleteHospital(h_num)) {
			forward.setErrorMsg("病院削除が失敗しました。");
			return forward;
		}
		
		forward.setMsg("病院削除が完了しました。");
		forward.setPath("m03_01");
		return forward;
	}

}
