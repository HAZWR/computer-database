package org.db.dao;

import java.util.List;

import org.db.model.*;

public interface CompanyDAO {

	public List<Company> getAllCompanies();

	public Company getCompanyById(int id);

	int countCompanies();

	List<Company> getAllCompaniesPagination(int nombre, int offset);

}
