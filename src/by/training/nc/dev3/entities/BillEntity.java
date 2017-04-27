package by.training.nc.dev3.entities;

import by.training.nc.dev3.dao.interfaces.Identified;

public class BillEntity implements Identified<Integer> {

	private int id;
	private int order_id;
	private Float total_price;
	
	public BillEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BillEntity(int id, int order_id, Float total_price) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.total_price = total_price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Float getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Float total_price) {
		this.total_price = total_price;
	}
	
	
	
}
