package action.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.DoctorDAO;
import dao.Drug_informationDAO;
import dao.Electronic_prescriptionDAO;
import dao.HospitalDAO;
import dao.PharmacyDAO;
import dao.Prescribe_medicineDAO;
import model.ChildBean;
import model.Drug_informationBean;
import model.Electronic_prescriptionBean;
import model.MemberBean;

public class U08_01 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		MemberBean member = (MemberBean) session.getAttribute("member");

		List<Electronic_prescriptionBean> epList = Electronic_prescriptionDAO.getInstance().findByM_numAndC_numToEp_num(member.getM_num(), 0);
		Drug_informationDAO diDAO = Drug_informationDAO.getInstance();
		List<Drug_informationBean> diList = new ArrayList<>();
		List<String> hospital_nameList = new ArrayList<>();
		List<String> pharmacy_nameList = new ArrayList<>();
		List<Integer> totalPriceList = new ArrayList<>();
		PharmacyDAO pharmacyDAO = PharmacyDAO.getInstance();
		HospitalDAO hospitalDAO = HospitalDAO.getInstance();
		Prescribe_medicineDAO pmDAO = Prescribe_medicineDAO.getInstance();

		if (!epList.isEmpty()) {
			for (Electronic_prescriptionBean ep : epList) {
				if (ep.getEp_di_num() != 0) {
					diList.add(diDAO.findByDi_numToDrug_informationBean(ep.getEp_di_num()));
					int d_h_num = DoctorDAO.getInstance().findByD_numToD_h_num(ep.getEp_d_num());
					hospital_nameList.add(hospitalDAO.findByD_h_numToH_name(d_h_num));
					totalPriceList.add(pmDAO.findByEp_numToDrugTotalPrice(ep.getEp_num()));
				}
			}
		}

		if (!diList.isEmpty()) {
			for (Drug_informationBean di : diList) {
				if(di != null) {
					pharmacy_nameList.add(pharmacyDAO.findBDi_p_numToP_name(di.getDi_p_num()));
				}
			}
		}
		request.setAttribute("selected", 0);
		request.setAttribute("diList", diList);
		request.setAttribute("hospital_nameList", hospital_nameList);
		request.setAttribute("pharmacy_nameList", pharmacy_nameList);
		request.setAttribute("totalPriceList", totalPriceList);

		forward.setPath("u08_01");
		return forward;
	}

}
