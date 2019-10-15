package org.db.model;

public class Company {
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}

	private int id;
	private String name;
	
	
	private Company(CompanyBuilder comp) {
		this.id=comp.id;
		this.name=comp.name;
	}

	public Company(String name) {
		 this.name=name;
	}

	public Company(int int1, String string) {
		this.id=int1;
		this.name=string;
	}

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
	
	private static class CompanyBuilder{
		private int id;
		private String name;
		
		public CompanyBuilder() {}
		
		public CompanyBuilder id(int id) {
			this.id=id;
			return this;
		}
		
		public CompanyBuilder name(String name) {
			this.name=name;
			return this;
		}
		
		public Company build() {
			return new Company(this);
		}
	}

}
