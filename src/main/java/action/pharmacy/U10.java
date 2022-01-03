package action.pharmacy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.ChildDAO;
import dao.PharmacyDAO;
import model.ChildBean;
import model.MemberBean;
import model.QuestionnaireBean;
import util.XssFilter;

public class U10 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		XssFilter xssFilter = XssFilter.getInstance();
		
		String m_qr_num = xssFilter.stripTagAll(request.getParameter("m_qr_num"));
		
		if("".equals(m_qr_num) || m_qr_num == null) {
			forward.setErrorMsg("ユーザーのQR番号を入力してください。");
			return forward;
		}
		
		PharmacyDAO pharmacyDAO = PharmacyDAO.getInstance();
		MemberBean member = pharmacyDAO.findByQr_numToMember(m_qr_num);
		if(member == null) {
			forward.setErrorMsg("ユーザーがみつかりません。");
			return forward;
		}
		QuestionnaireBean questionnaire = pharmacyDAO.findByM_numToQuestionnaire(member.getM_q_num());
		if(questionnaire == null) {
			forward.setErrorMsg("ユーザーの問診票がみつかりません。");
			return forward;
		}
		List<ChildBean> child = pharmacyDAO.getChild(member.getM_num());
		
		session.setAttribute("member", member);
		session.setAttribute("questionnaire", questionnaire);
		session.setAttribute("child", child);
		forward.setPath("u10_02_pharmacy");
		return forward;
	}

}
