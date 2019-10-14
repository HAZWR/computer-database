package org.db.dao.imp;

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
import org.db.model.Company;
import org.db.model.Computer;

public class CompanyDAOImpl implements CompanyDAO {

	private Statement statement = null;
	private PreparedStatement prepared = null;
	List<Company> listCompanies = new ArrayList<Company>();
	ResultSet rs = null;
	Company trouve = null;
	private final static String getAllCompanies="select * from company";
	private final static String getIdCompany="select * from company where id=";
	 private final static String getCount="select count(*) as nombre from company";
	Logger logger=Logger.getLogger("my logger");
	
	@Override
	public List<Company> getAllCompanies() {
		logger.log(Level.INFO,"Début de l'opération d'affichage de toutes les companies");
		try {
			statement = ConnectionBD.getInstance().createStatement();
			rs = statement.executeQuery(getAllCompanies);
			logger.log(Level.INFO,"Lancement de l'opération d'affichage de toutes les companies");
			while (rs.next()) {
				String name = rs.getString("name");
				listCompanies.add(new Company(name));
			}
			logger.log(Level.INFO,"Fin de l'opération d'affichage de toutes les companies");
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
		return listCompanies;
	}

	@Override
	public Company getCompanyById(int id) {
		logger.log(Level.INFO,"Début de l'opération de récupération de companie par id");
		try {
			statement = ConnectionBD.getInstance().createStatement();
			ConnectionBD.getInstance().setAutoCommit(false);
			rs = statement.executeQuery(getIdCompany + id);
			logger.log(Level.INFO,"Lancement de l'opération de récupération de companie par id");
			while (rs.next()) {
				String name = rs.getString("name");
				trouve = new Company(name);
			}
			logger.log(Level.INFO,"Fin de l'opération de récupération de companie par id");
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
		return trouve;
	}
	
	@Override
	public int countCompanies() {
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
	public List<Company> getAllCompaniesPagination(int nombre,int offset) {
		logger.log(Level.INFO,"Début de l'opération d'affichage d'ordinateurs avec pagination");
		try {
			logger.log(Level.INFO,"Lancement de l'opération d'affichage d'ordinateurs avec pagination");
			statement=ConnectionBD.getInstance().createStatement();
			rs=statement.executeQuery("select * from company LIMIT"+nombre+ "OFFSET"+offset);
			while (rs.next()) {
				String name = rs.getString("name");
				listCompanies.add(new Company(name));
			}
			logger.log(Level.INFO,"Fin de l'opération d'affichage d'ordinateurs avec pagination");
			return listCompanies;
		}catch(SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
		return null;
	}
}
