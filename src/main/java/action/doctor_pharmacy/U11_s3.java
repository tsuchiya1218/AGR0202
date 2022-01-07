package action.doctor_pharmacy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.DrugDAO;
import model.DrugBean;
import util.XssFilter;

public class U11_s3 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		String medicine_name;
		if(request.getParameter("medicine_name") == null || "".equals(request.getParameter("medicine_name"))) {
			forward.setErrorMsg("薬名がみつかりませんでした。");
			return forward;
		}
		medicine_name = XssFilter.getInstance().stripTagAll(request.getParameter("medicine_name"));
		DrugBean drug = DrugDAO.getInstance().findByDrug_nameToDrug(medicine_name);
		
		request.setAttribute("drug", drug);
		forward.setPath("u11_s3");
		return forward;
	}

}
