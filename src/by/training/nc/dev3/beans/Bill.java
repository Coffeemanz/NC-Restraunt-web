package by.training.nc.dev3.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class Bill. Holds the total <b>order</b> and the current <b>date</b>.
 * @author Sergey Zhyshkevich
 *
 */

public class Bill implements Serializable {
	

		
	
	private static final long serialVersionUID = -3837117297855240736L;
	
	/**Characteristic - order */
	Order order;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
	/**Characteristic -date */
	GregorianCalendar date = new GregorianCalendar();
	protected static Integer billCounter = 0;
	
	
	/**Creates new empty object 
	 * @see Bill#Bill(Order, GregorianCalendar)
	 * 
	 *  */
	public Bill()
	{
		super();
		billCounter++;;
	}
	
	/**
	 * Creates new empty object with given params
	 * @param order - order
	 * @param date - today date
	 * @see Bill#Bill()
	 * */
	public Bill(Order order, GregorianCalendar date)
	{
		super();
		this.order = order;
		this.date = date;
		billCounter++;
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
		Bill other = (Bill) otherObject;
		return Objects.equals(order, other.order) && Objects.equals(date, other.date);
	}

	/**
	 * hashCode method
	 * */
	@Override
	public int hashCode()
	{
		return Objects.hash(order, date);
	}

	/**
	 * toString() method
	 * */
	@Override 
	public String toString()
	{
		//TODO add date to toString
		return "Bill: " + order + ".";
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
	 * */
	public Order getOrder()
	{
		return order;
	}
	
	
	/**
	 * Sets the date
	 * @param date - current date
	 * */
	public void setDate(GregorianCalendar date)
	{
		this.date = date;
	}
	
	/**
	 * Returns the current date
	 * */
	public GregorianCalendar getDate()
	{
		return date;
	}
	
	
	/**
	 * Return the total number of Bill-objects
	 * */
	public Integer getBillCounter()
	{
		return billCounter;
	}
	
}
