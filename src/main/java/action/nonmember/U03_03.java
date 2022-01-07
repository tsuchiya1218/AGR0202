package action.nonmember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.DoctorDAO;
import dao.MemberDAO;
import util.Gmail;
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

		// emailは既にSHA256化している
		String email = xssFilter.stripTagAll(request.getParameter("email").replaceAll("\\s", ""));

		String pw_ = request.getParameter("pw");
		String pw_check_ = request.getParameter("pw_check");

		if (!email.equals(session.getAttribute("authToken"))) {
			forward.setErrorMsg("正しくないリクエストです。");
			return forward;
		} else if ("".equals(pw_) || "".equals(pw_check_) || pw_ == null || pw_check_ == null) {
			forward.setErrorMsg("パスワードを入力してください。");
			return forward;
		}

		String pw = xssFilter.stripTagAll(pw_);
		String pw_check = xssFilter.stripTagAll(pw_check_);

		if (!pw.matches(patternPw) || !pw_check.matches(patternPw)) {
			forward.setErrorMsg("パスワードは英数字のみ8桁以上にしてください。");
			return forward;
		}

		Gmail gmail = Gmail.getInstance();
		String noHashEmail = (String) session.getAttribute("beforeHashEmail");
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		if (memberDAO.findByEmail(email)) {
			String name = memberDAO.findByEmailToName(email);
			if (!memberDAO.changePassword(email, pw)) {
				forward.setErrorMsg("データベースエラー\\nパスワードの変更ができませんでした。\\n問い合わせしてください。");
				return forward;
			}
			gmail.sendUpdatePasswordNotice(noHashEmail, name);
		}

		DoctorDAO doctorDAO = DoctorDAO.getInstance();
		if (doctorDAO.findByEmail(email)) {
			String name = doctorDAO.findByEmailToName(email);
			if (!doctorDAO.changePassword(email, pw)) {
				forward.setErrorMsg("データベースエラー\\nパスワードの変更ができませんでした。\\n問い合わせしてください。");
				return forward;
			}
			gmail.sendUpdatePasswordNotice(noHashEmail, name);
		}

		session.invalidate();
		forward.setPath("u03_04");
		return forward;

	}

}
