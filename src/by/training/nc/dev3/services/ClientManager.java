package by.training.nc.dev3.services;

import by.training.nc.dev3.interfaces.*;
import by.training.nc.dev3.beans.*;
import by.training.nc.dev3.exceptions.WrongValueException;

public class ClientManager implements ClientActions {

	
	Client client = new Client();
	
	public ClientManager()
	{
		super();
	}
	
	public ClientManager (Client client)
	{
		super();
		this.client = client;
	}
	
	public void addToOrder(Food food) throws WrongValueException
	{
		if (food == null)
		{
			throw new WrongValueException();
		}
//		order.getResultOrder().add(food.getName());
//		order.sumValue(food.getValue());
//		cash -= food.getValue();
		
		client.getOrder().getResultOrder().add(food.getName());
		client.getOrder().sumValue(food.getValue());
		client.setCash(client.getCash() - food.getValue());
	}
	
	public void removeFromOrder(Food food) throws WrongValueException
	{
		if (food == null)
		{
			throw new WrongValueException();
		}
//		order.removePosition(food);
//		cash += food.getValue();
		
		client.getOrder().removePosition(food);
		client.setCash(client.getCash() + food.getValue());
	}
	public void viewOrder()
	{
		System.out.println("I've ordered:");
//		for (String item: order.getResultOrder())
//		{
//			System.out.println(item);
//		}
		
		for (String item: client.getOrder().getResultOrder())
		{
			System.out.println(item);
		}
	}
	public void clearOrder()
	{
//		cash += order.getResultValue();
//		order.getResultOrder().clear();
//		order.setResultValue(0f);
		
		client.setCash(client.getCash() + client.getOrder().getResultValue());
		client.getOrder().getResultOrder().clear();
		client.getOrder().setResultValue(0f);
	}
	public Boolean pay ()
	{
		System.out.println("It's okay... I'm paynig...");
		//paid = true;
		
		client.setPaid(true);

//		else if (cash < order.resultValue)
//		{
//			System.out.println("I don't have enough money...");
//			paid = false;
//		}
		//cash -= order.resultValue;
		return client.getPaid();
	}
	public void tip(int inputNumber)
	{
		if (inputNumber <= client.getCash())
		{
		System.out.println("I'm giving you some tips...");
		//cash -= inputNumber;
		client.setCash(client.getCash() - inputNumber);
		}
		else
		{
			System.out.println("Sorry, don't have money...");
		}
	}

}
