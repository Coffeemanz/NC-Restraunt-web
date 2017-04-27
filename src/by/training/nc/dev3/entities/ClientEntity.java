package by.training.nc.dev3.entities;

import by.training.nc.dev3.dao.interfaces.Identified;

public class ClientEntity implements Identified<Integer> {
	
	private int id;
	private String client_name;
	private String client_email;
	private String client_password;
	private Float client_cash;
	private Boolean paid;
	
	public ClientEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public ClientEntity(Float client_cash) {
		super();
		this.client_cash = client_cash;
	}
	
	public ClientEntity(int id, Float client_cash) {
		super();
		this.id = id;
		this.client_cash = client_cash;
	}

	public ClientEntity(Float client_cash, Boolean paid) {
		super();
		this.client_cash = client_cash;
		this.paid = paid;
	}
	
	public ClientEntity(String client_name, String client_email, String client_password, Float client_cash) {
		super();
		this.client_name = client_name;
		this.client_email = client_email;
		this.client_password = client_password;
		this.client_cash = client_cash;
	}
	
	public String getClient_name() {
		return client_name;
	}



	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}



	public String getClient_email() {
		return client_email;
	}



	public void setClient_email(String client_email) {
		this.client_email = client_email;
	}



	public String getClient_password() {
		return client_password;
	}



	public void setClient_password(String client_password) {
		this.client_password = client_password;
	}



	public ClientEntity(String client_name, String client_email, String client_password, Float client_cash,
			Boolean paid) {
		super();
		this.client_name = client_name;
		this.client_email = client_email;
		this.client_password = client_password;
		this.client_cash = client_cash;
		this.paid = paid;
	}

	/**
	 * @return the client_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param client_id the client_id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the client_cash
	 */
	public Float getClient_cash() {
		return client_cash;
	}

	/**
	 * @param client_cash the client_cash to set
	 */
	public void setClient_cash(Float client_cash) {
		this.client_cash = client_cash;
	}

	/**
	 * @return the paid
	 */
	public Boolean getPaid() {
		return paid;
	}

	/**
	 * @param paid the paid to set
	 */
	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client_cash == null) ? 0 : client_cash.hashCode());
		result = prime * result + id;
		result = prime * result + ((paid == null) ? 0 : paid.hashCode());
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
		if (!(obj instanceof ClientEntity))
			return false;
		ClientEntity other = (ClientEntity) obj;
		if (client_cash == null) {
			if (other.client_cash != null)
				return false;
		} else if (!client_cash.equals(other.client_cash))
			return false;
		if (id != other.id)
			return false;
		if (paid == null) {
			if (other.paid != null)
				return false;
		} else if (!paid.equals(other.paid))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClientEntity [client_id=" + id + ", client_cash=" + client_cash + ", paid=" + paid + "]";
	}


	
	

}
