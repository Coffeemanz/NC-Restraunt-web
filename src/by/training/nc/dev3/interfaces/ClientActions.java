package by.training.nc.dev3.interfaces;

import by.training.nc.dev3.beans.Bill;
import by.training.nc.dev3.beans.Food;
import by.training.nc.dev3.beans.Menu;
import by.training.nc.dev3.beans.Order;
import by.training.nc.dev3.exceptions.WrongValueException;

public interface ClientActions {
	
	void addToOrder(Food food) throws WrongValueException;
	void removeFromOrder(Food food) throws WrongValueException;
	void viewOrder();
	void clearOrder();
	Boolean pay ();
	void tip(int inputNumber);
	
}
