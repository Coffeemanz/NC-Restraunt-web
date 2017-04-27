package by.training.nc.dev3.commands;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.training.nc.dev3.constants.JspPaths;
import by.training.nc.dev3.dao.MySqlDaoFactory;
import by.training.nc.dev3.dao.MySqlFoodDao;
import by.training.nc.dev3.dao.MySqlOrderDao;
import by.training.nc.dev3.dao.interfaces.DaoFactory;
import by.training.nc.dev3.entities.FoodEntity;
import by.training.nc.dev3.exceptions.PersistException;

public class DeleteFromOrderCommand extends AbstractCommand {

	@Override
	public String execute(HttpServletRequest request) {
		
		String[] order_from_checkbox = request.getParameterValues("order_checkbox");
		
		if (order_from_checkbox == null)
		{
			return JspPaths.ORDER_PAGE_PATH;
		}
		
		for (String o: order_from_checkbox)
		{
			System.out.println("Id of selected food: " + o);
		}
		
		
		
		ArrayList<FoodEntity> order_current = (ArrayList<FoodEntity>) request.getSession().getAttribute("order");
		
		DaoFactory daoFactory = new MySqlDaoFactory();
		
		try (Connection con = daoFactory.getConnection())
		{
			MySqlOrderDao dao_order = daoFactory.getOrderDao(con);

			try {
				for (String s: order_from_checkbox)
				{
					dao_order.deleteByPkFood(Integer.parseInt(s));
				}

			} catch (PersistException e) {
				System.err.println(e);
			}
		} catch (SQLException e1) {

			System.err.println(e1);
		}
		
		System.out.println("Order before deleting: ");
		
		for (FoodEntity f: order_current)
		{
			System.out.println(f);
		}
		
		ArrayList<FoodEntity> food_to_delete = new ArrayList();
		
		for (FoodEntity f: order_current)
		{
			String id = String.valueOf(f.getId());
			if (Arrays.asList(order_from_checkbox).contains(id))
			{
				food_to_delete.add(f);
			}
		}
		
		Float current_cost = (Float) request.getSession().getAttribute("total_cost");
		
		for (FoodEntity f: food_to_delete)
		{
			current_cost  -= f.getFood_price();
		}
		
		order_current.removeAll(food_to_delete);
		
		
		System.out.println("Order after deleting: ");
		
		for (FoodEntity f: order_current)
		{
			System.out.println(f);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("order", order_current);
		session.setAttribute("total_cost", current_cost);
		
		return JspPaths.ORDER_PAGE_PATH;
	}

}
