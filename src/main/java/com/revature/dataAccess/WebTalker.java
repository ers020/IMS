package com.revature.dataAccess;

import java.util.Set;

import com.revature.beans.Address;
import com.revature.beans.Client;
import com.revature.beans.ClientType;
import com.revature.beans.Order;
import com.revature.beans.State;

public class WebTalker
{
	public void updateClient(int id, String name, String email, String pocName, String phone, String fax, Address address, ClientType type,
			Set<Order> clientOrders)
	{
		/*
		 *   >>>ALL OF THESE VALUES WILL BE SENT IN AN AJAX REQUEST<<<
		 *       >>>	NULL VALUES SHOULD NOT AFFECT UPDATE	<<<
		 */
		Client toUpdate = new Client(id, name, email, pocName, phone, fax, address, type, clientOrders);
		
		new ManagementDAO().insertObject(toUpdate);
	}
	
	public void updaateAddress(int id, String line1, String line2, String city, State state, String zip)
	{
		Address toUpdate = new Address(id, line1, line2, city, state, zip);
		
		new ManagementDAO().insertObject(toUpdate);
	}
}