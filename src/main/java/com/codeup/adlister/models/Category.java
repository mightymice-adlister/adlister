package com.codeup.adlister.models;

public class Category {
	private String name;
	private Long id;

	public Category (String cat_name, Long cat_id) {
		this.name = cat_name;
		this.id = cat_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
