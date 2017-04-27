package by.training.nc.dev3.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import by.training.nc.dev3.entities.FoodEntity;

public interface FoodDao {

	/** Создает новую запись и соответствующий ей объект */
    public FoodEntity create();

    /** Возвращает объект соответствующий записи с первичным ключом key или null 
     * @throws SQLException */
    public FoodEntity read(int key) throws SQLException;

    /** Сохраняет состояние объекта group в базе данных */
    public void update(FoodEntity food);

    /** Удаляет запись об объекте из базы данных */
    public void delete(FoodEntity food);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<FoodEntity> getAll() throws SQLException;
	
}
