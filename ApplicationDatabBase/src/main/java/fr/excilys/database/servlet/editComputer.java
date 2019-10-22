package fr.excilys.database.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.database.dao.imp.CompanyDAOImpl;
import fr.excilys.database.dao.imp.ComputerDAOImpl;
import fr.excilys.database.mapper.ConverterDate;
import fr.excilys.database.model.Company;
import fr.excilys.database.model.Computer.ComputerBuilder;

/**
 * Servlet implementation class editComputer
 */
@WebServlet("/editComputer")
public class editComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ComputerDAOImpl comp;
	CompanyDAOImpl compa;
	List<Company> listCompanies; 
	Logger logger=Logger.getLogger("my logger");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.setProperty("testing","false");
		compa=new CompanyDAOImpl();
		listCompanies=compa.getAllCompanies();
		request.setAttribute("listCompanies",listCompanies);
		this.getServletContext().getRequestDispatcher("/views/editComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		comp=new ComputerDAOImpl();
		String nom=request.getParameter("nom")!=null?request.getParameter("nom"):null;
		LocalDate introduced=request.getParameter("introducedDate")!=null?ConverterDate.StringDateToLocalDate(request.getParameter("introducedDate")):null;
		LocalDate discontinued=request.getParameter("discontinuedDate")!=null?ConverterDate.StringDateToLocalDate(request.getParameter("discontinuedDate")):null;
		String companyId=request.getParameter("companyEntity")!=null?request.getParameter("companyEntity"):null;
		boolean cr=comp.create(new ComputerBuilder().name(nom).introduced(introduced).discontinued(discontinued).company(new Company(0,companyId)).build());
			if(cr==true)
				logger.log(Level.INFO,"Création réussi dans la servlet AddComputer");
			else
				logger.log(Level.INFO,"Création non réussi dans la servlet AddComputer");
		doGet(request, response);
	}

}
