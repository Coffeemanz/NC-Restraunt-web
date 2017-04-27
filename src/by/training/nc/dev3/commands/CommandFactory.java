package by.training.nc.dev3.commands;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import by.training.nc.dev3.constants.*;

import static by.training.nc.dev3.constants.Commands.*;

public class CommandFactory {

	private static Map<String, Command> commands = new HashMap<>();
	
	static 
	{
		commands.put(GO_TO_MENU, new GoToMenuCommand());
		commands.put(LOGIN_USER, new LoginUserCommand());
		commands.put(GO_TO_ORDER, new GoToOrderCommand());
		commands.put(GO_TO_RESULT, new GoToResultCommand());
		commands.put(REGISTRATION_USER, new RegistrationUserCommand());
		commands.put(GO_TO_ORDER_FROM_MENU_BACK, new GoToOrderFromMenuBackCommand());
		commands.put(GO_TO_MENU_FROM_ORDER, new GoToMenuFromOrderCommand());
		commands.put(DELETE_FROM_ORDER, new DeleteFromOrderCommand());
		commands.put(CLEAR_ORDER, new ClearOrderCommand());
		commands.put(LOGIN_ADMIN, new LoginAdminCommand());
		commands.put(ADD_TO_MENU, new AddToMenuCommand());
		commands.put(REMOVE_FROM_MENU, new RemoveFromMenuCommand());
		commands.put(ACCEPT_ORDER, new AcceptOrderCommand());
		commands.put(CHECK_ORDER, new CheckOrderCommand());
		commands.put(CHANGE_CASH, new ChangeCashCommand());
		commands.put(LOG_OUT, new LogOutCommand());
	}
	
	public static Command defineCommand(HttpServletRequest request)
	{
		Command resultCommand = null;
		String commandName = request.getParameter("command");
		resultCommand = commands.get(commandName);
		return resultCommand;
	}
	
}
