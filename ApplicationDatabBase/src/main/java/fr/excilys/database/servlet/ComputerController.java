package fr.excilys.database.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	ComputerService computerService;
	
	@Autowired
	CompanyService companyService;
	
	@GetMapping(path= "/menuComputer")
	public String getAllComputers(Model model){
		List<Computer> listComputers=computerService.getAllComputers();
		model.addAttribute("listComputers", listComputers);
		return "dashboard";
	}
	
	@GetMapping("/addComputer1")
	public String addComputer(HttpServletRequest request, HttpServletResponse response){
		List<Company> listCompanies=companyService.getAllCompanies();
		request.setAttribute("listCompanies",listCompanies);
		return "addComputer";
	}
	
	@PostMapping("/addComputer1")
	public String addComputerPost(HttpServletRequest request, HttpServletResponse response) {
		String nom=request.getParameter("computerName")!=null?request.getParameter("computerName"):null;
		LocalDate introduced=request.getParameter("introduced")!=null?ConverterDate.StringDateToLocalDate(request.getParameter("introduced")):null;
		LocalDate discontinued=request.getParameter("discontinued")!=null?ConverterDate.StringDateToLocalDate(request.getParameter("discontinued")):null;
		String companyId=request.getParameter("companyId")!=null?request.getParameter("companyId"):null;
		computerService.create(new ComputerBuilder().name(nom).introduced(introduced).discontinued(discontinued).company(new Company(0,companyId)).build());
		return "addComputer";
	}
	
	@PostMapping("/menuComputer")
	public String deleteComputer(HttpServletRequest request, HttpServletResponse response) {
		String s=request.getParameter("selection");
		String[] myValues=s.split(",");
		for(String val:myValues) {
			System.out.println(val);
			computerService.supprimer(Integer.parseInt(val));
		}
		return "dashboard";
	}
	
	@GetMapping("/editComputer1")
	public String editComputer(HttpServletRequest request, HttpServletResponse response){
		List<Company> listCompanies=companyService.getAllCompanies();
		request.setAttribute("listCompanies",listCompanies);
		return "editComputer";
	}
	
	@PostMapping("/editComputer1")
	public String editComputerPost(HttpServletRequest request, HttpServletResponse response) {
		String nom=request.getParameter("computerName")!=null?request.getParameter("computerName"):null;
		LocalDate introduced=request.getParameter("introduced")!=null?ConverterDate.StringDateToLocalDate(request.getParameter("introduced")):null;
		LocalDate discontinued=request.getParameter("discontinued")!=null?ConverterDate.StringDateToLocalDate(request.getParameter("discontinued")):null;
		String companyId=request.getParameter("companyId")!=null?request.getParameter("companyId"):null;
		computerService.create(new ComputerBuilder().name(nom).introduced(introduced).discontinued(discontinued).company(new Company(0,companyId)).build());
		return "editComputer";
	}
}
