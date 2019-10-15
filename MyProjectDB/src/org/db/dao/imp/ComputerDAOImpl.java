package org.db.dao.imp;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.db.connection.ConnectionBD;
import org.db.dao.ComputerDAO;
import org.db.mapper.ConverterDate;
import org.db.model.Company;
import org.db.model.Computer;

public class ComputerDAOImpl implements ComputerDAO {
	
	private Statement statement = null;
	private PreparedStatement prepared = null;
	List<Computer> listComputers = new ArrayList<Computer>();
	ResultSet rs = null;
    private final static String createQuery="INSERT INTO computer (name,introduced,discontinued,company_id) VALUES(?,?,?,(select id from company where name like ?))";
    private final static String updateQuery="UPDATE computer SET name=?,introduced=?,discontinued=?,company_id=(select id from company where name like ?) WHERE id=?";
    private final static String supprimerQuery="DELETE FROM computer WHERE id = ?";
    private final static String getAllQuery="select * from computer AS cmpt LEFT JOIN company AS cmpa ON cmpt.company_id=cmpa.id";
    private final static String getIdQuery="select * from computer AS cmpt where id=? LEFT JOIN company AS cmpa ON cmpt.company_id=cmpa.id";
    private final static String getCount="select count(id) as nombre from computer";
	Logger logger=Logger.getLogger("my logger");
	
	@Override
	public void create(Computer nouveau) {
		logger.log(Level.INFO,"Début de l'opération de création ");
		String name = nouveau.getName();
		LocalDate introduced=nouveau.getIntroduced();
		LocalDate discontinued=nouveau.getDiscontinued();
		String company_id=nouveau.getManufacturer().getName();
		try {
			if (nouveau != null) {
				prepared = ConnectionBD.getInstance().prepareStatement(createQuery);
				logger.log(Level.INFO,"Lancement de la requete de création ");
				prepared.setString(1, name);
				prepared.setDate(2, ConverterDate.dateToSql(introduced));
				prepared.setDate(3, ConverterDate.dateToSql(discontinued));
				prepared.setString(4, company_id);
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
			prepared.setString(1,comp.getName());
			prepared.setDate(2,ConverterDate.dateToSql(comp.getIntroduced())); 
			prepared.setDate(3,ConverterDate.dateToSql(comp.getDiscontinued()));
			prepared.setString(4,comp.getManufacturer().getName());
			prepared.setInt(5, comp.getId());
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
			prepared = ConnectionBD.getInstance().prepareStatement(getAllQuery);
			rs = prepared.executeQuery();
			logger.log(Level.INFO,"Lancement de l'opération d'affichage d'ordinateurs");
			while (rs.next()) {
				Computer computer = new Computer();
				computer.setId(rs.getInt("cmpt.id"));
				computer.setName(rs.getString("cmpt.name"));
				computer.setIntroduced(rs.getDate("cmpt.introduced").toLocalDate());
				computer.setDiscontinued(rs.getDate("cmpt.discontinued").toLocalDate());
				computer.setManufacturer(new Company(rs.getInt("cmpa.id"),rs.getString("cmpa.name")));
				listComputers.add(computer);
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
		Computer trouve=new Computer();
		logger.log(Level.INFO,"Début de l'opération d'affichage d'ordinateur by id");
		try {
			logger.log(Level.INFO,"Lancement de l'opération d'affichage d'ordinateur by id");
			prepared = ConnectionBD.getInstance().prepareStatement(getIdQuery);
			rs = prepared.executeQuery();
			while (rs.next()) {
				trouve.setId(rs.getInt("cmpt.id"));
				trouve.setName(rs.getString("cmpt.name"));
				trouve.setIntroduced(rs.getDate("cmpt.introduced").toLocalDate());
				trouve.setDiscontinued(rs.getDate("cmpt.discontinued").toLocalDate());
				trouve.setManufacturer(new Company(rs.getInt("cmpa.id"),rs.getString("cmpa.name")));
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
			prepared=ConnectionBD.getInstance().prepareStatement(getCount);
			rs=prepared.executeQuery();
			return rs.getInt("nombre");
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
		
		return 0;
	}

	@Override
	public List<Computer> getAllComputersPagination(int nombre,int offset) {
//		logger.log(Level.INFO,"Début de l'opération d'affichage d'ordinateurs avec pagination");
//		try {
//			logger.log(Level.INFO,"Lancement de l'opération d'affichage d'ordinateurs avec pagination");
//			statement=ConnectionBD.getInstance().createStatement();
//			rs=statement.executeQuery("select * from computer AS cmpt LEFT JOIN company AS cmpa ON cmpt.company_id=cmpa.id LIMIT"+nombre+ "OFFSET"+offset);
//			while (rs.next()) {
//				String name = rs.getString("cmpt.name");
//				LocalDate introduced=rs.getObject("cmpt.introduced",LocalDate.class);
//				LocalDate discontinued=rs.getObject("cmpt.discontinued",LocalDate.class);
//				Company company=new Company(rs.getInt("cmpa.id"),rs.getString("cmpa.name"));
//				listComputers.add(new Computer(name,introduced,discontinued,company));
//			}
//			logger.log(Level.INFO,"Fin de l'opération d'affichage d'ordinateurs avec pagination");
//			return listComputers;
//		}catch(SQLException se) {
//			for(Throwable e : se) {
//                System.err.println("Erreurs : " + e);
//            }
//		}
		return null;
	}
	
	

}
