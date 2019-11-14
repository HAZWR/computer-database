package fr.excilys.database.dao;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import fr.excilys.database.model.Computer;

@Repository
@Transactional
public interface ComputerDAO extends JpaRepository<Computer,Integer>{
	
	@Query(value = "select * from computer  LEFT JOIN company ON computer.company_id=company.id where computer.name like :name or company.name like :name", nativeQuery = true)	
	List<Computer> findByName(@Param("name")String name);
	
	@Query(value="select * from computer  LEFT JOIN company ON computer.company_id=company.id where computer.id like :idFind",nativeQuery=true)
	Computer findById(@Param("idFind")int id);
	
	@Query(value="select count(*) as nombre from computer LEFT JOIN company ON computer.company_id=company.id where computer.name like :searchName or company.name like :searchName", nativeQuery = true)	
	int count(@Param("searchName")String searchName);
	
	@Modifying
	@Query(value="UPDATE computer SET name=?,introduced=?,discontinued=?,company_id=(select id from company where name like ?) WHERE id=?",nativeQuery=true)
	void updateComputer(@Param("name")String name,@Param("introduced")LocalDate introduced,@Param("introduced")LocalDate discontinued,@Param("companyName")String companyName,@Param("idComputer")int idComputer);
}
