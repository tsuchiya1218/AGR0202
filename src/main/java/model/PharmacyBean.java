package model;

import java.io.Serializable;

public class PharmacyBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int p_num;
	private String p_name;
	private String p_email;
	private String p_pw;
	private String p_tel;
	private String p_address;
	private boolean p_leave;
	
	public PharmacyBean(){}

	public PharmacyBean(int p_num, String p_name, String p_email, String p_pw, String p_tel, String p_address,
			boolean p_leave) {
		this.p_num = p_num;
		this.p_name = p_name;
		this.p_email = p_email;
		this.p_pw = p_pw;
		this.p_tel = p_tel;
		this.p_address = p_address;
		this.p_leave = p_leave;
	}

	public int getP_num() {
		return p_num;
	}

	public void setP_num(int p_num) {
		this.p_num = p_num;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_email() {
		return p_email;
	}

	public void setP_email(String p_email) {
		this.p_email = p_email;
	}

	public String getP_pw() {
		return p_pw;
	}

	public void setP_pw(String p_pw) {
		this.p_pw = p_pw;
	}

	public String getP_tel() {
		return p_tel;
	}

	public void setP_tel(String p_tel) {
		this.p_tel = p_tel;
	}

	public String getP_address() {
		return p_address;
	}

	public void setP_address(String p_address) {
		this.p_address = p_address;
	}

	public boolean isP_leave() {
		return p_leave;
	}

	public void setP_leave(boolean p_leave) {
		this.p_leave = p_leave;
	}

	@Override
	public String toString() {
		return "PharmacyBean [p_num=" + p_num + ", p_name=" + p_name + ", p_email=" + p_email + ", p_pw=" + p_pw
				+ ", p_tel=" + p_tel + ", p_address=" + p_address + ", p_leave=" + p_leave + "]";
	}

}
