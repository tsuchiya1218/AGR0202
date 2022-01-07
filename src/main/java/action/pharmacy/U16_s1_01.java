package action.pharmacy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.DrugDAO;
import model.DrugBean;

public class U16_s1_01 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		String drug_num_ = request.getParameter("drug_num");
		if(drug_num_ == null || "".equals(drug_num_)) {
			forward.setErrorMsg("薬番号がみつかりませんでした。");
			return forward;
		}
		int drug_num = Integer.parseInt(drug_num_);
		
		DrugDAO drugDAO = DrugDAO.getInstance();
		DrugBean drug = drugDAO.findByDrug_numToDrug(drug_num);
		if(drug == null) {
			forward.setErrorMsg("薬情報がみつかりませんでした。");
			return forward;
		}
		request.setAttribute("drug", drug);
		forward.setPath("u16_s1");
		return forward;
	}

}
