package by.training.nc.dev3.dao.interfaces;

import java.io.Serializable;


/**
 * Интерфейс идентифицируемых объектов.
 */
public interface Identified<PK extends Serializable> {
	/** Возвращает идентификатор объекта */
    public PK getId();
}
