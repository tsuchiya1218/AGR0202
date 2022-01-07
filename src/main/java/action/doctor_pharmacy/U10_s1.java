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

public class U10_s1 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		
		String selected_ = request.getParameter("selected");
		if("".equals(selected_) || selected_ == null) {
			forward.setErrorMsg("データベースのエラー: 子ども番号がみつかりませんでした。");
			return forward;
		}
		
		String m_num_ = request.getParameter("m_num");
		if("".equals(m_num_) || m_num_ == null) {
			forward.setErrorMsg("データベースのエラー: 会員情報がみつかりませんでした。");
			return forward;
		}
		
		int selected = Integer.parseInt(selected_);
		int m_num = Integer.parseInt(m_num_);
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberBean member = memberDAO.getMemberBeanByM_num(m_num);
		member.setAge(memberDAO.countBirth(member.getM_birth()));
		ChildDAO childDAO = ChildDAO.getInstance();
		List<ChildBean> child = childDAO.getChildList(member.getM_num());
		if(!child.isEmpty()) {
			for(ChildBean c : child) {
				c.setAge(childDAO.countBirth(c.getC_birth()));
			}
			
		}
		Electronic_prescriptionDAO epDAO = Electronic_prescriptionDAO.getInstance();
		QuestionnaireBean questionnaire = QuestionnaireDAO.getInstance().getQuestionnaire(member.getM_num());
		List<Electronic_prescriptionBean> epList;
		if(selected != 0) {
			epList = epDAO.findByM_numAndC_numToEp_num(member.getM_num(),child.get(selected-1).getC_num());
		}else {
			epList = epDAO.findByM_numAndC_numToEp_num(member.getM_num(), 0);
		}
		request.setAttribute("epList", epList);
		request.setAttribute("child", child);
		request.setAttribute("member", member);
		request.setAttribute("questionnaire", questionnaire);
		if (session.getAttribute("pharmacy") != null) {
			//本人を選択した場合
			if (selected == 0) {
				forward.setPath("u10_02_pharmacy");
				return forward;
			}
			//子どもを選択した場合
			request.setAttribute("selected", selected);
			forward.setPath("u10_s1_pharmacy");
		} else {
			if (selected == 0) {
				forward.setPath("u10_02_doctor");
				return forward;
			}
			request.setAttribute("selected", selected);
			forward.setPath("u10_s1_doctor");
		}
		return forward;
	}

}
