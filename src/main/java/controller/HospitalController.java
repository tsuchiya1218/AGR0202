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
import action.hospital.U12_01;
import action.hospital.U12_02;

/**
 * Servlet implementation class HospitalController
 */
@WebServlet("/HospitalController")
public class HospitalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Action> contList = new HashMap<>();
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		contList.put("u04",  new U04());
		contList.put("u12_01",  new U12_01());
		contList.put("u12_02",  new U12_02());
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
		
		ActionForward forward = new ActionForward();
		String path = "/WEB-INF/view/hospital/";
		
		if("hospital".equals(session.getAttribute("who"))) {
			try {
				if (action != null) {
					forward = contList.get(action).execute(request, response);
					if(!forward.isRedirectToAction()) {
						if(forward.getErrorMsg() == null) {
							request.setAttribute("nav", forward.getPath().substring(0, 3));
							if(forward.getMsg() != null) {
								writer.println("<script type='text/javascript'>");
								writer.println("alert('"+forward.getMsg()+"');");
								writer.println("location='HospitalController?view="+forward.getPath()+"';");
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
						writer.println("location='HospitalController?action="+forward.getPath()+"';");
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
			}catch(Exception e) {
				writer.println("<script type='text/javascript'>");
				writer.println("alert('正しくないリクエストです。');");
				writer.println("history.back();");
				writer.println("</script>");
				writer.close();
				e.printStackTrace();
			}
		}else {	
			writer.println("<script type='text/javascript'>");
			writer.println("alert('権限がありません。');");
			writer.println("history.back();");
			writer.println("</script>");
			writer.close();
		}
	}
}
