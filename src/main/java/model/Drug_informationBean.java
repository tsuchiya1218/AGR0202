package model;

import java.io.Serializable;

public class Drug_informationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int di_num;
	private String di_date;
	private boolean di_auth;
	private String di_p_num;

	public Drug_informationBean() {}

	public Drug_informationBean(int di_num, String di_date, boolean di_auth, String di_p_num) {
		this.di_num = di_num;
		this.di_date = di_date;
		this.di_auth = di_auth;
		this.di_p_num = di_p_num;
	}

	public int getDi_num() {
		return di_num;
	}

	public void setDi_num(int di_num) {
		this.di_num = di_num;
	}

	public String getDi_date() {
		return di_date;
	}

	public void setDi_date(String di_date) {
		this.di_date = di_date;
	}

	public boolean isDi_auth() {
		return di_auth;
	}

	public void setDi_auth(boolean di_auth) {
		this.di_auth = di_auth;
	}

	public String getDi_p_num() {
		return di_p_num;
	}

	public void setDi_p_num(String di_p_num) {
		this.di_p_num = di_p_num;
	}

	@Override
	public String toString() {
		return "Drug_informationBean [di_num=" + di_num + ", di_date=" + di_date + ", di_auth=" + di_auth
				+ ", di_p_num=" + di_p_num + "]";
	}
	
}
