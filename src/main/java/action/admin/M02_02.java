package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.AdminDAO;
import model.PharmacyBean;
import util.Gmail;

public class M02_02 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);
		ActionForward forward = new ActionForward();
		
		PharmacyBean pharmacy = (PharmacyBean) session.getAttribute("pharmacyBean");
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		if(!adminDAO.addPharmacy(pharmacy)) {
			forward.setErrorMsg("薬局登録が失敗しました。");
			return forward;
		}
		
		session.removeAttribute("pharmacyBean");
		
		forward.setMsg("薬局登録が完了しました。");
		forward.setPath("m02_01");
		return forward;
	}

}
