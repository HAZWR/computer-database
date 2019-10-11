
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.db.connection.ConnectionBD;
import org.db.dao.imp.CompanyDAOImpl;
import org.db.dao.imp.ComputerDAOImpl;
import org.db.mapper.HelperDate;
import org.db.model.Company;
import org.db.model.Computer;

import com.db.controller.CompanyController;
import com.db.controller.ComputerController;
import java.util.logging.*;

public class MainClass {

	public static void main(String[] args) {
		int i=9_999;
		MainClass.monMenu();
		System.out.println("Veuilez saisir un nombre : ");
		Scanner scanner = new Scanner(System.in);
		int nombre = scanner.nextInt(); 
		CompanyController companyImpl = new CompanyController();
		ComputerController computerImpl = new ComputerController();
		final Logger myloger=Logger.getLogger(MainClass.class.getName());
		switch (nombre) {
		case 1:
			System.out.println("Liste des companies : \n");
			companyImpl.getAllCompanies();
			break;
		case 2:
			System.out.println("Liste des computers: \n");
			computerImpl.getAllComputers();
			break;
		case 3:
			System.out.println("Veuillez saisir l'id du computer à chercher \n");
			int id = scanner.nextInt();
			computerImpl.getComputerById(id);
			break;
		case 4:
			System.out.println("Création d'un nouvel ordinateur \n");
			System.out.println("Veuillez saisir un identifiant \n");
			int idline=scanner.nextInt();
			System.out.println("Veuillez saisir un nom \n");
			String name = scanner.next();
			System.out.println("Veuillez saisir d'introduction \n");
			String dateIntroduce = scanner.next();
			System.out.println("Veuillez saisir de discontinuation \n");
			String dateDiscont = scanner.next();
			System.out.println("Veuillez saisir la companie");
			int companie = scanner.nextInt();
			LocalDate dateIntroduced = HelperDate.StringDateToLocalDate(dateIntroduce);
			LocalDate dateDisconted = HelperDate.StringDateToLocalDate(dateDiscont);
			if(dateIntroduced.isBefore(dateDisconted)) {
				Computer mycomp = new Computer(idline,name,dateIntroduced,dateDisconted,companie);
				computerImpl.create(mycomp);
			}
			break;
		case 5:
			/* A implémenter dans la classe DAO */
		case 6:
			System.out.println("Veuillez saisir l'identifiant à supprimer \n");
			int idsup = scanner.nextInt();
			computerImpl.supprimer(idsup);
			scanner.next();
			break;
		default:
			System.out.println("Vous avez quitter la plateforme");
		}
	}
		public static void monMenu() {
			System.out.println("====================================================");
			System.out.println("                    MENU                             ");
			System.out.println("====================================================");
			System.out.println("Choose one option please, Entrer number of operation.");
			System.out.println("1 - Show list of companies");
			System.out.println("2 - Show list computers");
			System.out.println("3 - Show details of selected Computer");
			System.out.println("4 - Create computer");
			System.out.println("5 - Update computer");
			System.out.println("6 - Delete computer");
			System.out.println("0 - Quit");
			
		}
}
