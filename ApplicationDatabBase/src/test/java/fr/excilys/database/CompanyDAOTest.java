//package fr.excilys.database;
//
//import static org.junit.Assert.*;
//
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import fr.excilys.database.configuration.ApplicationConfig;
//import fr.excilys.database.dao.imp.CompanyDAOImpl;
//import fr.excilys.database.model.Company;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {ApplicationConfig.class})
//public class CompanyDAOTest {
//	
//	@Autowired
//	CompanyDAOImpl companyDAO;
//	
//	@Before
//	public void makeH2Connection() {
//		System.setProperty("testing","true");		
//	}
//	
//	@Test
//	public void testgetAllCompanies() {
//		List<Company> mycompanies=companyDAO.getAllCompanies();
//		assertEquals(mycompanies.size(),7);
//	}
//}
