package action.doctor_pharmacy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.ChildDAO;
import dao.Electronic_prescriptionDAO;
import dao.MemberDAO;
import dao.QuestionnaireDAO;
import model.ChildBean;
import model.Electronic_prescriptionBean;
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
		
		String m_qr_num = request.getParameter("m_qr_num");
		
		if("".equals(m_qr_num) || m_qr_num == null) {
			forward.setErrorMsg("ユーザーのQR番号を入力してください。");
			return forward;
		}
		m_qr_num = xssFilter.stripTagAll(m_qr_num);
		
		ChildDAO childDAO = ChildDAO.getInstance();
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberBean member = memberDAO.findByQr_numToMember(m_qr_num);
		if(member == null) {
			forward.setErrorMsg("ユーザーがみつかりません。");
			return forward;
		}
		QuestionnaireBean questionnaire = QuestionnaireDAO.getInstance().getQuestionnaire(member.getM_q_num());
		if(questionnaire == null) {
			forward.setErrorMsg("ユーザーの問診票がみつかりません。");
			return forward;
		}
		member.setAge(memberDAO.countBirth(member.getM_birth()));
		List<ChildBean> child = childDAO.getChildList(member.getM_num());
		if(!child.isEmpty()) {
			for(ChildBean c : child) {
				c.setAge(childDAO.countBirth(c.getC_birth()));
			}
		}
		
		Electronic_prescriptionDAO epDAO = Electronic_prescriptionDAO.getInstance();
		
		request.setAttribute("member", member);
		request.setAttribute("questionnaire", questionnaire);
		request.setAttribute("child", child);
		List<Electronic_prescriptionBean> epList = epDAO.findByM_numAndC_numToEp_num(member.getM_num() , 0);
		request.setAttribute("epList", epList);
		
		if(session.getAttribute("doctor") != null) {
			forward.setPath("u10_02_doctor");
		}else {
			forward.setPath("u10_02_pharmacy");
		}
		return forward;
	}

}
