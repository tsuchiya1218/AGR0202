package action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.AdminDAO;
import model.HospitalBean;

public class M03_s3 implements Action {

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
		
		List<HospitalBean> hospitalList;
		if("name".equals(keyword_type)) {
			hospitalList = adminDAO.searchByNameToHospital(keyword);
		}else if("tel".equals(keyword_type)) {
			hospitalList = adminDAO.searchByTelToHospital(keyword);
		}else if("address".equals(keyword_type)) {
			hospitalList = adminDAO.searchByAddressToHospital(keyword);
		}else {
			hospitalList = adminDAO.searchByEmailToHospital(keyword);
		}
		while (hospitalList.remove(null)) {}
		
		int for_footer_css = hospitalList.size();
		
		request.setAttribute("for_footer_css", for_footer_css);
		request.setAttribute("keyword_type", keyword_type);
		request.setAttribute("keyword", keyword);
		request.setAttribute("hospitalList", hospitalList);
		forward.setPath("m03_01");
		return forward;
	}

}
