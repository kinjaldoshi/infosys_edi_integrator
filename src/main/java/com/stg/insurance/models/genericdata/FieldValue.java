package com.stg.insurance.models.genericdata;

/**
 * @author Aditya Verma
 * 
 *         A Field Value which will have value and description
 *
 */
public class FieldValue {
	private String value;
	private String description;
	
	public FieldValue() {
	}
	
	public FieldValue(String value, String description) {
		this.value = value;
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
