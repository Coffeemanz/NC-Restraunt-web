package by.training.nc.dev3.commands;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.training.nc.dev3.constants.JspPaths;
import by.training.nc.dev3.dao.MySqlClientDao;
import by.training.nc.dev3.dao.*;
import by.training.nc.dev3.dao.interfaces.DaoFactory;
import by.training.nc.dev3.entities.*;
import by.training.nc.dev3.exceptions.PersistException;

public class GoToMenuCommand extends AbstractCommand {

	
	public String execute(HttpServletRequest request)
	{
		DaoFactory daoFactory = new MySqlDaoFactory();
		HttpSession session = request.getSession();
		
		//read all
		System.out.println("Read all values from table...");
		List<FoodEntity> list= null;
		//Float client_cash = (Float) session.getAttribute("client_cash");
		//Float client_cash = 0f;
		try (Connection con = daoFactory.getConnection())
		{
			MySqlFoodDao dao_food = daoFactory.getFoodDao(con);
			MySqlClientDao dao_client = daoFactory.getClientDao(con);
			try {
				list = (List<FoodEntity>) dao_food.getInMenu(1);
				//client_cash = dao_client.getByPK(Integer.parseInt((String) session.getAttribute("client_id"))).getClient_cash();
			} catch (PersistException e) {
				System.err.println(e);
			}
			System.out.println(list);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.err.println(e1);
		}
		//HttpSession session = request.getSession();
		
		//session.setAttribute("client_cash", client_cash);
		session.setAttribute("menu", list);
		return JspPaths.MENU_PAGE_PATH;
	}
}
