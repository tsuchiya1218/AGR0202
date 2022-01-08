package util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Gmail {
	private Gmail() {}
	
	private static class LazyHolder {
		public static final Gmail INSTANCE = new Gmail();
	}

	public static Gmail getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	private String dbName = "gr202/";
	private String host = "http://localhost:14276/";
	private String from = "cocojycompany@gmail.com";

	//if m_auth == 0場合これを送る
	public boolean sendAuthMail(String email) {
		String to = email;
		String subject = "COCO薬局 ユーザー認証メール";
		
		String content = "この度は、COCO薬局にお申し込み頂きまして誠にありがとうございます。<br>"
				+ "ご本人様確認のため、下記URLへアクセスしアカウントの本登録を完了させて下さい。<br>"
				+ "<br>"
				+ "<br>"
				+ "URL： "+ host + dbName +"NonMemberController?action=u01_04&email="+SHA256.getEncrypt(to)
				+ "<br>"
				+ "<br>"
				+ "※当メールに心当たりの無い場合は、誠に恐れ入りますが破棄して頂けますよう、よろしくお願い致　します。<br>"
				+ "＊このメールは自動送信しておりますので、お問い合わせはホームページよりお願いいたします。<br>"
				+ "<br>"
				+ "<br>"
				+ "ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー<br>"
				+ "<br>"
				+ "株式会社COCO薬局</br>"
				+ "<br>"
				+ "URL："+host + dbName +"index.jsp"
				+"<br>"
				+ "住所：〒160-0023　東京都新宿区西新宿5-6-7<br>"
				+ "TEL ：02-1234-5678";
		
		/* SMTPに　接続 */
		Properties p = new Properties();
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.connectiontimeout", "1000");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.timeout", "1000"); 
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		/* 利用者に管理者のメールアドレスを使って送る */
		try{
			Authenticator auth = new CompanyGmail(); 
			Session ses = Session.getInstance(p, auth); 
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses); //MimeMessaheを利用しメールを送る
			Address fromAddr = new InternetAddress(from);
			Address toAddr = new InternetAddress(to); 
			msg.setSubject(subject); //メールのタイトル
			msg.setFrom(fromAddr); //受信者の情報
			msg.addRecipient(Message.RecipientType.TO, toAddr); //受験者の情報
			msg.setContent(content,"text/html; charset=UTF-8"); //メール内容
			Transport t = ses.getTransport("smtp");
			t.connect();
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
			return true;
		}catch(MessagingException e) {
			e.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sendAuthMailToDoctor(String email) {
		String to = email;
		String subject = "COCO薬局 ユーザー認証メール";
		
		String content = "この度は、COCO薬局にお申し込み頂きまして誠にありがとうございます。<br>"
				+ "ご本人様確認のため、下記URLへアクセスしアカウントの本登録を完了させて下さい。<br>"
				+ "<br>"
				+ "<br>"
				+ "URL： "+ host + dbName +"NonMemberController?action=AllowToAuthToDoctor&email="+SHA256.getEncrypt(to)
				+ "<br>"
				+ "<br>"
				+ "※当メールに心当たりの無い場合は、誠に恐れ入りますが破棄して頂けますよう、よろしくお願い致　します。<br>"
				+ "＊このメールは自動送信しておりますので、お問い合わせはホームページよりお願いいたします。<br>"
				+ "<br>"
				+ "<br>"
				+ "ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー<br>"
				+ "<br>"
				+ "株式会社COCO薬局</br>"
				+ "<br>"
				+ "URL："+host + dbName +"index.jsp"
				+"<br>"
				+ "住所：〒160-0023　東京都新宿区西新宿5-6-7<br>"
				+ "TEL ：02-1234-5678";
		
		/* SMTPに　接続 */
		Properties p = new Properties();
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.connectiontimeout", "1000");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.timeout", "1000"); 
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		/* 利用者に管理者のメールアドレスを使って送る */
		try{
			Authenticator auth = new CompanyGmail(); 
			Session ses = Session.getInstance(p, auth); 
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses); //MimeMessaheを利用しメールを送る
			Address fromAddr = new InternetAddress(from);
			Address toAddr = new InternetAddress(to); 
			msg.setSubject(subject); //メールのタイトル
			msg.setFrom(fromAddr); //受信者の情報
			msg.addRecipient(Message.RecipientType.TO, toAddr); //受験者の情報
			msg.setContent(content,"text/html; charset=UTF-8"); //メール内容
			Transport t = ses.getTransport("smtp");
			t.connect();
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
			return true;
		}catch(MessagingException e) {
			e.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sendSingUpMailToDoctor(String email,String name) {
		String to = email;
		String subject = "COCO薬局 会員登録完了のお知らせ";
		
		String content = name+"\t様<br>"
				+ "COCO薬局での会員登録が完了しました。<br>"
				+ "<br>"
				+ "この度は会員登録ありがとうございます。<br>"
				+ "このメールは、ご登録時に確認のため送信させていただいております。<br>"
				+ "ご本人様確認のため、下記URLへアクセスしアカウントの本登録を完了させて下さい。<br>"
				+ "<br>"
				+ "URL："+ host + dbName +"NonMemberController?action=AllowToAuthToDoctor&email="+SHA256.getEncrypt(to)
				+ "<br>"
				+ "<br>"
				+ "＊記載した情報以外の登録情報は、マイページからご確認ください。（ログインが必要です）<br>"
				+ "＊このメールに心当たりがない方、またはご不明な点がある方はお問い合わせまでお問合わせください。<br>"
				+ "＊パスワードを忘れた方はこちら（"+host + dbName +"NonMemberController?view=u03_01）から再発行ができます。<br>"
				+ "＊このメールは自動送信しておりますので、お問い合わせはホームページよりお願いいたします。<br>"
				+ "<br>"
				+ "<br>"
				+ "ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー<br>"
				+ "<br>"
				+ "株式会社COCO薬局</br>"
				+ "<br>"
				+ "URL："+host + dbName +"index.jsp"
				+"<br>"
				+ "住所：〒160-0023　東京都新宿区西新宿5-6-7<br>"
				+ "TEL ：02-1234-5678";
		
		/* SMTPに　接続 */
		Properties p = new Properties();
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.connectiontimeout", "1000");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.timeout", "1000"); 
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		/* 利用者に管理者のメールアドレスを使って送る */
		try{
			Authenticator auth = new CompanyGmail(); 
			Session ses = Session.getInstance(p, auth); 
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses); //MimeMessaheを利用しメールを送る
			Address fromAddr = new InternetAddress(from);
			Address toAddr = new InternetAddress(to); 
			msg.setSubject(subject); //メールのタイトル
			msg.setFrom(fromAddr); //受信者の情報
			msg.addRecipient(Message.RecipientType.TO, toAddr); //受験者の情報
			msg.setContent(content,"text/html; charset=UTF-8"); //メール内容
			Transport t = ses.getTransport("smtp");
			t.connect();
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
			return true;
		}catch(MessagingException e) {
			e.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean sendLeaveMail(String email,String name) {
		String to = email;
		String subject = "COCO薬局 退会完了のお知らせ";
		
		String content = name+"\t様<br>"
				+ "平素はCOCO薬局をご利用頂き、誠にありがとうございました。<br>"
				+ "COCO薬局の退会手続きが完了しましたので、お知らせいたします。<br>"
				+ "<br>"
				+ "またの機会がありましたら、ご利用よろしくお願い申し上げます。<br>"
				+ "登録したことがない、退会手続きを間違えてしまった場合は下記までご連絡下さい。<br>"
				+ "＊このメールは自動送信しておりますので、お問い合わせはホームページよりお願いいたします。<br>"
				+ "<br>"
				+ "<br>"
				+ "ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー<br>"
				+ "<br>"
				+ "株式会社COCO薬局</br>"
				+ "<br>"
				+ "URL："+host + dbName +"index.jsp"
				+"<br>"
				+ "住所：〒160-0023　東京都新宿区西新宿5-6-7<br>"
				+ "TEL ：02-1234-5678";
		
		
		/* SMTPに　接続 */
		Properties p = new Properties();
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.connectiontimeout", "1000");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.timeout", "1000"); 
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		/* 利用者に管理者のメールアドレスを使って送る */
		try{
			Authenticator auth = new CompanyGmail(); 
			Session ses = Session.getInstance(p, auth); 
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses); //MimeMessaheを利用しメールを送る
			Address fromAddr = new InternetAddress(from);
			Address toAddr = new InternetAddress(to); 
			msg.setSubject(subject); //メールのタイトル
			msg.setFrom(fromAddr); //受信者の情報
			msg.addRecipient(Message.RecipientType.TO, toAddr); //受験者の情報
			msg.setContent(content,"text/html; charset=UTF-8"); //メール内容
			Transport t = ses.getTransport("smtp");
			t.connect();
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();	
			return true;
		}catch(MessagingException e) {
			e.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sendSingUpMail(String m_email,String name) {
		String to = m_email;
		String subject = "COCO薬局 会員登録完了のお知らせ";
		
		String content = name+"\t様<br>"
				+ "COCO薬局での会員登録が完了しました。<br>"
				+ "<br>"
				+ "この度は会員登録ありがとうございます。<br>"
				+ "このメールは、ご登録時に確認のため送信させていただいております。<br>"
				+ "ご本人様確認のため、下記URLへアクセスしアカウントの本登録を完了させて下さい。<br>"
				+ "<br>"
				+ "URL："+ host + dbName +"NonMemberController?action=u01_04&email="+SHA256.getEncrypt(to)
				+ "<br>"
				+ "<br>"
				+ "＊記載した情報以外の登録情報は、マイページからご確認ください。（ログインが必要です）<br>"
				+ "＊このメールに心当たりがない方、またはご不明な点がある方はお問い合わせまでお問合わせください。<br>"
				+ "＊パスワードを忘れた方はこちら（"+host + dbName +"NonMemberController?view=u03_01）から再発行ができます。<br>"
				+ "＊このメールは自動送信しておりますので、お問い合わせはホームページよりお願いいたします。<br>"
				+ "<br>"
				+ "<br>"
				+ "ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー<br>"
				+ "<br>"
				+ "株式会社COCO薬局</br>"
				+ "<br>"
				+ "URL："+host + dbName +"index.jsp"
				+"<br>"
				+ "住所：〒160-0023　東京都新宿区西新宿5-6-7<br>"
				+ "TEL ：02-1234-5678";
		
		/* SMTPに　接続 */
		Properties p = new Properties();
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.connectiontimeout", "1000");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.timeout", "1000"); 
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		/* 利用者に管理者のメールアドレスを使って送る */
		try{
			Authenticator auth = new CompanyGmail(); 
			Session ses = Session.getInstance(p, auth); 
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses); //MimeMessaheを利用しメールを送る
			Address fromAddr = new InternetAddress(from);
			Address toAddr = new InternetAddress(to); 
			msg.setSubject(subject); //メールのタイトル
			msg.setFrom(fromAddr); //受信者の情報
			msg.addRecipient(Message.RecipientType.TO, toAddr); //受験者の情報
			msg.setContent(content,"text/html; charset=UTF-8"); //メール内容
			Transport t = ses.getTransport("smtp");
			t.connect();
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
			return true;
		}catch(MessagingException e) {
			e.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean sendChangePasswordMail(String email,String name) {
		String to = email;
		String subject = "COCO薬局 パスワード再設定メール";
		
		String content = name+"\t様<br>"
				+ "COCO薬局のパスワード再設定メールです。<br>"
				+ "下記URLへアクセスし、パスワードを再設定して下さい。<br>"
				+ "<br>"
				+ "<br>"
				+ "URL："+ host + dbName +"NonMemberController?action=u03_02&email="+SHA256.getEncrypt(to)
				+ "<br>"
				+ "<br>"
				+ "※当メールに心当たりの無い場合は、誠に恐れ入りますが破棄して頂けますよう、よろしくお願い致　します。<br>"
				+ "＊このメールは自動送信しておりますので、お問い合わせはホームページよりお願いいたします。<br>"
				+ "<br>"
				+ "<br>"
				+ "ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー<br>"
				+ "<br>"
				+ "株式会社COCO薬局</br>"
				+ "<br>"
				+ "URL："+host + dbName +"index.jsp"
				+"<br>"
				+ "住所：〒160-0023　東京都新宿区西新宿5-6-7<br>"
				+ "TEL ：02-1234-5678";
		
		/* SMTPに　接続 */
		Properties p = new Properties();
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.connectiontimeout", "1000");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.timeout", "1000"); 
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		/* 利用者に管理者のメールアドレスを使って送る */
		try{
			Authenticator auth = new CompanyGmail(); 
			Session ses = Session.getInstance(p, auth); 
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses); //MimeMessaheを利用しメールを送る
			Address fromAddr = new InternetAddress(from);
			Address toAddr = new InternetAddress(to); 
			msg.setSubject(subject); //メールのタイトル
			msg.setFrom(fromAddr); //受信者の情報
			msg.addRecipient(Message.RecipientType.TO, toAddr); //受験者の情報
			msg.setContent(content,"text/html; charset=UTF-8"); //メール内容
			Transport t = ses.getTransport("smtp");
			t.connect();
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
			return true;
		}catch(MessagingException e) {
			e.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sendUpdateNotice(String m_email,String name) {
		String to = m_email;
		String subject = "COCO薬局 個人情報の変更完了のお知らせ";
		
		String content = name+"\t様<br>"
				+ "COCO薬局でのお客様の個人情報が変更されました。マイページからご確認できます。<br>"
				+ "ログイン："+host+dbName+"NonMemberController?view=u02"
				+ "<br>"
				+ "<br>"
				+ "※当メールに心当たりの無い場合は、誠に恐れ入りますが破棄して頂けますよう、よろしくお願い致　します。<br>"
				+ "＊このメールは自動送信しておりますので、お問い合わせはホームページよりお願いいたします。<br>"
				+ "<br>"
				+ "<br>"
				+ "ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー<br>"
				+ "<br>"
				+ "株式会社COCO薬局</br>"
				+ "<br>"
				+ "URL："+host + dbName +"index.jsp"
				+"<br>"
				+ "住所：〒160-0023　東京都新宿区西新宿5-6-7<br>"
				+ "TEL ：02-1234-5678";
		
		/* SMTPに　接続 */
		Properties p = new Properties();
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.connectiontimeout", "1000");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.timeout", "1000"); 
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		/* 利用者に管理者のメールアドレスを使って送る */
		try{
			Authenticator auth = new CompanyGmail(); 
			Session ses = Session.getInstance(p, auth); 
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses); //MimeMessaheを利用しメールを送る
			Address fromAddr = new InternetAddress(from);
			Address toAddr = new InternetAddress(to); 
			msg.setSubject(subject); //メールのタイトル
			msg.setFrom(fromAddr); //受信者の情報
			msg.addRecipient(Message.RecipientType.TO, toAddr); //受験者の情報
			msg.setContent(content,"text/html; charset=UTF-8"); //メール内容
			Transport t = ses.getTransport("smtp");
			t.connect();
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
			return true;
		}catch(MessagingException e) {
			e.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sendUpdatePasswordNotice(String email,String name) {
		String to = email;
		String subject = "COCO薬局 パスワードの変更のお知らせ";
		
		String content = name+"\t様<br>"
				+ "COCO薬局でのお客様のパスワードが変更されました。マイページからご確認できます。<br>"
				+ "ログイン："+host+dbName+"NonMemberController?view=u02"
				+ "<br>"
				+ "<br>"
				+ "※当メールに心当たりの無い場合は、誠に恐れ入りますが破棄して頂けますよう、よろしくお願い致　します。<br>"
				+ "＊このメールは自動送信しておりますので、お問い合わせはホームページよりお願いいたします。<br>"
				+ "<br>"
				+ "<br>"
				+ "ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー<br>"
				+ "<br>"
				+ "株式会社COCO薬局</br>"
				+ "<br>"
				+ "URL："+host + dbName +"index.jsp"
				+"<br>"
				+ "住所：〒160-0023　東京都新宿区西新宿5-6-7<br>"
				+ "TEL ：02-1234-5678";
		
		/* SMTPに　接続 */
		Properties p = new Properties();
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.connectiontimeout", "1000");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.timeout", "1000"); 
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		/* 利用者に管理者のメールアドレスを使って送る */
		try{
			Authenticator auth = new CompanyGmail(); 
			Session ses = Session.getInstance(p, auth); 
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses); //MimeMessaheを利用しメールを送る
			Address fromAddr = new InternetAddress(from);
			Address toAddr = new InternetAddress(to); 
			msg.setSubject(subject); //メールのタイトル
			msg.setFrom(fromAddr); //受信者の情報
			msg.addRecipient(Message.RecipientType.TO, toAddr); //受験者の情報
			msg.setContent(content,"text/html; charset=UTF-8"); //メール内容
			Transport t = ses.getTransport("smtp");
			t.connect();
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
			return true;
		}catch(MessagingException e) {
			e.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sendChildUpdateNotice(String m_email,String name) {
		String to = m_email;
		String subject = "COCO薬局 子供情報の変更完了のお知らせ";
		
		String content = name+"\t様<br>"
				+ "COCO薬局でのお客様の子供情報が変更されました。"
				+ "子供情報："+host+dbName+"MemberController?view=u07_01"
				+ "<br>"
				+ "<br>"
				+ "※当メールに心当たりの無い場合は、誠に恐れ入りますが破棄して頂けますよう、よろしくお願い致　します。<br>"
				+ "＊このメールは自動送信しておりますので、お問い合わせはホームページよりお願いいたします。<br>"
				+ "<br>"
				+ "<br>"
				+ "ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー<br>"
				+ "<br>"
				+ "株式会社COCO薬局</br>"
				+ "<br>"
				+ "URL："+host + dbName +"index.jsp"
				+"<br>"
				+ "住所：〒160-0023　東京都新宿区西新宿5-6-7<br>"
				+ "TEL ：02-1234-5678";
		
		/* SMTPに　接続 */
		Properties p = new Properties();
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.connectiontimeout", "1000");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.timeout", "1000"); 
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		/* 利用者に管理者のメールアドレスを使って送る */
		try{
			Authenticator auth = new CompanyGmail(); 
			Session ses = Session.getInstance(p, auth); 
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses); //MimeMessaheを利用しメールを送る
			Address fromAddr = new InternetAddress(from);
			Address toAddr = new InternetAddress(to); 
			msg.setSubject(subject); //メールのタイトル
			msg.setFrom(fromAddr); //受信者の情報
			msg.addRecipient(Message.RecipientType.TO, toAddr); //受験者の情報
			msg.setContent(content,"text/html; charset=UTF-8"); //メール内容
			Transport t = ses.getTransport("smtp");
			t.connect();
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
			return true;
		}catch(MessagingException e) {
			e.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sendUpdateEmailNotice(String m_email,String name) {
		String to = m_email;
		String subject = "COCO薬局 メールアドレスの変更完了のお知らせ";
		
		String content = name+"\t様<br>"
				+ "COCO薬局でのお客様のメールアドレスが変更されましたので、再ログインしてご確認ください。<br>"
				+ "ログイン："+host+dbName+"NonMemberController?view=u02"
				+ "<br>"
				+ "<br>"
				+ "※当メールに心当たりの無い場合は、誠に恐れ入りますが破棄して頂けますよう、よろしくお願い致　します。<br>"
				+ "＊このメールは自動送信しておりますので、お問い合わせはホームページよりお願いいたします。<br>"
				+ "<br>"
				+ "<br>"
				+ "ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー<br>"
				+ "<br>"
				+ "株式会社COCO薬局</br>"
				+ "<br>"
				+ "URL："+host + dbName +"index.jsp"
				+"<br>"
				+ "住所：〒160-0023　東京都新宿区西新宿5-6-7<br>"
				+ "TEL ：02-1234-5678";
		
		/* SMTPに　接続 */
		Properties p = new Properties();
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.connectiontimeout", "1000");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.timeout", "1000"); 
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		/* 利用者に管理者のメールアドレスを使って送る */
		try{
			Authenticator auth = new CompanyGmail(); 
			Session ses = Session.getInstance(p, auth); 
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses); //MimeMessaheを利用しメールを送る
			Address fromAddr = new InternetAddress(from);
			Address toAddr = new InternetAddress(to); 
			msg.setSubject(subject); //メールのタイトル
			msg.setFrom(fromAddr); //受信者の情報
			msg.addRecipient(Message.RecipientType.TO, toAddr); //受験者の情報
			msg.setContent(content,"text/html; charset=UTF-8"); //メール内容
			Transport t = ses.getTransport("smtp");
			t.connect();
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
			return true;
		}catch(MessagingException e) {
			e.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
