package action.member;

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
import dao.PharmacyDAO;
import dao.Prescribe_medicineDAO;
import model.ChildBean;
import model.DrugBean;
import model.Drug_informationBean;
import model.Electronic_prescriptionBean;
import model.MemberBean;
import model.PharmacyBean;
import model.Prescribe_medicineBean;

public class U08_s2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		String di_num_ = request.getParameter("di_num");
		if ("".equals(di_num_) || di_num_ == null) {
			forward.setErrorMsg("薬剤情報提供書の番号がみつかりませんでした。");
			return forward;
		}
		int di_num = Integer.parseInt(di_num_);

		Drug_informationBean drug_information = Drug_informationDAO.getInstance()
				.findByDi_numToDrug_informationBean(di_num);
		Electronic_prescriptionBean electronic_prescription = Electronic_prescriptionDAO.getInstance()
				.findByDi_numToEpBean(drug_information.getDi_num());
		
		int doctor_num = electronic_prescription.getEp_d_num();
		String doctor_name = DoctorDAO.getInstance().findByNumToName(doctor_num);
		int totalPrice = 0;

		PharmacyBean pharmacy = PharmacyDAO.getInstance().findByPharmacyBeanToP_num(drug_information.getDi_p_num());
		List<Prescribe_medicineBean> Prescribe_medicine = Prescribe_medicineDAO.getInstance()
				.getPmList(electronic_prescription.getEp_num());
		String hospital_name = HospitalDAO.getInstance().findByD_h_numToH_name(electronic_prescription.getEp_num());
		totalPrice = Prescribe_medicineDAO.getInstance()
				.findByEp_numToDrugTotalPrice(electronic_prescription.getEp_num());

		DrugDAO drugDAO = DrugDAO.getInstance();
		List<DrugBean> drug = new ArrayList<>();
		for (Prescribe_medicineBean pm : Prescribe_medicine) {
			drug.add(drugDAO.findByDrug_numToDrug(pm.getPm_drug_num()));
		}
		if(electronic_prescription.getEp_c_num() != 0) {
			ChildBean child = ChildDAO.getInstance().getChildBeanByC_num(electronic_prescription.getEp_c_num());
			request.setAttribute("child", child);
		}else {
			MemberBean member = MemberDAO.getInstance().getMemberBeanByM_num(electronic_prescription.getEp_m_num());
			request.setAttribute("member", member);
		}

		request.setAttribute("electronic_prescription", electronic_prescription);
		request.setAttribute("drug_information", drug_information);
		request.setAttribute("doctor_name", doctor_name);
		request.setAttribute("Prescribe_medicine", Prescribe_medicine);
		request.setAttribute("drug", drug);
		request.setAttribute("hospital_name", hospital_name);
		request.setAttribute("pharmacy", pharmacy);
		request.setAttribute("totalPrice", totalPrice);

		forward.setPath("u08_s2");
		return forward;
	}

}
