package action.pharmacy;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import action.ActionForward;
import dao.DrugDAO;
import model.DrugBean;
import util.XssFilter;

public class U16_s1_02 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		XssFilter xssFilter = XssFilter.getInstance();

		String path = "C:\\Users\\20jy0211\\git\\AGR0202\\src\\main\\webapp\\static\\img\\medicine";
		int size = 1024 * 1024 * 10; // 20mb

		try {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}

			MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
			
			String drug_name = multi.getParameter("drug_name");
			String drug_type = multi.getParameter("drug_type");
			String drug_effect = multi.getParameter("drug_effect");
			String drug_guide = multi.getParameter("drug_guide");
			String drug_note = multi.getParameter("drug_note");
			String drug_img_name = multi.getFilesystemName("drug_img");
			String drug_price_ = multi.getParameter("drug_price");
			String drug_num_ = multi.getParameter("drug_num");
			int drug_num = 0;
			
			if(drug_num_ != null && !"".equals(drug_num_)) {
				drug_num = Integer.parseInt(drug_num_);
			}
			String fileName = path+drug_img_name;
			DrugDAO drugDAO = DrugDAO.getInstance();
			DrugBean drug = drugDAO.findByDrug_numToDrug(drug_num);
			
			//もし写真を変更する場合。
			if (drug_img_name != null && !"".equals(drug_img_name)) {
				if(drug.getDrug_img_name() != null && !"".equals(drug.getDrug_img_name())) {
					String original_img_name = path + drug.getDrug_img_name();
					File original_img = new File(original_img_name);
					original_img.delete();
				}
				drug.setDrug_img_name(drug_img_name);
			}
			int drug_price = 0;
			
			File img = new File (fileName);
			
			if(drug_price_ != null && !"".equals(drug_price_)) {
				drug_price = Integer.parseInt(drug_price_);
			}
			
			
			int check_drug_num = drugDAO.findByDrug_numToDrug_name(drug_name);
			if(check_drug_num != 0 && drug_num != 0) {
				//同じ薬名が違う番号の場合、重複処理, 同じ名前をもう一度入れる時重複処理をしないように。
				if(check_drug_num != drug_num) {
					if (drugDAO.isDuplicateMDrug_name(drug_name)) {
						img.delete();
						forward.setErrorMsg("既に登録されている薬名です。");
						return forward;
					}
				}
			}
			if ("".equals(drug_name) || drug_name == null) {
				forward.setErrorMsg("薬名を入力してください。");
				img.delete();
				return forward;
			}
			if ("".equals(drug_type) || drug_type == null) {
				forward.setErrorMsg("種類を選択してください。");
				img.delete();
				return forward;
			}
			if ("".equals(drug_effect) || drug_effect == null) {
				forward.setErrorMsg("効果を入力してください。");
				img.delete();
				return forward;
			}
			if ("".equals(drug_guide) || drug_guide == null) {
				forward.setErrorMsg("服用方法を選択してください。");
				img.delete();
				return forward;
			}
			if ("".equals(drug_note) || drug_note == null) {
				forward.setErrorMsg("備考を入力してください。");
				img.delete();
				return forward;
			}
			if (drug_price == 0) {
				forward.setErrorMsg("薬剤の値段を入力してください。");
				img.delete();
				return forward;
			}
			if(drug_name.length() > 50) {
				forward.setErrorMsg("薬名は500文字以下までです。");
				img.delete();
				return forward;
			}
			if(drug_note.length() > 500) {
				forward.setErrorMsg("備考は500文字以下までです。");
				img.delete();
				return forward;
			}
			if(drug_effect.length() > 500) {
				forward.setErrorMsg("効果は500文字以下までです。");
				img.delete();
				return forward;
			}
			if(drug_price > 1000000000) {
				forward.setErrorMsg("値段は「1.000.000.000」までです。");
				img.delete();
				return forward;
			}
			

			drug.setDrug_name(xssFilter.stripTagAll(drug_name));
			drug.setDrug_type(xssFilter.stripTagAll(drug_type));
			drug.setDrug_effect(xssFilter.stripTagAll(drug_effect.replaceAll("\r\n", "<br>")));
			drug.setDrug_guide(xssFilter.stripTagAll(drug_guide));
			drug.setDrug_note(xssFilter.stripTagAll(drug_note.replaceAll("\r\n", "<br>")));
			drug.setDrug_price(drug_price);
			
			
			if(!drugDAO.updateDrug(drug)) {
				forward.setErrorMsg("薬情報の変更が失敗しました。");
				return forward;
			}
			
			forward.setRedirectToAction(true);
			forward.setPath("u15_01");
			forward.setMsg("薬情報の変更が完了しました。");
			return forward;
		} catch(NullPointerException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		forward.setErrorMsg("薬情報の変更が失敗しました。");
		return forward;
	}

}
