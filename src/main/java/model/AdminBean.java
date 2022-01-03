package model;

import java.io.Serializable;

public class AdminBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String admin_email;
	private String admin_pw;
	
	public AdminBean() {}
	
	public AdminBean(String admin_email, String admin_pw) {
		this.admin_email = admin_email;
		this.admin_pw = admin_pw;
	}

	public String getAdmin_email() {
		return admin_email;
	}

	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}

	public String getAdmin_pw() {
		return admin_pw;
	}

	public void setAdmin_pw(String admin_pw) {
		this.admin_pw = admin_pw;
	}

	@Override
	public String toString() {
		return "AdminBean [admin_email=" + admin_email + ", admin_pw=" + admin_pw + "]";
	}

}
