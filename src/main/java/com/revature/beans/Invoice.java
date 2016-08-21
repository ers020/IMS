package com.revature.beans;

import javax.persistence.*;

//THIS BEAN INITIALIZES NEW PRODUCT ORDERS PER THE PO_LINE TABLE
//NOT TO BE CONFUSED WITH THE PURCHASE ORDER TABLE

@Entity
@Table(name="BEARDO_ORDER_INVOICE")  //FORMERLY KNOWN AS "IMS_PO_LINE"
public class Invoice 
{
	/*@Id
	@Column(name="ORDER_NUMBER")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INVOICE_ID_GEN")
	private int orderId;
									//I'M NOT SURE IF WE STILL NEED THIS, COMPOSITE KEYS ARE CONFUSING
	@Column(name="INVOICE_NUMBER")
	private int invoiceId;*/
	
	@EmbeddedId
	private InvoicePK invoiceCK;
	
	@Column(name="UNIT_PRICE", nullable=false)
	private double price;
	
	@Column(name="QUANTITY_ORDERED", nullable=false)
	private int quantity;
	 
    @ManyToOne
    @JoinColumn(name="PRODUCT_UPC", nullable=false)
	private Product prodId;  //PRODUCT ID
	
	/*@ManyToOne
	private Set<Product> orderedProduct = new HashSet<Product>();
	
	@ManyToOne
	private Set<Order> clientOrder = new HashSet<Order>();*/

	public InvoicePK getInvoiceCK()
	{
		return invoiceCK;
	}

	public void setInvoiceCK(InvoicePK invoiceCK)
	{
		this.invoiceCK = invoiceCK;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public Product getProdId()
	{
		return prodId;
	}

	public void setProdId(Product prodId)
	{
		this.prodId = prodId;
	}

/*	public Set<Product> getOrderedProduct()
	{
		return orderedProduct;
	}

	public void setOrderedProduct(Set<Product> orderedProduct)
	{
		this.orderedProduct = orderedProduct;
	}

	public Set<Order> getClientOrder()
	{
		return clientOrder;
	}

	public void setClientOrder(Set<Order> clientOrder)
	{
		this.clientOrder = clientOrder;
	}*/

	public Invoice(InvoicePK invoiceCK, double price, int quantity, Product prodId)
	{
		super();
		this.invoiceCK = invoiceCK;
		this.price = price;
		this.quantity = quantity;
		this.prodId = prodId;
	
	}

	public Invoice()
	{
		super();
	}

	@Override
	public String toString()
	{
		return "Invoice [invoiceCK=" + invoiceCK + ", price=" + price + ", quantity=" + quantity + ", prodId=" + prodId
				+ "]";
	}
}
