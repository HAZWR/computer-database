
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.db.connection.ConnectionBD;
import org.db.dao.imp.CompanyDAOImpl;
import org.db.dao.imp.ComputerDAOImpl;
import org.db.model.Company;
import org.db.model.Computer;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("Veuilez saisir un nombre : ");
		Scanner scanner = new Scanner(System.in);
		int nombre = scanner.nextInt();
		CompanyDAOImpl companyImpl = new CompanyDAOImpl();
		ComputerDAOImpl computerImpl = new ComputerDAOImpl();
		switch (nombre) {
			case 1:
				System.out.println("Liste des computers : \n");
				List<Company> companies = companyImpl.getAllCompanies();
			case 2:
				System.out.println("Liste des companies : \n");
				List<Computer> computers = computerImpl.getAllComputers();
			case 3:
				System.out.println("Veuillez saisir l'id du computer à chercher \n");
			    int id=scanner.nextInt();
			    Computer recherche=computerImpl.getComputerById(id);
			case 4:
				System.out.println("Création d'un nouvel ordinateur \n");
				System.out.println("Veuillez saisir un nom \n");
				String name=scanner.next();
				System.out.println("Veuillez saisir d'introduction \n");
				String dateIntroduce=scanner.next();
				System.out.println("Veuillez saisir de discontinuation \n");
				String dateDiscont=scanner.next();
				System.out.println("Veuillez saisir la companie");
				String companie=scanner.next();
				Computer mycomp=new Computer(name,Timestamp.valueOf(dateIntroduce),Timestamp.valueOf(dateDiscont),companie);
				computerImpl.create(mycomp);
			case 5:
				/* A implémenter dans la classe DAO */
			case 6:
				System.out.println("Veuillez saisie l'identifiant à supprimer \n");
				int idsup = scanner.nextInt();
				computerImpl.supprimer(idsup);
		}

	}
}
