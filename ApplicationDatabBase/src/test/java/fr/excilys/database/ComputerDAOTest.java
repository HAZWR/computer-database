//package fr.excilys.database;
//
//import static org.junit.Assert.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import fr.excilys.database.configuration.ApplicationConfig;
//import fr.excilys.database.dao.imp.ComputerDAOImpl;
//import fr.excilys.database.model.Company;
//import fr.excilys.database.model.Computer;
//import fr.excilys.database.model.Computer.ComputerBuilder;;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {ApplicationConfig.class})
//public class ComputerDAOTest {
//	@Autowired
//	ComputerDAOImpl computerDAO;
//	
//	@Before
//	public void makeH2Connection() {
//		System.setProperty("testing","true");			
//	}
//
//	@Test
//	public void testgetAllComputers() {
//		List<Computer> mycomputers=computerDAO.getAllComputers();
//		assertEquals(mycomputers.size(),7);
//	}
//	
//	@Test 
//	public void testsupprimer() {
//		int id=3;
//		assertTrue(computerDAO.supprimer(id));
//	}
//	
//	@Test
//	public void testcreate() {
//		Computer nuevo=new ComputerBuilder().id(23).name("Stealing").introduced(LocalDate.of(1998,02,12)).discontinued(LocalDate.of(2012, 03, 05)).company(new Company(1,"Apple Inc.")).build();
//		assertTrue(computerDAO.create(nuevo));	
//	}
//	
//	@Test
//	public void testgetComputerById() {
//		Computer m=computerDAO.getComputerById(2);
//		
//	}
//	
//	@Test
//	public void testupdate() {
//		Computer compup=new ComputerBuilder().id(4).name("CM-5e").introduced(null).discontinued(LocalDate.of(2012, 03, 05)).company(new Company(1,"Apple Inc.")).build();
//		assertTrue(computerDAO.update(compup));
//	}
//	
//	@Test
//	public void testgetAllComputersPagination(){
//		List<Computer> mycomputers=computerDAO.getAllComputersPagination(5, 1);
//		assertEquals(mycomputers.size(),5);
//		
//	}
//}
