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

public class MySqlBillDao extends AbstractJDBCDao<BillEntity, Integer>{
	
	private class PersistBill extends BillEntity {
        public void setId(int id) {
            super.setId(id);
        }
    }
    
    public MySqlBillDao(Connection connection) {
        super(connection);
    }
    
    @Override
    public String getSelectQuery() {
        return "SELECT * FROM restraunt_nc.bills";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO restraunt_nc.bills (order_id, total_price) \n" +
                "VALUES (?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE restraunt_nc.bills SET order_id= ?, total_price= ? WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM restraunt_nc.bills WHERE id= ?;";
    }

    @Override
    public BillEntity create() throws PersistException {
        BillEntity w = new BillEntity();
        return persist(w);
    }

 

    @Override
    protected List<BillEntity> parseResultSet(ResultSet rs) {
        LinkedList<BillEntity> result = new LinkedList<BillEntity>();
     
            try {
				while (rs.next()) {
				    PersistBill m = new PersistBill();
				    m.setId(rs.getInt("id"));
				    m.setOrder_id(rs.getInt("order_id"));
				    m.setTotal_price(rs.getFloat("total_price"));
				    result.add(m);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Nope");
			}
     
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, BillEntity object) {
    
            try {
				//statement.setInt(1, object.getId());
				statement.setInt(1, object.getOrder_id());
	            statement.setFloat(2, object.getTotal_price());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoEcxeption("Bill dao prepare for update error ");
			}
            
            
     
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, BillEntity object) throws PersistException {
        try {
        	
            statement.setInt(1, object.getOrder_id());
            statement.setFloat(2, object.getTotal_price());
            statement.setInt(3, object.getId());
        	
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

	@Override
	public BillEntity getByPK(int key) throws SQLException, PersistException {
		// TODO Auto-generated method stub
		return null;
	}

}
