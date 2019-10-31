package fr.excilys.database.service;

import java.util.List;

import fr.excilys.database.dao.CompanyDAO;
import fr.excilys.database.dao.imp.CompanyDAOImpl;
import fr.excilys.database.model.Company;


public class CompanyService {
	
	private CompanyDAO companyDAO=new CompanyDAOImpl();
	
	public List<Company> getAllCompanies(){		
	 return companyDAO.getAllCompanies();
	}
	
	public List<Company> getAllCompaniesPagination(int nblignes,int offset) {
	return companyDAO.getAllCompaniesPagination(nblignes, offset);
	}
	
}
