package action.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.ChildDAO;
import model.ChildBean;
import model.MemberBean;
import util.XssFilter;

public class U07_02 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		XssFilter xssFilter = XssFilter.getInstance();
		
		String[] check_birth = xssFilter.stripTagAll(request.getParameterValues("birth"));
		String[] check_insurance_expiry_date = xssFilter.stripTagAll(request.getParameterValues("insurance_expiry_date"));
		String insurance_num = request.getParameterValues("insurance_num")[0] + "-"
				+ request.getParameterValues("insurance_num")[1];

		Map<String, String> map = new HashMap<String, String>();
		map.put("medical_num", request.getParameter("medical_num"));
		map.put("insurance_num", insurance_num);
		map.put("insurance_mark", request.getParameter("insurance_mark"));
		map.put("name", request.getParameter("frist_name").replaceAll("\\s", "") + " "
				+ request.getParameter("last_name").replaceAll("\\s", ""));
		map.put("kana", request.getParameter("frist_kana").replaceAll("\\s", "") + " "
				+ request.getParameter("last_kana").replaceAll("\\s", ""));
		map.put("gender", request.getParameter("gender"));
		map.put("blood_type", request.getParameter("blood_type"));
		map.put("medical_history", request.getParameter("medical_history").replaceAll("\r\n", "<br>"));
		map.put("medication", request.getParameter("medication").replaceAll("\r\n", "<br>"));
		map.put("allergy", request.getParameter("allergy").replaceAll("\r\n", "<br>"));

		String patternNum = "^[0-9]*$";
		map = xssFilter.stripTagAll(map);
		
		if(map.get("name").length() > 50) {
			forward.setErrorMsg("???????????????50????????????????????????????????????????????????");
			return forward;
		}
		if(map.get("kana").length() > 50) {
			forward.setErrorMsg("?????????????????????50????????????????????????????????????????????????");
			return forward;
		}
		

		if(check_birth != null) {
			for(String birth1 : check_birth) {
				if (!birth1.replaceAll("-", "").matches(patternNum)) {
					forward.setErrorMsg("????????????????????????????????????");
					return forward;
				}
				if(birth1 == null || "".equals(birth1)) {
					forward.setErrorMsg("??????????????????????????????????????????");
					return forward;
				}
			}
			if (check_birth[0].length() != 4
					|| Integer.parseInt(check_birth[1]) < 1
					|| Integer.parseInt(check_birth[1]) > 12 
					|| Integer.parseInt(check_birth[2]) < 1
					|| Integer.parseInt(check_birth[2]) > 31
					|| check_birth[1].length() != 2
					|| check_birth[2].length() != 2) {
				forward.setErrorMsg("??????????????????????????????????????????????????? \\n???)1998 01 05");
				return forward;
			}
		}else {
			forward.setErrorMsg("??????????????????????????????????????????");
			return forward;
		}
		
		String birth = check_birth[0] + "-" + check_birth[1] + "-" + check_birth[2];
		map.put("birth", birth);
		
		String insurance_expiry_date = check_insurance_expiry_date[0] + "-"
				+ check_insurance_expiry_date[1] + "-"
				+ check_insurance_expiry_date[2];
		map.put("insurance_expiry_date", insurance_expiry_date);
		
		MemberBean member = (MemberBean) session.getAttribute("member");

		ChildDAO childDAO = ChildDAO.getInstance();
		ChildBean childBean = new ChildBean();
		
		if("".equals(map.get("medical_num").replaceAll("\\s", ""))) {
			forward.setErrorMsg("??????????????????????????????????????????????????????");
			return forward;
		}

		if (childDAO.isDuplicateMedical_num(map.get("medical_num"))) {
			forward.setErrorMsg("????????????????????????????????????????????????????????????");
			return forward;
		}

		childBean.setC_m_num(member.getM_num());
		childBean.setC_medical_num(map.get("medical_num"));
		childBean.setC_i_num(map.get("insurance_num"));
		childBean.setC_i_expiry_date(map.get("insurance_expiry_date"));
		childBean.setC_i_mark(map.get("insurance_mark"));
		childBean.setC_name(map.get("name"));
		childBean.setC_kana(map.get("kana"));
		childBean.setC_birth(map.get("birth"));
		childBean.setC_gender(map.get("gender"));
		childBean.setC_blood_type(map.get("blood_type"));
		childBean.setC_medical_history(map.get("medical_history"));
		childBean.setC_medication(map.get("medication"));
		childBean.setC_allergy(map.get("allergy"));

		session.setAttribute("childBean", childBean);
		forward.setPath("u07_03");

		return forward;
	}

}
