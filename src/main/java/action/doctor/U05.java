package action.doctor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.DoctorDAO;
import model.DoctorBean;
import util.Gmail;
import util.SHA256;
import util.XssFilter;

public class U05 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);
		ActionForward forward = new ActionForward();
		XssFilter xssFilter = XssFilter.getInstance();
		String pw = request.getParameter("pw");
		DoctorBean doctor =  (DoctorBean) session.getAttribute("doctor");
		
		if("".equals(pw) || pw == null) {
			forward.setErrorMsg("パスワードを入力してください。");
			return forward;
		}
		pw = xssFilter.stripTagAll(pw);
		
		if(!doctor.getD_pw().equals(SHA256.getEncrypt(pw))) {
			forward.setErrorMsg("パスワードが一致しません。");
			return forward;
		}
		DoctorDAO doctorDAO = DoctorDAO.getInstance();
		if(!doctorDAO.leave(doctor.getD_email())) {
			forward.setErrorMsg("データベースエラーが発生しました。もう一度やり直してください。");
			return forward;
		}else {
			Gmail gmail = Gmail.getInstance();
			String beforeHashEmail = (String) session.getAttribute("beforeHashEmail");
			if(!gmail.sendLeaveMail(beforeHashEmail,doctor.getD_name())) {
				forward.setMsg("メール送信は失敗しましたが、\\n退会は完了しましたので、ご安心してください。");
				forward.setPath("u05_02");
				session.invalidate();
				return forward;
			}
			forward.setPath("u05_02");
			session.invalidate();
			return forward;
		}
	}

}
