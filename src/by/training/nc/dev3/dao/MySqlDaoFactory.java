package by.training.nc.dev3.dao;

import by.training.nc.dev3.dao.interfaces.*;
import by.training.nc.dev3.entities.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

public class MySqlDaoFactory implements DaoFactory {
	
    private String user = "root";//Логин пользователя
    private String password = "root";//Пароль пользователя
    private String url = "jdbc:mysql://localhost:3306/mysql";//URL адрес
    
    public MySqlDaoFactory() {
//		try {
//			DriverManager.registerDriver(new FabricMySQLDriver());
//		} catch (SQLException e) {
//			System.out.println("Can't register the driver");
//		}
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public Connection getConnection() throws SQLException
    {
    	return DriverManager.getConnection(url, user, password);
    }
    
    
    public MySqlMenuDao getMenuDao(Connection connection)
    {
    	return new MySqlMenuDao(connection);
    }

    
	public MySqlWaiterDao getWaiterDao(Connection connection)
	{
		return new MySqlWaiterDao(connection);
	}


	public MySqlFoodDao getFoodDao(Connection connection) {
		return new MySqlFoodDao(connection);
	}


	public MySqlClientDao getClientDao(Connection connection) {
		return new MySqlClientDao(connection);
	}


	public MySqlOrderDao getOrderDao(Connection connection) {
		return new MySqlOrderDao(connection);
	}

	public MySqlBillDao getBillDao(Connection connection) {
		return new MySqlBillDao(connection);
	}
    


	
	
    
    

}
