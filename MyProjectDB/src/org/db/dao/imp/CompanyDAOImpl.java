package org.db.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
	
	@Override
	public List<Company> getAllCompanies() {
		try {
			statement = ConnectionBD.getInstance().createStatement();
			rs = statement.executeQuery(getAllCompanies);
			while (rs.next()) {
				String name = rs.getString("name");
				listCompanies.add(new Company(name));
			}
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
		return listCompanies;
	}

	@Override
	public Company getCompanyById(int id) {
		try {
			statement = ConnectionBD.getInstance().createStatement();
			ConnectionBD.getInstance().setAutoCommit(false);
			rs = statement.executeQuery(getIdCompany + id);
			while (rs.next()) {
				String name = rs.getString("name");
				trouve = new Company(name);
			}
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
		return trouve;
	}

}
