package action.nonmember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.MemberDAO;
import dao.QuestionnaireDAO;
import model.MemberBean;
import model.QuestionnaireBean;
import util.Gmail;
import util.SHA256;

public class U01_02 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		MemberBean member = (MemberBean) session.getAttribute("memberBean");
		QuestionnaireBean questionnaire = (QuestionnaireBean) session.getAttribute("questionnaireBean");
		
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		if(!memberDAO.singUp(member)) {
			forward.setErrorMsg("会員登録のエラーが発生しました。");
			return forward;
		}
		int m_num = memberDAO.getM_num(member.getM_email());
		/* questionnaire */
		questionnaire.setQ_num(memberDAO.getM_num(member.getM_email()));
		QuestionnaireDAO questionnaireDAO = QuestionnaireDAO.getInstance();
		if(!questionnaireDAO.createQuestionnaire(questionnaire)) {
			forward.setErrorMsg("問診票登録のエラーが発生しました。");
			return forward;
		}
		if(!memberDAO.updateM_q_Num(m_num,questionnaire.getQ_num())) {
			forward.setErrorMsg("会員の問診票番号登録のエラーが発生しました。");
			return forward;
		}
		Gmail gmail = Gmail.getInstance();
		if(!gmail.sendSingUpMail(member.getM_email(), member.getM_name())) {
			forward.setErrorMsg("認証メール送信のエラーが発生しました。\\nログインするともう一度認証メールが送られます。");
			return forward;
		}
		session.invalidate();
		session = request.getSession(true);
		session.setAttribute("authToken", SHA256.getEncrypt(member.getM_email()));
		forward.setPath("u01_03");
		return forward;
	}

}
