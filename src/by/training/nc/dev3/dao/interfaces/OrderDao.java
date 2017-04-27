package by.training.nc.dev3.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import by.training.nc.dev3.entities.OrderEntity;

public interface OrderDao {

	/** Создает новую запись и соответствующий ей объект */
    public OrderEntity create();

    /** Возвращает объект соответствующий записи с первичным ключом key или null 
     * @throws SQLException */
    public OrderEntity read(int key) throws SQLException;

    /** Сохраняет состояние объекта group в базе данных */
    public void update(OrderEntity order);

    /** Удаляет запись об объекте из базы данных */
    public void delete(OrderEntity order);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<OrderEntity> getAll() throws SQLException;
	
}
