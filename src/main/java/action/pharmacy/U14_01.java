package action.pharmacy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.DrugDAO;
import model.DrugBean;

public class U14_01 implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);
		ActionForward forward = new ActionForward();
		DrugDAO drugDAO = DrugDAO.getInstance();
		DrugBean drug = (DrugBean) session.getAttribute("drugBean");
		
		if (!drugDAO.createDrug(drug)) {
			forward.setErrorMsg("薬情報の登録が失敗しました。");
			return forward;
		}
		session.removeAttribute("drugBean");
		forward.setRedirectToAction(true);
		forward.setPath("u15_01");
		forward.setMsg("薬情報の登録が完了しました。");
		return forward;
	}
}
