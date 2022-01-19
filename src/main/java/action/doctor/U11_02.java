package action.doctor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.ChildDAO;
import dao.DrugDAO;
import dao.Electronic_prescriptionDAO;
import dao.MemberDAO;
import dao.Prescribe_medicineDAO;
import model.ChildBean;
import model.DoctorBean;
import model.Electronic_prescriptionBean;
import model.MemberBean;
import model.Prescribe_medicineBean;
import util.XssFilter;

public class U11_02 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		String[] ep_expiry_date = request.getParameterValues("ep_expiry_date");
		String ep_burden_num_ = request.getParameter("ep_burden_num").replaceAll("\\s", "");
		String ep_burden_person_ = request.getParameter("ep_burden_person").replaceAll("\\s", "");
		String ep_burden_num = (ep_burden_num_ != null) ? ep_burden_num_.replaceAll("\\s", "") : "";
		String ep_burden_person = (ep_burden_person_ != null) ? ep_burden_person_.replaceAll("\\s", "") : "";
		String ep_patient_type = request.getParameter("ep_patient_type");
		String ep_disease = request.getParameter("ep_disease");
		String[] medicine_name = request.getParameterValues("medicine_name");
		String[] pm_dosage = request.getParameterValues("pm_dosage");
		String[] pm_dose = request.getParameterValues("pm_dose");
		String pm_usage = request.getParameter("pm_usage");
		String pm_dose_day = request.getParameter("pm_dose_day");
		String pm_all_dose_day = request.getParameter("pm_all_dose_day");
		String ep_note = request.getParameter("ep_note").replaceAll("\r\n", "<br>");
		String m_num_ = request.getParameter("m_num");
		String c_num_ = request.getParameter("c_num");
		String dosage_type = request.getParameter("dosage_type");
		String dose_type = request.getParameter("dose_type");
		String regPm = "^[0-9]+(.)?[0-9]*$";
		String regNum = "^[0-9]*$";
		
		if (ep_expiry_date[0].length() < 4 || ep_expiry_date[1].length() < 2 || ep_expiry_date[2].length() < 2
				|| Integer.parseInt(ep_expiry_date[1]) < 1 || Integer.parseInt(ep_expiry_date[1]) > 12
				|| Integer.parseInt(ep_expiry_date[2]) < 1 || Integer.parseInt(ep_expiry_date[2]) > 31) {
			forward.setErrorMsg("正しい有効時間を入力してください。");
			return forward;
		} else if ("".equals(ep_disease.replaceAll("\\s", "")) || ep_disease == null) {
			forward.setErrorMsg("病名を入力してください。");
			return forward;
		}
		if (medicine_name != null) {
			for (String medicine : medicine_name) {
				if (medicine == null || "".equals(medicine)) {
					forward.setErrorMsg("薬を選択してください。");
					return forward;
				}
			}
		}else {
			forward.setErrorMsg("薬を選択してください。");
			return forward;
		}
		if (pm_dosage == null) {
			forward.setErrorMsg("分量を入力してください。");
			return forward;
		} else if (dosage_type == null || "".equals(dosage_type)) {
			forward.setErrorMsg("分量のタイプを選択してください。");
			return forward;
		} else if (pm_dose == null) {
			forward.setErrorMsg("用量を入力してください。");
			return forward;
		} else if (dose_type == null || "".equals(dose_type)) {
			forward.setErrorMsg("用量のタイプを選択してください。");
			return forward;
		} else if (pm_usage == null || "".equals(pm_usage)) {
			forward.setErrorMsg("用法を選択してください。");
			return forward;
		} else if (pm_dose_day == null || "".equals(pm_dose_day)) {
			forward.setErrorMsg("日数を入力してください。");
			return forward;
		} else if (pm_all_dose_day == null || "".equals(pm_all_dose_day)) {
			forward.setErrorMsg("総投与日数を入力してください。");
			return forward;
		} else if (!pm_dose_day.matches(regNum)) {
			forward.setErrorMsg("日数は数字のみです。");
			return forward;
		} else if (!pm_all_dose_day.matches(regNum)) {
			forward.setErrorMsg("総投与日数は数字のみです");
			return forward;
		}
		if (pm_dosage != null) {
			for (String dosage : pm_dosage) {
				if (dosage == null || "".equals(dosage)) {
					forward.setErrorMsg("分量を入力してください。");
					return forward;
				}else if(!dosage.matches(regPm)) {
					forward.setErrorMsg("正しい分量を入力してください。\\n数字と「.」のみです。");
					return forward;
				}
			}
		}
		if (pm_dose != null) {
			for (String dose : pm_dose) {
				if (dose == null || "".equals(dose)) {
					forward.setErrorMsg("用量を入力してください。");
					return forward;
				}else if(!dose.matches(regPm)) {
					forward.setErrorMsg("正しい用量を入力してください。\\n数字と「.」のみです。");
					return forward;
				}
			}
		}

		XssFilter xssFilter = XssFilter.getInstance();
		ep_expiry_date = xssFilter.stripTagAll(ep_expiry_date);
		ep_burden_num = xssFilter.stripTagAll(ep_burden_num);
		ep_burden_person = xssFilter.stripTagAll(ep_burden_person);
		ep_patient_type = xssFilter.stripTagAll(ep_patient_type);
		ep_disease = xssFilter.stripTagAll(ep_disease);
		medicine_name = xssFilter.stripTagAll(medicine_name);
		pm_dosage = xssFilter.stripTagAll(pm_dosage);
		dosage_type = xssFilter.stripTagAll(dosage_type);
		pm_dose = xssFilter.stripTagAll(pm_dose);
		dose_type = xssFilter.stripTagAll(dose_type);
		pm_usage = xssFilter.stripTagAll(pm_usage);
		pm_dose_day = xssFilter.stripTagAll(pm_dose_day);
		pm_all_dose_day = xssFilter.stripTagAll(pm_all_dose_day);
		ep_note = xssFilter.stripTagAll(ep_note);

		Electronic_prescriptionBean epBean = new Electronic_prescriptionBean();
		Electronic_prescriptionDAO epDAO = Electronic_prescriptionDAO.getInstance();

		DoctorBean doctor = (DoctorBean) session.getAttribute("doctor");

		int m_num = Integer.parseInt(xssFilter.stripTagAll(m_num_));
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberBean member = memberDAO.getMemberBeanByM_num(m_num);
		epBean.setEp_m_num(member.getM_num());

		// 子供の登録の場合、FKであるmember番号を入れて、子ども番号を入力。
		if (!"".equals(c_num_) && c_num_ != null) {
			ChildDAO childDAO = ChildDAO.getInstance();
			int c_num = Integer.parseInt(xssFilter.stripTagAll(c_num_));
			ChildBean child = childDAO.getChildBeanByC_num(c_num);
			epBean.setEp_c_num(child.getC_num());
		}

		// 電子処方箋の処理
		epBean.setEp_d_num(doctor.getD_num());
		epBean.setEp_expiry_date(ep_expiry_date[0] + "-" + ep_expiry_date[1] + "-" + ep_expiry_date[2]);
		epBean.setEp_burden_num(ep_burden_num);
		epBean.setEp_burden_person(ep_burden_person);
		epBean.setEp_patient_type(ep_patient_type);
		epBean.setEp_disease(ep_disease);
		epBean.setEp_note(ep_note);
		epBean.setEp_auth(false);
		// 電子処方箋を登録する。

		int ep_num = epDAO.createEp(epBean);
		if (ep_num == 0) {
			forward.setErrorMsg("電子処方箋の登録が失敗しました。");
			return forward;
		}

		// 薬処方の処理 複数入るから繰り返し分を使う。
		int index = 0;
		DrugDAO drugDAO = DrugDAO.getInstance();
		Prescribe_medicineDAO pmDAO = Prescribe_medicineDAO.getInstance();
		List<Prescribe_medicineBean> pmList = new ArrayList<Prescribe_medicineBean>();
		for (String medicine : medicine_name) {
			Prescribe_medicineBean pmBean = new Prescribe_medicineBean();
			int drug_num = drugDAO.findByDrug_numToDrug_name(medicine);
			if (drug_num == 0) {
				forward.setErrorMsg("登録されていない薬です。薬名を確認してください。");
			}
			pmBean.setPm_drug_num(drugDAO.findByDrug_numToDrug_name(medicine));
			pmBean.setPm_ep_num(ep_num);
			pmBean.setPm_dosage(pm_dosage[index] + "回" + pm_dosage[index + 1] + dosage_type);
			pmBean.setPm_usage(pm_usage);
			pmBean.setPm_dose(pm_dose[index] + "日" + pm_dose[index + 1] + dose_type);
			pmBean.setPm_dose_day(Integer.parseInt(pm_dose_day));
			pmBean.setPm_all_dose_day(Integer.parseInt(pm_all_dose_day));
			index += 2;
			pmList.add(pmBean);
		}
		// 薬処方を登録する。
		if (!pmDAO.createPm(pmList)) {
			forward.setErrorMsg("薬処方の登録が失敗しました。");
			return forward;
		}
		
		forward.setMsg("電子処方箋登録が完了しました。");
		forward.setPath("u10_01_doctor");

		return forward;
	}

}
