package com.codeup.adlister.models;

public class Category {
	private String cat_name;
	private int cat_id;

	public Category (String cat_name, int cat_id) {
		this.cat_name = cat_name;
		this.cat_id = cat_id;
	}

	public int getCat_Id() {
		return cat_id;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
}
