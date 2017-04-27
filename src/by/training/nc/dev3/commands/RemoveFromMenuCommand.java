package by.training.nc.dev3.commands;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.training.nc.dev3.constants.JspPaths;
import by.training.nc.dev3.dao.MySqlDaoFactory;
import by.training.nc.dev3.dao.MySqlFoodDao;
import by.training.nc.dev3.dao.interfaces.DaoFactory;
import by.training.nc.dev3.entities.FoodEntity;
import by.training.nc.dev3.exceptions.PersistException;

public class RemoveFromMenuCommand extends AbstractCommand{

	@Override
	public String execute(HttpServletRequest request) {
		
		String[] menu_strings_ids = request.getParameterValues("menu");
		
		List<FoodEntity> food_from_menu = new ArrayList();
		
		DaoFactory daoFactory = new MySqlDaoFactory();

		try (Connection con = daoFactory.getConnection())
		{
			MySqlFoodDao dao_food = daoFactory.getFoodDao(con);

			try {
				for (String s: menu_strings_ids)
				{
					food_from_menu.add(dao_food.getByPK(Integer.parseInt(s)));
				}

			} catch (PersistException e) {
				System.err.println(e);
			}
		} catch (SQLException e1) {

			System.err.println(e1);
		}

		System.out.println("Selected food (ids) for deleting from menu: ");
		for (FoodEntity f: food_from_menu)
		{
			System.out.println(f);
		}
		
//		for (FoodEntity f: food_to_menu)
//		{
//			f.setId_menu(1);
//		}
		
		try (Connection con = daoFactory.getConnection())
		{
			MySqlFoodDao dao_food = daoFactory.getFoodDao(con);

			try {
				for (FoodEntity f: food_from_menu)
				{
					dao_food.updateRemoveFromMenu(f);
				}

			} catch (PersistException e) {
				System.err.println(e);
			}
		} catch (SQLException e1) {

			System.err.println(e1);
		}
		
		List<FoodEntity> menu = null;
		
		try (Connection con = daoFactory.getConnection())
		{
			MySqlFoodDao dao_food = daoFactory.getFoodDao(con);
			try {
				//admin = dao_waiter.getByEmail(admin_email);
				//all_food = dao_food.getAll();
				menu = dao_food.getInMenu(1);
				
			} catch (PersistException e) {
				System.err.println(e);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.err.println(e1);
		}
		
		request.getSession().setAttribute("menu", menu);
		
		
		return JspPaths.ADMIN_PAGE_PATH;
	}

}
