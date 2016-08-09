package com.beans;

public class Bank {

	private String id;
	private String name;
	
	public Bank() {}

	public Bank(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Bank [id=" + id + ", name=" + name + "]";
	}
	
}
