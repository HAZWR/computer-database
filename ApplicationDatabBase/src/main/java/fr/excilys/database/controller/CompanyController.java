package fr.excilys.database.controller;

import java.util.ArrayList;
import java.util.List;

import fr.excilys.database.dao.imp.CompanyDAOImpl;
import fr.excilys.database.model.Company;


public class CompanyController {
	
	List<Company> listCompanies=new ArrayList<Company>();
	
	CompanyDAOImpl company=new CompanyDAOImpl();
	
	public void getAllCompanies(){		
		listCompanies=company.getAllCompanies();		
		for (Company r : listCompanies) {
			System.out.println("Nom : " + r.getName());
		}		
	}
	
	public void getAllCompaniesPagination(int nblignes,int offset) {
		listCompanies=company.getAllCompaniesPagination(nblignes, offset);
		for (Company comp : listCompanies) {
			System.out.println("Nom des computers : " + comp.getName());
		}
	}
	
}
