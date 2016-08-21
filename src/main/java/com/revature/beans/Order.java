package com.revature.beans;

import java.util.Date;
import javax.persistence.*;

//THIS BEAN INITIALIZES NEW ORDERS, PER PURCHASE_ORDER TABLE
//NOT TO BE CONFUSED WITH INVOICES NOR THE PO_LINE TABLE

@Entity
@Table(name="BEARDO_ORDER_LINES")
public class Order
{
	@Id
	@Column(name="ORDER_NUMBER", nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDER_ID_GEN")
	@SequenceGenerator(name="ORDER_ID_GEN", sequenceName="ORDER_ID_SEQ", initialValue=1, allocationSize=1)
	private int id;
	
	@Column(nullable = false)
	private double subtotal;
	
	@Column(name="PURCHASE_DATE", nullable=false)
	private Date date; 
	
	@Column
	private double taxes;
	
	@Column(name="ORDER_TOTAL", nullable=false)
	private double total;
	
	@ManyToOne//>>REALLY NOT SURE ABOUT THIS ONE...ORDERS SHOULD BE 1-1 FOR CLIENTS, RIGHT???<<
	@JoinColumn(name="CLIENT_ID", nullable=false)
	private Client client;
	
//	@ManyToOne
//	@JoinColumn(name="CLIENT_ORDERS")//fix this one
//	private Client client;   //>>DO WE REALLY NEED THIS SET [AND] THE SET IN CLIENT???<<

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public double getSubtotal()
	{
		return subtotal;
	}

	public void setSubtotal(double subtotal)
	{
		this.subtotal = subtotal;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public double getTaxes()
	{
		return taxes;
	}

	public void setTaxes(double taxes)
	{
		this.taxes = taxes;
	}

	public double getTotal()
	{
		return total;
	}

	public void setTotal(double total)
	{
		this.total = total;
	}

	public Client getClient()
	{
		return client;
	}

	public void setClient(Client client)
	{
		this.client = client;
	}

//	public Set<Client> getClientOrders()
//	{
//		return clientOrders;
//	}
//
//	public void setClientOrders(Set<Client> clientOrders)
//	{
//		this.clientOrders = clientOrders;
//	}
//, Set<Client> clientOrders

	public Order(int id, double subtotal, Date date, double taxes, double total, Client client)
	{
		super();
		this.id = id;
		this.subtotal = subtotal;
		this.date = date;
		this.taxes = taxes;
		this.total = total;
		this.client = client;
		//this.clientOrders = clientOrders;
	}

	public Order()
	{
		super();
	}

	public Order(double subtotal, Date date, double taxes, double total, Client client) {
		super();
		this.subtotal = subtotal;
		this.date = date;
		this.taxes = taxes;
		this.total = total;
		this.client = client;
	}

	@Override
	public String toString()
	{
		return "Order [id=" + id + ", subtotal=" + subtotal + ", date=" + date + ", taxes=" + taxes + ", total=" + total
				+ ", clientId=" + client + "]";
	}	
}
