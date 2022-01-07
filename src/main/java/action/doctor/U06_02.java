package action.doctor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.HospitalDAO;
import model.DoctorBean;

public class U06_02 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		DoctorBean doctor = (DoctorBean) session.getAttribute("doctor");
		String h_name = HospitalDAO.getInstance().findByD_h_numToH_name(doctor.getD_h_num());
		request.setAttribute("h_name", h_name);
		
		forward.setPath("u06_02_doctor");
		return forward;
	}

}
