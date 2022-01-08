package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.AdminDAO;
import model.PharmacyBean;

public class M02_s1_01 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);
		ActionForward forward = new ActionForward();
		
		String p_num_ = request.getParameter("p_num");
		
		if("".equals(p_num_) || p_num_ == null) {
			forward.setErrorMsg("薬局番号がみつかりませんでした。");
			return forward;
		}
		int p_num = Integer.parseInt(p_num_);
		
		PharmacyBean pharmacy = AdminDAO.getInstance().getPharmacyBean(p_num);
		request.setAttribute("pharmacy", pharmacy);
		forward.setPath("m02_s1");
		return forward;
	}

}
