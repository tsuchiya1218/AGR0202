package action.pharmacy;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.ChildDAO;
import dao.DoctorDAO;
import dao.DrugDAO;
import dao.Drug_informationDAO;
import dao.Electronic_prescriptionDAO;
import dao.HospitalDAO;
import dao.MemberDAO;
import dao.Prescribe_medicineDAO;
import model.ChildBean;
import model.DoctorBean;
import model.DrugBean;
import model.Drug_informationBean;
import model.Electronic_prescriptionBean;
import model.HospitalBean;
import model.MemberBean;
import model.Prescribe_medicineBean;

public class U18 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		
		String ep_di_num_ = request.getParameter("ep_di_num");
		
		if(ep_di_num_ == null || "".equals(ep_di_num_)) {
			forward.setErrorMsg("電子処方箋番号がみつかりませんでした。");
			return forward;
		}
		
		int ep_di_num = Integer.parseInt(ep_di_num_);
		Electronic_prescriptionBean ep = Electronic_prescriptionDAO.getInstance().findByDi_numToEpBean(ep_di_num);
		
		if(ep.getEp_c_num() != 0) {
			ChildBean child = ChildDAO.getInstance().getChildBeanByC_num(ep.getEp_c_num());
			request.setAttribute("child", child);
		}else {
			MemberBean member = MemberDAO.getInstance().getMemberBeanByM_num(ep.getEp_m_num());
			request.setAttribute("member", member);
		}
		
		DoctorDAO doctorDAO = DoctorDAO.getInstance();
		DoctorBean doctor = doctorDAO.findByD_numToDoctor(ep.getEp_d_num());
		List<Prescribe_medicineBean> pmList = Prescribe_medicineDAO.getInstance().getPmList(ep.getEp_m_num());
		HospitalBean hospital = HospitalDAO.getInstance().findByH_numToDoctor_hospital_num(doctor.getD_h_num());
		Drug_informationBean di = Drug_informationDAO.getInstance().findByDi_numToDrug_informationBean(ep_di_num);
		List<DrugBean> drugList = new ArrayList<>();
		for(Prescribe_medicineBean pm : pmList) {
			drugList.add(DrugDAO.getInstance().findByDrug_numToDrug(pm.getPm_drug_num()));
		}
		
		request.setAttribute("ep", ep);
		request.setAttribute("doctor", doctor);
		request.setAttribute("pmList", pmList);
		request.setAttribute("drugList", drugList);
		request.setAttribute("hospital", hospital);
		request.setAttribute("di", di);
		forward.setPath("u18");
		return forward;
	}

}
