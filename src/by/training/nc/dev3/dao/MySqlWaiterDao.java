package by.training.nc.dev3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


import by.training.nc.dev3.entities.*;
import by.training.nc.dev3.exceptions.DaoEcxeption;
import by.training.nc.dev3.exceptions.PersistException;

public class MySqlWaiterDao extends AbstractJDBCDao<WaiterEntity, Integer> {
	
	private class PersistWaiter extends WaiterEntity {
        public void setId(int id) {
            super.setId(id);
        }
    }
    
	 public MySqlWaiterDao(Connection connection) {
	        super(connection);
	    }
    
    @Override
    public String getSelectQuery() {
        return "SELECT * FROM restraunt_nc.waiters";
    }
    
    public String getSelectOneQuery(){
    	return "Select * from restraunt_nc.waiters where id= ?;";
    }
    
    public String getSelectEmailQuery(){
    	return "Select * from restraunt_nc.waiters where waiter_email= ?;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO restraunt_nc.waiters (waiter_name) \n" +
                "VALUES (?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE restraunt_nc.waiters SET waiter_name= ?,  WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM restraunt_nc.menu WHERE id= ?;";
    }

    @Override
    public WaiterEntity create() throws PersistException {
        WaiterEntity w = new WaiterEntity();
        return persist(w);
    }

 

    @Override
    protected List<WaiterEntity> parseResultSet(ResultSet rs) {
        LinkedList<WaiterEntity> result = new LinkedList<WaiterEntity>();
 
            try {
				while (rs.next()) {
				    PersistWaiter m = new PersistWaiter();
				    m.setId(rs.getInt("id"));
				    m.setWaiter_name(rs.getString("waiter_name"));
				    m.setWaiter_email(rs.getString("waiter_email"));
				    m.setWaiter_password(rs.getString("waiter_password"));
				    result.add(m);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
     
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, WaiterEntity object)  {
     
            try {
				//statement.setInt(1, object.getId());
				 statement.setString(1, object.getWaiter_name());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoEcxeption("Waiter dao prepare for update error ");
			}
           
      
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, WaiterEntity object) throws PersistException {
        try {
            statement.setString(1, object.getWaiter_name());
            statement.setInt(2, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

	@Override
public WaiterEntity getByPK(int key) throws SQLException, PersistException {
		
		List<WaiterEntity> list = null;
		String sql = getSelectOneQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			   statement.setInt(1, key);
	           ResultSet rs = statement.executeQuery();
	           list = parseResultSet(rs);
	       } catch (SQLException e) {
	           throw new DaoEcxeption("Persist error in select");
	       }
		return list.get(0);
	}
	
public WaiterEntity getByEmail(String email) throws SQLException, PersistException {
		
		List<WaiterEntity> list = null;
		String sql = getSelectEmailQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			   statement.setString(1, email);
	           ResultSet rs = statement.executeQuery();
	           list = parseResultSet(rs);
	       } catch (SQLException e) {
	           throw new DaoEcxeption("Persist error in select");
	       }
		//return list.get(0);
		
		if (list.size() == 0) 
		{
			return new WaiterEntity();
		}
		else
		{
			return list.get(0);
		}
	}

}
