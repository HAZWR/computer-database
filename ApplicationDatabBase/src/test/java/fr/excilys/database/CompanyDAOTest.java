package fr.excilys.database;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.excilys.database.dao.imp.CompanyDAOImpl;
import fr.excilys.database.model.Company;

public class CompanyDAOTest {
	CompanyDAOImpl company;
	
	@Before
	public void makeH2Connection() {
		System.setProperty("testing","true");
		company=new CompanyDAOImpl();			
	}
	
	@Test
	public void testgetAllCompanies() {
		List<Company> mycompanies=company.getAllCompanies();
		assertEquals(mycompanies.size(),7);
	}
}
