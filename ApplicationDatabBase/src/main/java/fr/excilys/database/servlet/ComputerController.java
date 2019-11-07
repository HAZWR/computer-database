package fr.excilys.database.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.excilys.database.mapper.ConverterDate;
import fr.excilys.database.model.Company;
import fr.excilys.database.model.Computer;
import fr.excilys.database.model.Computer.ComputerBuilder;
import fr.excilys.database.service.CompanyService;
import fr.excilys.database.service.ComputerService;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
public class ComputerController {

	@Autowired
	private ComputerService computerService;
	
	@Autowired
	private CompanyService companyService;
	
	private List<Computer> listComputers;
	
	@GetMapping(path= "/menu")
	public String getAllComputers(Model model,@RequestParam(name="search",defaultValue="")String search){
		listComputers=computerService.getComputerByName(search);
		model.addAttribute("listComputers", listComputers);
		int nombre=computerService.count(search);
		model.addAttribute("nombre",nombre);
		return "dashboard";
	}
	
	@GetMapping("/addComputer")
	public String addComputer(Model model){
		List<Company> listCompanies=companyService.getAllCompanies();
		model.addAttribute("listCompanies",listCompanies);
		return "addComputer";
	}
	
	@GetMapping("/editComputer")
	public String editComputer(Model model,@RequestParam(name="id")int id){
		List<Company> listCompanies=companyService.getAllCompanies();
		model.addAttribute("listCompanies",listCompanies);
		Computer ordi = computerService.getComputerById(id);
		model.addAttribute("ordi", ordi);
		return "editComputer";
	}
	
	@PostMapping("/addComputer")
	public String addComputerPost(HttpServletRequest request, HttpServletResponse response) {
		String nom=request.getParameter("computerName")!=null?request.getParameter("computerName"):null;
		LocalDate introduced=request.getParameter("introduced")!=null?ConverterDate.StringDateToLocalDate(request.getParameter("introduced")):null;
		LocalDate discontinued=request.getParameter("discontinued")!=null?ConverterDate.StringDateToLocalDate(request.getParameter("discontinued")):null;
		String companyId=request.getParameter("companyId")!=null?request.getParameter("companyId"):null;
		computerService.create(new ComputerBuilder().name(nom).introduced(introduced).discontinued(discontinued).company(new Company(0,companyId)).build());
		return "addComputer";
	}
	
	@PostMapping("/menu")
	public String deleteComputer(HttpServletRequest request) {
		String s=request.getParameter("selection");
		String[] myValues=s.split(",");
		for(String val:myValues) {
			System.out.println(val);
			computerService.supprimer(Integer.parseInt(val));
		}
		return "dashboard";
	}
	
	@PostMapping("/editComputer")
	public String editComputerPost(HttpServletRequest request, HttpServletResponse response) {
		int id=request.getParameter("id")!=null?Integer.parseInt(request.getParameter("id")):null;
		String nom=request.getParameter("nom")!=null?request.getParameter("nom"):null;
		LocalDate introduced=request.getParameter("introduced")!=null?ConverterDate.StringDateToLocalDate(request.getParameter("introduced")):null;
		LocalDate discontinued=request.getParameter("discontinued")!=null?ConverterDate.StringDateToLocalDate(request.getParameter("discontinued")):null;
		String companyId=request.getParameter("companyId")!=null?request.getParameter("companyId"):null;
		computerService.update(new ComputerBuilder().id(id).name(nom).introduced(introduced).discontinued(discontinued).company(new Company(0,companyId)).build());
		return "editComputer";
	}
}
