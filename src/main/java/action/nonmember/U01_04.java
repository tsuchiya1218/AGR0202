package action.nonmember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.MemberDAO;
import util.XssFilter;

public class U01_04 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		XssFilter xssFilter = XssFilter.getInstance();
		String m_email = xssFilter.stripTagAll(request.getParameter("email").replaceAll("\\s", ""));
		MemberDAO memberDAO = MemberDAO.getInstance();
		try {
			if(!m_email.equals(session.getAttribute("authToken"))) {
				forward.setErrorMsg("正しくないリクエストです。");
				return forward;
			}
		}catch(NullPointerException e) {
			forward.setErrorMsg("正しくないリクエストです。");
			return forward;
		}
		
		if(memberDAO.isAuth(m_email)) {
			forward.setErrorMsg("既にメールアドレスの認証がされています。");
			return forward;
		}
		
		if(!memberDAO.updateAuth(m_email)) {
			forward.setErrorMsg("メール認証のエラーが発生しました。\\nもう一度、認証URLにアクセスしてください。");
			return forward;
		}
		 
		String[] m_numANDm_brith = memberDAO.findbyEmailToM_numAndBrith(m_email);
		if(!memberDAO.generateQRcode(Integer.parseInt(m_numANDm_brith[0]), m_numANDm_brith[1])) {
			forward.setErrorMsg("QRコード登録のエラーが発生しました。\\n管理者に問い合わせしてください。");
			return forward;
		}
		
		session.invalidate();
		forward.setPath("u01_04");
		return forward;
	}

}
