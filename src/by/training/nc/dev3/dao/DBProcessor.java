package by.training.nc.dev3.dao;

import java.sql.*;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

public class DBProcessor {

	private Connection connection;
	
	public DBProcessor() throws SQLException
	{
		DriverManager.registerDriver(new FabricMySQLDriver());
	}
	
	public Connection getConnection(String url, String userName, String password) throws SQLException
	{   
		if (connection != null)
			return connection;
		
		connection = DriverManager.getConnection(url, userName, password);
		return connection;
	}
}
