package fr.excilys.database.service;

import java.util.List;
import fr.excilys.database.dao.imp.ComputerDAOImpl;
import fr.excilys.database.model.Computer;

public class ComputerService {

		private ComputerDAOImpl computerDAO=new ComputerDAOImpl();

		public List<Computer> getAllComputers() {
			return computerDAO.getAllComputers();
		}
		
		public Computer getComputerById(int id) {
			return computerDAO.getComputerById(id);
		}
		
		public boolean  create(Computer comp) {			
			return computerDAO.create(comp); 
		}
		
		public boolean update(Computer comp) {
			return computerDAO.update(comp); 
		}
		
		public boolean supprimer(int id) {
			return computerDAO.supprimer(id);
		}
		
		public List<Computer> getAllComputersPagination(int nblignes,int offset) {
			return computerDAO.getAllComputersPagination(nblignes, offset);
		}
		
		public int count() {
	       return computerDAO.count();
		}
}	
