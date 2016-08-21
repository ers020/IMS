package com.revature.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//THIS BEAN MATCHES CATEGORY IDs TO THEIR RESPECTIVE DESCRIPTIONS
//NOT TO BE CONFUSED WITH THE PRODUCT_CATEGORIES TABLE
//NOT SURE WHO CHOSE THESE NAMES...

@Entity
@Table(name="BEARDO_CATEGORY_DESCRIPTIONS")
public class CategoryDescription
{
	@Id
	@Column(name="CATEGORY_ID", nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATEGORY_ID_GEN")
	@SequenceGenerator(name="CATEGORY_ID_GEN", sequenceName="CATEGORY_ID_SEQ", initialValue=1, allocationSize=1)
	private int id;  //CATEGORY ID
	
	@Column(name="CATEGORY_DESCRIPTION", nullable=false)
	private String description;
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="categoryDesc")//POSSIBLY NEED SOMETHING MORE HERE
	private Set<Product> productCats;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Set<Product> getproductCats()
	{
		return productCats;
	}

	public void setproductCats(Set<Product> productCats)
	{
		this.productCats = productCats;
	}

	public CategoryDescription(int id, String description, Set<Product> productCats)
	{
		super();
		this.id = id;
		this.description = description;
		this.productCats = productCats;
	}

	public CategoryDescription()
	{
		super();
	}

	@Override
	public String toString()
	{
		return "CategoryDescription [id=" + id + ", description=" + description + "]";
	}
}
