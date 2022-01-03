package model;

import java.io.Serializable;

public class Prescribe_medicineBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int pm_drug_num;
	private int pm_ep_num;
	private String pm_dosage;
	private String pm_dose;
	private String pm_usage;
	private int pm_dose_day;
	private int pm_all_dose_day;
	
	public Prescribe_medicineBean() {}
	
	public Prescribe_medicineBean(int pm_drug_num, int pm_ep_num, String pm_dosage, String pm_dose, String pm_usage,
			int pm_dose_day, int pm_all_dose_day) {
		super();
		this.pm_drug_num = pm_drug_num;
		this.pm_ep_num = pm_ep_num;
		this.pm_dosage = pm_dosage;
		this.pm_dose = pm_dose;
		this.pm_usage = pm_usage;
		this.pm_dose_day = pm_dose_day;
		this.pm_all_dose_day = pm_all_dose_day;
	}
	public int getPm_drug_num() {
		return pm_drug_num;
	}
	public void setPm_drug_num(int pm_drug_num) {
		this.pm_drug_num = pm_drug_num;
	}
	public int getPm_ep_num() {
		return pm_ep_num;
	}
	public void setPm_ep_num(int pm_ep_num) {
		this.pm_ep_num = pm_ep_num;
	}
	public String getPm_dosage() {
		return pm_dosage;
	}
	public void setPm_dosage(String pm_dosage) {
		this.pm_dosage = pm_dosage;
	}
	public String getPm_dose() {
		return pm_dose;
	}
	public void setPm_dose(String pm_dose) {
		this.pm_dose = pm_dose;
	}
	public String getPm_usage() {
		return pm_usage;
	}
	public void setPm_usage(String pm_usage) {
		this.pm_usage = pm_usage;
	}
	public int getPm_dose_day() {
		return pm_dose_day;
	}
	public void setPm_dose_day(int pm_dose_day) {
		this.pm_dose_day = pm_dose_day;
	}
	public int getPm_all_dose_day() {
		return pm_all_dose_day;
	}
	public void setPm_all_dose_day(int pm_all_dose_day) {
		this.pm_all_dose_day = pm_all_dose_day;
	}
	@Override
	public String toString() {
		return "Prescribe_medicineBean [pm_drug_num=" + pm_drug_num + ", pm_ep_num=" + pm_ep_num + ", pm_dosage="
				+ pm_dosage + ", pm_dose=" + pm_dose + ", pm_usage=" + pm_usage + ", pm_dose_day=" + pm_dose_day
				+ ", pm_all_dose_day=" + pm_all_dose_day + "]";
	}
	
	
}
