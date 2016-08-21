package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//THIS BEANS HOLDS DESCRIPTIONS OF THE CLIENT

@Entity
@Table(name="BEARDO_CLIENTS_TYPES")
public class ClientType
{
	@Id
	@Column(name="CLIENT_TYPE_ID", nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIENT_TYPE_ID_GEN")
	@SequenceGenerator(name="CLIENT_TYPE_ID_GEN", sequenceName="CLIENT_TYPE_ID_SEQ", initialValue=1, allocationSize=1)
	private int id;

	
	//getting the follow error at compile time
	//Illegal attempt to map a non collection as a @OneToMany, @ManyToMany or @CollectionOfElements: com.revature.beans.ClientType.description
	//tried making it a collection, didn't work.
	//@OneToMany(fetch=FetchType.EAGER, mappedBy="clientType")
	//@JoinColumn(name="CLIENT_TYPE_DESCRIPTION")
	@Column(name="CLIENT_TYPE_DESCRIPTION", nullable=false)
	private String clientType;
	
/*	@OneToMany
	@JoinTable(name="CLIENT_TYPE_SET",
			   joinColumns=@JoinColumn(name="CLIENT_TYPE"),
			   inverseJoinColumns=@JoinColumn(name="CLIENT_TYPE_ID"))
	Set<Order> clientTypes = new HashSet<Order>();*/
	
	//I'M PRETTY SURE WE DON'T NEED THE COMMENTED PART??? ^^^

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
	
	
//	public String getDescription()
//	{
//		return description;
//	}
//
//	public void setDescription(String description)
//	{
//		this.description = description;
//	}
//
//	public ClientType(int id, String description)
//	{
//		super();
//		this.id = id;
//		this.description = description;
//	}


//	public Set<Client> getClientList() {
//		return clientList;
//	}
//
//	public void setClientList(Set<Client> clientList) {
//		this.clientList = clientList;
//	}

	public String getClientList() {
		return clientType;
	}

	public void setClientList(String clientType) {
		this.clientType = clientType;
	}

	public ClientType()
	{
		super();
	}

	public ClientType(int id, String clientType) {
		super();
		this.id = id;
		this.clientType = clientType;
	}

	@Override
	public String toString() {
		return "ClientType [id=" + id + ", clientList=" + clientType + "]";
	}
	
	
//	@Override
//	public String toString()
//	{
//		return "ClientType [id=" + id + ", description=" + description + "]";
//	}
}
