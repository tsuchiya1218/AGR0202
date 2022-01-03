package action.pharmacy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.DrugDAO;
import model.DrugBean;

public class U16_01 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		DrugDAO drugDAO = DrugDAO.getInstance();
		String drug_num_ = request.getParameter("drug_num");
		System.out.println(drug_num_);
		int drug_num = Integer.parseInt(drug_num_);
		
		DrugBean drug = drugDAO.getDrugByDrug_num(drug_num);
		if(drug == null) {
			forward.setErrorMsg("ERROR:薬情報をみつかりませんでした。");
			return forward;
		}
		request.setAttribute("drug", drug);
		forward.setPath("u16_01");
		return forward;
	}

}
