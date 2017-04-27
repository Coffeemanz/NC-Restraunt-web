package by.training.nc.dev3.entities;

import by.training.nc.dev3.dao.interfaces.Identified;

public class WaiterEntity implements Identified<Integer> {
	
	private int id;
	private String waiter_name;
	private String waiter_email;
	private String waiter_password;
	
	public WaiterEntity()
	{
		super();
	}
	
	public String getWaiter_email() {
		return waiter_email;
	}

	public void setWaiter_email(String waiter_email) {
		this.waiter_email = waiter_email;
	}

	public String getWaiter_password() {
		return waiter_password;
	}

	public WaiterEntity(int id, String waiter_name, String waiter_email, String waiter_password) {
		super();
		this.id = id;
		this.waiter_name = waiter_name;
		this.waiter_email = waiter_email;
		this.waiter_password = waiter_password;
	}

	public void setWaiter_password(String waiter_password) {
		this.waiter_password = waiter_password;
	}

	public WaiterEntity(int id, String waiter_name) {
		super();
		this.id = id;
		this.waiter_name = waiter_name;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the waiter_name
	 */
	public String getWaiter_name() {
		return waiter_name;
	}

	/**
	 * @param waiter_name the waiter_name to set
	 */
	public void setWaiter_name(String waiter_name) {
		this.waiter_name = waiter_name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((waiter_name == null) ? 0 : waiter_name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof WaiterEntity))
			return false;
		WaiterEntity other = (WaiterEntity) obj;
		if (id != other.id)
			return false;
		if (waiter_name == null) {
			if (other.waiter_name != null)
				return false;
		} else if (!waiter_name.equals(other.waiter_name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WaiterEntity [id=" + id + ", waiter_name=" + waiter_name + ", waiter_email=" + waiter_email
				+ ", waiter_password=" + waiter_password + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	


	
	
	
	

}
