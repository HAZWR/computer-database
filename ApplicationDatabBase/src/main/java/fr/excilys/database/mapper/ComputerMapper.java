package fr.excilys.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import fr.excilys.database.dto.ComputerDTO;
import fr.excilys.database.model.Company;
import fr.excilys.database.model.Computer;
import fr.excilys.database.model.Computer.ComputerBuilder;

@Component
public class ComputerMapper implements RowMapper<Computer>{

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		int id=rs.getInt("computer.id");
		String name=rs.getString("computer.name");
		LocalDate introduced=rs.getDate("computer.introduced")!=null?rs.getDate("computer.introduced").toLocalDate():null;
		LocalDate discontinued=rs.getDate("computer.discontinued")!=null?rs.getDate("computer.discontinued").toLocalDate():null;
		Company company=new Company(rs.getInt("company.id"),rs.getString("company.name"));
		return new ComputerBuilder().id(id).name(name).introduced(introduced).discontinued(discontinued).company(company).build();
	}

	public Computer computerDtoToComputer(ComputerDTO computerDto){
		return new ComputerBuilder().id(computerDto.getId())
				.name(computerDto.getName())
				.introduced(computerDto.getIntroduced()!=null?LocalDate.parse(computerDto.getIntroduced()):null)
				.discontinued(computerDto.getDiscontinued()!=null?LocalDate.parse(computerDto.getDiscontinued()):null)
				.company(new Company(computerDto.getCompany())).build();
		
	}
	
	public ComputerDTO computerToComputerDto(Computer computer) {
		ComputerDTO computerDto=new ComputerDTO();
		computerDto.setId(computer.getId());
		computerDto.setName(computer.getName());
		computerDto.setIntroduced(computer.getIntroduced()!=null?computer.getIntroduced().toString():null);
		computerDto.setDiscontinued(computer.getDiscontinued()!=null?computer.getDiscontinued().toString():null);
		computerDto.setCompany(computer.getManufacturer().getName());
		return computerDto;
	}
}
