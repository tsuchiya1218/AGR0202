package action.pharmacy;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.DrugDAO;
import model.DrugBean;

public class U16_s2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		DrugDAO drugDAO = DrugDAO.getInstance();
		String drug_num_ = request.getParameter("drug_num");
		
		if(drug_num_ == null || "".equals(drug_num_)) {
			forward.setErrorMsg("薬番号がみつかりませんでした。");
			return forward;
		}
		
		int drug_num = 0;
		drug_num = Integer.parseInt(drug_num_);
		String root = "C:\\Users\\ksmzz\\git\\AGR0202\\src\\main\\webapp\\static\\img\\medicine\\";
		DrugBean drug = drugDAO.findByDrug_numToDrug(drug_num);
		String drug_img_name =  drug.getDrug_img_name();
		
		String img_name = root + drug_img_name;
		File imgFile = new File(img_name);
		if(imgFile.isFile()) {
			imgFile.delete();
			if(!drugDAO.updateDrugImgName(null, drug_num)) {
				forward.setErrorMsg("データベースエラー:写真は削除しましたが、\\nデータベースにある情報は変更できませんでした。");
				return forward;
			}
		}
		
		forward.setMsg("薬の写真を削除しました。");
		forward.setRedirectToAction(true);
		forward.setPath("u16_s1_01&drug_num="+drug_num);
		return forward;
	}

}
