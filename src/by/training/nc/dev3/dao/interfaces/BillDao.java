package by.training.nc.dev3.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import by.training.nc.dev3.entities.BillEntity;

public interface BillDao {
	
	/** Создает новую запись и соответствующий ей объект */
    public BillEntity create();

    /** Возвращает объект соответствующий записи с первичным ключом key или null 
     * @throws SQLException */
    public BillEntity read(int key) throws SQLException;

    /** Сохраняет состояние объекта group в базе данных */
    public void update(BillEntity bill);

    /** Удаляет запись об объекте из базы данных */
    public void delete(BillEntity bill);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<BillEntity> getAll() throws SQLException;

}
