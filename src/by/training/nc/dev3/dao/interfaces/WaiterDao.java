package by.training.nc.dev3.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import by.training.nc.dev3.entities.WaiterEntity;

public interface WaiterDao {

	/** Создает новую запись и соответствующий ей объект */
    public WaiterEntity create();

    /** Возвращает объект соответствующий записи с первичным ключом key или null 
     * @throws SQLException */
    public WaiterEntity read(int key) throws SQLException;

    /** Сохраняет состояние объекта group в базе данных */
    public void update(WaiterEntity waiter);

    /** Удаляет запись об объекте из базы данных */
    public void delete(WaiterEntity waiter);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<WaiterEntity> getAll() throws SQLException;
	
}
