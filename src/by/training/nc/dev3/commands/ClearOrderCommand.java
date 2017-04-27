package by.training.nc.dev3.commands;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.training.nc.dev3.constants.JspPaths;
import by.training.nc.dev3.dao.MySqlDaoFactory;
import by.training.nc.dev3.dao.MySqlOrderDao;
import by.training.nc.dev3.dao.interfaces.DaoFactory;
import by.training.nc.dev3.entities.ClientEntity;
import by.training.nc.dev3.entities.FoodEntity;
import by.training.nc.dev3.exceptions.PersistException;

public class ClearOrderCommand extends AbstractCommand {

	@Override
	public String execute(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		ClientEntity client = (ClientEntity) session.getAttribute("client");
		
		ArrayList<FoodEntity> order_current = (ArrayList<FoodEntity>) session.getAttribute("order");
		
		Float current_cost = (Float) session.getAttribute("total_cost");
		
		System.out.println("Order before clearing: ");
		for (FoodEntity f: order_current)
		{
			System.out.println(f);
		}
		
		DaoFactory daoFactory = new MySqlDaoFactory();
		
		try (Connection con = daoFactory.getConnection())
		{
			MySqlOrderDao dao_order = daoFactory.getOrderDao(con);

			try {
				dao_order.deleteByPkClient(client.getId());

			} catch (PersistException e) {
				System.err.println(e);
			}
		} catch (SQLException e1) {
			System.err.println(e1);
		}
		
		order_current.clear();
		
		if (order_current.isEmpty())
		{
			System.out.println("Order is clear...");
		}
		
		current_cost = 0f;
		
		session.setAttribute("order", order_current);
		session.setAttribute("total_cost", current_cost);
		
		
		return JspPaths.ORDER_PAGE_PATH;
	}

}
