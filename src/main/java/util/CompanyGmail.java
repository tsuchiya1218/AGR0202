package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class CompanyGmail extends Authenticator{ //인증수행을 도와주는 클래스
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("cocojycompany@gmail.com","20jy0211");
	}
}
