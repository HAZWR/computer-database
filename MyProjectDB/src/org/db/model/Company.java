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

	public int getId() {
		return id;
	}


	public String getName() {
		return name;
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
