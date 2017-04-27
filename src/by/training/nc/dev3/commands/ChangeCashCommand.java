package by.training.nc.dev3.commands;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import by.training.nc.dev3.constants.JspPaths;
import by.training.nc.dev3.dao.MySqlClientDao;
import by.training.nc.dev3.dao.MySqlDaoFactory;
import by.training.nc.dev3.dao.MySqlFoodDao;
import by.training.nc.dev3.dao.interfaces.DaoFactory;
import by.training.nc.dev3.entities.ClientEntity;
import by.training.nc.dev3.exceptions.PersistException;

public class ChangeCashCommand extends AbstractCommand{

	@Override
	public String execute(HttpServletRequest request) {
		
		
		String cash = request.getParameter("cash");
		
		if(cash == "")
		{
			return JspPaths.USER_PAGE_PATH;
		}
		
		System.out.println(cash);
		
		Float current_cash = Float.parseFloat(request.getParameter("cash"));
		
		ClientEntity client = (ClientEntity) request.getSession().getAttribute("client");
		
		client.setClient_cash(current_cash);
		
		DaoFactory daoFactory = new MySqlDaoFactory();

		try (Connection con = daoFactory.getConnection())
		{
			MySqlClientDao dao_client = daoFactory.getClientDao(con);

			try {
				dao_client.update(client);

			} catch (PersistException e) {
				System.err.println(e);
			}
		} catch (SQLException e1) {

			System.err.println(e1);
		}
		
		request.getSession().setAttribute("client", client);
		
		return JspPaths.USER_PAGE_PATH;
	}

}
