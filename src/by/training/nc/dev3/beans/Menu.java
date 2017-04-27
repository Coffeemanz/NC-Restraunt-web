package by.training.nc.dev3.beans;


import java.io.Serializable;
import java.util.*;

import by.training.nc.dev3.exceptions.WrongValueException;

/**
 * 
 * Menu class
 * @author Сергей
 *
 */

public class Menu implements Serializable {

	private static final long serialVersionUID = -8404088619328906342L;
	//protected Map<Food, Integer> listOfFood;
	protected List<Food> listOfFood = new ArrayList<Food>();
	protected static Integer menuCounter = 0;
	
	public Menu()
	{
		super();
		menuCounter++;
		
	}
	
	@SuppressWarnings("unchecked")
	public Menu(List<Food> listOfFood)
	{
		super();
		this.listOfFood = listOfFood;
		menuCounter++;
		
	}
	

	
	@Override
	public boolean equals(Object otherObject)
	{
		if (this == otherObject) 
			return true;
		if (otherObject == null) 
			return false;
		if (getClass() != otherObject.getClass())
			return false;
		Menu other = (Menu) otherObject;
		return Objects.equals(listOfFood, other.listOfFood);
	}

	
	@Override
	public int hashCode()
	{
		return Objects.hash(listOfFood);
	}

	@Override 
	public String toString()
	{
//			Iterator it = listOfFood.entrySet().iterator();
//			while (it.hasNext())
//			{
//				Map.Entry pair = (Map.Entry)it.next();
//				
//				Food f = (Food)pair.getKey();
//				System.out.println(f.getName() + " = " +  f.getValue() + "$" + ". Quantity: " + pair.getValue());
//				it.remove();
//			}
		
		for (int i = 0; i < listOfFood.size(); i++)
		{
			System.out.println(i + 1 + ". " + listOfFood.get(i));
		}
		return "";
	}


	public void setListOfFood(List<Food> listOfFood)
	{
		this.listOfFood = listOfFood;
	}
	
	public List<Food> getListOfFood()
	{
		return listOfFood;
	}
	
	
	/**
	 * 
	 * Add food to the current menu
	 * @param food
	 * @throws WrongValueException
	 */
	public void addFood(Food food) throws WrongValueException
	{
		if (food == null)
		{
			throw new WrongValueException();
		}
		listOfFood.add(food);
	}
	
	/**
	 * Remove food from the current menu 
	 * 
	 * @param food
	 * @throws WrongValueException
	 */
	public void removeFood(Food food) throws WrongValueException
	{
		if (food == null)
		{
			throw new WrongValueException();
		}
		listOfFood.remove(food);
	}
	
	public Integer getMenuCounter()
	{
		return menuCounter;
	}
		
}
