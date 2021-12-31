package action.nonmember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.MemberDAO;
import util.XssFilter;

public class U03_03 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);
		ActionForward forward = new ActionForward();
		XssFilter xssFilter = XssFilter.getInstance();
		String patternPw = "^[A-Za-z0-9]{8,64}$";
		String m_email = xssFilter.stripTagAll(request.getParameter("email").replaceAll("\\s", ""));
		String pw = xssFilter.stripTagAll(request.getParameter("pw"));
		String pw_check = xssFilter.stripTagAll(request.getParameter("pw_check"));
		
		try {
			MemberDAO memberDAO = MemberDAO.getInstance();
			if(!m_email.equals(session.getAttribute("authToken"))) {
				forward.setErrorMsg("正しくないリクエストです。");
				return forward;
			}
			if("".equals(pw) || "".equals(pw_check)) {
				forward.setErrorMsg("パスワードを入力してください。");
				return forward;
			}
			if(!pw.matches(patternPw) || !pw_check.matches(patternPw)) {
				forward.setErrorMsg("パスワードは英数字のみ8文字以上です。");
				return forward;
			}
			if(!memberDAO.changePassword(m_email,pw)) {
				forward.setErrorMsg("データベースエラー\\nパスワード変更ができませんでｈした。\\n問い合わせしてください。");
				return forward;
			}
			session.invalidate();
			forward.setPath("u03_04");
			return forward;
		}catch(NullPointerException e) {
			forward.setErrorMsg("パスワードを入力してください。");
			return forward;
		}
		
	}

}
