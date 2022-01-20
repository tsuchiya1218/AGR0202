package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.AdminDAO;

public class M02_s2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);
		ActionForward forward = new ActionForward();
		AdminDAO adminDAO = AdminDAO.getInstance();
		String p_num_ = request.getParameter("p_num");
		
		if("".equals(p_num_) || p_num_ == null) {
			forward.setErrorMsg("薬局番号がみつかりませんでした。");
			return forward;
		}
		int p_num = Integer.parseInt(p_num_);
		
		if(!adminDAO.deletePharmacy(p_num)) {
			forward.setErrorMsg("薬局削除が失敗しました。");
			return forward;
		}
		
		forward.setMsg("薬局削除が完了しました。");
		forward.setPath("m02_01");
		return forward;
	}

}
