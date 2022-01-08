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
import action.admin.M01_01;
import action.admin.M01_02;
import action.admin.M01_s1_01;
import action.admin.M01_s1_02;
import action.admin.M01_s2;
import action.admin.M02_01;
import action.admin.M02_02;
import action.admin.M02_s1_01;
import action.admin.M02_s1_02;
import action.admin.M02_s2;
import action.admin.M02_s3;
import action.admin.M03_01;
import action.admin.M03_02;
import action.admin.M03_s1_01;
import action.admin.M03_s1_02;
import action.admin.M03_s2;
import action.admin.M03_s3;
import action.admin.M04_01;
import action.common.U04;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Map<String, Action> contList = new HashMap<>();
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		contList.put("u04",  new U04());
		contList.put("m01_01",  new M01_01());
		contList.put("m01_02",  new M01_02());
		contList.put("m01_s1_01",  new M01_s1_01());
		contList.put("m01_s1_02",  new M01_s1_02());
		contList.put("m01_s2",  new M01_s2());
		contList.put("m02_01",  new M02_01());
		contList.put("m02_02",  new M02_02());
		contList.put("m02_s1_01",  new M02_s1_01());
		contList.put("m02_s1_02", new M02_s1_02());
		contList.put("m02_s2", new M02_s2());
		contList.put("m02_s3", new M02_s3());
		contList.put("m03_01",  new M03_01());
		contList.put("m03_02",  new M03_02());
		contList.put("m03_s1_01",  new M03_s1_01());
		contList.put("m03_s1_02", new M03_s1_02());
		contList.put("m03_s2", new M03_s2());
		contList.put("m03_s3", new M03_s3());
		contList.put("m04_01", new M04_01());
		
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
		String path = "/WEB-INF/view/admin/";
		
		if("admin".equals(session.getAttribute("who"))) {
			try {
				if (action != null) {
					forward = contList.get(action).execute(request, response);
					if(!forward.isRedirectToAction()) {
						if(forward.getErrorMsg() == null) {
							request.setAttribute("nav", forward.getPath().substring(0, 3));
							if(forward.getMsg() != null) {
								writer.println("<script type='text/javascript'>");
								writer.println("alert('"+forward.getMsg()+"');");
								writer.println("location='AdminController?view="+forward.getPath()+"';");
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
						writer.println("location='AdminController?action="+forward.getPath()+"';");
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
