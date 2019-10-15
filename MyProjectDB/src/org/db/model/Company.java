package org.db.model;

public class Company {
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}

	private int id;
	private String name;

	public Company(String name) {
		 this.name=name;
	}

	public Company(int int1, String string) {
		this.id=int1;
		this.name=string;
	}

	public Company() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
