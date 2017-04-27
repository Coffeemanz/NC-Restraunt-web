package by.training.nc.dev3.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import by.training.nc.dev3.entities.OrderEntity;

public interface OrderDao {

	/** ������� ����� ������ � ��������������� �� ������ */
    public OrderEntity create();

    /** ���������� ������ ��������������� ������ � ��������� ������ key ��� null 
     * @throws SQLException */
    public OrderEntity read(int key) throws SQLException;

    /** ��������� ��������� ������� group � ���� ������ */
    public void update(OrderEntity order);

    /** ������� ������ �� ������� �� ���� ������ */
    public void delete(OrderEntity order);

    /** ���������� ������ �������� ��������������� ���� ������� � ���� ������ */
    public List<OrderEntity> getAll() throws SQLException;
	
}
