package action.nonmember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.DoctorDAO;
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
		String email_ = request.getParameter("email");
		String email = xssFilter.stripTagAll(email_.replaceAll("\\s", ""));
		MemberDAO memberDAO = MemberDAO.getInstance();
		DoctorDAO doctorDAO = DoctorDAO.getInstance();
		String authToken = (String) session.getAttribute("authToken");
		/* 
		 authToken and email has been sha256 encrypt
		*/
		if(authToken == null || "".equals(authToken)) {
			forward.setErrorMsg("正しくないリクエストです。");
			return forward;
		}
		
		if(!email.equals(authToken)) {
			forward.setErrorMsg("正しくないリクエストです。");
			return forward;
		}
		if(memberDAO.findByEmail(email)) {
			if(memberDAO.isAuth(email)) {
				forward.setErrorMsg("既にメールアドレスの認証がされています。");
				return forward;
			}
			if(!memberDAO.updateAuth(email)) {
				forward.setErrorMsg("メール認証のエラーが発生しました。\\nもう一度、認証URLにアクセスしてください。");
				return forward;
			}
			String[] m_numANDm_birth = memberDAO.findbyEmailToM_numAndBirth(email);
			if(!memberDAO.generateQRcode(Integer.parseInt(m_numANDm_birth[0]), m_numANDm_birth[1])) {
				forward.setErrorMsg("QRコード登録のエラーが発生しました。\\n管理者に問い合わせしてください。");
				return forward;
			}
		}
		
		if(doctorDAO.findByEmail(email)) {
			if(doctorDAO.isAuth(email)) {
				forward.setErrorMsg("既にメールアドレスの認証がされています。");
				return forward;
			}
			if(!doctorDAO.updateAuth(email)) {
				forward.setErrorMsg("メール認証のエラーが発生しました。\\nもう一度、認証URLにアクセスしてください。");
				return forward;
			}
		}
		
		session.invalidate();
		forward.setPath("u01_04");
		return forward;
	}

}
