package by.training.nc.dev3.beans;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Objects;

import by.training.nc.dev3.exceptions.WrongValueException;
import by.training.nc.dev3.interfaces.ClientActions;

/**
 * Class Client. Holds all tne necessary to work with Client instance(<b>cash</b>,
 * <b>menu</b>, <b>order</b>, <b>bill</b>).
 * @author Сергей
 *
 */

public class Client implements Serializable {
	

	private static final long serialVersionUID = 2679491248481851265L;
	protected Float cash;
	protected Boolean paid = false;
	Menu menu;
	Order order;
	Bill bill;
	protected static Integer clientCounter = 0;
	
	/**Creates new empty object 
	 * @see Client#Client(Float, Menu, Order, Bill)
	 * 
	 *  */
	public Client()
	{
		super();
		clientCounter++;
	}
	
	
	/**Creates new empty object 
	 * @see Client#Client()
	 * 
	 * @param cash - total money
	 * @param menu - menu
	 * @param order - order
	 * @bill - bill
	 *  */
	public Client(Float cash, Menu menu, Order order, Bill bill )
	{
		super();
		this.cash = cash;
		this.menu = menu;
		this.order = order;
		this.bill = bill;
		clientCounter++;
	}
	
	/**
	 * Equals method
	 * */
	@Override
	public boolean equals(Object otherObject)
	{
		if (this == otherObject) 
			return true;
		if (otherObject == null) 
			return false;
		if (getClass() != otherObject.getClass())
			return false;
		Client other = (Client) otherObject;
		return Objects.equals(menu, other.menu)  &&
				Objects.equals(order, other.order) &&
				Objects.equals(bill, other.bill) &&
				cash == other.cash && paid == other.paid;
	}

	/**
	 * hashCode method
	 * */
	@Override
	public int hashCode()
	{
		return Objects.hash(cash, paid, menu, order, bill);
	}

	/**
	 * toString() method
	 * */
	@Override 
	public String toString()
	{
		return "Client with " + cash + "$, which has paid =  " + paid;
	}
	
	
	/**
	 * Sets the cash
	 * @param cash - total money
	 * */
	public void setCash (Float cash)
	{
		this.cash = cash;
	}
	
	/**
	 * Returns cash
	 * 
	 * */
	public Float getCash()
	{
		return cash;
	}
	
	public void setPaid(Boolean paid)
	{
		this.paid = paid;
	}
	
	public Boolean getPaid()
	{
		return paid;
	}
	
	/**
	 * Sets the menu
	 * @param menu - menu
	 * */
	public void setMenu(Menu menu)
	{
		this.menu = menu;
	}
	
	/**
	 * Returns the menu
	 *
	 * */
	public Menu getMenu()
	{
		return menu;
	}
	
	/**
	 * Sets the order
	 * @param order - order
	 * */
	public void setOrder(Order order)
	{
		this.order = order;
	}
	
	
	/**
	 * Returns the order
	 * 
	 * */
	public Order getOrder()
	{
		return order;
	}
	
	/**
	 * Sets the bill
	 * @param bill - bill
	 * */
	public void setBill(Bill bill)
	{
		this.bill = bill;
	}
	
	
	/**
	 * Returns the bill
	 * 
	 * */
	public Bill getBill()
	{
		return bill;
	}
	
	
	/**
	 * Return the total number of Client-objects
	 * */
	
	public Integer getClientCounter()
	{
		return clientCounter;
	}
	
	
}
