package by.training.nc.dev3.dao.interfaces;

import by.training.nc.dev3.entities.*;
import by.training.nc.dev3.exceptions.PersistException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Унифицированный интерфейс управления персистентным состоянием объектов
 * @param <T> тип объекта персистенции
 * @param <PK> тип первичного ключа
 */
public interface GenericDao<T, PK extends Serializable> {
	/** Создает новую запись и соответствующий ей объект 
	 * @throws PersistException */
    public T create() throws SQLException, PersistException;

    /** Создает новую запись, соответствующую объекту object 
     * @throws PersistException */
    public T persist(T object)  throws SQLException, PersistException;

    /** Возвращает объект соответствующий записи с первичным ключом key или null 
     * @throws PersistException */
    public T getByPK(int key) throws SQLException, PersistException;

    /** Сохраняет состояние объекта group в базе данных 
     * @throws PersistException */
    public void update(T object) throws SQLException, PersistException;

    /** Удаляет запись об объекте из базы данных 
     * @throws PersistException */
    public void delete(T object) throws SQLException, PersistException;

    /** Возвращает список объектов соответствующих всем записям в базе данных 
     * @throws PersistException */
    public List<T> getAll() throws SQLException, PersistException;

}
