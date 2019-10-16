package fr.excilys.database.dao;

import java.util.List;

import fr.excilys.database.model.Computer;


public interface ComputerDAO {

	public List<Computer> getAllComputers();

	public Computer getComputerById(int id);

	public void create(Computer nouveau);

	public void update(Computer comp);

	public void supprimer(int id);
	
	public int count();

	public List<Computer> getAllComputersPagination(int nombre, int offset);

}
