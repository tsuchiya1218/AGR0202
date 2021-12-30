package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import action.common.U04;
import action.member.U05;
import action.member.U06;
import action.member.U06_s1;
import action.member.U06_s2_01;
import action.member.U07_02;
import action.member.U07_03;
import action.member.U07_s1;
import action.member.U07_s2;

@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ポリモーフィズムのため action
	private Map<String, Action> contList = new HashMap<>();
	@Override
	public void init(ServletConfig config) throws ServletException {
		/*
		 	一回 Servletインスタンスが生産されたら Servletインスタンスはメモリに保存されている。
		 	それで、初めて実行する時だけ init()メソッドを呼び出すことで、処理速度が早くなる。
		*/
		super.init(config);
		contList.put("u04",  new U04());
		contList.put("u05",  new U05());
		contList.put("u06",  new U06());
		contList.put("u06_s1",  new U06_s1());
		contList.put("u06_s2_01",  new U06_s2_01());
		contList.put("u07_02",  new U07_02());
		contList.put("u07_03",  new U07_03());
		contList.put("u07_s1",  new U07_s1());
		contList.put("u07_s2",  new U07_s2());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession(true);
		PrintWriter writer = response.getWriter();
		String action = request.getParameter("action");
		String view = request.getParameter("view");
		// パスを決めるため forward
		ActionForward forward = new ActionForward();
		String path = "/WEB-INF/view/member/";
		
		if("member".equals(session.getAttribute("who"))) {
			try {
				if (action != null) {
					forward = contList.get(action).execute(request, response);
					if(forward.getErrorMsg() == null) {
						request.setAttribute("nav", forward.getPath().substring(0, 3));
						if(forward.getMsg() != null) {
							writer.println("<script type='text/javascript'>");
							writer.println("alert('"+forward.getMsg()+"');");
							writer.println("</script>");
						}
						if ("index".equals(forward.getPath())) {
							response.sendRedirect("index.jsp");
						} else {
							dispatcher = request.getRequestDispatcher(path + forward.getPath() + ".jsp");
							dispatcher.forward(request, response);
						}
					}else {
						writer.println("<script type='text/javascript'>");
						writer.println("alert('"+forward.getErrorMsg()+"');");
						writer.println("history.back();");
						writer.println("</script>");
					}
				} else {
					request.setAttribute("nav", view == null ? "index" : view.substring(0, 3));
					if ("index".equals(view)) {
						response.sendRedirect("index.jsp");
					} else {
						forward.setPath(path + view + ".jsp"); // webapp/web-inf/view/member/
						dispatcher = request.getRequestDispatcher(forward.getPath());
						dispatcher.forward(request, response);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {	
			writer.println("<script type='text/javascript'>");
			writer.println("alert('権限がありません。');");
			writer.println("</script>");
			writer.close();
		}
	}

}
