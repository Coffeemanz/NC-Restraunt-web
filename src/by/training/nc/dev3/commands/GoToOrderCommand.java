package by.training.nc.dev3.commands;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.training.nc.dev3.constants.JspPaths;
import by.training.nc.dev3.dao.*;
import by.training.nc.dev3.dao.interfaces.DaoFactory;
import by.training.nc.dev3.entities.*;
import by.training.nc.dev3.exceptions.PersistException;

public class GoToOrderCommand extends AbstractCommand {

	
	public String execute(HttpServletRequest request)
	{
//		DaoFactory daoFactory = new MySqlDaoFactory();
//		
//		//read all
//		System.out.println("Read all values from table...");
//		List<FoodEntity> list= null;
//		try (Connection con = daoFactory.getConnection())
//		{
//			MySqlFoodDao dao = daoFactory.getFoodDao(con);
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
//		HttpSession session = request.getSession();
//		session.setAttribute("menu", list);
//		return JspPaths.MENU_PAGE_PATH;
		
	//-------------------------------------------------------------------------	
		
		//String[] order = request.getParameterValues("menu");
		
		String[] order_strings_ids = request.getParameterValues("menu");
		
		if (order_strings_ids == null)
		{
			return JspPaths.ERROR_PAGE_PATH;
		}
				
		ArrayList<FoodEntity> order_list = new ArrayList();
				
		Float total_cost = 0f;
		
//		for (String o: order)
//		{
//			System.out.println(o);
//		}
		
		
		DaoFactory daoFactory = new MySqlDaoFactory();

		try (Connection con = daoFactory.getConnection())
		{
			MySqlFoodDao dao_food = daoFactory.getFoodDao(con);

			try {
				for (String s: order_strings_ids)
				{
					order_list.add(dao_food.getByPK(Integer.parseInt(s)));
				}

			} catch (PersistException e) {
				System.err.println(e);
			}
		} catch (SQLException e1) {

			System.err.println(e1);
		}
		
		//ArrayList<Integer> order_int = new ArrayList();
		
//		for (String o: order)
//		{
//			order_int.add(Integer.parseInt(o));
//		}
		
		System.out.println("Order:");
		for (FoodEntity f: order_list)
		{
			System.out.println(f);
		}
		
		HttpSession session = request.getSession();
		//session.setAttribute("order", order);
		
		//DaoFactory daoFactory = new MySqlDaoFactory();
		OrderEntity o = null;
		//ArrayList<FoodEntity> list_food =  new ArrayList();
		
		ClientEntity client = (ClientEntity) request.getSession().getAttribute("client");
		
		try (Connection con = daoFactory.getConnection())
		{
			MySqlOrderDao dao_order = daoFactory.getOrderDao(con);
			MySqlFoodDao dao_food = daoFactory.getFoodDao(con);
			
			
			for (FoodEntity i: order_list)
			{
				o = new OrderEntity(1,client.getId(),i.getId());
				dao_order.persist(o);
			}
			
//			for (Integer i: order_int)
//			{
//				try {
//					list_food.add(dao_food.getByPK(i));
//				} catch (PersistException e) {
//					e.printStackTrace();
//				}
//			}
			
		} catch (SQLException e1) {
			System.err.println(e1);
		}
			
		//FoodEntity[] order = order_list.toArray(new FoodEntity[order_list.size()]);
		
		for (FoodEntity food: order_list)
		{
			total_cost += food.getFood_price();
		}
		
		//session.setAttribute("food", array_food);
		session.setAttribute("order", order_list);
		session.setAttribute("total_cost", total_cost);
		return JspPaths.ORDER_PAGE_PATH;
	}
}
