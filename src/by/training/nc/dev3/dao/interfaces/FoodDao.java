package by.training.nc.dev3.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import by.training.nc.dev3.entities.FoodEntity;

public interface FoodDao {

	/** ������� ����� ������ � ��������������� �� ������ */
    public FoodEntity create();

    /** ���������� ������ ��������������� ������ � ��������� ������ key ��� null 
     * @throws SQLException */
    public FoodEntity read(int key) throws SQLException;

    /** ��������� ��������� ������� group � ���� ������ */
    public void update(FoodEntity food);

    /** ������� ������ �� ������� �� ���� ������ */
    public void delete(FoodEntity food);

    /** ���������� ������ �������� ��������������� ���� ������� � ���� ������ */
    public List<FoodEntity> getAll() throws SQLException;
	
}
