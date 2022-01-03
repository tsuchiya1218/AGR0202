package model;

import java.io.Serializable;

public class HospitalBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int h_num;
	private String h_name;
	private String h_email;
	private String h_pw;
	private String h_tel;
	private String h_address;
	
	public HospitalBean() {}

	public HospitalBean(int h_num, String h_name, String h_email, String h_pw, String h_tel, String h_address) {
		super();
		this.h_num = h_num;
		this.h_name = h_name;
		this.h_email = h_email;
		this.h_pw = h_pw;
		this.h_tel = h_tel;
		this.h_address = h_address;
	}

	public int getH_num() {
		return h_num;
	}

	public void setH_num(int h_num) {
		this.h_num = h_num;
	}

	public String getH_name() {
		return h_name;
	}

	public void setH_name(String h_name) {
		this.h_name = h_name;
	}

	public String getH_email() {
		return h_email;
	}

	public void setH_email(String h_email) {
		this.h_email = h_email;
	}

	public String getH_pw() {
		return h_pw;
	}

	public void setH_pw(String h_pw) {
		this.h_pw = h_pw;
	}

	public String getH_tel() {
		return h_tel;
	}

	public void setH_tel(String h_tel) {
		this.h_tel = h_tel;
	}

	public String getH_address() {
		return h_address;
	}

	public void setH_address(String h_address) {
		this.h_address = h_address;
	}

	@Override
	public String toString() {
		return "HospitalBean [h_num=" + h_num + ", h_name=" + h_name + ", h_email=" + h_email + ", h_pw=" + h_pw
				+ ", h_tel=" + h_tel + ", h_address=" + h_address + "]";
	}
	
	
}
