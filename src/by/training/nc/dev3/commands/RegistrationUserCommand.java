package by.training.nc.dev3.commands;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.training.nc.dev3.constants.JspPaths;
import by.training.nc.dev3.dao.MySqlClientDao;
import by.training.nc.dev3.dao.MySqlDaoFactory;
import by.training.nc.dev3.dao.interfaces.DaoFactory;
import by.training.nc.dev3.entities.ClientEntity;

public class RegistrationUserCommand extends AbstractCommand {

	@Override
	public String execute(HttpServletRequest request) {
		
		System.out.println("Register new client...");	
        System.out.println("Create new valuein db...");
		
		HttpSession session = request.getSession();
		
		//Integer id = Integer.parseInt(request.getParameter("id"));
		
		String client_name = request.getParameter("name");
		String client_email = request.getParameter("email");
		String client_password = request.getParameter("password");
		Float client_cash = Float.parseFloat(request.getParameter("cash"));
	
		
		System.out.println(client_name + " " + client_email + " " + client_password + " " + client_cash);
		ClientEntity client = new ClientEntity(client_name, client_email, client_password, client_cash);
		ClientEntity cl = null;
		
		DaoFactory daoFactory = new MySqlDaoFactory();
		
		try (Connection con = daoFactory.getConnection())
		{
			MySqlClientDao dao_client = daoFactory.getClientDao(con);
			
			 cl = dao_client.persist(client);
			
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		session.setAttribute("client", cl);
//		session.setAttribute("client_id", cl.getId());
//		session.setAttribute("client_name", client_name);
//		session.setAttribute("client_email", client_email);
//		session.setAttribute("client_password", client_password);
//		session.setAttribute("client_cash", client_cash);
		
		return "/Index.jsp";
	
	}

}
