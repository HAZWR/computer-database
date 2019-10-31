package fr.excilys.database.dao;

import java.util.List;
import fr.excilys.database.model.Computer;


public interface ComputerDAO {

	List<Computer> getAllComputers();

	Computer getComputerById(int id);

	boolean create(Computer nouveau);

	boolean update(Computer comp);

	boolean supprimer(int id);
	
    int count();

	List<Computer> getAllComputersPagination(int nombre, int offset);

}
