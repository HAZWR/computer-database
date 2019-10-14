package org.db.dao.imp;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.db.connection.ConnectionBD;
import org.db.dao.CompanyDAO;
import org.db.dao.ComputerDAO;
import org.db.mapper.ConverterDate;
import org.db.model.Company;
import org.db.model.Computer;

public class ComputerDAOImpl implements ComputerDAO {
	
	private Statement statement = null;
	private PreparedStatement prepared = null;
	List<Computer> listComputers = new ArrayList<Computer>();
	ResultSet rs = null;
	Computer trouve = null;
    private final static String createQuery="INSERT INTO computer VALUES(?,?,?,?,?)";
    private final static String updateQuery="UPDATE computer SET name=?,introduced=?,discontinued=?,company_id=? WHERE id=?";
    private final static String supprimerQuery="DELETE FROM computer WHERE id = ?";
    private final static String getAllQuery="select * from computer";
    private final static String getIdQuery="select * from computer where id=";
    private final static String getCount="select count(*) as nombre from computer";
	Logger logger=Logger.getLogger("my logger");
	
	@Override
	public void create(Computer nouveau) {
		logger.log(Level.INFO,"Début de l'opération de création ");
		int id=nouveau.getId();
		String name = nouveau.getName();
		LocalDate introduced=nouveau.getIntroduced();
		LocalDate discontinued=nouveau.getDiscontinued();
		int company_id=nouveau.getManufacturer();
		try {
			if (nouveau != null) {
				prepared = ConnectionBD.getInstance().prepareStatement(createQuery);
				logger.log(Level.INFO,"Lancement de la requete de création ");
				prepared.setInt(1, id);
				prepared.setString(2, name);
				prepared.setDate(3, ConverterDate.dateToSql(introduced));
				prepared.setDate(4, ConverterDate.dateToSql(discontinued));
				prepared.setInt(5, company_id);
				prepared.execute();
				logger.log(Level.INFO,"Fin de l'opération de création");
			}
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
	}

	@Override
	public void update(Computer comp) {
		logger.log(Level.INFO,"Début de l'opération de modification");
		try {		
			prepared=ConnectionBD.getInstance().prepareStatement(updateQuery);
			logger.log(Level.INFO,"Lancement de l'opération de modification");
			prepared.setInt(1,comp.getId());
			prepared.setString(2,comp.getName());
			prepared.setDate(3,ConverterDate.dateToSql(comp.getIntroduced())); 
			prepared.setDate(4,ConverterDate.dateToSql(comp.getDiscontinued()));
			prepared.setInt(5,comp.getManufacturer());
			prepared.execute(); 
			logger.log(Level.INFO,"Fin de l'opération de modification");
		}catch(SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
	}

	@Override
	public void supprimer(int id) {
		logger.log(Level.INFO,"Début de l'opération de suppression");
		try {
			prepared = ConnectionBD.getInstance().prepareStatement(supprimerQuery);
			logger.log(Level.INFO,"Lancement de l'opération de suppression");
			prepared.setInt(1,id);
			int relt = prepared.executeUpdate();
			System.out.print(relt);
			logger.log(Level.INFO,"Fin de l'opération de suppression");
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
	}
	@Override
	public List<Computer> getAllComputers() {
		logger.log(Level.INFO,"Début de l'opération d'affichage d'ordinateurs");
		try {
			statement = ConnectionBD.getInstance().createStatement();
			//ConnectionBD.getInstance().setAutoCommit(false);
			rs = statement.executeQuery(getAllQuery);
			logger.log(Level.INFO,"Lancement de l'opération d'affichage d'ordinateurs");
			while (rs.next()) {
				String name = rs.getString("name");
				LocalDate introduced=rs.getObject("introduced",LocalDate.class);
				LocalDate discontinued=rs.getObject("discontinued",LocalDate.class);
				int company=rs.getInt("company_id");
				listComputers.add(new Computer(name,introduced,discontinued,company));
			}
			logger.log(Level.INFO,"Fin de l'opération d'affichage d'ordinateurs");
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
		return listComputers;
	}

	@Override
	public Computer getComputerById(int id) {
		logger.log(Level.INFO,"Début de l'opération d'affichage d'ordinateur by id");
		try {
			logger.log(Level.INFO,"Lancement de l'opération d'affichage d'ordinateur by id");
			statement = ConnectionBD.getInstance().createStatement();
			//ConnectionBD.getInstance().setAutoCommit(false);
			rs = statement.executeQuery(getIdQuery + id);
			while (rs.next()) {
				String name = rs.getString("name");
				LocalDate introduced=rs.getObject("introduced",LocalDate.class);
				LocalDate discontinued=rs.getObject("discontinued",LocalDate.class);
				int company=rs.getInt("company_id");
				trouve = new Computer(name,introduced,discontinued,company);
			}	
			logger.log(Level.INFO,"Fin de l'opération d'affichage d'ordinateur by id");
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
		return trouve;
	}

	@Override
	public int count() {
		try {
			statement=ConnectionBD.getInstance().createStatement();
			rs=statement.executeQuery(getCount);
			int nombreLignes=rs.getInt("nombre");
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
		
		return 0;
	}

	@Override
	public List<Computer> getAllComputersPagination(int nombre,int offset) {
		logger.log(Level.INFO,"Début de l'opération d'affichage d'ordinateurs avec pagination");
		try {
			logger.log(Level.INFO,"Lancement de l'opération d'affichage d'ordinateurs avec pagination");
			statement=ConnectionBD.getInstance().createStatement();
			rs=statement.executeQuery("select * from computer LIMIT"+nombre+ "OFFSET"+offset);
			while (rs.next()) {
				String name = rs.getString("name");
				LocalDate introduced=rs.getObject("introduced",LocalDate.class);
				LocalDate discontinued=rs.getObject("discontinued",LocalDate.class);
				int company=rs.getInt("company_id");
				listComputers.add(new Computer(name,introduced,discontinued,company));
			}
			logger.log(Level.INFO,"Fin de l'opération d'affichage d'ordinateurs avec pagination");
			return listComputers;
		}catch(SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
		return null;
	}
	
	

}
