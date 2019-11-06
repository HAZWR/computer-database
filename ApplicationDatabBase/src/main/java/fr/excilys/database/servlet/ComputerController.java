package fr.excilys.database.servlet;

import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.excilys.database.dao.imp.ComputerDAOImpl;
import fr.excilys.database.model.Computer;
import fr.excilys.database.service.ComputerService;

import java.util.List;
@Controller
public class ComputerController {

	@Autowired
	ComputerService computerService;
	
	
	@RequestMapping(path= "/menuComputer",method=RequestMethod.GET)
	public String getAllComputers(Model model){
		List<Computer> listComputers=computerService.getAllComputers();
		model.addAttribute("listComputers", listComputers);
		return "dashboard";
	}
	
}
