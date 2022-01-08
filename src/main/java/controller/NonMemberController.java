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
import action.nonmember.AllowToAuthToDoctor;
import action.nonmember.U01_01;
import action.nonmember.U01_02;
import action.nonmember.U01_04;
import action.nonmember.U02;
import action.nonmember.U03_01;
import action.nonmember.U03_02;
import action.nonmember.U03_03;

@WebServlet("/NonMemberController")
public class NonMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ポリモーフィズムのため action
	private Map<String, Action> contList = new HashMap<>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		/*
		 * 一回 Servletインスタンスが生産されたら Servletインスタンスはメモリに保存されている。 それで、初めて実行する時だけ
		 * init()メソッドを呼び出すことで、処理速度が早くなる。
		 */
		super.init(config);
		contList.put("u01_01", new U01_01());
		contList.put("u01_02", new U01_02());
		contList.put("u01_04", new U01_04());
		contList.put("u02", new U02());
		contList.put("u03_01", new U03_01());
		contList.put("u03_02", new U03_02());
		contList.put("u03_03", new U03_03());
		contList.put("AllowToAuthToDoctor", new AllowToAuthToDoctor());
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
		String path = "/WEB-INF/view/non_member/";
		if (session.getAttribute("who") == null) {
			try {
				if (action != null) {
					forward = contList.get(action).execute(request, response);
					if(!forward.isRedirectToAction()) {
						if(forward.getErrorMsg() == null) {
							request.setAttribute("nav", forward.getPath().substring(0, 3));
							if(forward.getMsg() != null) {
								writer.println("<script type='text/javascript'>");
								writer.println("alert('"+forward.getMsg()+"');");
								writer.println("location='NonMemberController?view="+forward.getPath()+"';");
								writer.println("</script>");
							}else {
								if("index".equals(forward.getPath())) {
									dispatcher = request.getRequestDispatcher(forward.getPath() + ".jsp");
								}else {
									dispatcher = request.getRequestDispatcher(path + forward.getPath() + ".jsp");
								}
								dispatcher.forward(request, response);
							}
						}else {
							writer.println("<script type='text/javascript'>");
							writer.println("alert('"+forward.getErrorMsg()+"');");
							writer.println("history.back();");
							writer.println("</script>");
						}
					}else {
						writer.println("<script type='text/javascript'>");
						writer.println("alert('"+forward.getMsg()+"');");
						writer.println("location='NonMemberController?action="+forward.getPath()+"';");
						writer.println("</script>");
					}
				} else {
					request.setAttribute("nav", view == null ? "index" : view.substring(0, 3));
					if ("index".equals(view)) {
						response.sendRedirect("index.jsp");
					} else {
						forward.setPath(path + view + ".jsp");
						dispatcher = request.getRequestDispatcher(forward.getPath());
						dispatcher.forward(request, response);
					}
				}
			} catch(NullPointerException e) {
				writer.println("<script type='text/javascript'>");
				writer.println("alert('正しくないリクエストです。');");
				writer.println("history.back();");
				writer.println("</script>");
				writer.close();
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('エラーが発生しました。');");
				writer.println("history.back();");
				writer.println("</script>");
				writer.close();
			}
		} else {
			writer.println("<script type='text/javascript'>");
			writer.println("alert('権限がありません。\\nもう一度、ログインしてください。');");
			writer.println("location='NonMemberController?view=02';");
			writer.println("</script>");
			writer.close();
			session.invalidate();
		}
	}
}
