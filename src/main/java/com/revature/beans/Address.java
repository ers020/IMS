package com.revature.beans;

import javax.persistence.*;

//THIS CREATES A NEW ADDRESS OBJECT, PER IMS_ADDRESS

@Entity
@Table(name="BEARDO_ADDRESSES")
@org.hibernate.annotations.Entity(dynamicUpdate = true)  //NO, YOU CANNOT SIMPLY PUT THIS AFTER @ENTITY....I'M NOT SURE WHY
public class Address
{
	@Id
	@Column(name="IMS_ADDRESS_ID", nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADDRESS_ID_GEN")
	@SequenceGenerator(name="ADDRESS_ID_GEN", sequenceName="ADDRESS_ID_SEQ", initialValue=1, allocationSize=1)
	private int id;
	
	@Column(name="STREET_ADDRESS_1", nullable=false)
	private String line1;
	
	@Column(name="STREET_ADDRESS_2", nullable=false)  //THIS MUST BE TRUE, NOT ALL ADDRESSES HAVE 2 LINES
	private String line2;
	
	@Column(name="ADDRESS_CITY", nullable=false)
	private String city;
	
	@Column(name="ADDRESS_ZIP", nullable=false)
	private String zip;
	
	@ManyToOne  //MANY ADDRESS, ONE STATE...
	@JoinColumn(name="STATE_ID", nullable=false)  //NEED TO VERIFY THAT THIS IS CORRECT!!!!!
	private State state;
	


	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getLine1()
	{
		return line1;
	}

	public void setLine1(String line1)
	{
		this.line1 = line1;
	}

	public String getLine2()
	{
		return line2;
	}

	public void setLine2(String line2)
	{
		this.line2 = line2;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public State getState()
	{
		return state;
	}

	public void setState(State state)
	{
		this.state = state;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}
	
	public Address()
	{
		super();
	}

	public Address(String line1, String line2, String city, State state, String zip){
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public Address(int id, String line1, String line2, String city, State state, String zip)
	{
		super();
		this.id = id;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	

	@Override
	public String toString()
	{
		return "Address [id=" + id + ", line1=" + line1 + ", line2=" + line2 + ", city=" + city + ", stateId=" + state
				+ ", zip=" + zip + "]";
	}
}
