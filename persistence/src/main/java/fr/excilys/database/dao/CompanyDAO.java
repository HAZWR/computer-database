package fr.excilys.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import fr.excilys.database.model.Company;

@Repository
@Transactional
public interface CompanyDAO extends JpaRepository<Company,Integer>{

}
