

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Model.Company;
import Model.Computer;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("hello world");
		List<Computer> listOrdinateurs=new ArrayList<Computer>();
		List<Company> listCompanies=new ArrayList<Company>();		
		Scanner scan=new Scanner(System.in);
		int nombre=scan.nextInt();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/computer-database-db","root","root");  
			stmt=con.createStatement();  
			rs=stmt.executeQuery("select * from computer");  
			while(rs.next()) {  
				String name= rs.getString("name");
				Timestamp introduced=rs.getTimestamp("introduced");
				Timestamp discontinued=rs.getTimestamp("discontinued");	
				String company=rs.getString("company_id");
				listOrdinateurs.add(new Computer(name,introduced,discontinued,company));
			} 
			rs=stmt.executeQuery("select * from company");
			while(rs.next()) {  
				String name= rs.getString("name");
				listCompanies.add(new Company(name));
			} 
			switch(nombre) {
			case 1:
				for(Computer c1:listOrdinateurs){
					System.out.println("name:"+c1.getName()+" introduced:"+c1.getIntroduced()+" discontinued:"+c1.getDiscontinued());					
				}
			case 2:
				for(Company c1:listCompanies){
					System.out.println("name:"+c1.getName());					
				}
			
			}
		}catch(Exception e){ 
			System.out.println(e); 
		}
//		}finally{ 
//			rs.close();
//			stmt.close();
//			con.close();
//		} 
}
	}


