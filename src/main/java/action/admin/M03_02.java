package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.AdminDAO;
import model.HospitalBean;

public class M03_02 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);
		ActionForward forward = new ActionForward();
		
		HospitalBean hospital = (HospitalBean) session.getAttribute("hospitalBean");
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		if(!adminDAO.addHospital(hospital)) {
			forward.setErrorMsg("病院登録が失敗しました。");
			return forward;
		}
		
		session.removeAttribute("hospitalBean");
		
		forward.setMsg("病院登録が完了しました。");
		forward.setPath("m03_01");
		return forward;
	}

}
