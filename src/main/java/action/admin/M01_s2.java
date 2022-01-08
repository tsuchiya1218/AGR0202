package action.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.AdminDAO;
import model.DoctorBean;

public class M01_s2 implements Action {

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
		
		List<DoctorBean> doctorList;
		if("name".equals(keyword_type)) {
			doctorList = adminDAO.searchByNameToDoctor(keyword);
		}else if("kana".equals(keyword_type)) {
			doctorList = adminDAO.searchByKanaToDoctor(keyword);
		}else {
			doctorList = adminDAO.searchByEmailToDoctor(keyword);
		}
		List<String> h_name_list = new ArrayList<>();
		for(DoctorBean doctor : doctorList) {
			h_name_list.add(adminDAO.findByH_numToH_name(doctor.getD_h_num()));
		}
		while (doctorList.remove(null)) {}
		
		int for_footer_css = doctorList.size();
		
		request.setAttribute("for_footer_css", for_footer_css);
		request.setAttribute("h_name_list", h_name_list);
		request.setAttribute("keyword_type", keyword_type);
		request.setAttribute("keyword", keyword);
		request.setAttribute("doctorList", doctorList);
		forward.setPath("m01_01");
		return forward;
	}

}
