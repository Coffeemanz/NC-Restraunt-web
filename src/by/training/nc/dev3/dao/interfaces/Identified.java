package by.training.nc.dev3.dao.interfaces;

import java.io.Serializable;


/**
 * ��������� ���������������� ��������.
 */
public interface Identified<PK extends Serializable> {
	/** ���������� ������������� ������� */
    public PK getId();
}
