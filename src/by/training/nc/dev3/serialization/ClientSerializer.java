package by.training.nc.dev3.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import by.training.nc.dev3.beans.Client;
import by.training.nc.dev3.beans.Menu;

/**
 * 
 * Serialization for Client objects
 * 
 * @author Сергей
 *
 */
public class ClientSerializer {

	
	/**
	 * Serializes the Client object
	 * 
	 * @param bill
	 */
	
	private final static String ROOT_DIRECTORY_PATH = "src/by/training/nc/dev3/files/input/";
	private final static String FILE_EXTENSION = ".ser";
	
	public static void serializer(Client client)
	{
		
		String fileName = ROOT_DIRECTORY_PATH + "client" + FILE_EXTENSION; 
		ObjectOutputStream os = null;
		
		try 
		{
			FileOutputStream fs = new FileOutputStream(fileName);
			os = new ObjectOutputStream(fs);
			os.writeObject(client);
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
	 * Deserializes the Bill object
	 * 
	 * @return
	 * @throws InvalidObjectException
	 */
	public static Client deserialization() throws InvalidObjectException
	{
		

		String fileName = ROOT_DIRECTORY_PATH + "client" + FILE_EXTENSION; 
		
		ObjectInputStream os = null;
		try 
		{
			FileInputStream fs = new FileInputStream(fileName);
			os = new ObjectInputStream(fs);
			Object ob = os.readObject();
			Client c = (Client) ob;
			//os.mlose();
			return c;
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
