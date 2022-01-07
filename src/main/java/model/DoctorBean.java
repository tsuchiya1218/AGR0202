package model;

import java.io.Serializable;

public class DoctorBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int d_num;
	private String d_email;
	private String d_pw;
	private String d_name;
	private String d_kana;
	private String d_birth;
	private String d_tel;
	private String d_gender;
	private String d_department;
	private int d_h_num;
	private boolean d_leave;
	private boolean d_auth;
	
	public DoctorBean() {}

	public DoctorBean(int d_num, String d_email, String d_pw, String d_name, String d_kana, String d_birth,
			String d_tel, String d_gender, String d_department, int d_h_num, boolean d_leave, boolean d_auth) {
		super();
		this.d_num = d_num;
		this.d_email = d_email;
		this.d_pw = d_pw;
		this.d_name = d_name;
		this.d_kana = d_kana;
		this.d_birth = d_birth;
		this.d_tel = d_tel;
		this.d_gender = d_gender;
		this.d_department = d_department;
		this.d_h_num = d_h_num;
		this.d_leave = d_leave;
		this.d_auth = d_auth;
	}

	public int getD_num() {
		return d_num;
	}

	public void setD_num(int d_num) {
		this.d_num = d_num;
	}

	public String getD_email() {
		return d_email;
	}

	public void setD_email(String d_email) {
		this.d_email = d_email;
	}

	public String getD_pw() {
		return d_pw;
	}

	public void setD_pw(String d_pw) {
		this.d_pw = d_pw;
	}

	public String getD_name() {
		return d_name;
	}

	public void setD_name(String d_name) {
		this.d_name = d_name;
	}

	public String getD_kana() {
		return d_kana;
	}

	public void setD_kana(String d_kana) {
		this.d_kana = d_kana;
	}

	public String getD_birth() {
		return d_birth;
	}

	public void setD_birth(String d_birth) {
		this.d_birth = d_birth;
	}

	public String getD_tel() {
		return d_tel;
	}

	public void setD_tel(String d_tel) {
		this.d_tel = d_tel;
	}

	public String getD_gender() {
		return d_gender;
	}

	public void setD_gender(String d_gender) {
		this.d_gender = d_gender;
	}

	public String getD_department() {
		return d_department;
	}

	public void setD_department(String d_department) {
		this.d_department = d_department;
	}

	public int getD_h_num() {
		return d_h_num;
	}

	public void setD_h_num(int d_h_num) {
		this.d_h_num = d_h_num;
	}

	public boolean isD_leave() {
		return d_leave;
	}

	public void setD_leave(boolean d_leave) {
		this.d_leave = d_leave;
	}

	public boolean isD_auth() {
		return d_auth;
	}

	public void setD_auth(boolean d_auth) {
		this.d_auth = d_auth;
	}

	@Override
	public String toString() {
		return "DoctorBean [d_num=" + d_num + ", d_email=" + d_email + ", d_pw=" + d_pw + ", d_name=" + d_name
				+ ", d_kana=" + d_kana + ", d_birth=" + d_birth + ", d_tel=" + d_tel + ", d_gender=" + d_gender
				+ ", d_department=" + d_department + ", d_h_num=" + d_h_num + ", d_leave=" + d_leave + ", d_auth="
				+ d_auth + "]";
	}

}
