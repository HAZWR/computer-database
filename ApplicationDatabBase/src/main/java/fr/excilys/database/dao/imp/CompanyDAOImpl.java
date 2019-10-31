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
import fr.excilys.database.dao.CompanyDAO;
import fr.excilys.database.mapper.CompanyMapper;
import fr.excilys.database.model.Company;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

	private List<Company> listCompanies = new ArrayList<Company>();
	private ResultSet rs = null;
	private final static String getAllCompanies="select * from company";
	private final static String getCount="select count(id) as nombre from company";
	private final static String getWithPagination="select * from company LIMIT ? OFFSET ?";
	private Logger logger=Logger.getLogger("my logger");
	private CompanyMapper computerMapper;
	@Autowired
	private ConnectionBD connection;
	
	
	@Override
	public List<Company> getAllCompanies() {
		logger.log(Level.INFO,"Début de l'opération d'affichage de toutes les companies");
		try (PreparedStatement prepared = connection.getConnection().prepareStatement(getAllCompanies);) {
			computerMapper=CompanyMapper.getInstance();
			rs = prepared.executeQuery();
			logger.log(Level.INFO,"Lancement de l'opération d'affichage de toutes les companies");
			while (rs.next()) {
				listCompanies.add(computerMapper.convertSQLtoCompany(rs));
			}
			logger.log(Level.INFO,"Fin de l'opération d'affichage de toutes les companies");
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}finally{
			ConnectionBD.closeConnection();
		}
		return listCompanies;
	}

	@Override
	public int countCompanies() {
		int nombreLignes=0;
		try (PreparedStatement prepared = connection.getConnection().prepareStatement(getCount);){
			rs=prepared.executeQuery();
			nombreLignes=rs.getInt("nombre");
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}finally{
			ConnectionBD.closeConnection();
		}
		return nombreLignes;
	}

	@Override
	public List<Company> getAllCompaniesPagination(int nombre,int offset) {
		logger.log(Level.INFO,"Début de l'opération d'affichage d'ordinateurs avec pagination");
		try(PreparedStatement prepared = connection.getConnection().prepareStatement(getWithPagination);) {
			logger.log(Level.INFO,"Lancement de l'opération d'affichage d'ordinateurs avec pagination");
			prepared.setInt(1, nombre);
			prepared.setInt(2, offset);
			rs=prepared.executeQuery();			
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
		}finally{
			ConnectionBD.closeConnection();
		}
		return null;
	}
}
