package by.training.nc.dev3.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import by.training.nc.dev3.entities.*;

public interface MenuDao {

	/** Создает новую запись и соответствующий ей объект */
    public MenuEntity create();

    /** Возвращает объект соответствующий записи с первичным ключом key или null 
     * @throws SQLException */
    public MenuEntity read(int key) throws SQLException;

    /** Сохраняет состояние объекта group в базе данных */
    public void update(MenuEntity menu);

    /** Удаляет запись об объекте из базы данных */
    public void delete(MenuEntity menu);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<MenuEntity> getAll() throws SQLException;
	
}
