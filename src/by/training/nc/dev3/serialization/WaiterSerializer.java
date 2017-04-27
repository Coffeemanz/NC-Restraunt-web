package by.training.nc.dev3.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import by.training.nc.dev3.beans.Menu;
import by.training.nc.dev3.beans.Waiter;


/**
 * 
 * Serialization for Order objects
 * 
 * @author Сергей
 *
 */
public  class WaiterSerializer {
	
	
	/**
	 * Serializes the Waiter object
	 * 
	 * @param bill
	 */
	
	private final static String ROOT_DIRECTORY_PATH = "src/by/training/nc/dev3/files/input/";
	private final static String FILE_EXTENSION = ".ser";
	
	public static void serializer(Waiter waiter)
	{
		String fileName = ROOT_DIRECTORY_PATH + "waiter" + FILE_EXTENSION; 
		ObjectOutputStream os = null;

		try 
		{
			FileOutputStream fs = new FileOutputStream(fileName);
			os = new ObjectOutputStream(fs);
			os.writeObject(waiter);

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
	 * Deserializes the Waiter object
	 * 
	 * @return
	 * @throws InvalidObjectException
	 */
	public static Waiter deserialization() throws InvalidObjectException
	{
		String fileName = ROOT_DIRECTORY_PATH + "waiter" + FILE_EXTENSION; 

		ObjectInputStream os = null;
		try 
		{
			FileInputStream fs = new FileInputStream(fileName);
			os = new ObjectInputStream(fs);
			Object ob = os.readObject();
			Waiter w = (Waiter) ob;
			//os.mlose();
			return w;
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
