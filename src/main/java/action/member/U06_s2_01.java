package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.MemberDAO;
import model.MemberBean;
import util.Gmail;
import util.XssFilter;

public class U06_s2_01 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		String patternEmail = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
		XssFilter xssFilter = XssFilter.getInstance();
		
		String new_email = xssFilter.stripTagAll(request.getParameter("email").replaceAll("\\s", ""));
		String pw = xssFilter.stripTagAll(request.getParameter("pw"));
		
		// email
		if (!new_email.matches(patternEmail)) {
			forward.setErrorMsg("正しいメールアドレスを入力してください。");
			return forward;
		}
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		if (memberDAO.isDuplicateEmail(new_email)) {
			forward.setErrorMsg("既に存在しているメールアドレスです。");
			return forward;
		}
		
		MemberBean member = (MemberBean) session.getAttribute("member");
		
		if(!memberDAO.checkPassword(member.getM_email(), pw)) {
			forward.setErrorMsg("パスワードが間違いです。");
			return forward;
		}
		String noHashEmail = (String) session.getAttribute("beforeHashEmail");
		
		if(!memberDAO.updateMemberEmail(member.getM_email(), new_email)) {
			forward.setErrorMsg("メールアドレス変更に失敗しました。");
			return forward;
		}
		
		Gmail gmail = Gmail.getInstance();
		if(!gmail.sendUpdateEmailNotice(noHashEmail, member.getM_name())) {
			forward.setErrorMsg("個人情報変更のお知らせメールの送信が失敗しました。\\n個人情報は変更されましたのでご安心してください。");
			return forward;
		}
		if(!gmail.sendAuthMail(new_email)) {
			forward.setErrorMsg("認証メール送信が失敗しました。\\n変更したメールアドレスでログインしてください。");
			return forward;
		}
		session.invalidate();
		forward.setPath("u06_s2_02");
		return forward;
	}

}
