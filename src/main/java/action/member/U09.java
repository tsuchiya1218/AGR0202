package action.member;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import model.MemberBean;

public class U09 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);
		MemberBean member = (MemberBean) session.getAttribute("member");
		String path = "C:\\Users\\20jy0211\\git\\AGR0202\\src\\main\\webapp\\static\\img\\qr_code\\";
		String fileName = member.getM_qr_num()+".png";
		String savefileName = "My QRコード.png";
		
		File file = null;
		BufferedInputStream is = null;
		BufferedOutputStream os = null;
		boolean skip = false;

		file = new File(path, fileName);
		int leng = (int) file.length();
		try {
	        try{
	        	response.reset();
	        	response.setContentType( "application/download; UTF-8" );
	            response.setContentLength(leng);
	            response.setHeader("Content-Type", "application/octet-stream");                
	            response.setHeader("Content-Transfer-Encoding", "binary;");
	            response.setHeader("Pragma", "no-cache;");
	            response.setHeader("Expires", "-1;");
	            is = new BufferedInputStream(new FileInputStream(file));
	            os = new BufferedOutputStream(response.getOutputStream());
	        }catch(FileNotFoundException fe){
	            skip = true;
	            forward.setErrorMsg("ダウンロードできませんでした。");
	            return forward;
	        }
	        if(!skip) {
	        	setDisposition(savefileName, request, response);
	        	byte[] b = new byte[2048];
	        	while((leng = is.read(b)) != -1){
	        		os.write(b,0,leng);
	        	}
	        	
	        	is.close();
	        	os.close();
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		forward.setPath("u09");
		forward.setMsg("My QRコードをダウンロードしました。");
		return forward;
	}
	
	
	void setDisposition(String savefileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);
		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(savefileName, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(savefileName.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(savefileName.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < savefileName.length(); i++) {
				char c = savefileName.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}

	}
	
	private String getBrowser(HttpServletRequest request) {
	      String header = request.getHeader("User-Agent");
	      if (header.indexOf("MSIE") > -1) {
	           return "MSIE";
	      } else if (header.indexOf("Chrome") > -1) {
	           return "Chrome";
	      } else if (header.indexOf("Opera") > -1) {
	           return "Opera";
	      } else if (header.indexOf("Firefox") > -1) {
	           return "Firefox";
	      } else if (header.indexOf("Mozilla") > -1) {
	           if (header.indexOf("Firefox") > -1) {
	                return "Firefox";
	           }else{
	                return "MSIE";
	           }
	      }
	      return "MSIE";
	 }


}
