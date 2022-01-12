package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.AdminDAO;
import dao.DoctorDAO;
import dao.HospitalDAO;
import dao.MemberDAO;
import dao.PharmacyDAO;
import model.MemberBean;
import util.Gmail;
import util.XssFilter;

public class U06_s2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		String patternEmail = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
		XssFilter xssFilter = XssFilter.getInstance();
		String new_email_ = request.getParameter("email");
		String pw_ = request.getParameter("pw");
		
		// email
		if (!new_email_.matches(patternEmail)) {
			forward.setErrorMsg("正しいメールアドレスを入力してください。");
			return forward;
		}
		
		String new_email = xssFilter.stripTagAll(new_email_.replaceAll("\\s", ""));
		
		if(pw_ == null || "".equals(pw_)) {
			forward.setErrorMsg("パスワードを入力してください。");
			return forward;
		}
		
		String pw = xssFilter.stripTagAll(pw_);
		
		DoctorDAO doctorDAO = DoctorDAO.getInstance();
		MemberDAO memberDAO = MemberDAO.getInstance();
		PharmacyDAO pharmacyDAO = PharmacyDAO.getInstance();
		HospitalDAO hospitalDAO = HospitalDAO.getInstance();
		AdminDAO adminDAO = AdminDAO.getInstance();
		
		if (doctorDAO.isDuplicateEmail(new_email) || memberDAO.isDuplicateEmail(new_email) || pharmacyDAO.isDuplicateEmail(new_email)
				|| hospitalDAO.isDuplicateEmail(new_email) || adminDAO.isDuplicateEmail(new_email)) {
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
			forward.setErrorMsg("個人情報変更のお知らせメールの送信が失敗しましたが,\\n個人情報は変更されましたので再ログインしてください。");
			session.invalidate();
			return forward;
		}
		if(!gmail.sendAuthModifyMail(new_email)) {
			forward.setErrorMsg("認証メール送信が失敗しました。\\n変更したメールアドレスでログインしてください。");
			session.invalidate();
			return forward;
		}
		session.invalidate();
		forward.setPath("u06_s2_02");
		return forward;
	}

}
