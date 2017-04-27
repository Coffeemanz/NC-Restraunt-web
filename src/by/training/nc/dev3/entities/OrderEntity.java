package by.training.nc.dev3.entities;

import java.io.Serializable;

import by.training.nc.dev3.dao.interfaces.Identified;

public class OrderEntity implements Identified<Integer>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int waiter_id;
	private int client_id;
	private int food_id;
	//private Float total_price;
	
	public OrderEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrderEntity(int waiter_id, int client_id, int food_id) {
		super();
		this.waiter_id = waiter_id;
		this.client_id = client_id;
		this.food_id = food_id;
		//this.total_price = total_price;
	}

	public OrderEntity(int id, int waiter_id, int client_id, int food_id) {
		super();
		this.id = id;
		this.waiter_id = waiter_id;
		this.client_id = client_id;
		this.food_id = food_id;
		//this.total_price = total_price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the waiter_id
	 */
	public int getWaiter_id() {
		return waiter_id;
	}

	/**
	 * @param waiter_id the waiter_id to set
	 */
	public void setWaiter_id(int waiter_id) {
		this.waiter_id = waiter_id;
	}

	/**
	 * @return the client_id
	 */
	public int getClient_id() {
		return client_id;
	}

	/**
	 * @param client_id the client_id to set
	 */
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	/**
	 * @return the food_id
	 */
	public int getFood_id() {
		return food_id;
	}

	/**
	 * @param food_id the food_id to set
	 */
	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}

	@Override
	public String toString() {
		return "OrderEntity [id=" + id + ", waiter_id=" + waiter_id + ", client_id=" + client_id + ", food_id="
				+ food_id + "]";
	}

//	/**
//	 * @return the total_price
//	 */
//	public Float getTotal_price() {
//		return total_price;
//	}
//
//	/**
//	 * @param total_price the total_price to set
//	 */
//	public void setTotal_price(Float total_price) {
//		this.total_price = total_price;
//	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	


	
	
	
	
	
}
