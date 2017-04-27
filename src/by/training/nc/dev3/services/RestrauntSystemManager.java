package by.training.nc.dev3.services;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import by.training.nc.dev3.beans.*;
import by.training.nc.dev3.exceptions.WrongValueException;
import by.training.nc.dev3.serialization.BillSerializer;
import by.training.nc.dev3.serialization.ClientSerializer;
import by.training.nc.dev3.serialization.MenuSerializer;
import by.training.nc.dev3.serialization.OrderSerializer;
import by.training.nc.dev3.serialization.WaiterSerializer;
import by.training.nc.dev3.services.*;


/**
 * 
 * The class contains menu to interact with command line.
 * 
 * @author Сергей
 *
 */

public class RestrauntSystemManager {

	public static void clientWorking(Client client, Waiter waiter)
	{
		ClientManager clientManager = new ClientManager(client);
		
		System.out.println("Welcome to our restraunt!");
		System.out.println("How much cash do you have?");
		Float inputCash = (float) ClientUtils.getNumber();
		client.setCash(inputCash);
		
		
		System.out.println("What would you like to do?");
		while(true)
		{
			System.out.println("---------------------");
			System.out.println("1. View menu.");
			System.out.println("0. Exit");
			System.out.println("---------------------");

			out:
			switch (ClientUtils.getNumber())
			{
			case 1:
				System.out.println("Today we have in menu:");
				System.out.println(client.getMenu());
				System.out.println("---------------------");
				
				while(true)
				{
					System.out.println("1. What would you like to add to order?");
					System.out.println("2. What would you like to remove from order?");
					System.out.println("3. View the order.");
					System.out.println("4. Clear the order.");
					System.out.println("5. Accept the order.");
					System.out.println("6. View the menu");
					System.out.println("7. View the cash");
					System.out.println("0. Exit.");
					System.out.println("---------------------");
					
					switch(ClientUtils.getNumber())
					{
					case 1:

						System.out.println("Please, choose something from the menu:");
						System.out.println(client.getMenu());
						
						int inputNumber = ClientUtils.getNumber();

						if ( inputNumber > 0 && inputNumber <= client.getMenu().getListOfFood().size())
						{
							if (client.getCash() >= client.getMenu().getListOfFood().get(inputNumber - 1).getValue())
							{
							try {
								//client.addToOrder(client.getMenu().getListOfFood().get(inputNumber - 1));
								clientManager.addToOrder(client.getMenu().getListOfFood().get(inputNumber - 1));
							} catch (WrongValueException e) {
								System.out.println("You are trying to add a wrong value!");
							}
							
							System.out.println("You've ordered: " + client.getMenu().getListOfFood().get(inputNumber - 1).getName());
							}
							else if (client.getCash() < client.getMenu().getListOfFood().get(inputNumber - 1).getValue())
							{
								System.out.println("You don't have enough money for this. Please, try again or choose something else...");
							}
						}
						else 
						{
							System.out.println("No such meal in our menu! Please, try again or choose another option.");
							
						}					
						System.out.println("---------------------");
						break;
					case 2:
						if (!client.getOrder().getResultOrder().isEmpty())
						{
							System.out.println("Your order is:");
							for (int i = 0; i < client.getOrder().getResultOrder().size(); i++)
							{
								System.out.println( i + 1 + ". " + client.getOrder().getResultOrder().get(i));
							} 
							System.out.println("What would you like to remove?");
							
							int inputNumber1 = ClientUtils.getNumber();
	
							if ( inputNumber1 > 0 && inputNumber1 <= client.getOrder().getResultOrder().size())
							{
								String removeFood = client.getOrder().getResultOrder().get(inputNumber1 - 1);
								
								for (Food food: client.getMenu().getListOfFood())
								{
									if (removeFood.equals(food.getName()))
									{
										try {
											//client.removeFromOrder(food);
											clientManager.removeFromOrder(food);
											System.out.println("You've removed: " + food.getName());
										} catch (WrongValueException e) {
											System.out.println("You are trying to add a wrong value!");
										}
									}
								}
								
								
							}
							else 
							{
								System.out.println("No such meal in your order! Please, try again or choose another option.");
								
							}	
						}
						else 
						{
							System.out.println("Your order is empty. You have nothing to remove.");
						}
						System.out.println("---------------------");
						
						

						break;
					case 3:
						if (!client.getOrder().getResultOrder().isEmpty())
						{
							//client.viewOrder();
							clientManager.viewOrder();
						}
						else
						{
							System.out.println("Your order is empty.");
						}
						
						System.out.println("---------------------");
						break;
					case 4:
						if (!client.getOrder().getResultOrder().isEmpty())
						{
							//client.clearOrder();
							clientManager.clearOrder();
							System.out.println("Removed all from the order.");
						}
						else
						{
							System.out.println("Your order is empty. You have nothing to remove.");
						}
						
						System.out.println("---------------------");
						break;
					case 5:
						if (!client.getOrder().getResultOrder().isEmpty())
						{
							try {
								waiter.acceptOrder(client.getOrder());
							} catch (WrongValueException e) {
								System.out.println("You are trying to add a wrong value!");
							}
							System.out.println("---------------------");
							System.out.println("What would you like to do next?");
							while(true)
							{
								System.out.println("1. View bill");
								System.out.println("2. Pay");
								System.out.println("3. Give tip");
								System.out.println("4. Make another order");
								System.out.println("0. Exit");
								System.out.println("---------------------");
								switch(ClientUtils.getNumber())
								{
								case 1:
									try {
										waiter.giveBill(client.getBill());
									} catch (WrongValueException e) {
										System.out.println("You are trying to add a wrong value!");;
									}
									System.out.println("---------------------");
									break;
								case 2:
//									if (!client.pay())
//									{
//										System.out.println("---------------------");
//										waiter.callPolice();
//										System.exit(0);
//									}
									
									if (client.getPaid()) 
									{
										System.out.println("I've already paid for this...");
										System.out.println("---------------------");
									}
									else 
									{
										//client.pay();
										clientManager.pay();
										System.out.println("And now I have " +  client.getCash() + "$");
										System.out.println("---------------------");
									}
									break;
								case 3:
									
									if (client.getPaid())
									{
										System.out.println("How much tips do you want to give?");
										int inputTip = ClientUtils.getNumber();
										//client.tip(inputTip);
										clientManager.tip(inputTip);
										System.out.println("And now I have " +  client.getCash() + "$");
										System.out.println("---------------------");
									}
									else
									{
										System.out.println("I haven't paid for the bill yet.");
									}
									System.out.println("---------------------");
									break;
								case 4:
									if (client.getPaid())
									{
										Float currentOrderValue = client.getOrder().getResultValue();
										//client.clearOrder();
										clientManager.clearOrder();
										client.setCash(client.getCash() - currentOrderValue);
										client.setPaid(false);
										break out;
									}
									else 
									{
										System.out.println("You should pay for this order first...");
										System.out.println("---------------------");
									}
									break;
										
								case 0:
									if (client.getPaid())
									{
										System.out.println("Good bye!");
										System.exit(0);
									}
									else
									{
										System.out.println("You should pay for this order before exiting...");
										System.out.println("---------------------");
									}
									break;
								default:
									System.out.println("You should choose something.");
									break;
								}
							}
						}
						else
						{
							System.out.println("You have nothing to accept. Your order is empty.");
							
						}
						System.out.println("---------------------");
						break;
					case 6:
						System.out.println(client.getMenu());
						System.out.println("---------------------");
						break;
					case 7:
						System.out.println("I have " +  client.getCash() + "$");
						System.out.println("---------------------");
						break;
					case 0:
						System.out.println("Good bye!");
						System.exit(0);
						
						
						break;
					default:
						System.out.println("You should choose something.");
						System.out.println("---------------------");
						break;
						
					}
				}

			case 0:
				System.out.println("Good bye!");
				System.exit(0);
				break;
			default:
				System.out.println("You should choose something.");
				System.out.println("---------------------");
				break;
			}
			
		}
		
	}
	
	public static void startMenu()
	{
		Initialization.initialize();
		

		Waiter waiter = null;
		try {
			waiter = WaiterSerializer.deserialization();
		} catch (InvalidObjectException e) {
			System.out.println("Incorrect object for deserialization");
		}
		
		Client client = null;
		try {
			client = ClientSerializer.deserialization();
		} catch (InvalidObjectException e) {
			System.out.println("Incorrect object for deserialization");
		}
		
		
		
		
		clientWorking(client, waiter);
	}
}


