package fr.excilys.database.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.excilys.database.model.Computer;

public interface ComputerDAO extends JpaRepository<Computer,Integer>{
	
	@Query("select comp from computer comp LEFT JOIN company  ON comp.company_id=company.id where comp.name=(:name)")	
	List<Computer> findByName(@Param("name") String name);
	
	Computer findById(int id);
	
	@Query("select count(comp) as nombre from computer comp where comp.name=(:searchName)")	
	int count(@Param("searchName")String searchName);
	
}
