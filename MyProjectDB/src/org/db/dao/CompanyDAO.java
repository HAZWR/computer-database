package org.db.dao;

import java.util.List;

import org.db.model.*;

public interface CompanyDAO {

	public List<Company> getAllCompanies();

	int countCompanies();

	List<Company> getAllCompaniesPagination(int nombre, int offset);

}
