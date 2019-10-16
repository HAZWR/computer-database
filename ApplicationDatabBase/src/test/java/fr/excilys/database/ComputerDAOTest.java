package fr.excilys.database;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.excilys.database.dao.imp.CompanyDAOImpl;
import fr.excilys.database.dao.imp.ComputerDAOImpl;
import fr.excilys.database.model.Computer;

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
		computer.supprimer(id);
	}
	
}
