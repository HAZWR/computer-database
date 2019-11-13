package fr.excilys.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.excilys.database.model.Company;

public interface CompanyDAO extends JpaRepository<Company,Integer>{

}
