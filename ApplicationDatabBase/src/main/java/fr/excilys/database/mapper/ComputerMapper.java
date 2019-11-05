package fr.excilys.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import fr.excilys.database.model.Company;
import fr.excilys.database.model.Computer;
import fr.excilys.database.model.Computer.ComputerBuilder;

@Component
public class ComputerMapper implements RowMapper<Computer>{

	
	public Computer convertSQLtoComputer(ResultSet rs) {
			Computer computer;
			int id=0;
			String name=null;
			LocalDate introduced=null;
			LocalDate discontinued=null;
			Company company=null;
		try {
			id=rs.getInt("computer.id");
			name=rs.getString("computer.name");
			introduced=rs.getDate("computer.introduced")!=null?rs.getDate("computer.introduced").toLocalDate():null;
			discontinued=rs.getDate("computer.discontinued")!=null?rs.getDate("computer.discontinued").toLocalDate():null;
			company=new Company(rs.getInt("company.id"),rs.getString("company.name"));
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
		computer=new ComputerBuilder().id(id).name(name).introduced(introduced).discontinued(discontinued).company(company).build();
		return computer;
	}

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		int id=rs.getInt("computer.id");
		String name=rs.getString("computer.name");
		LocalDate introduced=rs.getDate("computer.introduced")!=null?rs.getDate("computer.introduced").toLocalDate():null;
		LocalDate discontinued=rs.getDate("computer.discontinued")!=null?rs.getDate("computer.discontinued").toLocalDate():null;
		Company company=new Company(rs.getInt("company.id"),rs.getString("company.name"));
		return new ComputerBuilder().id(id).name(name).introduced(introduced).discontinued(discontinued).company(company).build();
	}

}
