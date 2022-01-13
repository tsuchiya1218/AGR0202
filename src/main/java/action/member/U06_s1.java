package action.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.QuestionnaireDAO;
import model.MemberBean;
import model.QuestionnaireBean;
import util.Gmail;
import util.XssFilter;

public class U06_s1 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		
		XssFilter xssFilter = XssFilter.getInstance();
		/* 問診票 */
		Map<String, String> q_map = new HashMap<String, String>();
		q_map.put("blood_type", request.getParameter("blood_type"));
		q_map.put("medical_history", request.getParameter("medical_history").replaceAll("\r\n", "<br>"));
		q_map.put("medication", request.getParameter("medication").replaceAll("\r\n", "<br>"));
		q_map.put("drink", request.getParameter("drink"));
		q_map.put("smoke", request.getParameter("smoke"));
		q_map.put("pregnancy", request.getParameter("pregnancy"));
		q_map.put("allergy", request.getParameter("allergy").replaceAll("\r\n", "<br>"));
		
		q_map = xssFilter.stripTagAll(q_map);
		
		/* 問診票情報 */
		QuestionnaireDAO questionnaireDAO = QuestionnaireDAO.getInstance();
		QuestionnaireBean questionnaire = (QuestionnaireBean) session.getAttribute("questionnaire");
		questionnaire.setQ_blood_type(q_map.get("blood_type"));
		questionnaire.setQ_medical_history(q_map.get("medical_history"));
		questionnaire.setQ_medication(q_map.get("medication"));
		questionnaire.setQ_drink("1".equals(q_map.get("drink")) ? true : false);
		questionnaire.setQ_smoke("1".equals(q_map.get("smoke")) ? true : false);
		questionnaire.setQ_pregnancy("1".equals(q_map.get("pregnancy")) ? true : false);
		questionnaire.setQ_allergy(q_map.get("allergy"));
		
		if(!questionnaireDAO.updateQuestionnaire(questionnaire)) {
			forward.setErrorMsg("問診票の変更が失敗しました。");
			return forward;
		}
		
		MemberBean member = (MemberBean) session.getAttribute("member");
		QuestionnaireBean new_questionnaire = questionnaireDAO.getQuestionnaire(questionnaire.getQ_num());
		Gmail gmail = Gmail.getInstance();
		if(!gmail.sendUpdateNotice( (String) session.getAttribute("beforeHashEmail"), member.getM_name())) {
			forward.setErrorMsg("メール送信が失敗しました。\\n変更は完了しましたので、ご安心してください。");
			return forward;
		}
		
		forward.setPath("u06_01");
		session.setAttribute("questionnaire", new_questionnaire);
		return forward;
	}

}
