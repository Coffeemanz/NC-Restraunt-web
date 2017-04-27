package by.training.nc.dev3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import by.training.nc.dev3.entities.FoodEntity;
import by.training.nc.dev3.exceptions.DaoEcxeption;
import by.training.nc.dev3.exceptions.PersistException;

public class MySqlFoodDao extends AbstractJDBCDao<FoodEntity, Integer>{
	
	private class PersistFood extends FoodEntity {
        public void setId(int id) {
            super.setId(id);
        }
    }
    
    public MySqlFoodDao(Connection connection) {
        super(connection);
    }
    
    @Override
    public String getSelectQuery() {
        return "SELECT * FROM restraunt_nc.food";
    }
    
    public String getSelectOneQuery(){
    	return "Select * from restraunt_nc.food where id= ?;";
    }
    
    public String getSelectMenuQuery(){
    	return "Select * from restraunt_nc.food where id_menu= ?;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO restraunt_nc.food (food_name, food_desc, food_price, id_menu) \n" +
                "VALUES (?, ?, ?, ?);";
    }

//    @Override
//    public String getUpdateQuery() {
//        return "UPDATE restraunt_nc.food SET food_name= ?, food_desc= ?, food_price=?, id_menu= ? WHERE id= ?;";
//    }
    
    @Override
    public String getUpdateQuery() {
        return "UPDATE restraunt_nc.food SET id_menu= ? WHERE id= ?;";
    }
    
    public String getUpdateRemoveFromMenuQuery() {
        return "UPDATE restraunt_nc.food SET id_menu=NULL WHERE id= ?;";
    }
    

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM restraunt_nc.food WHERE id= ?;";
    }

    @Override
    public FoodEntity create() throws PersistException {
        FoodEntity w = new FoodEntity();
        return persist(w);
    }

 

    @Override
    protected List<FoodEntity> parseResultSet(ResultSet rs) {
        LinkedList<FoodEntity> result = new LinkedList<FoodEntity>();
   
            try {
				while (rs.next()) {
				    PersistFood m = new PersistFood();
				    m.setId(rs.getInt("id"));
				    m.setFood_name(rs.getString("food_name"));
				    m.setFood_desc(rs.getString("food_desc"));
				    m.setFood_price(rs.getFloat("food_price"));
				    m.setId_menu(rs.getInt("id_menu"));
				    result.add(m);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, FoodEntity object) {
    
            try {
				//statement.setInt(1, object.getId());
				statement.setString(1, object.getFood_name());
	            statement.setString(2, object.getFood_desc());
	            statement.setFloat(3, object.getFood_price());
	            statement.setInt(4, object.getId_menu());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoEcxeption("Food dao prepare for update error ");
			}
            
            
       
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, FoodEntity object) throws PersistException {
        try {
        	
            //statement.setString(1, object.getFood_name());
            //statement.setString(2, object.getFood_desc());
            //statement.setFloat(3, object.getFood_price());
            statement.setInt(1, object.getId_menu());
            statement.setInt(2, object.getId());
        	
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
    
    protected void prepareStatementForUpdateRemoveFromMenu(PreparedStatement statement, FoodEntity object) throws PersistException {
        try {
        	
            //statement.setString(1, object.getFood_name());
            //statement.setString(2, object.getFood_desc());
            //statement.setFloat(3, object.getFood_price());
           // statement.setInt(1, object.getId_menu());
            statement.setInt(1, object.getId());
        	
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public void updateRemoveFromMenu(FoodEntity f) throws PersistException {
        String sql = getUpdateRemoveFromMenuQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdateRemoveFromMenu(statement, f);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
    
    
	@Override
	public FoodEntity getByPK(int key) throws SQLException, PersistException {
		
		List<FoodEntity> list = null;
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
	
public List<FoodEntity> getInMenu(int key) throws SQLException, PersistException {
		
		List<FoodEntity> list = null;
		String sql = getSelectMenuQuery();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			   statement.setInt(1, key);
	           ResultSet rs = statement.executeQuery();
	           list = parseResultSet(rs);
	       } catch (SQLException e) {
	           throw new DaoEcxeption("Persist error in select");
	       }
		return list;
	}

}
