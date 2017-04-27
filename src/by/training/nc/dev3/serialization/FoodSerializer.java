package by.training.nc.dev3.serialization;

import java.io.*;

import by.training.nc.dev3.beans.Food;


/**
 * 
 * Serialization for Food objects
 * 
 * @author Сергей
 *
 */
public class FoodSerializer {

	
	/**
	 * Serializes the Food object
	 * 
	 * @param bill
	 */
	
	private final static String ROOT_DIRECTORY_PATH = "src/by/training/nc/dev3/files/input/";
	private final static String FILE_EXTENSION = ".ser";	
	
	public static void serializer(Food food)
	{
		String fileName = ROOT_DIRECTORY_PATH + "food" + FILE_EXTENSION; 
		ObjectOutputStream os = null;
		try 
		{
			FileOutputStream fs = new FileOutputStream(fileName);
			os = new ObjectOutputStream(fs);
			os.writeObject(food);
		}
		catch (NotSerializableException e)
		{
			System.out.println("Object can't be serialized!");
		} catch (IOException e) {
			System.err.println(e);;
		}
		finally	
		{
			try 
			{
				if (os != null)
				{
					os.close();
				}
			}
			catch (IOException e)
			{
				System.err.println("Error with closing the flow!");
			}
		}
		
	}
	
	
	/**
	 * 
	 * Deserializes the Food object
	 * 
	 * @return
	 * @throws InvalidObjectException
	 */
	public static Food deserialization() throws InvalidObjectException
	{
		String fileName = ROOT_DIRECTORY_PATH + "food" + FILE_EXTENSION; 
		ObjectInputStream os = null;
		try 
		{
			FileInputStream fs = new FileInputStream(fileName);
			os = new ObjectInputStream(fs);
			Object ob = os.readObject();
			Food f = (Food) ob;
			//os.close();
			return f;
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Can't find file to recover object from!");
		}
		catch (IOException e)
		{
			System.err.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println("Can't match class");
		}
		finally	
		{
			try 
			{
				if (os != null)
				{
					os.close();
				}
			}
			catch (IOException e)
			{
				System.err.println("Error with closing the flow!");
			}
		}
		throw new InvalidObjectException("Object was't recovered");
		
	}
	
}
