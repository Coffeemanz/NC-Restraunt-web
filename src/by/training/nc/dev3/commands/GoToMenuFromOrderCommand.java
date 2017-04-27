package by.training.nc.dev3.commands;

import javax.servlet.http.HttpServletRequest;

import by.training.nc.dev3.constants.JspPaths;

public class GoToMenuFromOrderCommand extends AbstractCommand {

	@Override
	public String execute(HttpServletRequest request) {
		return JspPaths.MENU_PAGE_PATH;
	}
	
	

}
