package fr.excilys.database.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.excilys.database.dao.CompanyDAO;
import fr.excilys.database.model.Company;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyDAO companyDAO;
	
	public List<Company> getAllCompanies(){		
	 return companyDAO.getAllCompanies();
	}
	
	public List<Company> getAllCompaniesPagination(int nblignes,int offset){
	return companyDAO.getAllCompaniesPagination(nblignes, offset);
	}
	
}
