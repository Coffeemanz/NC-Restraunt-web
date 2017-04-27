package by.training.nc.dev3.commands;

import javax.servlet.http.HttpServletRequest;

import by.training.nc.dev3.constants.JspPaths;

public class LogOutCommand extends AbstractCommand {

	@Override
	public String execute(HttpServletRequest request) {
		
		request.getSession().invalidate();
		
		return JspPaths.INDEX_PAGE_PATH;
	}

}
