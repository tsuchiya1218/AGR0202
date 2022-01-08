package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.AdminDAO;
import model.DoctorBean;

public class M01_s1_01 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		String d_num_ = request.getParameter("d_num");
		if(d_num_ == null || "".equals(d_num_)) {
			forward.setErrorMsg("医者番号がみつかりませんでした。");
			return forward;
		}
		int d_num = Integer.parseInt(d_num_);

		AdminDAO adminDAO = AdminDAO.getInstance();
		DoctorBean doctor = adminDAO.getDoctorBean(d_num);
		String h_name = adminDAO.findByH_numToH_name(doctor.getD_h_num());
		
		request.setAttribute("doctor", doctor);
		request.setAttribute("h_name", h_name);
		forward.setPath("m01_s1");
		return forward;
	}

}
