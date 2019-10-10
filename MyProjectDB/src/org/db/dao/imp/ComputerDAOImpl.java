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

import org.db.connection.ConnectionBD;
import org.db.dao.CompanyDAO;
import org.db.dao.ComputerDAO;
import org.db.mapper.HelperDate;
import org.db.model.Company;
import org.db.model.Computer;

public class ComputerDAOImpl implements ComputerDAO {
	
	private Statement statement = null;
	private PreparedStatement prepared = null;
	List<Computer> listComputers = new ArrayList<Computer>();
	ResultSet rs = null;
	Computer trouve = null;

	
	@Override
	public void create(Computer nouveau) {
		int id=nouveau.getId();
		String name = nouveau.getName();
		LocalDate introduced=nouveau.getIntroduced();
		LocalDate discontinued=nouveau.getDiscontinued();
		int company_id=nouveau.getManufacturer();
		try {
			if (nouveau != null) {
				prepared = ConnectionBD.getInstance().prepareStatement("INSERT INTO computer VALUES(?,?,?,?,?)");
				prepared.setInt(1, id);
				prepared.setString(2, name);
				prepared.setDate(3, HelperDate.dateToSql(introduced));
				prepared.setDate(4, HelperDate.dateToSql(discontinued));
				prepared.setInt(5, company_id);
				prepared.execute();
			}
			System.out.println("Création efféctuée avec succès de l'ordinateur : "+name);
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
	}

	@Override
	public void update(Computer comp) {
		try {		
			prepared=ConnectionBD.getInstance().prepareStatement("UPDATE computer SET name=?,introduced=?,discontinued=?,company_id=? WHERE id=?");
			prepared.setInt(1,comp.getId());
			prepared.setString(2,comp.getName());
			prepared.setDate(3,HelperDate.dateToSql(comp.getIntroduced())); 
			prepared.setDate(4,HelperDate.dateToSql(comp.getDiscontinued()));
			prepared.setInt(5,comp.getManufacturer());
			prepared.execute(); 
		}catch(SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
	}

	@Override
	public void supprimer(int id) {
		try {
			prepared = ConnectionBD.getInstance().prepareStatement("DELETE FROM computer WHERE id = ?");
			prepared.setInt(1,id);
			int relt = prepared.executeUpdate();
			System.out.print(relt);
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
	}
	@Override
	public List<Computer> getAllComputers() {

		try {
			statement = ConnectionBD.getInstance().createStatement();
			//ConnectionBD.getInstance().setAutoCommit(false);
			rs = statement.executeQuery("select * from computer");
			while (rs.next()) {
				String name = rs.getString("name");
				LocalDate introduced=rs.getObject("introduced",LocalDate.class);
				LocalDate discontinued=rs.getObject("discontinued",LocalDate.class);
				int company=rs.getInt("company_id");
				listComputers.add(new Computer(name,introduced,discontinued,company));
			}
			for (Computer r : listComputers) {
				System.out.println("Nom des computers : " + r.getName());
			}
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
		return listComputers;
	}

	@Override
	public Computer getComputerById(int id) {

		try {
			statement = ConnectionBD.getInstance().createStatement();
			//ConnectionBD.getInstance().setAutoCommit(false);
			rs = statement.executeQuery("select * from computer where id=" + id);
			while (rs.next()) {
				String name = rs.getString("name");
				LocalDate introduced=rs.getObject("introduced",LocalDate.class);
				LocalDate discontinued=rs.getObject("discontinued",LocalDate.class);
				int company=rs.getInt("company_id");
				trouve = new Computer(name,introduced,discontinued,company);
			}
				System.out.println(" Nom : " + trouve.getName()+" Introduced : "+trouve.getIntroduced()+" Discontinued : "+trouve.getDiscontinued()+" Company : "+trouve.getManufacturer());
				
		} catch (SQLException se) {
			for(Throwable e : se) {
                System.err.println("Erreurs : " + e);
            }
		}
		return trouve;
	}

}
