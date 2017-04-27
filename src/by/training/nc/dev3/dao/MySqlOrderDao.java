package by.training.nc.dev3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import by.training.nc.dev3.entities.ClientEntity;
import by.training.nc.dev3.entities.OrderEntity;
import by.training.nc.dev3.exceptions.DaoEcxeption;
import by.training.nc.dev3.exceptions.PersistException;

public class MySqlOrderDao extends AbstractJDBCDao<OrderEntity, Integer>{
	
	private class PersistOrder extends OrderEntity {
        public void setId(int id) {
            super.setId(id);
        }
    }
    
    public MySqlOrderDao(Connection connection) {
        super(connection);
    }
    
    @Override
    public String getSelectQuery() {
        return "SELECT * FROM restraunt_nc.orders";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO restraunt_nc.orders (waiter_id, client_id, food_id) \n" +
                "VALUES (?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE restraunt_nc.orders SET waiter_id= ?, client_id= ?, food_id= ? WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM restraunt_nc.orders WHERE id= ?;";
    }

    public String getDeletePkFoodQuery() {
        return "DELETE FROM restraunt_nc.orders WHERE food_id= ?;";
    }
    
    public String getDeletePkClientQuery() {
        return "DELETE FROM restraunt_nc.orders WHERE client_id= ?;";
    }
    
    @Override
    public OrderEntity create() throws PersistException {
        OrderEntity w = new OrderEntity();
        return persist(w);
    }

 

    @Override
    protected List<OrderEntity> parseResultSet(ResultSet rs) {
        LinkedList<OrderEntity> result = new LinkedList<OrderEntity>();
      
            try {
				while (rs.next()) {
				    PersistOrder m = new PersistOrder();
				    m.setId(rs.getInt("id"));
				    m.setWaiter_id(rs.getInt("waiter_id"));
				    m.setClient_id(rs.getInt("client_id"));
				    m.setFood_id(rs.getInt("food_id"));
				    result.add(m);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, OrderEntity object)  {
       
            try {
				//statement.setInt(1, object.getId());
				 statement.setInt(1, object.getWaiter_id());
		            statement.setInt(2, object.getClient_id());
		            statement.setInt(3, object.getFood_id());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoEcxeption("Order dao prepare for update error ");
			}
           
        
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, OrderEntity object) throws PersistException {
        try {
        	
        	
            statement.setInt(1, object.getWaiter_id());
            statement.setInt(2, object.getClient_id());
            statement.setInt(3, object.getFood_id());
            statement.setInt(4, object.getId());
            
          
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

	@Override
	public OrderEntity getByPK(int key) throws SQLException, PersistException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void deleteByPkFood(int key) throws PersistException {
        String sql = getDeletePkFoodQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setInt(1, key);
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more then 1 record: " + count);
            }
            statement.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
	
	public void deleteByPkClient(int key) throws PersistException {
        String sql = getDeletePkClientQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setInt(1, key);
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
//            if (count != 1) {
//                throw new PersistException("On delete modify more then 1 record: " + count);
//            }
            statement.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
	

}
