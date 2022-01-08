package action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.AdminDAO;
import model.PharmacyBean;

public class M02_s3 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		
		String keyword_type = request.getParameter("keyword_type");
		String keyword = request.getParameter("keyword");
		
		if(keyword_type == null || "".equals(keyword_type)) {
			forward.setErrorMsg("検索する方法を選択してください。");
			return forward;
		}
		if(keyword == null || "".equals(keyword)) {
			forward.setErrorMsg("検索する内容を入力してください。");
			return forward;
		}
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		List<PharmacyBean> pharmacyList;
		if("name".equals(keyword_type)) {
			pharmacyList = adminDAO.searchByNameToPharmacy(keyword);
		}else if("tel".equals(keyword_type)) {
			pharmacyList = adminDAO.searchByTelToPharmacy(keyword);
		}else if("address".equals(keyword_type)) {
			pharmacyList = adminDAO.searchByAddressToPharmacy(keyword);
		}else {
			pharmacyList = adminDAO.searchByEmailToPharmacy(keyword);
		}
		while (pharmacyList.remove(null)) {}
		
		int for_footer_css = pharmacyList.size();
		
		request.setAttribute("for_footer_css", for_footer_css);
		request.setAttribute("keyword_type", keyword_type);
		request.setAttribute("keyword", keyword);
		request.setAttribute("pharmacyList", pharmacyList);
		forward.setPath("m02_01");
		return forward;
	}

}
