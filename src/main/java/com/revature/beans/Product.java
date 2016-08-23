package com.revature.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.type.BlobType;

//THIS BEAN INITIALIZES NEW PRODUCTS PER PRODUCT TABLE

@Entity
@Table(name="BEARDO_PRODUCTS")
@org.hibernate.annotations.Entity(dynamicUpdate = true)  //NO, YOU CANNOT SIMPLY PUT THIS AFTER @ENTITY....I'M NOT SURE WHY
public class Product 
{
	@Id
	@Column(name="PRODUCT_UPC")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROD_UPC_GEN")
	@SequenceGenerator(name="PROD_UPC_GEN", sequenceName="PROD_UPC_SEQ", initialValue=1, allocationSize=1)
	private int id;
	
	@Column(name="PRODUCT_NAME", nullable=false)
	private String name;
	
	@Column(name="PRODUCT_SHORT_NAME", nullable=false)
	private String sName;
	
	@Column(name="PRODUCT_DESCRIPTION", nullable=false)
	private String description;
	
	@Column(name="PRODUCT_COST", nullable=false)
	private double cost;
	
	@Column(name="PRODUCT_PACKAGE_SIZE", nullable=false)
	private String size;
	
	@Column(name="PRODUCT_IN_STOCK", nullable=false)
	private int stock;
	
	@Column(name="PRODUCT_REORDER_QUANTITY", nullable=false)  //THE AUTOMATIC AMOUNT ASSIGNED TO NEW ORDERS OF THIS PRODUCT
	private int quantity;
	
	@Column(name="PRODUCT_RETAIL_PRICE", nullable=false)
	private double msrp;
	
	@Column(name="PRODUCT_WEIGHT")
	private double weight;
	
	@Column(name="PRODUCT_IMAGE")
	private BlobType image;  //OPTIONAL, ALSO NOT SURE IF BLOBTYPE
	
	@ManyToMany(cascade= CascadeType.ALL)
	@JoinTable(name="PRODUCT_CATEGORIES", 
			joinColumns=@JoinColumn(name="PRODUCT_UPC"),
			inverseJoinColumns=@JoinColumn(name="CATEGORY_ID"))
	private Set<CategoryDescription> categoryDesc;

	//////////////////////////////////////////////////////
	private String strCost;
	private String strSize;
	private String strStock;
	private String strPreQuantity;
	private String strRetailPrice;
	private String catDescId[];
	
	
	public String getStrCost() {
		return strCost;
	}

	public void setStrCost(String strCost) {
		this.strCost = strCost;
	}

	public String getStrSize() {
		return strSize;
	}

	public void setStrSize(String strSize) {
		this.strSize = strSize;
	}

	public String getStrStock() {
		return strStock;
	}

	public void setStrStock(String strStock) {
		this.strStock = strStock;
	}

	public String getStrPreQuantity() {
		return strPreQuantity;
	}

	public void setStrPreQuantity(String strPreQuantity) {
		this.strPreQuantity = strPreQuantity;
	}

	public String getStrRetailPrice() {
		return strRetailPrice;
	}

	public void setStrRetailPrice(String strRetailPrice) {
		this.strRetailPrice = strRetailPrice;
	}

	public String[] getCatDescId() {
		return catDescId;
	}

	public void setCatDescId(String catDescId[]) {
		this.catDescId = catDescId;
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

	public String getsName()
	{
		return sName;
	}

	public void setsName(String sName)
	{
		this.sName = sName;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public double getCost()
	{
		return cost;
	}

	public void setCost(double cost)
	{
		this.cost = cost;
	}

	public String getSize()
	{
		return size;
	}

	public void setSize(String size)
	{
		this.size = size;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public double getMsrp()
	{
		return msrp;
	}

	public void setMsrp(double msrp)
	{
		this.msrp = msrp;
	}

	public double getWeight()
	{
		return weight;
	}

	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	public BlobType getImage()
	{
		return image;
	}

	public void setImage(BlobType image)
	{
		this.image = image;
	}

//	public Set<ProductCategories> getProductCategories()
//	{
//		return productCategories;
//	}

//	public void setProductCategories(Set<ProductCategories> productCategories)
//	{
//		this.productCategories = productCategories;
//	}

	public Set<CategoryDescription> getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(Set<CategoryDescription> categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Product()
	{
		super();
	}
	
	public Product(String name, String sName, String description, String strCost, String strSize, String strStock,
			String strPreQuantity, String strRetailPrice, String[] catDescId) {
		super();
		this.name = name;
		this.sName = sName;
		this.description = description;
		this.strCost = strCost;
		this.strSize = strSize;
		this.strStock = strStock;
		this.strPreQuantity = strPreQuantity;
		this.strRetailPrice = strRetailPrice;
		this.catDescId = catDescId;
	}

	public Product(int id, String name, String sName, String description, double cost, String size, int stock,
			int quantity, double msrp, Set<CategoryDescription> categoryDesc) {
		super();
		this.id = id;
		this.name = name;
		this.sName = sName;
		this.description = description;
		this.cost = cost;
		this.size = size;
		this.stock = stock;
		this.quantity = quantity;
		this.msrp = msrp;
		this.categoryDesc = categoryDesc;
	}
	
	public Product(String name, String sName, String description, double cost, String size, int stock,
			int quantity, double msrp, Set<CategoryDescription> categoryDesc) {
		super();
		this.name = name;
		this.sName = sName;
		this.description = description;
		this.cost = cost;
		this.size = size;
		this.stock = stock;
		this.quantity = quantity;
		this.msrp = msrp;
		this.categoryDesc = categoryDesc;
	}

	public Product(int id, String name, String sName, String description, double cost, String size, int stock,
			int quantity, double msrp, double weight, BlobType image, Set<CategoryDescription> categoryDesc) {
		super();
		this.id = id;
		this.name = name;
		this.sName = sName;
		this.description = description;
		this.cost = cost;
		this.size = size;
		this.stock = stock;
		this.quantity = quantity;
		this.msrp = msrp;
		this.weight = weight;
		this.image = image;
		this.categoryDesc = categoryDesc;
	}

	
	
	

	@Override
	public String toString()
	{
		return "Product [id=" + id + ", name=" + name + ", sName=" + sName + ", description=" + description + ", cost="
				+ cost + ", size=" + size + ", quantity=" + quantity + ", msrp=" + msrp + ", weight=" + weight
				+ ", image=" + image + "]";
	}


}





















