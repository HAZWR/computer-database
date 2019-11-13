package fr.excilys.database.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.excilys.database.dao.ComputerDAO;
import fr.excilys.database.model.Computer;

@Service
public class ComputerService {

	@Autowired
	private ComputerDAO computerDAO;

	public List<Computer> getAllComputers() {
		return computerDAO.findAll();
	}

	public Computer getComputerById(int id) {
		return computerDAO.findById(id);
	}
	
	@Transactional
	public boolean create(Computer comp) {
		if(comp!=null)
			computerDAO.save(comp);
		return true;
	}

//	public boolean update(Computer comp) {
//		return computerDAO.update(comp);
//	}

	public boolean supprimer(int id) {
		if(id!=0)
			computerDAO.deleteById(id);
		return true;
	}

//	@Transactional
//	public List<Computer> getAllComputersPagination(int nblignes, int offset) {
//		return computerDAO.getAllComputersPagination(nblignes, offset);
//	}

	public int count(String searchName) {
		return computerDAO.count(searchName);
	}

	public List<Computer> getComputerByName(String name) {
		return computerDAO.findByName("%" + name + "%");
	}
}
