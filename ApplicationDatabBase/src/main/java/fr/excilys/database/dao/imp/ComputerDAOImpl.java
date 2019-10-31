package fr.excilys.database.dao.imp;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.excilys.database.connection.ConnectionBD;
import fr.excilys.database.dao.ComputerDAO;
import fr.excilys.database.mapper.ComputerMapper;
import fr.excilys.database.mapper.ConverterDate;
import fr.excilys.database.model.Computer;

@Repository
public class ComputerDAOImpl implements ComputerDAO {
	private List<Computer> listComputers = new ArrayList<Computer>();
	private ResultSet rs = null;
    private final static String createQuery="INSERT INTO computer (name,introduced,discontinued,company_id) VALUES(?,?,?,(select id from company where name like ?))";
    private final static String updateQuery="UPDATE computer SET name=?,introduced=?,discontinued=?,company_id=(select id from company where name like ?) WHERE id=?";
    private final static String supprimerQuery="DELETE FROM computer WHERE id = ?";
    private final static String getAllQuery="select * from computer LEFT JOIN company ON computer.company_id=company.id";
    private final static String getIdQuery="select * from computer  LEFT JOIN company ON computer.company_id=company.id where computer.id=?";
    private final static String getCount="select count(*) as nombre from computer";
    private final static String getAllWithPaginatin="select * from computer  LEFT JOIN company  ON computer.company_id=company.id LIMIT ? OFFSET ?";
	private Logger logger=Logger.getLogger("my logger");
	private ComputerMapper computerMapper;
	@Autowired
	private ConnectionBD connection;
	
	@Override
	public boolean create(Computer computer){
		logger.log(Level.INFO,"Début de l'opération de création ");
		boolean bool=false;
		try(PreparedStatement prepared =connection.getConnection().prepareStatement(createQuery);) {
			if (computer != null) {
				logger.log(Level.INFO,"Lancement de la requete de création ");
				prepared.setString(1, computer.getName());
				prepared.setDate(2, ConverterDate.dateToSql(computer.getIntroduced()));
				prepared.setDate(3, ConverterDate.dateToSql(computer.getDiscontinued()));
				prepared.setString(4, computer.getManufacturer().getName());
				prepared.executeUpdate();
				logger.log(Level.INFO,"Fin de l'opération de création");
				bool=true;
			}
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}finally{
			ConnectionBD.closeConnection();
		}
		return bool;
	}

	@Override
	public boolean update(Computer computer){
		logger.log(Level.INFO,"Début de l'opération de modification");
		boolean etat=false;
		try(PreparedStatement prepared=connection.getConnection().prepareStatement(updateQuery);) {		
			logger.log(Level.INFO,"Lancement de l'opération de modification");
			prepared.setString(1,computer.getName());
			prepared.setDate(2,ConverterDate.dateToSql(computer.getIntroduced())); 
			prepared.setDate(3,ConverterDate.dateToSql(computer.getDiscontinued()));
			prepared.setString(4,computer.getManufacturer().getName());
			prepared.setInt(5, computer.getId());
			prepared.execute(); 
			etat=true;
			logger.log(Level.INFO,"Fin de l'opération de modification");
		}catch(SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}finally{
			ConnectionBD.closeConnection();
		}
		return etat;
	}

	@Override
	public boolean supprimer(int id){
		logger.log(Level.INFO,"Début de l'opération de suppression");
		boolean etat=false;
		try (PreparedStatement prepared = connection.getConnection().prepareStatement(supprimerQuery); ){
			logger.log(Level.INFO,"Lancement de l'opération de suppression");
			prepared.setInt(1,id);
			int relt = prepared.executeUpdate();
			System.out.print(relt);
			etat=true;
			logger.log(Level.INFO,"Fin de l'opération de suppression");
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}finally{
			ConnectionBD.closeConnection();
		}
		return etat;
	}
	@Override
	public List<Computer> getAllComputers(){
		logger.log(Level.INFO,"Début de l'opération d'affichage d'ordinateurs");
		try (PreparedStatement prepared = connection.getConnection().prepareStatement(getAllQuery); ){
			rs = prepared.executeQuery();
			logger.log(Level.INFO,"Lancement de l'opération d'affichage d'ordinateurs");
			computerMapper=ComputerMapper.getInstance();
			while (rs.next()) {
				listComputers.add(computerMapper.convertSQLtoComputer(rs));
			}
			logger.log(Level.INFO,"Fin de l'opération d'affichage d'ordinateurs");
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}finally{
			ConnectionBD.closeConnection();
		}
		return listComputers;
	}

	@Override
	public Computer getComputerById(int id){
		Computer trouve = null;
		logger.log(Level.INFO,"Début de l'opération d'affichage d'ordinateur by id");
		try(PreparedStatement prepared = connection.getConnection().prepareStatement(getIdQuery);) {
		    logger.log(Level.INFO,"Lancement de l'opération d'affichage d'ordinateur by id");
			prepared.setInt(1, id);
			rs = prepared.executeQuery();
			computerMapper=ComputerMapper.getInstance();
			while (rs.next()) {
			trouve=computerMapper.convertSQLtoComputer(rs);
			}	
			logger.log(Level.INFO,"Fin de l'opération d'affichage d'ordinateur by id");
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}finally{
			ConnectionBD.closeConnection();
		}
		return trouve;
	}

	@Override
	public int count(){
		try(PreparedStatement prepared=connection.getConnection().prepareStatement(getCount);) {
			rs=prepared.executeQuery();
			if (rs.first()) {
			return rs.getInt("nombre");
			}
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}finally{
			ConnectionBD.closeConnection();
		}
		
		return 0;
	}

	@Override
	public List<Computer> getAllComputersPagination(int nombre,int offset){
		logger.log(Level.INFO,"Début de l'opération d'affichage d'ordinateurs avec pagination");
		try (PreparedStatement prepared=connection.getConnection().prepareStatement(getAllWithPaginatin); ){
			logger.log(Level.INFO,"Lancement de l'opération d'affichage d'ordinateurs avec pagination");
			prepared.setInt(1, nombre);
			prepared.setInt(2, offset);
			rs=prepared.executeQuery();
			computerMapper=ComputerMapper.getInstance();
			while (rs.next()) {
				listComputers.add(computerMapper.convertSQLtoComputer(rs));
			}
			logger.log(Level.INFO,"Fin de l'opération d'affichage d'ordinateurs avec pagination");
			return listComputers;
		}catch(SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}finally{
			ConnectionBD.closeConnection();
		}
		return null;
	}

}
