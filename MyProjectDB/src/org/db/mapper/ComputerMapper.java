package org.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.db.model.Company;
import org.db.model.Computer;

public class ComputerMapper {
	
    private static ComputerMapper computerMap;
	
	private ComputerMapper() {}
	
	public static ComputerMapper getInstance() {
		if(computerMap==null) {
			computerMap=new ComputerMapper();
		}
		return computerMap;
	}
	
	public Computer convertSQLtoComputer(ResultSet rs) {
			Computer computer=new Computer();
		try {
			computer.setId(rs.getInt("cmpt.id"));
			computer.setName(rs.getString("cmpt.name"));
			computer.setIntroduced(rs.getDate("cmpt.introduced")!=null?rs.getDate("cmpt.introduced").toLocalDate():null);
			computer.setDiscontinued(rs.getDate("cmpt.discontinued")!=null?rs.getDate("cmpt.discontinued").toLocalDate():null);
			computer.setManufacturer(new Company(rs.getInt("cmpa.id"),rs.getString("cmpa.name")));
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
		return computer;
	}

}
