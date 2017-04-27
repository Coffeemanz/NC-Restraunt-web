package by.training.nc.dev3.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import by.training.nc.dev3.entities.ClientEntity;

public interface ClientDao {

	/** Создает новую запись и соответствующий ей объект */
    public ClientEntity create();

    /** Возвращает объект соответствующий записи с первичным ключом key или null 
     * @throws SQLException */
    public ClientEntity read(int key) throws SQLException;

    /** Сохраняет состояние объекта group в базе данных */
    public void update(ClientEntity client);

    /** Удаляет запись об объекте из базы данных */
    public void delete(ClientEntity client);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<ClientEntity> getAll() throws SQLException;
	
	
}
