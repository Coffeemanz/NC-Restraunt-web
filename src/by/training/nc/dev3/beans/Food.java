package by.training.nc.dev3.beans;

import java.io.Serializable;
import java.util.Objects;

/**
 * Food class
 * @author Сергей
 *
 */
public class Food implements Serializable, Comparable<Food> {

	
	private static final long serialVersionUID = 4518959062556062616L;
	protected String name;
	protected Float value;
	protected static Integer foodCounter = 0;
	
	public Food()
	{
		super();
		foodCounter++;
	}
	
	public Food(String name, Float value)
	{
		super();
		this.name = name;
		this.value = value;
		foodCounter++;
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
		Food other = (Food) otherObject;
		return name == other.name  &&
				value == other.value;
	}

	
	@Override
	public int hashCode()
	{
		return Objects.hash(name, value);
	}

	@Override 
	public String toString()
	{
		return name + " = " + value + "$";
	}
	
	/**
	 * Compares two instances of food-class
	 * @param food - food to compare
	 * 
	 * 
	 */
	public int compareTo(Food food)
	{
		if (this.getValue() > food.getValue())
			return 1;
		else if (this.getValue() < food.getValue())
			return -1;
		else 
			return 0;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setValue(Float value)
	{
		this.value = value;
	}
	
	public Float getValue()
	{
		return value;
	}
	
	public Integer getFoodCounter()
	{
		return foodCounter;
	}
			
	
}
