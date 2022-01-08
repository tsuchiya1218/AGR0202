package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.AdminDAO;
import model.HospitalBean;

public class M03_s1_01 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);
		ActionForward forward = new ActionForward();
		
		String h_num_ = request.getParameter("h_num");
		
		if("".equals(h_num_) || h_num_ == null) {
			forward.setErrorMsg("薬局番号がみつかりませんでした。");
			return forward;
		}
		int h_num = Integer.parseInt(h_num_);
		
		HospitalBean hospital = AdminDAO.getInstance().getHospitalBean(h_num);
		request.setAttribute("hospital", hospital);
		forward.setPath("m03_s1");
		return forward;
	}

}
