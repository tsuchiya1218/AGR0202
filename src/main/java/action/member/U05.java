package action.member;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.MemberDAO;
import model.MemberBean;
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
		MemberBean member =  (MemberBean) session.getAttribute("member");
		
		if("".equals(pw) || pw == null) {
			forward.setErrorMsg("パスワードを入力してください。");
			return forward;
		}
		pw = xssFilter.stripTagAll(pw);
		
		if(!member.getM_pw().equals(SHA256.getEncrypt(pw))) {
			forward.setErrorMsg("パスワードが一致しません。");
			return forward;
		}
		MemberDAO memberDAO = MemberDAO.getInstance();
		if(!memberDAO.leave(member.getM_num())) {
			forward.setErrorMsg("データベースエラーが発生しました。もう一度やり直してください。");
			return forward;
		}else {
			String root = "C:\\Users\\20jy0211\\git\\AGR0202\\src\\main\\webapp\\static\\img\\qr_code";
			String qr_name = member.getM_qr_num()+".png";
			File file = new File(root+qr_name);
			file.delete();
			
			Gmail gmail = Gmail.getInstance();
			String beforeHashEmail = (String) session.getAttribute("beforeHashEmail");
			if(!gmail.sendLeaveMail(beforeHashEmail,member.getM_name())) {
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
