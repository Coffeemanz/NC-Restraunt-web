package by.training.nc.dev3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import by.training.nc.dev3.entities.ClientEntity;
import by.training.nc.dev3.entities.FoodEntity;
import by.training.nc.dev3.exceptions.DaoEcxeption;
import by.training.nc.dev3.exceptions.PersistException;

public class MySqlClientDao extends AbstractJDBCDao<ClientEntity, Integer>{
	
	private class PersistClient extends ClientEntity {
        public void setId(int id) {
            super.setId(id);
        }
    }
    
    public MySqlClientDao(Connection connection) {
        super(connection);
    }
    
    @Override
    public String getSelectQuery() {
        return "SELECT * FROM restraunt_nc.clients";
    }
    
    public String getSelectOneQuery(){
    	return "Select * from restraunt_nc.clients where id= ?;";
    }
    
    public String getSelectEmailQuery(){
    	return "Select * from restraunt_nc.clients where client_email= ?;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO restraunt_nc.clients (client_name, client_email,client_password, client_cash) \n" +
                "VALUES (?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE restraunt_nc.clients SET client_cash= ? WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM restraunt_nc.clients WHERE id= ?;";
    }

    @Override
    public ClientEntity create() throws PersistException {
        ClientEntity w = new ClientEntity();
        return persist(w);
    }

 

    @Override
    protected List<ClientEntity> parseResultSet(ResultSet rs) {
        LinkedList<ClientEntity> result = new LinkedList<ClientEntity>();

            try {
				while (rs.next()) {
				    PersistClient m = new PersistClient();
				    m.setId(rs.getInt("id"));
				    m.setClient_name(rs.getString("client_name"));
				    m.setClient_email(rs.getString("client_email"));
				    m.setClient_password(rs.getString("client_password"));
				    m.setClient_cash(rs.getFloat("client_cash"));
				    m.setPaid(rs.getBoolean("paid"));
				    result.add(m);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoEcxeption("Nope");
			}
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, ClientEntity object) {
     
            
            try {
            	//statement.setInt(1, object.getId());
				statement.setString(1, object.getClient_name());
				statement.setString(2, object.getClient_email());
				statement.setString(3, object.getClient_password());
				statement.setFloat(4, object.getClient_cash());
				//statement.setBoolean(3, object.getPaid());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoEcxeption("Client dao prepare for update error ");
			}
            
      
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, ClientEntity object) throws PersistException {
        try {
        	
			statement.setFloat(1, object.getClient_cash());
            statement.setInt(2, object.getId());
        	
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

	@Override

	public ClientEntity getByPK(int key) throws SQLException, PersistException {
		
		List<ClientEntity> list = null;
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
	
public ClientEntity getByEmail(String email) throws SQLException, PersistException {
		
		List<ClientEntity> list = null;
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
			return new ClientEntity();
		}
		else
		{
			return list.get(0);
		}
	}

}
