package action.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.ChildDAO;
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
		String selected_ = request.getParameter("selected");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		int selected = 0;
		if (selected_ != null && !"".equals(selected_)) {
			selected = Integer.parseInt(selected_);
		}
		Electronic_prescriptionDAO epDAO = Electronic_prescriptionDAO.getInstance();
		List<Electronic_prescriptionBean> epList = epDAO.findByM_numAndC_numToEp_num(member.getM_num(), selected);
		
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
					if (startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)) {
						diList.add(diDAO.findByDi_numAndReg_DateToDrug_informationBean(ep.getEp_di_num(), startDate,
								endDate));
					} else if(startDate != null && !"".equals(startDate) && (endDate == null || "".equals(endDate))){
						diList.add(diDAO.findByDi_numAndStartDateToDrug_informationBean(ep.getEp_di_num(), startDate));
					} else if((startDate == null || "".equals(startDate)) && endDate != null  && !"".equals(endDate)){
						diList.add(diDAO.findByDi_numAndEndDateToDrug_informationBean(ep.getEp_di_num(), endDate));
					} else {
						diList.add(diDAO.findByDi_numToDrug_informationBean(ep.getEp_di_num()));
					}
					int d_h_num = DoctorDAO.getInstance().findByD_numToD_h_num(ep.getEp_d_num());
					hospital_nameList.add(hospitalDAO.findByD_h_numToH_name(d_h_num));
					totalPriceList.add(pmDAO.findByEp_numToDrugTotalPrice(ep.getEp_num()));
				}
			}
		}

		while (diList.remove(null));
		if (!diList.isEmpty()) {
			for (Drug_informationBean di : diList) {
				if(di != null) {
					String p_name = pharmacyDAO.findBDi_p_numToP_name(di.getDi_p_num());
					if(p_name != null) {
						pharmacy_nameList.add(p_name);
					}
				}
			}
		}
		
		List<ChildBean> children = ChildDAO.getInstance().getChildList(member.getM_num());
		
		request.setAttribute("children", children);
		request.setAttribute("selected", selected);
		request.setAttribute("diList", diList);
		request.setAttribute("hospital_nameList", hospital_nameList);
		request.setAttribute("pharmacy_nameList", pharmacy_nameList);
		request.setAttribute("totalPriceList", totalPriceList);
		
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);

		forward.setPath("u08_01");
		return forward;
	}

}
