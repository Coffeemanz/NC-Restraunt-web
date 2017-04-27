package by.training.nc.dev3.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import by.training.nc.dev3.beans.Bill;
import by.training.nc.dev3.beans.Menu;

/**
 * 
 * Serialization for Bill objects
 * 
 * @author Сергей
 *
 */

public class BillSerializer {
	
	/**
	 * Serializes the Bill object
	 * 
	 * @param bill
	 * 
	 */
	
	private final static String ROOT_DIRECTORY_PATH = "src/by/training/nc/dev3/files/input";
	private final static String FILE_EXTENSION = ".ser";
	
	
	public static void serializer(Bill bill)
	{
	
		String fileName = ROOT_DIRECTORY_PATH + "bill" + FILE_EXTENSION; 
		
		ObjectOutputStream os = null;
		try 
		{
			FileOutputStream fs = new FileOutputStream(fileName);
			os = new ObjectOutputStream(fs);
			os.writeObject(bill);
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
	public static Bill deserialization() throws InvalidObjectException
	{
		
		String fileName = ROOT_DIRECTORY_PATH + "bill" + FILE_EXTENSION; 
		ObjectInputStream os = null;
		try 
		{
			FileInputStream fs = new FileInputStream(fileName);
			os = new ObjectInputStream(fs);
			Object ob = os.readObject();
			Bill b = (Bill) ob;
			//os.mlose();
			return b;
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
