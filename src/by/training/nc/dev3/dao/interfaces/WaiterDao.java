package by.training.nc.dev3.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import by.training.nc.dev3.entities.WaiterEntity;

public interface WaiterDao {

	/** ������� ����� ������ � ��������������� �� ������ */
    public WaiterEntity create();

    /** ���������� ������ ��������������� ������ � ��������� ������ key ��� null 
     * @throws SQLException */
    public WaiterEntity read(int key) throws SQLException;

    /** ��������� ��������� ������� group � ���� ������ */
    public void update(WaiterEntity waiter);

    /** ������� ������ �� ������� �� ���� ������ */
    public void delete(WaiterEntity waiter);

    /** ���������� ������ �������� ��������������� ���� ������� � ���� ������ */
    public List<WaiterEntity> getAll() throws SQLException;
	
}
