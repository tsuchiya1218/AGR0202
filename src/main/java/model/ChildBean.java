package model;

public class ChildBean {
	private int c_num;
	private int c_m_num;
	private String c_medical_num;
	private String c_i_num;
	private String c_i_expiry_date;
	private String c_i_mark;
	private String c_name;
	private String c_kana;
	private String c_brith;
	private String c_gender;
	private String c_blood_type;
	private String c_medical_history;
	private String c_medication;
	private String c_allergy;
	
	private String age;
	
	public ChildBean() {}

	public ChildBean(int c_num, int c_m_num, String c_medical_num, String c_i_num, String c_i_expiry_date,
			String c_i_mark, String c_name, String c_kana, String c_brith, String c_gender, String c_blood_type,
			String c_medical_history, String c_medication, String c_allergy) {
		this.c_num = c_num;
		this.c_m_num = c_m_num;
		this.c_medical_num = c_medical_num;
		this.c_i_num = c_i_num;
		this.c_i_expiry_date = c_i_expiry_date;
		this.c_i_mark = c_i_mark;
		this.c_name = c_name;
		this.c_kana = c_kana;
		this.c_brith = c_brith;
		this.c_gender = c_gender;
		this.c_blood_type = c_blood_type;
		this.c_medical_history = c_medical_history;
		this.c_medication = c_medication;
		this.c_allergy = c_allergy;
	}


	public int getC_num() {
		return c_num;
	}

	public void setC_num(int c_num) {
		this.c_num = c_num;
	}

	public int getC_m_num() {
		return c_m_num;
	}

	public void setC_m_num(int c_m_num) {
		this.c_m_num = c_m_num;
	}

	public String getC_medical_num() {
		return c_medical_num;
	}

	public void setC_medical_num(String c_medical_num) {
		this.c_medical_num = c_medical_num;
	}

	public String getC_i_num() {
		return c_i_num;
	}

	public void setC_i_num(String c_i_num) {
		this.c_i_num = c_i_num;
	}

	public String getC_i_expiry_date() {
		return c_i_expiry_date;
	}

	public void setC_i_expiry_date(String c_i_expiry_date) {
		this.c_i_expiry_date = c_i_expiry_date;
	}

	public String getC_i_mark() {
		return c_i_mark;
	}

	public void setC_i_mark(String c_i_mark) {
		this.c_i_mark = c_i_mark;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_kana() {
		return c_kana;
	}

	public void setC_kana(String c_kana) {
		this.c_kana = c_kana;
	}

	public String getC_brith() {
		return c_brith;
	}

	public void setC_brith(String c_brith) {
		this.c_brith = c_brith;
	}

	public String getC_gender() {
		return c_gender;
	}

	public void setC_gender(String c_gender) {
		this.c_gender = c_gender;
	}

	public String getC_blood_type() {
		return c_blood_type;
	}

	public void setC_blood_type(String c_blood_type) {
		this.c_blood_type = c_blood_type;
	}

	public String getC_allergy() {
		return c_allergy;
	}

	public void setC_allergy(String c_allergy) {
		this.c_allergy = c_allergy;
	}

	public String getC_medical_history() {
		return c_medical_history;
	}

	public void setC_medical_history(String c_medical_history) {
		this.c_medical_history = c_medical_history;
	}

	public String getC_medication() {
		return c_medication;
	}

	public void setC_medication(String c_medication) {
		this.c_medication = c_medication;
	}


	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "ChildBean [c_num=" + c_num + ", c_m_num=" + c_m_num + ", c_medical_num=" + c_medical_num + ", c_i_num="
				+ c_i_num + ", c_i_expiry_date=" + c_i_expiry_date + ", c_i_mark=" + c_i_mark + ", c_name=" + c_name
				+ ", c_kana=" + c_kana + ", c_brith=" + c_brith + ", c_gender=" + c_gender + ", c_blood_type="
				+ c_blood_type + ", c_medical_history=" + c_medical_history + ", c_medication=" + c_medication
				+ ", c_allergy=" + c_allergy + "]";
	}


	
	

}
