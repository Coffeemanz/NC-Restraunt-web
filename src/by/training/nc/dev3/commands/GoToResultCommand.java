package by.training.nc.dev3.commands;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.training.nc.dev3.constants.JspPaths;
import by.training.nc.dev3.dao.MySqlClientDao;
import by.training.nc.dev3.dao.MySqlDaoFactory;
import by.training.nc.dev3.dao.MySqlFoodDao;
import by.training.nc.dev3.dao.MySqlOrderDao;
import by.training.nc.dev3.dao.interfaces.DaoFactory;
import by.training.nc.dev3.entities.ClientEntity;
import by.training.nc.dev3.entities.FoodEntity;
import by.training.nc.dev3.entities.OrderEntity;
import by.training.nc.dev3.exceptions.PersistException;

public class GoToResultCommand extends AbstractCommand{

	@Override
	public String execute(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		ClientEntity client = (ClientEntity) session.getAttribute("client");
		
		Boolean paid = client.getPaid();
		
		Float total_cost = (Float) session.getAttribute("total_cost");
		Float client_cash = client.getClient_cash();
		
		Float result_cost = client_cash - total_cost;
		
		
		session.setAttribute("paid", paid);
		session.setAttribute("result_cost", result_cost);
		
		DaoFactory daoFactory = new MySqlDaoFactory();
		
		
		try (Connection con = daoFactory.getConnection())
		{
			MySqlClientDao dao_client = daoFactory.getClientDao(con);
			try {
				client = dao_client.getByPK(client.getId());
			} catch (PersistException e) {
				
				e.printStackTrace();
			}
			client.setClient_cash(result_cost);
			try {
				dao_client.update(client);
			} catch (PersistException e) {

				e.printStackTrace();
			}
			
			
		} catch (SQLException e1) {
			System.err.println(e1);
		}
		
		
		System.out.println("Paying" + " " + result_cost);
		return JspPaths.RESULT_PAGE_PATH;
	}

}
