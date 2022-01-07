package action.doctor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.ChildDAO;
import dao.DrugDAO;
import dao.HospitalDAO;
import dao.MemberDAO;
import model.ChildBean;
import model.DoctorBean;
import model.DrugBean;
import model.MemberBean;

public class U11_01 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		String c_num_ = request.getParameter("c_num");
		String m_num_ = request.getParameter("m_num");
		int c_num = 0;
		int m_num = 0;
		
		m_num = Integer.parseInt(m_num_);
		MemberBean member = MemberDAO.getInstance().getMemberBeanByM_num(m_num);
		member.setAge(MemberDAO.getInstance().countBirth(member.getM_birth()));
		request.setAttribute("member", member);

		if (!"".equals(c_num_) && c_num_ != null) {
			c_num = Integer.parseInt(c_num_);
			ChildBean child = ChildDAO.getInstance().getChildBeanByC_num(c_num);
			child.setAge(ChildDAO.getInstance().countBirth(child.getC_birth()));
			request.setAttribute("child", child);
		}
		
		DoctorBean doctor = (DoctorBean) session.getAttribute("doctor");
		List<DrugBean> drugList = DrugDAO.getInstance().getDrugList();
		HospitalDAO hospitalDAO = HospitalDAO.getInstance();
		String h_tel = hospitalDAO.findByD_numToH_tel(doctor.getD_h_num());
		String h_name = hospitalDAO.findByD_h_numToH_name(doctor.getD_h_num());
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);
		String todayDate = sdf. format(date);
		
		request.setAttribute("todayDate", todayDate);
		request.setAttribute("h_tel", h_tel);
		request.setAttribute("drugList", drugList);
		request.setAttribute("h_name", h_name);
		forward.setPath("u11_01");
		
		return forward;
	}

}
