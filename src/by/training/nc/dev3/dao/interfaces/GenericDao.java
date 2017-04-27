package by.training.nc.dev3.dao.interfaces;

import by.training.nc.dev3.entities.*;
import by.training.nc.dev3.exceptions.PersistException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * ��������������� ��������� ���������� ������������� ���������� ��������
 * @param <T> ��� ������� ������������
 * @param <PK> ��� ���������� �����
 */
public interface GenericDao<T, PK extends Serializable> {
	/** ������� ����� ������ � ��������������� �� ������ 
	 * @throws PersistException */
    public T create() throws SQLException, PersistException;

    /** ������� ����� ������, ��������������� ������� object 
     * @throws PersistException */
    public T persist(T object)  throws SQLException, PersistException;

    /** ���������� ������ ��������������� ������ � ��������� ������ key ��� null 
     * @throws PersistException */
    public T getByPK(int key) throws SQLException, PersistException;

    /** ��������� ��������� ������� group � ���� ������ 
     * @throws PersistException */
    public void update(T object) throws SQLException, PersistException;

    /** ������� ������ �� ������� �� ���� ������ 
     * @throws PersistException */
    public void delete(T object) throws SQLException, PersistException;

    /** ���������� ������ �������� ��������������� ���� ������� � ���� ������ 
     * @throws PersistException */
    public List<T> getAll() throws SQLException, PersistException;

}
