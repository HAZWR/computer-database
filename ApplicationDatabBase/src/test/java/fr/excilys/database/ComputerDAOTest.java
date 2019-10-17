package fr.excilys.database;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


import fr.excilys.database.dao.imp.ComputerDAOImpl;
import fr.excilys.database.model.Company;
import fr.excilys.database.model.Computer;
import fr.excilys.database.model.Computer.ComputerBuilder;;
public class ComputerDAOTest {

	ComputerDAOImpl computer;
	
	@Before
	public void makeH2Connection() {
		System.setProperty("testing","true");
		computer=new ComputerDAOImpl();			
	}
	
	@Test
	public void testgetAllComputers() {
		List<Computer> mycomputers=computer.getAllComputers();
		assertEquals(mycomputers.size(),7);
	}
	
	@Test 
	public void testsupprimer() {
		int id=3;
		assertTrue(computer.supprimer(id));
	}
	
	@Test
	public void testcreate() {
		Computer nuevo=new ComputerBuilder().id(23).name("Stealing").introduced(LocalDate.of(1998,02,12)).discontinued(LocalDate.of(2012, 03, 05)).company(new Company(1,"Apple Inc.")).build();
		assertTrue(computer.create(nuevo));	
	}
	
	@Test
	public void testgetComputerById() {
		Computer m=computer.getComputerById(2);
	}
	
	@Test
	public void testupdate() {
		Computer compup=new ComputerBuilder().id(4).name("CM-5e").introduced(null).discontinued(LocalDate.of(2012, 03, 05)).company(new Company(1,"Apple Inc.")).build();
		assertTrue(computer.update(compup));
	}
	
	@Test
	public void testgetAllComputersPagination(){
		List<Computer> mycomputers=computer.getAllComputersPagination(5, 1);
		assertEquals(mycomputers.size(),5);
		
	}
}
