//package fr.excilys.database.servlet;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.context.support.SpringBeanAutowiringSupport;
//
//import fr.excilys.database.mapper.ConverterDate;
//import fr.excilys.database.model.Company;
//import fr.excilys.database.model.Computer;
//import fr.excilys.database.model.Computer.ComputerBuilder;
//import fr.excilys.database.service.CompanyService;
//import fr.excilys.database.service.ComputerService;
//
///**
// * Servlet implementation class editComputer
// */
//@Controller
//@WebServlet("/editComputer")
//public class editComputer extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	@Autowired
//	private ComputerService computerService;
//	@Autowired
//	private CompanyService companyService;
//	private List<Company> listCompanies; 
//	private Logger logger=Logger.getLogger("my logger");
//	
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//	}
//	
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public editComputer() {
//        super();
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.setProperty("testing","false");
//		listCompanies=companyService.getAllCompanies();
//		request.setAttribute("listCompanies",listCompanies);
//		if(request.getParameter("id")!=null) {
//			int id=Integer.parseInt(request.getParameter("id"));
//		Computer ordi = computerService.getComputerById(id);
//		request.setAttribute("ordi", ordi);
//		}	
//		this.getServletContext().getRequestDispatcher("/views/editComputer.jsp").forward(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int id=request.getParameter("id")!=null?Integer.parseInt(request.getParameter("id")):null;
//		String nom=request.getParameter("nom")!=null?request.getParameter("nom"):null;
//		LocalDate introduced=request.getParameter("introduced")!=null?ConverterDate.StringDateToLocalDate(request.getParameter("introduced")):null;
//		LocalDate discontinued=request.getParameter("discontinued")!=null?ConverterDate.StringDateToLocalDate(request.getParameter("discontinued")):null;
//		String companyId=request.getParameter("companyId")!=null?request.getParameter("companyId"):null;
//		boolean cr = computerService.update(new ComputerBuilder().id(id).name(nom).introduced(introduced).discontinued(discontinued).company(new Company(0,companyId)).build());
//			if(cr==true)
//				logger.log(Level.INFO,"Création réussi dans la servlet AddComputer");
//			else
//				logger.log(Level.INFO,"Création non réussi dans la servlet AddComputer");
//		doGet(request, response);
//	}
//
//}
