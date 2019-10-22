package fr.excilys.database.model;

public class Company {
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}

	private int id;
	private String name;
	
	public Company() {}
	
	public Company(int id,String name) {
		this.id=id;
		this.name=name;
	}
	
	public Company(String name) {
		this.name=name;
	}

	public Company(int companyId) {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
