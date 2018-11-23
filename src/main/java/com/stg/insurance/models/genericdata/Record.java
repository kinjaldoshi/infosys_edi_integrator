package com.stg.insurance.models.genericdata;

import java.util.HashMap;

/**
 * @author Aditya Verma
 * 
 * A record with fields
 *
 */
public class Record {
	
	private HashMap<String, FieldValue> fields;

	public HashMap<String, FieldValue> getFields() {
		return fields;
	}

	public void setFields(HashMap<String, FieldValue> fields) {
		this.fields = fields;
	}


}
