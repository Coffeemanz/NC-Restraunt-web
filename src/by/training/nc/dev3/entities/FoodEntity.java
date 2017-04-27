package by.training.nc.dev3.entities;

import by.training.nc.dev3.dao.interfaces.Identified;

public class FoodEntity implements Identified<Integer> {

	private int id;
	private String food_name;
	private String food_desc;
	private Float food_price;
	private int id_menu;
	
	
	public FoodEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public FoodEntity(int id, String food_name, String food_desc, Float food_price, int id_menu) {
		super();
		this.id = id;
		this.food_name = food_name;
		this.food_desc = food_desc;
		this.food_price = food_price;
		this.id_menu = id_menu;
	}


	public Integer getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFood_name() {
		return food_name;
	}


	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}


	public String getFood_desc() {
		return food_desc;
	}


	public void setFood_desc(String food_desc) {
		this.food_desc = food_desc;
	}


	public Float getFood_price() {
		return food_price;
	}


	public void setFood_price(Float food_price) {
		this.food_price = food_price;
	}


	public int getId_menu() {
		return id_menu;
	}


	public void setId_menu(int id_menu) {
		this.id_menu = id_menu;
	}
	
	@Override
	public String toString() {
		return "FoodEntity [id=" + id + ", food_name=" + food_name + ", food_desc=" + food_desc + ", food_price="
				+ food_price + ", id_menu=" + id_menu + "]";
	}

}
