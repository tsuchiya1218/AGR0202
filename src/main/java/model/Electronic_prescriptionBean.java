package model;

import java.io.Serializable;

public class Electronic_prescriptionBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int ep_num;
	private int ep_d_num;
	private int m_num;
	private int ep_di_num;
	private String ep_expiry_date;
	private String ep_reg_date;
	private String ep_patient_type;
	private String ep_burden_num;
	private String ep_burden_person;
	private String ep_disease;
	private String ep_note;
	private boolean ep_auth;
	
	public Electronic_prescriptionBean() {}

	public Electronic_prescriptionBean(int ep_num, int ep_d_num, int m_num, int ep_di_num, String ep_expiry_date,
			String ep_reg_date, String ep_patient_type, String ep_burden_num, String ep_burden_person,
			String ep_disease, String ep_note, boolean ep_auth) {
		this.ep_num = ep_num;
		this.ep_d_num = ep_d_num;
		this.m_num = m_num;
		this.ep_di_num = ep_di_num;
		this.ep_expiry_date = ep_expiry_date;
		this.ep_reg_date = ep_reg_date;
		this.ep_patient_type = ep_patient_type;
		this.ep_burden_num = ep_burden_num;
		this.ep_burden_person = ep_burden_person;
		this.ep_disease = ep_disease;
		this.ep_note = ep_note;
		this.ep_auth = ep_auth;
	}

	public int getEp_num() {
		return ep_num;
	}

	public void setEp_num(int ep_num) {
		this.ep_num = ep_num;
	}

	public int getEp_d_num() {
		return ep_d_num;
	}

	public void setEp_d_num(int ep_d_num) {
		this.ep_d_num = ep_d_num;
	}

	public int getM_num() {
		return m_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public int getEp_di_num() {
		return ep_di_num;
	}

	public void setEp_di_num(int ep_di_num) {
		this.ep_di_num = ep_di_num;
	}

	public String getEp_expiry_date() {
		return ep_expiry_date;
	}

	public void setEp_expiry_date(String ep_expiry_date) {
		this.ep_expiry_date = ep_expiry_date;
	}

	public String getEp_reg_date() {
		return ep_reg_date;
	}

	public void setEp_reg_date(String ep_reg_date) {
		this.ep_reg_date = ep_reg_date;
	}

	public String getEp_patient_type() {
		return ep_patient_type;
	}

	public void setEp_patient_type(String ep_patient_type) {
		this.ep_patient_type = ep_patient_type;
	}

	public String getEp_burden_num() {
		return ep_burden_num;
	}

	public void setEp_burden_num(String ep_burden_num) {
		this.ep_burden_num = ep_burden_num;
	}

	public String getEp_burden_person() {
		return ep_burden_person;
	}

	public void setEp_burden_person(String ep_burden_person) {
		this.ep_burden_person = ep_burden_person;
	}

	public String getEp_disease() {
		return ep_disease;
	}

	public void setEp_disease(String ep_disease) {
		this.ep_disease = ep_disease;
	}

	public String getEp_note() {
		return ep_note;
	}

	public void setEp_note(String ep_note) {
		this.ep_note = ep_note;
	}

	public boolean isEp_auth() {
		return ep_auth;
	}

	public void setEp_auth(boolean ep_auth) {
		this.ep_auth = ep_auth;
	}

	@Override
	public String toString() {
		return "Electronic_prescriptionBean [ep_num=" + ep_num + ", ep_d_num=" + ep_d_num + ", m_num=" + m_num
				+ ", ep_di_num=" + ep_di_num + ", ep_expiry_date=" + ep_expiry_date + ", ep_reg_date=" + ep_reg_date
				+ ", ep_patient_type=" + ep_patient_type + ", ep_burden_num=" + ep_burden_num + ", ep_burden_person="
				+ ep_burden_person + ", ep_disease=" + ep_disease + ", ep_note=" + ep_note + ", ep_auth=" + ep_auth
				+ "]";
	}
	
}
