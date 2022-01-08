package action.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.AdminDAO;
import model.DoctorBean;
import model.MemberBean;

public class M04_01 implements Action {

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
		
		List<MemberBean> memberList;
		if("name".equals(keyword_type)) {
			memberList = adminDAO.searchByNameToMember(keyword);
		}else if("kana".equals(keyword_type)) {
			memberList = adminDAO.searchByKanaToMember(keyword);
		}else {
			memberList = adminDAO.searchByEmailToMember(keyword);
		}
		while (memberList.remove(null)) {}
		
		int for_footer_css = memberList.size();
		
		request.setAttribute("for_footer_css", for_footer_css);
		request.setAttribute("keyword_type", keyword_type);
		request.setAttribute("keyword", keyword);
		request.setAttribute("memberList", memberList);
		forward.setPath("m04_01");
		return forward;
	}

}
