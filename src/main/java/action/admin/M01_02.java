package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.AdminDAO;
import model.DoctorBean;
import util.Gmail;

public class M01_02 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);
		ActionForward forward = new ActionForward();
		
		DoctorBean doctor = (DoctorBean) session.getAttribute("doctorBean");
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		if(!adminDAO.addDoctor(doctor)) {
			forward.setErrorMsg("医者登録が失敗しました。");
			return forward;
		}
		Gmail gmail = Gmail.getInstance();
		
		if(!gmail.sendSingUpMailToDoctor(doctor.getD_email(), doctor.getD_name())) {
			forward.setMsg("登録完了メールの送信が失敗しました。");
			return forward;
		}
		
		session.removeAttribute("doctorBean");
		
		forward.setMsg("医者登録が完了しました。");
		forward.setPath("m01_01");
		return forward;
	}

}
