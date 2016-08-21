package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//THIS BEAN HOUSES STATE ABBREVIATIONS, NAME, AND IDs

@Entity
@Table(name="BEARDO_STATES")
public class State 
{
	@Id
	@Column(name="STATE_ID", nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STATE_ID_GEN")
	@SequenceGenerator(name="STATE_ID_GEN", sequenceName="STATE_ID_SEQ", initialValue=1, allocationSize=1)
	private int id;
	
	@Column(name="STATE_NAME", nullable=false)
	private String name;
	
	@Column(name="STATE_INITIALS", nullable=false)
	private String initials;
	
	//do this at lunch!
//	@OneToMany(mappedBy ="stateId")
//	private Set<Address> stateAddresses = new HashSet<Address>();  //I'M FAIRLY CERTAIN ALL THESE XtoX ANNOTATIONS NEED SOMETING MORE

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

	public String getInitials()
	{
		return initials;
	}

	public void setInitials(String initials)
	{
		this.initials = initials;
	}

//	public Set<Address> getStateAddresses()
//	{
//		return stateAddresses;
//	}
//
//	public void setStateAddresses(Set<Address> stateAddresses)
//	{
//		this.stateAddresses = stateAddresses;
//	}

//	public State(int id, String name, String initials, Set<Address> stateAddresses)
//	{
//		super();
//		log.info("Getting the Id, Name, Initials, and stateAddresses");
//		this.id = id;
//		this.name = name;
//		this.initials = initials;
//	//	this.stateAddresses = stateAddresses;
//	}

	public State()
	{
		super();
	}

	public State(int id, String name, String initials) {
		super();
		this.id = id;
		this.name = name;
		this.initials = initials;
	}

	@Override
	public String toString()
	{
		return "State [id=" + id + ", name=" + name + ", initials=" + initials + "]";
	}
}
