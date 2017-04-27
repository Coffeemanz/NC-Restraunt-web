package by.training.nc.dev3.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import by.training.nc.dev3.beans.*;
import by.training.nc.dev3.commands.Command;
import by.training.nc.dev3.commands.CommandFactory;
import by.training.nc.dev3.dao.MySqlClientDao;
import by.training.nc.dev3.dao.MySqlDaoFactory;
import by.training.nc.dev3.dao.interfaces.DaoFactory;
import by.training.nc.dev3.entities.ClientEntity;
import by.training.nc.dev3.exceptions.PersistException;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	String page = null;
    	Command command = CommandFactory.defineCommand(request);
    	page = command.execute(request);
    	if (page != null)
    	{
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
    		dispatcher.forward(request, response);
    	}
    	else
    	{
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("Index.jsp");
    		dispatcher.forward(request, response);
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		PrintWriter out = response.getWriter();
//		
//		out.println("NOW IN SERVLET");
//		
//		DaoFactory daoFactory = new MySqlDaoFactory();
//		
//		//read all
//		System.out.println("Read all values from table...");
//		List<ClientEntity> list= null;
//		try (Connection con = daoFactory.getConnection())
//		{
//			MySqlClientDao dao = daoFactory.getClientDao(con);
//			try {
//				list = dao.getAll();
//			} catch (PersistException e) {
//				System.err.println(e);
//			}
//			System.out.println(list);
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			System.err.println(e1);
//		}
//		
//		HttpSession session = request.getSession();
//		//String a = "LELEL";
//		
//		session.setAttribute("a", list.get(0));
//		request.getRequestDispatcher("Index.jsp").forward(request, response);
//		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
