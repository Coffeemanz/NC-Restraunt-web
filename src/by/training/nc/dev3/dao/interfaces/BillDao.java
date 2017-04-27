package by.training.nc.dev3.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import by.training.nc.dev3.entities.BillEntity;

public interface BillDao {
	
	/** ������� ����� ������ � ��������������� �� ������ */
    public BillEntity create();

    /** ���������� ������ ��������������� ������ � ��������� ������ key ��� null 
     * @throws SQLException */
    public BillEntity read(int key) throws SQLException;

    /** ��������� ��������� ������� group � ���� ������ */
    public void update(BillEntity bill);

    /** ������� ������ �� ������� �� ���� ������ */
    public void delete(BillEntity bill);

    /** ���������� ������ �������� ��������������� ���� ������� � ���� ������ */
    public List<BillEntity> getAll() throws SQLException;

}
