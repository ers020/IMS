package com.revature.dataAccess;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.revature.beans.Address;
import com.revature.beans.CategoryDescription;
import com.revature.beans.Client;
import com.revature.beans.ClientType;
import com.revature.beans.Invoice;
import com.revature.beans.Order;
import com.revature.beans.Product;
import com.revature.beans.State;


public class ManagementDAO 
{
	private Session session;
	
	
	
	public ManagementDAO(Session session) 
	{
		//log.info("Create Session");
		this.session = session;
	}

	public ManagementDAO()
	{
		super();
		//log.info("DAO Constructor instantiated");
	}

	//made this more abstract
	///EPIC!!!!!///
	public void insertObject(Object obj)
	{
		//log.info("Abstract Insert Object: Also does Update");
		//THIS SHOULD UPDATE [AND] INSERT
		Object mergedObj = session.merge(obj); 
		session.saveOrUpdate(mergedObj);
//		discrete process
//		session.save(obj);
	}
	
	
	
	//////////////////////////// BEGIN CLIENT SECTION /////////////////////////////
	
	//IDEA!!! WHEN SHOWING CLIENT LIST, DON'T SHOW IF INCLUDE [TERMINATED], 
	//BUT VIEWABLE IN HISTORY
	//DEF SHOULD NOT BE HARDCODED
	//NEED TO FIGURE OUT HOW TO PROGRAMATICALLY SET TYPE
	public void deleteClient(Client obj)
	{
		/*log.info("Create 'Terminated' Client");
		obj.setName(obj.getName() + " [TERMINATED]");	
														
		obj.setClientType(new ClientType(3, obj.getName()));  
															 
		Object mergedObj = session.merge(obj); 
		session.saveOrUpdate(mergedObj);
		log.info("'Terminated' Client saved into session");
		return;*/
		
		session.delete(obj);  //BTW.  I *THINK* THE OBJ MUST BE PERSISTED
		
	}
	
	public List<Client> getAllClients()
	{
		//log.info("Query DB for All Clients");
		String hql = "FROM Client";
		
		Query query = session.createQuery(hql);
		//something is wrong with this code
		//Criteria criteria = session.createCriteria(Client.class).createAlias("Client.id", "id")
			//				.addOrder(org.hibernate.criterion.Order.asc("id"));
							//Hopefully this works
							//AddOrder might not work
		
		//@SuppressWarnings("unchecked")
		//List<Client> results = criteria.list();
		
	//	System.out.println("Criteria " + criteria.toString());
		
		@SuppressWarnings("unchecked")
		List<Client> results = query.list();
		
		//log.info("Return All Clients results");
		return results;
	}
	
	public Client getClient(String name)
	{
		Client results = null;
		
		Criteria criteria = session.createCriteria(Client.class).add(Restrictions.eq("name", name));

		@SuppressWarnings("unchecked")
		List<Client> clientInfo = criteria.list();

		/*String hql = "FROM Client";
		
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Client> clientInfo = query.list();*/
		
		results = clientInfo.get(0);
		
		//log.info("Return All Clients results");		
		
		return results;
	}
	
/*	public void updateClient(Client obj)
	{
		Object mergedObj = session.merge(obj); 	//   >>>  POSSIBLY UNNECESSARY  <<<
		session.saveOrUpdate(mergedObj);
	}*/
	
////////////////////////////////// END CLIENT SECTION //////////////////////////////////
	
///////////////////////////////// GENERATE REPORTS ////////////////////////////////////////
	
	///////////////// BEGIN INVOICE SECTION //////////////////////////
	
	// Could eventually have it take a Date figure, and pull only those
	// before or after a certain Date.
	// Something fun for later.
	public List<Invoice> getInvoices(/* possibly a parameter */)
	{
		//log.info("Query DB for Invoices");
		String hql = "FROM INVOICES";
		
		Query query =  session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Invoice> results = query.list();
		//log.info("Return All Invoices");
		return results;
	}
	
	public List<Invoice> getClientInvoices(String clientName)
	{
		//log.info("Query DB for Invoices, based on Client Id");
		String hql ="FROM Order WHERE CLIENT_ID =:param";
		
		Query query = session.createQuery(hql);
		query.setParameter("param", clientName);
		
		@SuppressWarnings("unchecked")
		List<Invoice> clientInvoices = query.list();
		
		return clientInvoices;
	}

	public void deleteInvoice(Invoice obj)
	{
		/*log.info("Create 'Terminated' Client");
		obj.setName(obj.getName() + " [TERMINATED]");	
														
		obj.setClientType(new ClientType(3, obj.getName()));  
															 
		Object mergedObj = session.merge(obj); 
		session.saveOrUpdate(mergedObj);
		log.info("'Terminated' Client saved into session");
		return;*/
		
		session.delete(obj);  //BTW.  I *THINK* THE OBJ MUST BE PERSISTED
		
	}
	///////////////// END INVOICE SECTION //////////////////////////
	
	///////////////////////////////// END GENERATE REPORTS ////////////////////////////////////////
	


	/////////////////////// BEGIN INCOMING AND OUTGOING SECTION ////////////////////////
	
	//Get client type
	public List<ClientType> getTypes() 
	{
		//log.info("Query DB for List of Client Type");
		String clientTypeQuery = "FROM com.revature.beans.ClientType";
		Query query = session.createQuery(clientTypeQuery);
		
		@SuppressWarnings("unchecked")
		List<ClientType> type = query.list();
		//log.info("Return result list of Client Type");
		return type;
	}
	
	//Get clients
	public List<Client> getClients(int clientTypeId)
	{
		//log.info("Query DB for List of Clients based on Client Type Id");
		String clientType = "FROM com.revature.beans.Client WHERE CLIENT_TYPE = :param";
		Query query = session.createQuery(clientType);
		query.setInteger("param", clientTypeId);
	
		@SuppressWarnings("unchecked")
		List<Client> clients = query.list();
		
		//log.info("Return result for List of Clients based on Client Type Id");
		return clients;
	}

	//Get products
	public List<Product> getAllProducts()
	{
		//String hql = "FROM BEARDO_PRODUCTS";
		
		//Query query = session.createQuery(hql);
		//log.info("Query DB for List of Products");
		String productQuery = "FROM BEARDO_PRODUCTS";
		Query query = session.createQuery(productQuery);
		
		System.out.println(query.toString());
		@SuppressWarnings("unchecked")
		List<Product> products = query.list();
		System.out.println(products);
		//log.info("Result for List of Products");
		return products;
	}

	//Update stocks
	public void changeStock(int amount, int productId) 
	{
		//log.info("Query DB to change the Stock amount based on Product ID");
		String productQuery = "UPDATE BEARDO_PRODUCTS set quantity =:quantity "+
				"WHERE id =:id";
		Query query = session.createQuery(productQuery);
		query.setParameter("quantity", amount);
		query.setParameter("id", productId);
		query.executeUpdate();
		//log.info("Update executed.");
		return;
	}
	
	//Get current order after creation --> needed because the ID changes AFTER going into the database, but before being
	//stored into the DB
	public Order getCurrentOrder(Date orderDate) 
	{
		
		//log.info("Query DB get newly created Order based on Date");
		String hql = "FROM Order WHERE date =:date";
		Query query = session.createQuery(hql);
		query.setParameter("date", orderDate);
		
		Order order = (Order) query.uniqueResult();
		//log.info("Return result of search for new Order based on Date");
		return order;
	}
	///////////////////// END INCOMING AND OUTGOING SECTION //////////////////////
	
	///////////////////////// ADD NEW ITEMS/PRODUCTS ///////////////////////////////
	
	public List<CategoryDescription> getCatChoice(String categoryChoice) 
	{
		//log.info("Query DB for List of Category Description based on categoryChoice");
		String hql = "FROM CategoryDescription WHERE description =:description";
		Query query = session.createQuery(hql);
		query.setParameter("description", categoryChoice);

		@SuppressWarnings("unchecked")
		List<CategoryDescription> catDesc = query.list();
		//log.info("Return List result of Category Description based on categoryChoice");
		return catDesc;
	}

	public Product getNewProduct(String newItemName) 
	{
		//log.info("Query DB for new product by the name");
		String hql = "FROM Product WHERE name =:name";
		Query query = session.createQuery(hql);
		query.setParameter("name", newItemName);
		Product product = (Product) query.uniqueResult();
		//log.info("Return result of search for new product by the name parameter");
		return product;
	}

	///////////////////////// END ADD NEW ITEMS/PRODUCTS ///////////////////////////////
	
	//////////////////////// BEGIN DELETE PRODUCT //////////////////////////////////////
	
	public void deleteProduct(Product obj) 
	{
		/*log.info("Create 'Deleted' Product");
		obj.setName(obj.getName() +" [REDACTED]");		 
		Object mergedObj = session.merge(obj); 
		session.saveOrUpdate(mergedObj);
		log.info("'Deleted' Product saved into session");*/
		
		session.delete(obj);  //BTW.  I *THINK* THE OBJ MUST BE PERSISTED
	}
	
	public List<Product> getClientProds(int clientId)
	{
		Criteria criteria = session.createCriteria(Invoice.class)
							.createAlias("Invoice.prodId", "prodId")
							.createAlias("Invoice.orderId", "orderId")
							.createAlias("Order.clientId", "clientId")
							.add(Restrictions.idEq(clientId));  //"Basically" query for select all products where owning client restriction
																//"Basically" a quadruple join that may or may not work.  Pls help ;_;
		
		@SuppressWarnings("unchecked")
		List<Product> results = criteria.list();
		
		return results;
	}
	
	//////////////////////// END DELETE PRODUCT ///////////////////////////////////////

	///////////////////////// ADD CLIENT SECTION ///////////////////////////////////////
	
	///These have re-useablility for later///
	public State getState(String stateName) 
	{
		//log.info("Query DB for State object based on state name");
		String theState = "FROM State WHERE name =:param";
		Query query = session.createQuery(theState);
		query.setParameter("param", stateName);
		State state = (State) query.uniqueResult();
		//log.info("Return state object found based off of state name");
		return state;
	}

	public Address getAddress(State state) 
	{
		//log.info("Query DB for Address based on state Id");
		String addy = "FROM Address WHERE STATE_ID =:state";
		Query query = session.createQuery(addy);
		query.setParameter("state", state.getId());
		
		Address address = (Address) query.uniqueResult();
		//log.info("Return Address result based on search by state Id");
		return address;
	}

	/// CHANGE THIS TO TAKE CLIENT_TYPE_ID ///
	public ClientType getClientType(int clientTypeId) {
		//log.info("Query DB for client type based on client type id");
		String hql = "FROM ClientType WHERE id =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", clientTypeId);
		
		ClientType clientType = (ClientType) query.uniqueResult();
		//log.info("Return result for client type based on client type id");
		return clientType;
	}
///////////////////////// END ADD CLIENT SECTION ///////////////////////////////////////

	public Address getAddressById(int id) {
		String hql ="FROM Address WHERE IMS_ADDRESS_ID =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Address address = (Address) query.uniqueResult();
		return address;
	}

	public void deleteAddress(Address address) {
		session.delete(address);
		
	}

	

	

}