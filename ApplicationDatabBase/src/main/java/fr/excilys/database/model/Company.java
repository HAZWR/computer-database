package fr.excilys.database.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class Company {
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}
	
	@Positive
	private int id;
	
	@NotNull(message="Name can't be null")
	@Size(min=5,max=50,message="La sasise devrait etre entre 10 et 20 caractères")
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
