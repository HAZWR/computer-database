package fr.excilys.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.excilys.database.model.Company;


public class CompanyMapper {
	
	private static CompanyMapper companyMap;
	
	private CompanyMapper() {}
	
	public static CompanyMapper getInstance() {
		if(companyMap==null) {
			companyMap=new CompanyMapper();
		}
		return companyMap;
	}
        
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
}
