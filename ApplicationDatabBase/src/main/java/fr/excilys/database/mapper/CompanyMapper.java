package fr.excilys.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import fr.excilys.database.model.Company;

@Component
public class CompanyMapper implements RowMapper<Company>{
        
	public Company convertSQLtoCompany(ResultSet rs){
		   Company comp=new Company();
		   try {
			   comp.setId(rs.getInt("id"));
			   comp.setName(rs.getString("name"));
		   }catch (SQLException se) {
				for(Throwable e : se) {
	                System.err.println("Erreurs : " + e);
	            }
			}
		   return comp;
	}

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		Company comp=new Company();
		comp.setId(rs.getInt("id"));
		   comp.setName(rs.getString("name"));
		return comp;
	}
}
