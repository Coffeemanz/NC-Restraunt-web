package by.training.nc.dev3.filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.nc.dev3.dao.MySqlClientDao;
import by.training.nc.dev3.dao.MySqlDaoFactory;
import by.training.nc.dev3.dao.MySqlWaiterDao;
import by.training.nc.dev3.dao.interfaces.DaoFactory;
import by.training.nc.dev3.entities.ClientEntity;
import by.training.nc.dev3.entities.WaiterEntity;
import by.training.nc.dev3.exceptions.PersistException;

@WebFilter(urlPatterns = {"/jsps/*"}, initParams = { @WebInitParam(name = "LOGIN_PATH", value = "/login_admin.jsp") })
public class AuthentificationAdminFilter implements Filter {
	 private String loginPath;
	
	 @Override
		public void init(FilterConfig config) throws ServletException {
			loginPath = config.getInitParameter("LOGIN_PATH");
		}
	 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
	WaiterEntity currentWaiter = (WaiterEntity) req.getSession().getAttribute("admin");
		
		DaoFactory daoFactory = new MySqlDaoFactory();
		
		try (Connection con = daoFactory.getConnection())
		{
			MySqlWaiterDao dao_waiter = daoFactory.getWaiterDao(con);
			try {
				if (currentWaiter == null || dao_waiter.getByPK(currentWaiter.getId()) == null)
				{
					res.sendRedirect(loginPath);
				}
				else
				{
					chain.doFilter(request, response);
				}
				
				
			} catch (PersistException e) {
				System.err.println(e);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.err.println(e1);
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
