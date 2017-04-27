package by.training.nc.dev3.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import by.training.nc.dev3.entities.ClientEntity;

public interface ClientDao {

	/** ������� ����� ������ � ��������������� �� ������ */
    public ClientEntity create();

    /** ���������� ������ ��������������� ������ � ��������� ������ key ��� null 
     * @throws SQLException */
    public ClientEntity read(int key) throws SQLException;

    /** ��������� ��������� ������� group � ���� ������ */
    public void update(ClientEntity client);

    /** ������� ������ �� ������� �� ���� ������ */
    public void delete(ClientEntity client);

    /** ���������� ������ �������� ��������������� ���� ������� � ���� ������ */
    public List<ClientEntity> getAll() throws SQLException;
	
	
}
