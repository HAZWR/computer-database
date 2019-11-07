//package fr.excilys.database.servlet;
//
//
//import java.io.IOException;
//import java.util.List;
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
//import fr.excilys.database.model.Computer;
//import fr.excilys.database.service.ComputerService;
//
//
//
///**
// * Servlet implementation class test
// */
//@Controller
//@WebServlet("/menu")
//public class Menu extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	@Autowired
//	private ComputerService computerService;
//	private List<Computer> listComputers;
//	private int nombre=0;
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
//    public Menu() {
//        super();
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.setProperty("testing","false");
//		listComputers=computerService.getAllComputers();
//		nombre=computerService.count();
//		request.setAttribute("listComputers", listComputers);
//		request.setAttribute("nombre",nombre);
//		this.getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String s=request.getParameter("selection");
//		String[] myValues=s.split(",");
//		System.out.println(s);
//		computerService=new ComputerService();
//		for(String val:myValues) {
//			System.out.println(val);
//			computerService.supprimer(Integer.parseInt(val));
//		}
//		doGet(request, response);
//	}
//
//}
