package fr.excilys.database.dao.imp;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import fr.excilys.database.dao.ComputerDAO;
import fr.excilys.database.mapper.ComputerMapper;
import fr.excilys.database.mapper.ConverterDate;
import fr.excilys.database.model.Computer;

@Repository
public class ComputerDAOImpl implements ComputerDAO {
    private final static String createQuery="INSERT INTO computer (name,introduced,discontinued,company_id) VALUES(?,?,?,(select id from company where name like ?))";
    private final static String updateQuery="UPDATE computer SET name=?,introduced=?,discontinued=?,company_id=(select id from company where name like ?) WHERE id=?";
    private final static String supprimerQuery="DELETE FROM computer WHERE id = ?";
    private final static String getAllQuery="select * from computer LEFT JOIN company ON computer.company_id=company.id";
    private final static String getIdQuery="select * from computer  LEFT JOIN company ON computer.company_id=company.id where computer.id=?";
    private final static String getCount="select count(*) as nombre from computer where name like ?";
    private final static String getAllWithPaginatin="select * from computer  LEFT JOIN company  ON computer.company_id=company.id LIMIT ? OFFSET ?";
    private final static String getComputerByName="select * from computer  LEFT JOIN company  ON computer.company_id=company.id where computer.name like ?";
	private Logger logger=Logger.getLogger("my logger");
	@Autowired
	private ComputerMapper computerMapper;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean create(Computer computer){
		logger.log(Level.INFO,"Début de l'opération de création ");
		jdbcTemplate.update(createQuery,computer.getName(),ConverterDate.dateToSql(computer.getIntroduced()),ConverterDate.dateToSql(computer.getDiscontinued()),computer.getManufacturer().getName());
		return true;
	}

	@Override
	public boolean update(Computer computer){
		logger.log(Level.INFO,"Début de l'opération de modification");
		jdbcTemplate.update(updateQuery,computer.getName(),ConverterDate.dateToSql(computer.getIntroduced()),ConverterDate.dateToSql(computer.getDiscontinued()),computer.getManufacturer().getName(),computer.getId());
		return true;
	}

	@Override
	public boolean supprimer(int id){
		logger.log(Level.INFO,"Début de l'opération de suppression");
		jdbcTemplate.update(supprimerQuery, id);
		return true;
	}
	@Override
	public List<Computer> getAllComputers(){
		logger.log(Level.INFO,"Début de l'opération d'affichage d'ordinateurs");
		return jdbcTemplate.query(getAllQuery,computerMapper);
	}

	@Override
	public Computer getComputerById(int id){
		logger.log(Level.INFO,"Début de l'opération d'affichage d'ordinateur by id");
		return jdbcTemplate.queryForObject(getIdQuery,computerMapper, id);
	}

	@Override
	public int count(String name){
		logger.log(Level.INFO,"Début de l'opération d'affichage de comptage de computers");
		return jdbcTemplate.queryForObject(getCount, Integer.class,"%"+name+"%");
	}

	@Override
	public List<Computer> getAllComputersPagination(int nombre,int offset){
		logger.log(Level.INFO,"Début de l'opération d'affichage d'ordinateurs avec pagination");
		return jdbcTemplate.query(getAllWithPaginatin,computerMapper,nombre,offset);
	}
	
	@Override
	public List<Computer> getComputerByName(String name) {
		logger.log(Level.INFO,"Début de l'opération de récupération d'un computer à partir de son nom");
		return jdbcTemplate.query(getComputerByName,computerMapper,"%"+name+"%");
	}

}
