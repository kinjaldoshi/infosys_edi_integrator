package com.stg.insurance.models.genericdata;

/**
 * @author Aditya Verma
 * 
 *         A Document which will have a main category on top of the heirarchy
 *
 */
public class Document {
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
