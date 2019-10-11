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

	@Override
	public List<Company> getAllCompanies() {
		try {
			statement = ConnectionBD.getInstance().createStatement();
			ConnectionBD.getInstance().setAutoCommit(false);
			rs = statement.executeQuery("select * from company");
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
			rs = statement.executeQuery("select * from company where id=" + id);
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

	@Override
	public void create(Company nouveau) {
		String name = nouveau.getName();
		try {
			if (nouveau != null) {
				prepared = ConnectionBD.getInstance().prepareStatement("INSERT INTO company(name) VALUES(?)");
				prepared.setString(2, name);
				prepared.execute();
			}
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
	}

	@Override
	public void update(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void supprimer(int id) {
		try {
			prepared = ConnectionBD.getInstance().prepareStatement("DELETE FROM company WHERE id=?");
			prepared.setInt(1,id);
			prepared.execute();
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
	}

}
