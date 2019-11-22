
package fr.excilys.database.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import fr.excilys.database.dto.ComputerDTO;
import fr.excilys.database.model.Computer;
import fr.excilys.database.service.ComputerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="Computer API", description="Operations for managing a computer")
@RestController
@RequestMapping("/api/v1/computer")
public class RestComputerController {
private ComputerService computerService;
	
	public RestComputerController(ComputerService  computerService) {
		this.computerService=computerService;
	}
	
	@ApiOperation(value = "View the list of computers")
	@GetMapping
	public List<Computer> getListComputers(){
		return computerService.getAllComputers();
	}
	
	@ApiOperation(value = "Create a new computer")
	@PostMapping
	public void createComputer(@ApiParam(value = "Computer object store in database table", required = true) @Valid @RequestBody ComputerDTO computer) {
		computerService.create(computer);
	}
	
	@ApiOperation(value = "Delete ingredient")
	@DeleteMapping("/{id}")
	public void deleteComputer(@ApiParam(value = "Computer id from which computer object will retrieve", required = true)@PathVariable(value="id")int id) {
		computerService.supprimer(id);
	}
	
	@ApiOperation(value = "Update ingredient")
	@PutMapping
	public void updateComputer(@ApiParam(value = "Employee object store in database table", required = true) @Valid @RequestBody ComputerDTO computer) {
		computerService.update(computer);
	}
	
	@ApiOperation(value = "View the list of computers by name")
	@GetMapping(value="/ByName")
	public List<ComputerDTO> getComputerByName(@ApiParam(value = "Computer name from which computer object will retrieve", required = true)@RequestParam(name="name")String name){
		return computerService.getComputerByName(name);
	}
}
