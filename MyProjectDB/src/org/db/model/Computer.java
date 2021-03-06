package org.db.model;

import java.time.LocalDate;

public class Computer {
	private int id; 
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company manufacturer;

	public Computer(ComputerBuilder builder) {
		this.id=builder.id;
		this.name=builder.name;
		this.introduced=builder.introduced;
		this.discontinued=builder.discontinued;
		this.manufacturer=builder.company;
		}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public Company getManufacturer() {
		return manufacturer;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", manufacturer=" + manufacturer.toString() + "]";
	}
	
	public static class ComputerBuilder{
		private int id;
		private String name;
		private LocalDate introduced;
		private LocalDate discontinued;
		private Company company;
		
		public ComputerBuilder() {}
		
		public ComputerBuilder id(int id) {
			this.id=id;
			return this;
		}
		
		public ComputerBuilder name(String name) {
			this.name=name;
			return this;
		}
		
		public ComputerBuilder introduced(LocalDate introduced) {
			this.introduced=introduced;
			return this;
		}
		
		public ComputerBuilder discontinued(LocalDate discontinued) {
			this.discontinued=discontinued;
			return this;
		}
		
		public ComputerBuilder company(Company company) {
			this.company=company;
			return this;
		}
		
		public Computer build() {
			return new Computer(this);
		}
	}

}
