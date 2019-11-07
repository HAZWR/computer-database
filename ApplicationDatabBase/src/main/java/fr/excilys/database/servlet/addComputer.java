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
//import fr.excilys.database.model.Computer.ComputerBuilder;
//import fr.excilys.database.service.CompanyService;
//import fr.excilys.database.service.ComputerService;
//@Controller
//@WebServlet("/addComputer")
//public class addComputer extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	@Autowired
//	private ComputerService computerService;
//	@Autowired
//	private CompanyService companyService;
//	private List<Company> listCompanies;
//	private Logger logger=Logger.getLogger("my logger"); 
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//	
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//	}
//	
//    public addComputer() {
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
//		this.getServletContext().getRequestDispatcher("/views/addComputer.jsp").forward(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String nom=request.getParameter("computerName")!=null?request.getParameter("computerName"):null;
//		LocalDate introduced=request.getParameter("introduced")!=null?ConverterDate.StringDateToLocalDate(request.getParameter("introduced")):null;
//		LocalDate discontinued=request.getParameter("discontinued")!=null?ConverterDate.StringDateToLocalDate(request.getParameter("discontinued")):null;
//		String companyId=request.getParameter("companyId")!=null?request.getParameter("companyId"):null;
//		boolean cr = computerService.create(new ComputerBuilder().name(nom).introduced(introduced).discontinued(discontinued).company(new Company(0,companyId)).build());
//			if(cr==true)
//				logger.log(Level.INFO,"Création réussie dans la servlet AddComputer");
//			else
//				logger.log(Level.INFO,"Création non réussie dans la servlet AddComputer");
//		doGet(request, response);
//	}
//
//}
//
