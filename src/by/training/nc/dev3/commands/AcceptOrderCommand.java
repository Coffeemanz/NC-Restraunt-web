package by.training.nc.dev3.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.training.nc.dev3.constants.JspPaths;

public class AcceptOrderCommand extends AbstractCommand{

	@Override
	public String execute(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		Boolean paid = (Boolean) session.getAttribute("paid");
		
		paid = true;
		
		System.out.println("Accepted order");
		System.out.println("Paid:");
		System.out.println(paid);
		
		session.setAttribute("paid", paid);
		
		return JspPaths.ADMIN_PAGE_PATH;
	}

}
