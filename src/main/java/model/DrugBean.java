package model;

import java.io.Serializable;

public class DrugBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int drug_num;
	private String drug_name;
	private String drug_type;
	private String drug_effect;
	private String drug_guide;
	private String drug_note;
	private int drug_price;
	private String drug_img_name;
	
	
	public DrugBean() {}


	public DrugBean(int drug_num, String drug_name, String drug_type, String drug_effect, String drug_guide,
			String drug_note, int drug_price, String drug_img_name) {
		this.drug_num = drug_num;
		this.drug_name = drug_name;
		this.drug_type = drug_type;
		this.drug_effect = drug_effect;
		this.drug_guide = drug_guide;
		this.drug_note = drug_note;
		this.drug_price = drug_price;
		this.drug_img_name = drug_img_name;
	}


	public int getDrug_num() {
		return drug_num;
	}


	public void setDrug_num(int drug_num) {
		this.drug_num = drug_num;
	}


	public String getDrug_name() {
		return drug_name;
	}


	public void setDrug_name(String drug_name) {
		this.drug_name = drug_name;
	}


	public String getDrug_type() {
		return drug_type;
	}


	public void setDrug_type(String drug_type) {
		this.drug_type = drug_type;
	}


	public String getDrug_effect() {
		return drug_effect;
	}


	public void setDrug_effect(String drug_effect) {
		this.drug_effect = drug_effect;
	}


	public String getDrug_guide() {
		return drug_guide;
	}


	public void setDrug_guide(String drug_guide) {
		this.drug_guide = drug_guide;
	}


	public String getDrug_note() {
		return drug_note;
	}


	public void setDrug_note(String drug_note) {
		this.drug_note = drug_note;
	}


	public int getDrug_price() {
		return drug_price;
	}


	public void setDrug_price(int drug_price) {
		this.drug_price = drug_price;
	}


	public String getDrug_img_name() {
		return drug_img_name;
	}


	public void setDrug_img_name(String drug_img_name) {
		this.drug_img_name = drug_img_name;
	}


	@Override
	public String toString() {
		return "DrugBean [drug_num=" + drug_num + ", drug_name=" + drug_name + ", drug_type=" + drug_type
				+ ", drug_effect=" + drug_effect + ", drug_guide=" + drug_guide + ", drug_note=" + drug_note
				+ ", drug_price=" + drug_price + ", drug_img_name=" + drug_img_name + "]";
	}


}
