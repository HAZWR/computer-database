package fr.excilys.database.service;

import java.util.List;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.excilys.database.dao.ComputerDAO;
import fr.excilys.database.model.Computer;

@Service
public class ComputerService {

	    @Autowired
		private ComputerDAO computerDAO;

		public List<Computer> getAllComputers() {
			return computerDAO.getAllComputers();
		}
		
		public Computer getComputerById(int id)  {
			return computerDAO.getComputerById(id);
		}
		
		public boolean  create(Computer comp) {			
			return computerDAO.create(comp); 
		}
		
		public boolean update(Computer comp) {
			return computerDAO.update(comp); 
		}
		
		public boolean supprimer(int id)  {
			return computerDAO.supprimer(id);
		}
		
		public List<Computer> getAllComputersPagination(int nblignes,int offset)  {
			return computerDAO.getAllComputersPagination(nblignes, offset);
		}
		
		public int count(String searchName) {
	       return computerDAO.count(searchName);
		}
		
		public List<Computer> getComputerByName(String name) {
			return computerDAO.getComputerByName(name);
		}
}	
