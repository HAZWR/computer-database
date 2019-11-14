package fr.excilys.database.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import fr.excilys.database.annotation.ValidDate;

@Entity
@Table(name = "computer")
public class Computer {
	@Id
	private int id;
	
	@Column(name = "name")
	@NotNull(message = "Name can't be null")
	@Size(min = 5, max = 50, message = "La sasise devrait etre entre 10 et 20 caractères")
	private String name;
	
	@Column(name = "introduced")
	@NotNull(message = "Introduced Date can't be null")
	@ValidDate(comments = "Date valide")
	private LocalDate introduced;
	
	@Column(name = "discontinued")
	@NotNull(message = "Discontinued Date can't be null")
	@ValidDate(comments = "Date valide")
	private LocalDate discontinued;

	@NotNull(message = "Company not null")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="company_id")
	private Company manufacturer;
	
	public Computer() {
		super();
	}

	public Computer(ComputerBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.introduced = builder.introduced;
		this.discontinued = builder.discontinued;
		this.manufacturer = builder.company;
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
				+ ", manufacturer=" + manufacturer + "]";
	}

	public static class ComputerBuilder {
		private int id;
		private String name;
		private LocalDate introduced;
		private LocalDate discontinued;
		private Company company;

		public ComputerBuilder() {
		}

		public ComputerBuilder id(int id) {
			this.id = id;
			return this;
		}

		public ComputerBuilder name(String name) {
			this.name = name;
			return this;
		}

		public ComputerBuilder introduced(LocalDate introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerBuilder discontinued(LocalDate discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerBuilder company(Company company) {
			this.company = company;
			return this;
		}

		public Computer build() {
			return new Computer(this);
		}
	}

}
