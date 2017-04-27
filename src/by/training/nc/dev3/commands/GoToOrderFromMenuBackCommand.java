package by.training.nc.dev3.commands;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.training.nc.dev3.constants.JspPaths;
import by.training.nc.dev3.dao.MySqlDaoFactory;
import by.training.nc.dev3.dao.MySqlFoodDao;
import by.training.nc.dev3.dao.MySqlOrderDao;
import by.training.nc.dev3.dao.interfaces.DaoFactory;
import by.training.nc.dev3.entities.ClientEntity;
import by.training.nc.dev3.entities.FoodEntity;
import by.training.nc.dev3.entities.OrderEntity;
import by.training.nc.dev3.exceptions.PersistException;

public class GoToOrderFromMenuBackCommand extends AbstractCommand {

	@Override
	public String execute(HttpServletRequest request) {
		
		
		String[] order_strings_ids_new = request.getParameterValues("menu");
		
		if (order_strings_ids_new == null)
		{
			return JspPaths.ORDER_PAGE_PATH;
		}
				
		ArrayList<FoodEntity> order_list_new = new ArrayList();
				
		Float total_cost_new = 0f;
		
//		for (String o: order)
//		{
//			System.out.println(o);
//		}
		
		
		DaoFactory daoFactory = new MySqlDaoFactory();

		try (Connection con = daoFactory.getConnection())
		{
			MySqlFoodDao dao_food = daoFactory.getFoodDao(con);

			try {
				for (String s: order_strings_ids_new)
				{
					order_list_new.add(dao_food.getByPK(Integer.parseInt(s)));
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
		HttpSession session = request.getSession();
		
		
		System.out.println("Add to order:");
		for (FoodEntity f: order_list_new)
		{
			System.out.println(f);
		}
		
		ArrayList<FoodEntity> order_check = (ArrayList<FoodEntity>) session.getAttribute("order");
		
		System.out.println("The whole order at the moment:");
		for (FoodEntity f: order_check)
		{
			System.out.println(f);
		}
		
		//session.setAttribute("order", order);
		
		//DaoFactory daoFactory = new MySqlDaoFactory();
		OrderEntity o = null;
		//ArrayList<FoodEntity> list_food =  new ArrayList();
		
		ClientEntity client = (ClientEntity) request.getSession().getAttribute("client");
		
		try (Connection con = daoFactory.getConnection())
		{
			MySqlOrderDao dao_order = daoFactory.getOrderDao(con);
			MySqlFoodDao dao_food = daoFactory.getFoodDao(con);
			
			
			for (FoodEntity i: order_list_new)
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
		
		for (FoodEntity food: order_list_new)
		{
			total_cost_new += food.getFood_price();
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<FoodEntity> order_list_current = (ArrayList<FoodEntity>) session.getAttribute("order");
		ArrayList<FoodEntity> order_list = new ArrayList<FoodEntity>(order_list_new.size() + order_list_current.size());
		
		order_list.addAll(order_list_current);
		order_list.addAll(order_list_new);
		
		
		Float total_cost_current = (Float) session.getAttribute("total_cost");
		Float total_cost = total_cost_new + total_cost_current;
		
	
		session.setAttribute("order", order_list);
		session.setAttribute("total_cost", total_cost);

		return JspPaths.ORDER_PAGE_PATH;
	}

}
