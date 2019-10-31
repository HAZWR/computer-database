package fr.excilys.database.servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import fr.excilys.database.model.Computer;
import fr.excilys.database.dao.imp.ComputerDAOImpl;


/**
 * Servlet implementation class test
 */
@Controller
@WebServlet("/menu")
public class Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerDAOImpl computer;
	private List<Computer> listComputers;
	private int nombre=0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Menu() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.setProperty("testing","false");
		computer=new ComputerDAOImpl();
		listComputers=computer.getAllComputers();
		request.setAttribute("listComputers", listComputers);
		nombre=computer.count();
		System.out.println(nombre);
		request.setAttribute("nombre",nombre);
		this.getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s=request.getParameter("selection");
		String[] myValues=s.split(",");
		System.out.println(s);
		computer=new ComputerDAOImpl();
		for(String val:myValues) {
			System.out.println(val);
			computer.supprimer(Integer.parseInt(val));
		}
		doGet(request, response);
	}

}
