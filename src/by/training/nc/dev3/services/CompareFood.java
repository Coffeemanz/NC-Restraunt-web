package by.training.nc.dev3.services;

import java.util.Comparator;

import by.training.nc.dev3.beans.Food;


/**
 * Comparator fo Food objects
 * 
 * @author Сергей
 *
 */
public class CompareFood implements Comparator<Food> {
	
	public int compare(Food food1, Food food2)
	{
		Float food1Value = food1.getValue();
		Float food2Value = food2.getValue();
		
		if (food1Value > food2Value)
			return 1;
		else if (food1Value < food2Value)
			return -1;
		else return 0;
	}

}
