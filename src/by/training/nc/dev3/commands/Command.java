package by.training.nc.dev3.commands;


import javax.servlet.http.HttpServletRequest;

public interface Command {
	String execute (HttpServletRequest request);
}
