package fr.excilys.database.dto;

import java.time.LocalDate;

public class Computer {
	
	private int id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company company;
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
	
	public LocalDate getIntroduced() {
		return introduced;
	}
	
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}
	
	public LocalDate getDiscontinued() {
		return discontinued;
	}
	
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company=" + company + "]";
	}
	

}
