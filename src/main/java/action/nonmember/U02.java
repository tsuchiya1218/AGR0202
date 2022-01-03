package action.nonmember;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.ChildDAO;
import dao.MemberDAO;
import dao.PharmacyDAO;
import dao.QuestionnaireDAO;
import model.ChildBean;
import model.MemberBean;
import model.PharmacyBean;
import model.QuestionnaireBean;
import util.Gmail;
import util.SHA256;
import util.XssFilter;


public class U02 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		XssFilter xssFilter = XssFilter.getInstance();
		String email = xssFilter.stripTagAll(request.getParameter("email").replaceAll("\\s", ""));
		String pw = xssFilter.stripTagAll(request.getParameter("password").replaceAll("\\s", ""));
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		String patternPw = "^[A-Za-z0-9]{8,64}$";
		String patternEmail = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
		if(!email.matches(patternEmail)) {
			forward.setErrorMsg("正しいメールアドレスを入力してください。");
			return forward;
		}
		if(!pw.matches(patternPw)) {
			forward.setErrorMsg("パスワードは英数字のみ8桁以上を入力してください。");
			return forward;
		}
		//returnは member doctor hospital admin pharmacy
		String who = memberDAO.login(email, pw);
		
		if("member".equals(who)) {
			if(!memberDAO.isAuth(email)) {
				//F5押すことでメールが送信されるからそれの防止の為
				if(email != null && !"".equals(email) && pw != null && !"".equals(pw)) {
					Gmail gmail = Gmail.getInstance();
					if(!gmail.sendAuthMail(email)) {
						forward.setErrorMsg("認証メール送信が失敗しました。問い合わせしてください。");
						return forward;
					}
					session.setAttribute("authToken",SHA256.getEncrypt(email));
					forward.setErrorMsg("該当するメールアドレスは認証されていません。\\n認証メールを送信しました。メールアドレス認証を行ってください。");
					return forward;
				}
			}
			//controllerで認証のために使う
			session.setAttribute("who", who);
			
			MemberBean member = memberDAO.getMemberBean(email);
			member.setAge(memberDAO.countBirth(member.getM_birth()));
			
			ChildDAO childDAO = ChildDAO.getInstance();
			List<ChildBean> child = childDAO.getChildList(member.getM_num());
			int countChild = 0;
			if(!child.isEmpty()) {
				for(ChildBean c : child) {
					c.setAge(childDAO.countBirth(c.getC_birth()));
					countChild++;
				}
				session.setAttribute("child", child);
			}
			
			member.setM_children(String.valueOf(countChild));
			
			//メール内のURLで使ったTokenを万が一ある場合を考え、あれば消す。
			if(session.getAttribute("authToken") != null) session.removeAttribute("authToken");
			session.setAttribute("beforeHashEmail", email); //メールを送信する時必要
			session.setAttribute("member", member);
			
			QuestionnaireBean questionnaire = QuestionnaireDAO.getInstance().getQuestionnaire(member.getM_num());
			session.setAttribute("questionnaire", questionnaire);
			
			forward.setPath("index");
			return forward;
		}
		
		PharmacyDAO pharmacyDAO = PharmacyDAO.getInstance();	
		who = pharmacyDAO.login(email, pw);
		if("pharmacy".equals(who)) {
			PharmacyBean pharmacy = pharmacyDAO.getPharmacyBean(email);
			session.setAttribute("who", who);
			session.setAttribute("pharmacy", pharmacy);
			forward.setPath("index");
			return forward;
		}
		
		forward.setErrorMsg("メールアドレス又はパスワードが間違いです。もう一度確認してください。");
		return forward;
	}

}
