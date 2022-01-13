package action.doctor_pharmacy;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.ChildDAO;
import dao.DoctorDAO;
import dao.DrugDAO;
import dao.Electronic_prescriptionDAO;
import dao.HospitalDAO;
import dao.MemberDAO;
import dao.Prescribe_medicineDAO;
import model.ChildBean;
import model.DoctorBean;
import model.DrugBean;
import model.Electronic_prescriptionBean;
import model.HospitalBean;
import model.MemberBean;
import model.Prescribe_medicineBean;

public class U13_01 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		String ep_num_ = request.getParameter("ep_num");
		if(ep_num_ == null || "".equals(ep_num_)) {
			forward.setErrorMsg("電子処方箋番号がみつかりませんでした。");
			return forward;
		}
		int ep_num = Integer.parseInt(ep_num_);
		Electronic_prescriptionBean ep = Electronic_prescriptionDAO.getInstance().findByEp_num(ep_num);
		List<Prescribe_medicineBean> pmList = Prescribe_medicineDAO.getInstance().getPmList(ep.getEp_num());
		List<DrugBean> drugList = new ArrayList<>();
		DrugDAO drugDAO = DrugDAO.getInstance();
		
		//同じindexに値が入る
		for(Prescribe_medicineBean pm : pmList) {
			drugList.add(drugDAO.findByDrug_numToDrug(pm.getPm_drug_num()));
		}
		
		if(ep.getEp_c_num() == 0) {
			MemberDAO memberDAO = MemberDAO.getInstance();
			MemberBean member = memberDAO.getMemberBeanByM_num(ep.getEp_m_num());
			member.setAge(memberDAO.countBirth(member.getM_birth()));
			request.setAttribute("member", member);
		}else {
			ChildDAO childDAO = ChildDAO.getInstance();
			ChildBean child = childDAO.getChildBeanByC_num(ep.getEp_c_num());
			child.setAge(childDAO.countBirth(child.getC_birth()));
			request.setAttribute("child", child);
		}
		DoctorBean doctorBean = DoctorDAO.getInstance().findByD_numToDoctor(ep.getEp_d_num());
		HospitalBean hospital = HospitalDAO.getInstance().findByH_numToDoctor_hospital_num(doctorBean.getD_h_num());
		request.setAttribute("hospital", hospital);
		request.setAttribute("doctorBean", doctorBean);
		request.setAttribute("drugList", drugList);
		request.setAttribute("pmList", pmList);
		request.setAttribute("ep", ep);
		if(session.getAttribute("doctor") != null) {
			forward.setPath("u13_01_doctor");
			return forward;
		}else if(session.getAttribute("pharmacy") != null) {
			forward.setPath("u13_01_pharmacy");
			return forward;
		}
		return null;
	}

}
