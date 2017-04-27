package by.training.nc.dev3.dao;

import by.training.nc.dev3.dao.interfaces.*;
import by.training.nc.dev3.entities.*;
import by.training.nc.dev3.exceptions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MySqlMenuDao extends AbstractJDBCDao<MenuEntity, Integer>{

    private class PersistMenu extends MenuEntity {
        public void setId(int id) {
            super.setId(id);
        }
    }
    
    public MySqlMenuDao(Connection connection) {
        super(connection);
    }
    
    @Override
    public String getSelectQuery() {
        return "SELECT * FROM restraunt_nc.menu";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO restraunt_nc.menu (id, menu_name) \n" +
                "VALUES (?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE restraunt_nc.menu SET menu_name= ?,  WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM restraunt_nc.menu WHERE id= ?;";
    }

    @Override
    public MenuEntity create() throws PersistException {
        MenuEntity m = new MenuEntity();
        return persist(m);
    }

 

    @Override
    protected List<MenuEntity> parseResultSet(ResultSet rs)  {
        LinkedList<MenuEntity> result = new LinkedList<MenuEntity>();
   
            try {
				while (rs.next()) {
				    PersistMenu m = new PersistMenu();
				    m.setId(rs.getInt("id"));
				    m.setMenu_name(rs.getString("menu_name"));
				    result.add(m);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, MenuEntity object) {
  
            try {
				statement.setInt(1, object.getId());
				statement.setString(2, object.getMenu_name());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoEcxeption("Menu dao prepare for update error ");
			}
            
     
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, MenuEntity object) throws PersistException {
        try {
            statement.setString(1, object.getMenu_name());
            statement.setInt(2, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

	@Override
	public MenuEntity getByPK(int key) throws SQLException, PersistException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
//	private final Connection connection;
//	
//	public MySqlMenuDao(Connection connection) {
//        this.connection = connection;
//    }
//
//    @Override
//    public MenuEntity create() {
//        return null;
//    }
//    
//    @Override
//    public MenuEntity read(int key) throws SQLException{
//        String sql = "SELECT * FROM restraunt_nc.menu WHERE id = ?;";
//        PreparedStatement stm = connection.prepareStatement(sql);
//
//        stm.setInt(1, key);
//
//        ResultSet rs = stm.executeQuery();
//        rs.next();
//        MenuEntity m = new MenuEntity();
//        m.setProduct_id(rs.getInt("product_id"));
//        m.setProduct_name(rs.getString("product_name"));
//        m.setProduct_description(rs.getString("product_description"));
//        m.setProduct_price(rs.getFloat("product_price"));
//        return m;
//    }
//    
//    @Override
//    public void update(MenuEntity menu) {
//
//    }
//    
//    @Override
//    public void delete(MenuEntity menu) {
//
//    }
//    
//    @Override
//    public List<MenuEntity> getAll() throws SQLException {
//        String sql = "SELECT * FROM restraunt_nc.menu;";
//        PreparedStatement stm = connection.prepareStatement(sql);
//        ResultSet rs = stm.executeQuery();
//        List<MenuEntity> list = new ArrayList<MenuEntity>();
//        while (rs.next()) {
//            MenuEntity m = new MenuEntity();
//            m.setProduct_id(rs.getInt("product_id"));
//            m.setProduct_name(rs.getString("product_name"));
//            m.setProduct_description(rs.getString("product_description"));
//            m.setProduct_price(rs.getFloat("product_price"));
//            list.add(m);
//        }
//        return list;
//    }
    
    
}
