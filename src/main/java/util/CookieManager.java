package util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
	private static CookieManager ck_manager = new CookieManager();
	private CookieManager() {}
	public static CookieManager getInstance() {
		return ck_manager;
	}
	
	public void setCookie(HttpServletResponse res, String key ,String value){
	    Cookie cookie = new Cookie(key, value); 
	    cookie.setMaxAge(60*60*24); //有効期限: １日(60秒 * 60分 * 24시時間)
	    cookie.setPath("/"); //全てのパスからアクセスできるように設定
	    res.addCookie(cookie);
	}
	
	public String getCookie(HttpServletRequest req, String key){
	    Cookie[] cookies=req.getCookies();
	    if(cookies!=null){
	        for (Cookie c : cookies) {
	            if (c.getName().equals(key)) {
	                return c.getValue();
	            }
	        }
	    }
	    return null;
	}
	
	public void deleteCookie(HttpServletResponse res,String key){
	    Cookie cookie = new Cookie(key, null); // 削除するcookieの値をnullにする
	    cookie.setMaxAge(0); 
	    res.addCookie(cookie);
	}
	
	public void deleteAllCookies(HttpServletRequest req,HttpServletResponse res) {
	    Cookie[] cookies = req.getCookies(); // 全てのcookie情報を cookiesに 保存
	    if (cookies != null) { //cookieが一つでもあれば
	        for (int i = 0; i < cookies.length; i++) {
	            cookies[i].setMaxAge(0);
	            res.addCookie(cookies[i]); // 有効時間を0にした後、レスポンスに追加する そしたら使えなくなる
	        }
	    }
	}
}
