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

public class U14 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		XssFilter xssFilter = XssFilter.getInstance();

		String root = request.getSession().getServletContext().getRealPath("/");
		String path = root + "static\\img\\medicine";
		int size = 1024 * 1024 * 10; // 20mb

		// cos.jar라이브러리 클래스를 가지고 실제 파일을 업로드하는 과정
		try {
			// DefaultFileRenamePolicy 처리는 중복된 이름이 존재할 경우 처리할 때
			// request, 파일저장경로, 용량, 인코딩타입, 중복파일명에 대한 정책
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
			int drug_price = 0;
			
			
			if(drug_price_ != null && !"".equals(drug_price_)) {
				drug_price = Integer.parseInt(drug_price_);
			}
			String fileName = path+drug_img_name;
			File deleteImg = new File (fileName);
			DrugDAO drugDAO = DrugDAO.getInstance();
			if (drugDAO.isDuplicateMDrug_name(drug_name)) {
	    	    deleteImg.delete();
				forward.setErrorMsg("既に登録されている薬名です。");
				return forward;
			}
			if ("".equals(drug_name) || drug_name == null) {
				forward.setErrorMsg("薬名を入力してください。");
				deleteImg.delete();
				return forward;
			}
			if ("".equals(drug_type) || drug_type == null) {
				forward.setErrorMsg("種類を選択してください。");
				deleteImg.delete();
				return forward;
			}
			if ("".equals(drug_effect) || drug_effect == null) {
				forward.setErrorMsg("効果を入力してください。");
				deleteImg.delete();
				return forward;
			}
			if ("".equals(drug_guide) || drug_guide == null) {
				forward.setErrorMsg("服用方法を選択してください。");
				deleteImg.delete();
				return forward;
			}
			if ("".equals(drug_note) || drug_note == null) {
				forward.setErrorMsg("備考を入力してください。");
				deleteImg.delete();
				return forward;
			}
			if (drug_price == 0) {
				forward.setErrorMsg("薬剤の値段を入力してください。");
				deleteImg.delete();
				return forward;
			}
			if(drug_name.length() > 50) {
				forward.setErrorMsg("薬名は50文字以下までです。");
				deleteImg.delete();
				return forward;
			}
			if(drug_note.length() > 500) {
				forward.setErrorMsg("備考は500文字以下までです。");
				deleteImg.delete();
				return forward;
			}
			if(drug_effect.length() > 500) {
				forward.setErrorMsg("効果は500文字以下までです。");
				deleteImg.delete();
				return forward;
			}
			if(drug_price > 1000000000) {
				forward.setErrorMsg("値段は「1.000.000.000」までです。");
				deleteImg.delete();
				return forward;
			}
			

			DrugBean drug = new DrugBean();
			drug.setDrug_name(xssFilter.stripTagAll(drug_name));
			drug.setDrug_type(xssFilter.stripTagAll(drug_type));
			drug.setDrug_effect(xssFilter.stripTagAll(drug_effect.replaceAll("\r\n", "<br>")));
			drug.setDrug_guide(xssFilter.stripTagAll(drug_guide));
			drug.setDrug_note(xssFilter.stripTagAll(drug_note.replaceAll("\r\n", "<br>")));
			drug.setDrug_price(drug_price);
			drug.setDrug_img_name((drug_img_name != null ) ?xssFilter.stripTagAll(drug_img_name) : null);
			
			if(!drugDAO.createDrug(drug)) {
				deleteImg.delete();
				forward.setErrorMsg("薬情報の登録が失敗しました。");
				return forward;
			}
			
			forward.setRedirectToAction(true);
			forward.setPath("u15_01");
			forward.setMsg("薬情報の登録が完了しました。");
			return forward;
		} catch(NullPointerException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		forward.setErrorMsg("薬情報の登録が失敗しました。");
		return forward;
	}
}
