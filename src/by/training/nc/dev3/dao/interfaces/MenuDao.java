package by.training.nc.dev3.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import by.training.nc.dev3.entities.*;

public interface MenuDao {

	/** ������� ����� ������ � ��������������� �� ������ */
    public MenuEntity create();

    /** ���������� ������ ��������������� ������ � ��������� ������ key ��� null 
     * @throws SQLException */
    public MenuEntity read(int key) throws SQLException;

    /** ��������� ��������� ������� group � ���� ������ */
    public void update(MenuEntity menu);

    /** ������� ������ �� ������� �� ���� ������ */
    public void delete(MenuEntity menu);

    /** ���������� ������ �������� ��������������� ���� ������� � ���� ������ */
    public List<MenuEntity> getAll() throws SQLException;
	
}
