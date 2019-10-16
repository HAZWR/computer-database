package fr.excilys.database.controller;

import java.util.ArrayList;
import java.util.List;

import fr.excilys.database.dao.imp.ComputerDAOImpl;
import fr.excilys.database.model.Computer;


public class ComputerController {
	
		ComputerDAOImpl compl=new ComputerDAOImpl();
		
		public List<Computer> mylist=new ArrayList<Computer>();
		
		
		public void getAllComputers() {
			mylist=compl.getAllComputers();
			for (Computer comp : mylist) {
				System.out.println("Nom des computers : " + comp.getName());
			}			
		}
		
		public Computer getComputerById(int id) {
			Computer comp=compl.getComputerById(id);
			System.out.println(comp.toString());	
			return comp;
		}
		
		public void create(Computer comp) {			
			compl.create(comp); 
			System.out.println("Création efféctuée avec succès de l'ordinateur : "+comp.getName());
		}
		
		public void update(Computer comp) {
			compl.update(comp); 
			System.out.println(comp.toString());
			System.out.println("Modification efféctuée avec succès de l'ordinateur : "+comp.getName());
		}
		
		public void supprimer(int id) {
			compl.supprimer(id);
			System.out.println("Votre suppression est faite avec succès");
		}
		
		public void getAllComputersPagination(int nblignes,int offset) {
			mylist=compl.getAllComputersPagination(nblignes, offset);
			for (Computer comp : mylist) {
				System.out.println("Nom des computers : " + comp.getName());
			}
			
		}
		public void count() {
	       int nombre=compl.count();
	       System.out.println("Nombre d'ordinateurs : "+nombre);
		}
}	
