package by.training.nc.dev3.entities;

import by.training.nc.dev3.dao.interfaces.Identified;

public class MenuEntity implements Identified<Integer> {
	
	
	private int id;
	private String menu_name;
	
	public MenuEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuEntity(int id, String menu_name) {
		super();
		this.id = id;
		this.menu_name = menu_name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "MenuEntity [id=" + id + ", menu_name=" + menu_name + "]";
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	
	
	
}
