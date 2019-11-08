package fr.excilys.database.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.excilys.database.dto.ComputerDTO;
import fr.excilys.database.mapper.ComputerMapper;
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
	
	@Autowired
	private ComputerMapper computerMapper;
	
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
		model.addAttribute("computerDTO", new ComputerDTO());
		model.addAttribute("listCompanies",listCompanies);
		return "addComputer";
	}
	
	@GetMapping("/editComputer")
	public String editComputer(Model model,@RequestParam(name="id")int id){
		List<Company> listCompanies=companyService.getAllCompanies();
		model.addAttribute("listCompanies",listCompanies);
		Computer ordi = computerService.getComputerById(id);
		ComputerDTO computer=computerMapper.computerToComputerDto(ordi);
		model.addAttribute("computer", computer);
		return "editComputer";
	}
	
	@PostMapping("/addComputer")
	public String addComputerPost(@ModelAttribute("computerDTO") ComputerDTO computerDto,Model model) {
		Computer computer=computerMapper.computerDtoToComputer(computerDto);
		computerService.create(computer);
		return "addComputer";
	}
	
	@PostMapping("/menu")
	public String deleteComputer(@RequestParam(name="selection")String selection) {
		String[] myValues=selection.split(",");
		for(String val:myValues) {
			System.out.println(val);
			computerService.supprimer(Integer.parseInt(val));
		}
		return "dashboard";
	}
	
	@PostMapping("/editComputer")
	public String editComputerPost(@ModelAttribute("computer") ComputerDTO computerDto,Model model) {
		Computer computer=computerMapper.computerDtoToComputer(computerDto);
		computerService.update(computer);
		return "editComputer";
	}
}
