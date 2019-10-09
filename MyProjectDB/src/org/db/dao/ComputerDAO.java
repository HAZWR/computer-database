package org.db.dao;

import java.sql.Statement;
import java.util.List;

import org.db.connection.ConnectionBD;
import org.db.model.Company;
import org.db.model.Computer;

public interface ComputerDAO {
	
	public List<Computer> getAllComputers();
	public Computer getComputerById(int id);
	public void create(Computer nouveau);
	public void update(int id);
	public void supprimer(int id);

}
