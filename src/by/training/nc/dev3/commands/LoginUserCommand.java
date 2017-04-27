package by.training.nc.dev3.commands;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.training.nc.dev3.constants.JspPaths;
import by.training.nc.dev3.dao.MySqlClientDao;
import by.training.nc.dev3.dao.MySqlDaoFactory;
import by.training.nc.dev3.dao.MySqlFoodDao;
import by.training.nc.dev3.dao.interfaces.DaoFactory;
import by.training.nc.dev3.entities.ClientEntity;
import by.training.nc.dev3.exceptions.PersistException;

public class LoginUserCommand extends AbstractCommand {

	
	public String execute(HttpServletRequest request){
//		System.out.println("Create new value...");
//		
//		HttpSession session = request.getSession();
//		//Integer id = Integer.parseInt(request.getParameter("id"));
//		Float cash = Float.parseFloat(request.getParameter("cash"));
//	
//		
//		System.out.println(cash);
//		ClientEntity m1 = new ClientEntity(cash);
//		
//		DaoFactory daoFactory = new MySqlDaoFactory();
//		
//		try (Connection con = daoFactory.getConnection())
//		{
//			MySqlClientDao dao = daoFactory.getClientDao(con);
//			
//			dao.persist(m1);
//			
//		
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		return JspPaths.MENU_PAGE_PATH;
		
		String page;
		String user_email = request.getParameter("email");
		String user_password = request.getParameter("password");
		
		DaoFactory daoFactory = new MySqlDaoFactory();
		
		ClientEntity client = null;
		try (Connection con = daoFactory.getConnection())
		{
			MySqlClientDao dao_client = daoFactory.getClientDao(con);
			try {
				//client_cash = dao_client.getByPK(Integer.parseInt((String) session.getAttribute("client_id"))).getClient_cash();
				client = dao_client.getByEmail(user_email);
				
			} catch (PersistException e) {
				System.err.println(e);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.err.println(e1);
		}
		
		if (client != null && client.getClient_password() != null && client.getClient_password().equals(user_password))
		{
			request.getSession().setAttribute("client", client);
			page = "/user.jsp";
		}
		else
		{
			page = "/error.jsp";
		}
	
		return page;
	}
}
