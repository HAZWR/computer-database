package fr.excilys.database.dao;

import java.util.List;

import fr.excilys.database.model.Company;


public interface CompanyDAO {

	public List<Company> getAllCompanies();

	int countCompanies();

	List<Company> getAllCompaniesPagination(int nombre, int offset);

}
