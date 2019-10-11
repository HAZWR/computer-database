package com.db.controller;

import java.util.ArrayList;
import java.util.List;

import org.db.dao.imp.ComputerDAOImpl;
import org.db.model.Computer;

public class ComputerController {
	
		ComputerDAOImpl compl=new ComputerDAOImpl();
		
		public List<Computer> mylist=new ArrayList<Computer>();
		
		
		public void getAllComputers() {
			mylist=compl.getAllComputers();
			for (Computer comp : mylist) {
				System.out.println("Nom des computers : " + comp.getName());
			}			
		}
		
		public void getComputerById(int id) {
			Computer comp=compl.getComputerById(id);
			System.out.println(" Nom : " + comp.getName()+" Introduced : "+comp.getIntroduced()+" Discontinued : "+comp.getDiscontinued()+" Company : "+comp.getManufacturer());		
		}
		
		public void create(Computer comp) {			
			compl.create(comp); 
			System.out.println("Création efféctuée avec succès de l'ordinateur : "+comp.getName());
		}
		
		public void update(Computer comp) {
			/*
			 * A implémenter par la suite
			 */
		}
		
		public void supprimer(int id) {
			compl.supprimer(id);
			System.out.println("Votre suppression est faite avec succès");
		}
}	
