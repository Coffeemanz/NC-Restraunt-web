package by.training.nc.dev3.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.sql.PreparedStatement;
import by.training.nc.dev3.dao.interfaces.*;
import by.training.nc.dev3.exceptions.*;
import by.training.nc.dev3.entities.*;


/**
 * Абстрактный класс предоставляющий базовую реализацию CRUD операций с использованием JDBC.
 *
 * @param <T>  тип объекта персистенции
 * @param <PK> тип первичного ключа
 */
public abstract class AbstractJDBCDao <T extends Identified<PK>, PK extends Serializable> implements GenericDao<T, PK> {
	protected Connection connection;

	/**
     * Возвращает sql запрос для получения всех записей.
     * <p/>
     * SELECT * FROM [Table]
     */
    public abstract String getSelectQuery();

    /**
     * Возвращает sql запрос для вставки новой записи в базу данных.
     * <p/>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();

    /**
     * Возвращает sql запрос для обновления записи.
     * <p/>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     * Возвращает sql запрос для удаления записи из базы данных.
     * <p/>
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();

    /**
     * Разбирает ResultSet и возвращает список объектов соответствующих содержимому ResultSet.
     * @throws PersistException 
     */
    protected abstract List<T> parseResultSet(ResultSet rs);

    /**
     * Устанавливает аргументы insert запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object);

    /**
     * Устанавливает аргументы update запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;


    /**
     * Разбирает ResultSet и возвращает список объектов соответствующих содержимому ResultSet.
     */
//    protected abstract List<T> parseResultSet(ResultSet rs);
//
//    @Override
//    public T getByPK(int key) throws PersistException {
//        List<T> list;
//        String sql = getSelectQuery();
//        sql += " WHERE id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, key);
//            ResultSet rs = statement.executeQuery();
//            list = parseResultSet(rs);
//        } catch (Exception e) {
//            throw new PersistException(e);
//        }
//        if (list == null || list.size() == 0) {
//            return null;
//        }
//        if (list.size() > 1) {
//            throw new PersistException("Received more than one record.");
//        }
//        return list.iterator().next();
//    }

    @Override
    public List<T> getAll() throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return list;
    }
    
    @Override
    public void update(T object) throws PersistException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
//            if (count != 1) {
//                throw new PersistException("On update modify more then 1 record: " + count);
//            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void delete(T object) throws PersistException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more then 1 record: " + count);
            }
            statement.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
    
//    @Override
//    public T persist(T object) throws PersistException {
////        if (object.getId() != null) {
////            throw new PersistException("Object is already persist.");
////        }
//        T persistInstance = null;
//        // Добавляем запись
//        String sql = getCreateQuery();
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            prepareStatementForInsert(statement, object);
//            int count = statement.executeUpdate();
//            if (count != 1) {
//                throw new PersistException("On persist modify more then 1 record: " + count);
//            }
//        } catch (Exception e) {
//            //throw new PersistException(e);
//        	System.out.println("Something is not okay...");
//        }
//        // Получаем только что вставленную запись
//        sql = getSelectQuery() + " WHERE id = last_insert_id();";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            ResultSet rs = statement.executeQuery();
//            List<T> list = parseResultSet(rs);
//            if ((list == null) || (list.size() != 1)) {
//                throw new PersistException("Exception on findByPK new persist data.");
//            }
//            persistInstance = list.iterator().next();
//        } catch (Exception e) {
//            //throw new PersistException(e);
//        	System.out.println("smth is not okay...");
//        }
//        return persistInstance;
//    }
    
//    public T persist(T object) {
////      if (object.getId() != null) {
////          throw new PersistException("Object is already persist.");
////      }
//    	List<T> list;
//        String sql = getCreateQuery();
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            try {
//				prepareStatementForInsert(statement, object);
//			} catch (PersistException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new DaoEcxeption("Persist error");
//        }
//        sql = getSelectQuery() + " where id = last_insert_id();";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            ResultSet rs = statement.executeQuery();
//            list = parseResultSet(rs);
//        } catch (SQLException e) {
//            throw new DaoEcxeption("Persist error in select");
//        }
//        return list.iterator().next();
//  }
    
    public T persist(T object) {
        List<T> list;
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoEcxeption("Persist error");
        }
        sql = getSelectQuery() + " where id= last_insert_id();";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            throw new DaoEcxeption("Persist error in select");
        }
        
        T obj = null;
        try{
        obj = list.iterator().next();
        }
        catch(NoSuchElementException e) 
        {
        	System.out.println("Hz, vse norm dolzhno bit");
        }
        return obj;
    }

    public AbstractJDBCDao(Connection connection) {
        this.connection = connection;
    }
}
