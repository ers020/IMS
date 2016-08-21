package com.revature.beans;

import java.io.Serializable;
import javax.persistence.*;

@SuppressWarnings("serial")
@Embeddable
public class InvoicePK implements Serializable
{

	@ManyToOne
	@JoinColumn(name="ORDER_NUMBER")
	private Order orderId;
	
	@Column(name="LINE_NUMBER")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LINE_ID_GEN")
	@SequenceGenerator(name="LINE_ID_GEN", sequenceName="LINE_ID_SEQ", initialValue=1, allocationSize=1)
	private int lineId;

	public Order getOrderId()
	{
		return orderId;
	}

	public void setOrderId(Order orderId)
	{
		this.orderId = orderId;
	}

	public int getLineId()
	{
		return lineId;
	}

	public void setLineId(int lineId)
	{
		this.lineId = lineId;
	}

	public InvoicePK()
	{
		super();
	}

	public InvoicePK(Order orderId, int lineId)
	{
		super();
		this.orderId = orderId;
		this.lineId = lineId;
	}

	@Override
	public String toString()
	{
		return "InvoicePK [orderId=" + orderId + ", lineId=" + lineId + "]";
	}

	@Override
	public boolean equals(Object arg0)
	{
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

	@Override
	public int hashCode()
	{
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}
