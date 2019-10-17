package fr.excilys.database.dao;

import java.util.List;

import fr.excilys.database.model.Computer;


public interface ComputerDAO {

	public List<Computer> getAllComputers();

	public Computer getComputerById(int id);

	public boolean create(Computer nouveau);

	public boolean update(Computer comp);

	public boolean supprimer(int id);
	
	public int count();

	public List<Computer> getAllComputersPagination(int nombre, int offset);

}
