package fr.excilys.database.dao.imp;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import fr.excilys.database.dao.CompanyDAO;
import fr.excilys.database.mapper.CompanyMapper;
import fr.excilys.database.model.Company;

@Repository
public class CompanyDAOImpl implements CompanyDAO {
	private final static String getAllCompanies = "select * from company";
	private final static String getCount = "select count(id) as nombre from company";
	private final static String getWithPagination = "select * from company LIMIT ? OFFSET ?";
	private Logger logger = Logger.getLogger("my logger");
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Company> getAllCompanies() {
		logger.log(Level.INFO, "Début de l'opération d'affichage de toutes les companies");
		return jdbcTemplate.query(getAllCompanies, companyMapper);
	}

	@Override
	public int countCompanies() {
		return jdbcTemplate.queryForObject(getCount, Integer.class);
	}

	@Override
	public List<Company> getAllCompaniesPagination(int nombre, int offset) {
		return jdbcTemplate.query(getWithPagination, companyMapper, nombre, offset);
	}
}
