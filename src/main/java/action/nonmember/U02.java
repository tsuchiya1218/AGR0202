package action.nonmember;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.AdminDAO;
import dao.ChildDAO;
import dao.DoctorDAO;
import dao.HospitalDAO;
import dao.MemberDAO;
import dao.PharmacyDAO;
import dao.QuestionnaireDAO;
import model.AdminBean;
import model.ChildBean;
import model.DoctorBean;
import model.HospitalBean;
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
			
			MemberBean member = memberDAO.getMemberBeanByEmail(email);
			member.setAge(memberDAO.countBirth(member.getM_birth()));
			
			ChildDAO childDAO = ChildDAO.getInstance();
			List<ChildBean> children = childDAO.getChildList(member.getM_num());
			int countChild = 0;
			if(!children.isEmpty()) {
				for(ChildBean c : children) {
					c.setAge(childDAO.countBirth(c.getC_birth()));
					countChild++;
				}
				session.setAttribute("child", children);
			}
			
			member.setChildren_count(countChild);
			
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
		
		DoctorDAO doctorDAO = DoctorDAO.getInstance();
		who = doctorDAO.login(email, pw);
		if("doctor".equals(who)) {
			if(!doctorDAO.isAuth(email)) {
				if(email != null && !"".equals(email) && pw != null && !"".equals(pw)) {
					Gmail gmail = Gmail.getInstance();
					if(!gmail.sendAuthMailToDoctor(email)) {
						forward.setErrorMsg("認証メール送信が失敗しました。問い合わせしてください。");
						return forward;
					}
					session.setAttribute("authToken",SHA256.getEncrypt(email));
					forward.setErrorMsg("該当するメールアドレスは認証されていません。\\n認証メールを送信しました。メールアドレス認証を行ってください。");
					return forward;
				}
			}
			DoctorBean doctor = doctorDAO.getDoctorBean(email);
			session.setAttribute("beforeHashEmail", email);
			session.setAttribute("who", who);
			session.setAttribute("doctor", doctor);
			forward.setPath("index");
			return forward;
		}
		
		HospitalDAO hospitalDAO = HospitalDAO.getInstance();
		who = hospitalDAO.login(email, pw);
		if("hospital".equals(who)) {
			HospitalBean hospital = hospitalDAO.getHospitalBean(email);
			session.setAttribute("who", who);
			session.setAttribute("hospital", hospital);
			forward.setPath("index");
			return forward;
		}
		
		AdminDAO adminDAO = AdminDAO.getInstance();
		who = adminDAO.login(email, pw);
		if("admin".equals(who)) {
			AdminBean admin = adminDAO.getAdminBean(email);
			session.setAttribute("who", who);
			session.setAttribute("admin", admin);
			forward.setPath("index");
			return forward;
		}
		
		forward.setErrorMsg("メールアドレス又はパスワードが間違いです。もう一度確認してください。");
		return forward;
	}

}
