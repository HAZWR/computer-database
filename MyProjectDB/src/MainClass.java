
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Logger;

import org.db.mapper.ConverterDate;
import org.db.model.Company;
import org.db.model.Computer;
import org.db.model.Computer.ComputerBuilder;

import com.db.controller.CompanyController;
import com.db.controller.ComputerController;

public class MainClass {
	public static void main(String[] args) {
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
			int identifiant=scanner.nextInt();
			System.out.println("Veuillez saisir un nom \n");
			String name=scanner.next();
			System.out.println("Veuillez saisir d'introduction \n");
			LocalDate dateIntroduced=ConverterDate.StringDateToLocalDate(scanner.next());
			System.out.println("Veuillez saisir de discontinuation \n");
			LocalDate dateDisconted=ConverterDate.StringDateToLocalDate(scanner.next());
			System.out.println("Veuillez saisir la companie");
			Company company=new Company(0,scanner.nextLine());
			if(dateIntroduced.isBefore(dateDisconted)) {
				computerImpl.create(new ComputerBuilder().id(identifiant).name(name).introduced(dateIntroduced).discontinued(dateDisconted).company(company).build());
			}
			break;
		case 5:
			System.out.println("Veuillez saisir un id pour l'ordinateur à modifier");
			int idUpdate=scanner.nextInt();
			Computer comp=computerImpl.getComputerById(idUpdate);
			System.out.println("Veuillez saisir un id \n");
			int id1=scanner.nextInt();
			System.out.println("Veuillez saisir un nom \n");
			String name1=scanner.next();
			System.out.println("Veuillez saisir d'introduction \n");
			LocalDate dateIntroduced1 = ConverterDate.StringDateToLocalDate(scanner.next());
			System.out.println("Veuillez saisir de discontinuation \n");
			LocalDate dateDisconted1 = ConverterDate.StringDateToLocalDate(scanner.next());
			System.out.println("Veuillez saisir la companie");
			Company company1=new Company(1,scanner.nextLine());
			if(dateIntroduced1.isBefore(dateDisconted1)) {
				computerImpl.update(new ComputerBuilder().id(id1).name(name1).introduced(dateIntroduced1).discontinued(dateDisconted1).company(company1).build());
			}
			break;
		case 6:
			System.out.println("Veuillez saisir l'identifiant à supprimer \n");
			int idsup = scanner.nextInt();
			computerImpl.supprimer(idsup);
			scanner.next();
			break;
		case 7 :
			System.out.println("Affichage des computers avec pagination");
			System.out.println("Veuillez saisir le nombre de lignes");
			int nbLigne=scanner.nextInt();
			System.out.println("Veuillez saisir le commencement(offset)");
			int offset=scanner.nextInt();
			computerImpl.getAllComputersPagination(nbLigne, offset);
			break;
		case 8 :
			System.out.println("Affichage des companies avec pagination");
			System.out.println("Veuillez saisir le nombre de lignes");
			int nbL=scanner.nextInt();
			System.out.println("Veuillez saisir le commencement(offset)");
			int offs=scanner.nextInt();
			companyImpl.getAllCompaniesPagination(nbL,offs);
			break;
		default:
			System.out.println("Vous avez quitter la plateforme");
		}
		
		scanner.close();
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
			System.out.println("7 -Affichage de computers par pagination");
			System.out.println("8- Affichage de companies par pagination");
			System.out.println("0 - Quit");
			
		}
}
