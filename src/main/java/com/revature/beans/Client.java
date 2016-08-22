package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//THIS BEAN INITIALIZES NEW CLIENTS, PER CLIENT TABLE

@Entity
@Table(name="BEARDO_CLIENTS")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Client 
{
	@Id
	@Column(name="CLIENT_ID", nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIENT_ID_GEN")
	@SequenceGenerator(name="CLIENT_ID_GEN", sequenceName="CLIENT_ID_SEQ", initialValue=2, allocationSize=1)
	
	private int id;  //PK, FK FOR ORDER AND CLIENT_TYPE

	@Column(name="CLIENT_NAME", nullable=false)
	private String name;

	@Column(name="CLIENT_EMAIL", nullable=false)
	private String email;

	@Column(name="CLIENT_POINT_OF_CONTACT_NAME", nullable=false)
	private String pocName;  //POINT_OF_CONTACT_NAME

	@Column(name="CLIENT_PHONE", nullable=false)
	private String phone;

	@Column(name="CLIENT_FAX", nullable=false)
	private String fax;
 
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="CLIENT_ADDRESS", nullable=false, unique=true)
	private Address address;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CLIENT_TYPE", nullable=false)
	private ClientType clientType;  //OneToMany for Clients to Types

	@OneToMany(mappedBy="client", fetch=FetchType.EAGER)
	Set<Order> clientOrders = new HashSet<Order>();

	//////////////////////////////////////////////////////////
	private String addLine1;
	private String addLine2;
	private String city;
	private String stateId;
	private String zip;
	private String clientTypeId;
	
	
	public String getAddLine1() {
		return addLine1;
	}

	public void setAddLine1(String addLine1) {
		this.addLine1 = addLine1;
	}

	public String getAddLine2() {
		return addLine2;
	}

	public void setAddLine2(String addLine2) {
		this.addLine2 = addLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getClientTypeId() {
		return clientTypeId;
	}

	public void setClientTypeId(String clientTypeId) {
		this.clientTypeId = clientTypeId;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPocName()
	{
		return pocName;
	}

	public void setPocName(String pocName)
	{
		this.pocName = pocName;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getFax()
	{
		return fax;
	}

	public void setFax(String fax)
	{
		this.fax = fax;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	
	
//	public int getType()
//	{
//		return type;
//	}
//
//	public void setType(int type)
//	{
//		this.type = type;
//	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public Set<Order> getClientOrders()
	{
		return clientOrders;
	}

	public void setClientOrders(Set<Order> clientOrders)
	{
		this.clientOrders = clientOrders;
	}

//	
	public Client()
	{
		super();
	}
	
	public Client(String name, String email, String pocName, String phone, String fax, Address address,
			ClientType clientType) {
			super();
			this.name = name;
			this.email = email;
			this.pocName = pocName;
			this.phone = phone;
			this.fax = fax;
			this.address = address;
			this.clientType = clientType;
		}
	
	
	public Client(int id, String name, String email, String pocName, String phone, String fax, Address address,
		ClientType clientType) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pocName = pocName;
		this.phone = phone;
		this.fax = fax;
		this.address = address;
		this.clientType = clientType;
	}

	public Client(int id, String name, String email, String pocName, String phone, String fax, Address address, ClientType type,
			Set<Order> clientOrders)
	{
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pocName = pocName;
		this.phone = phone;
		this.fax = fax;
		this.address = address;
		this.clientType = type;
		this.clientOrders = clientOrders;
	}

	

	public Client(String name, String email, String pocName, String phone, String fax,
			 String addLine1, String addLine2, String city, String stateId,
			String zip, String clientTypeId) {
		super();
		this.name = name;
		this.email = email;
		this.pocName = pocName;
		this.phone = phone;
		this.fax = fax;
		this.addLine1 = addLine1;
		this.addLine2 = addLine2;
		this.city = city;
		this.stateId = stateId;
		this.zip = zip;
		this.clientTypeId = clientTypeId;
	}

	@Override
	public String toString()
	{
		return "Client [id=" + id + ", name=" + name + ", email=" + email + ", pocName=" + pocName + ", phone=" + phone
				+ ", fax=" + fax + ", address=" + address + ", type=" + clientType + "]";
	}
}