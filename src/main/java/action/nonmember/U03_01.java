package action.nonmember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.MemberDAO;
import util.Gmail;
import util.SHA256;
import util.XssFilter;

public class U03_01 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);
		ActionForward forward = new ActionForward();
		XssFilter xssFilter = XssFilter.getInstance();
		
		String patternEmail = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
		String m_email = xssFilter.stripTagAll(request.getParameter("email"));
		String frist_name = xssFilter.stripTagAll(request.getParameter("frist_name").replaceAll("\\s", ""));
		String last_name = xssFilter.stripTagAll(request.getParameter("last_name").replaceAll("\\s", ""));
		String name = frist_name + " " +last_name;
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		if((m_email == null || "".equals(m_email)) || !m_email.matches(patternEmail)) {
			forward.setErrorMsg("正しいメールアドレスを入力してください。");
			return forward;
		}
		if(!memberDAO.findPassword(m_email,name)) {
			forward.setErrorMsg("入力した内容と一致しません。");
			return forward;
		}else {
			Gmail gmail = Gmail.getInstance();
			if(!gmail.sendChangePasswordMail(m_email,name)) {
				forward.setErrorMsg("パスワード再設定メールの送信が失敗しました。\\nもう一度やり直すか問い合わせしてください。");
				return forward;
			}
			
			session.setAttribute("authToken", SHA256.getEncrypt(m_email));
			forward.setPath("u03_02");
			return forward;
		}
		
	}

}
