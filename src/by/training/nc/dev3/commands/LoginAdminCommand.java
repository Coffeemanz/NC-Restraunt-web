package by.training.nc.dev3.commands;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.training.nc.dev3.dao.MySqlClientDao;
import by.training.nc.dev3.dao.MySqlDaoFactory;
import by.training.nc.dev3.dao.MySqlFoodDao;
import by.training.nc.dev3.dao.MySqlWaiterDao;
import by.training.nc.dev3.dao.interfaces.DaoFactory;
import by.training.nc.dev3.entities.ClientEntity;
import by.training.nc.dev3.entities.FoodEntity;
import by.training.nc.dev3.entities.WaiterEntity;
import by.training.nc.dev3.exceptions.PersistException;

public class LoginAdminCommand extends AbstractCommand{

	@Override
	public String execute(HttpServletRequest request) {
		
		String page;
		String admin_email = request.getParameter("admin_email");
		String admin_password = request.getParameter("admin_password");
		
		DaoFactory daoFactory = new MySqlDaoFactory();
		
		WaiterEntity admin = null;
		List<FoodEntity> all_food = null;
		List<FoodEntity> menu = null;
		
		try (Connection con = daoFactory.getConnection())
		{
			MySqlWaiterDao dao_waiter = daoFactory.getWaiterDao(con);
			MySqlFoodDao dao_food = daoFactory.getFoodDao(con);
			try {
				admin = dao_waiter.getByEmail(admin_email);
				all_food = dao_food.getAll();
				menu = dao_food.getInMenu(1);
				
			} catch (PersistException e) {
				System.err.println(e);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.err.println(e1);
		}
		
		System.out.println("Now admin:");
		System.out.println(admin);
		
		if (admin != null && admin.getWaiter_password() != null && admin.getWaiter_password().equals(admin_password))
		{
			request.getSession().setAttribute("all_food", all_food);
			request.getSession().setAttribute("menu", menu);
			request.getSession().setAttribute("admin", admin);
			page = "/adminPage.jsp";
		}
		else
		{
			page = "/error.jsp";
		}
	
		return page;
	}

}
