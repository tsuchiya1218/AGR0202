package action.pharmacy;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.DrugDAO;

public class U15_s1 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		DrugDAO drugDAO = DrugDAO.getInstance();
		String drug_num_ = request.getParameter("drug_num");
		int drug_num = 0;
		
		if(drug_num_ != null && !"".equals(drug_num_)) {
			drug_num = Integer.parseInt(drug_num_);
		}
		String path = "C:\\Users\\20jy0211\\git\\AGR0202\\src\\main\\webapp\\static\\img\\medicine\\";
		String drug_img_name =  drugDAO.findByDrug_img_nameToDrug_num(drug_num);
		if(drug_img_name != null) {
			String img_name = path + drug_img_name;
			File imgFile = new File(img_name);
			if(imgFile.isFile()) {
				imgFile.delete();
			}
		}
		
		drugDAO.deleteDrug(drug_num);
		
		forward.setMsg("薬情報の削除が完了しました。");
		forward.setRedirectToAction(true);
		forward.setPath("u15_01");
		return forward;
	}

}
