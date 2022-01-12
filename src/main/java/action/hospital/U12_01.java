package action.hospital;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.HospitalDAO;
import dao.MemberDAO;
import model.Electronic_prescriptionBean;
import model.HospitalBean;
import model.MemberBean;

public class U12_01 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		
		HospitalBean hospital = (HospitalBean) session.getAttribute("hospital");
		List<Electronic_prescriptionBean> epList = HospitalDAO.getInstance().getEpList(hospital.getH_num());
		List<MemberBean> memberList = new ArrayList<>();
		MemberDAO memberDAO = MemberDAO.getInstance();
		for(Electronic_prescriptionBean ep : epList) {
			memberList.add(memberDAO.getMemberListByM_numToUseHospital(ep.getEp_m_num()));
		}
		request.setAttribute("epList", epList);
		request.setAttribute("memberList", memberList);
		request.setAttribute("for_footer_css", epList.size());
		
		forward.setPath("u12");
		return forward;
	}

}
