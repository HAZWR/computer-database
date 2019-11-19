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
		boolean etat=false;
		if(comp!=null) {
			computerDAO.saveAndFlush(comp);
			etat=true;
		}
		return etat;
	}

	public boolean update(Computer comp) {
		boolean etat=false;
		if(comp!=null) {
			computerDAO.save(comp);
			etat=true;
		}
		return etat;
	}

	public boolean supprimer(int id) {
		boolean etat=false;
		if(id!=0) {
			computerDAO.deleteById(id);
			etat=true;
		}
		return etat;
	}

//	@Transactional
//	public List<Computer> getAllComputersPagination(int nblignes, int offset) {
//		return computerDAO.getAllComputersPagination(nblignes, offset);
//	}

	public int count(String searchName) {
		int count=0;
		if(searchName!=null)
			count = computerDAO.count("%"+searchName+"%");
		return count;
	}

	public List<Computer> getComputerByName(String name) {
		List<Computer> computers = null;
			if(name!=null)
				computers=computerDAO.findByName("%"+name+"%");
		return computers;
	}
}
