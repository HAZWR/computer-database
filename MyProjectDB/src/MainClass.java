
import java.sql.*;
import java.text.SimpleDateFormat;
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
			System.out.println("Liste des companies : \n");
			List<Company> companies = companyImpl.getAllCompanies();
			break;
		case 2:
			System.out.println("Liste des computers: \n");
			List<Computer> computers = computerImpl.getAllComputers();
			break;
		case 3:
			System.out.println("Veuillez saisir l'id du computer à chercher \n");
			int id = scanner.nextInt();
			Computer recherche = computerImpl.getComputerById(id);
			break;
		case 4:
			System.out.println("Création d'un nouvel ordinateur \n");
			System.out.println("Veuillez saisir un nom \n");
			String name = scanner.next();
			System.out.println("Veuillez saisir d'introduction \n");
			String dateIntroduce = scanner.next();
			System.out.println("Veuillez saisir de discontinuation \n");
			String dateDiscont = scanner.next();
			System.out.println("Veuillez saisir la companie");
			String companie = scanner.next();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			Computer mycomp = new Computer(name,dateIntroduce,dateDiscont,companie);
			computerImpl.create(mycomp);
			break;
		case 5:
			/* A implémenter dans la classe DAO */
		case 6:
			System.out.println("Veuillez saisir l'identifiant à supprimer \n");
			int idsup = scanner.nextInt();
			computerImpl.supprimer(idsup);
			System.out.println("La suppression a été établie avec succèes \n");
			scanner.next();

			break;
		}

	}
}
