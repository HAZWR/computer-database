package org.db.dao;

import java.util.List;

import org.db.model.Computer;

public interface ComputerDAO {

	public List<Computer> getAllComputers();

	public Computer getComputerById(int id);

	public void create(Computer nouveau);

	public void update(int id);

	public void supprimer(int id);

}
