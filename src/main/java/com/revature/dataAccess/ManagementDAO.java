package com.revature.dataAccess;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		
	}
	
	//Main insert Object method
	//made this more abstract
	///EPIC!!!!!///
	public void insertObject(Object obj)
	{
		Object mergedObj = session.merge(obj); 
		session.saveOrUpdate(mergedObj);

	}
	
	//////////////////////////// BEGIN CLIENT SECTION /////////////////////////////
		
	public void deleteClient(Client obj)
	{
		session.delete(obj);  
	}
	
	public List<Client> getAllClients()
	{
		
		String hql = "FROM Client";
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Client> results = query.list();
		
		return results;
	}
	
	public Client getClient(String name)
	{
		Client results = null;
		
		Criteria criteria = session.createCriteria(Client.class).add(Restrictions.eq("name", name));

		@SuppressWarnings("unchecked")
		List<Client> clientInfo = criteria.list();
		
		results = clientInfo.get(0);	
		return results;
	}
	
	public Client getClient(int id)
	{
		Client results = null;
		
		Criteria criteria = session.createCriteria(Client.class).add(Restrictions.eq("id", id));

		@SuppressWarnings("unchecked")
		List<Client> clientInfo = criteria.list();
		
		results = clientInfo.get(0);	
		return results;
	}
	
////////////////////////////////// END CLIENT SECTION //////////////////////////////////
	
///////////////////////////////// GENERATE REPORTS ////////////////////////////////////////
	
	///////////////// BEGIN INVOICE SECTION //////////////////////////
	
	// Could eventually have it take a Date figure, and pull only those
	// before or after a certain Date.
	// Something fun for later.
	public List<Invoice> getInvoices()
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
		String hql ="FROM Order WHERE CLIENT_ID =:param";
		
		Query query = session.createQuery(hql);
		query.setParameter("param", clientName);
		
		@SuppressWarnings("unchecked")
		List<Invoice> clientInvoices = query.list();
		
		return clientInvoices;
	}

	public void deleteInvoice(Invoice obj)
	{
		session.delete(obj);  
		
	}

	///////////////// END INVOICE SECTION //////////////////////////
	
	///////////////////////////////// END GENERATE REPORTS ////////////////////////////////////////
	


	/////////////////////// BEGIN INCOMING AND OUTGOING SECTION ////////////////////////
	
	//Get client type
	public List<ClientType> getTypes() 
	{
		String clientTypeQuery = "FROM ClientType";
		Query query = session.createQuery(clientTypeQuery);
		
		@SuppressWarnings("unchecked")
		List<ClientType> type = query.list();
		return type;
	}
	
	//Get clients
	public List<Client> getClients(int clientTypeId)
	{
		String clientType = "FROM Client WHERE CLIENT_TYPE = :param";
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
		
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Product> products = query.list();
		//System.out.println(products);
		return products;
	}

	//Update stocks
	public void changeStock(int amount, int productId) 
	{
		String productQuery = "UPDATE BEARDO_PRODUCTS set quantity =:quantity "+
				"WHERE id =:id";
		Query query = session.createQuery(productQuery);
		query.setParameter("quantity", amount);
		query.setParameter("id", productId);
		query.executeUpdate();
		return;
	}
	
	//Get current order after creation --> needed because the ID changes AFTER going into the database, but before being
	//stored into the DB
	public Order getCurrentOrder(Date orderDate) 
	{
		String hql = "FROM Order WHERE date =:date";
		Query query = session.createQuery(hql);
		query.setParameter("date", orderDate);
		
		Order order = (Order) query.uniqueResult();

		return order;
	}
	///////////////////// END INCOMING AND OUTGOING SECTION //////////////////////
	
	///////////////////////// ADD NEW ITEMS/PRODUCTS ///////////////////////////////
	
	public Set<CategoryDescription> getCatChoice(int catDescId) 
	{
		String hql = "FROM CategoryDescription WHERE id =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", catDescId);

		@SuppressWarnings("unchecked")
		Set<CategoryDescription> catDesc = new HashSet<CategoryDescription>(query.list());
		
		return catDesc;
	}

	public Product getNewProduct(String newItemName) 
	{
		String hql = "FROM Product WHERE name =:name";
		Query query = session.createQuery(hql);
		query.setParameter("name", newItemName);
		Product product = (Product) query.uniqueResult();
		return product;
	}

	///////////////////////// END ADD NEW ITEMS/PRODUCTS ///////////////////////////////
	
	//////////////////////// BEGIN DELETE PRODUCT //////////////////////////////////////
	
	public void deleteProduct(Product obj) 
	{
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
	
	
	public State getState(int id) 
	{
		String theState = "FROM State WHERE id =:param";
		Query query = session.createQuery(theState);
		query.setParameter("param", id);
		State state = (State) query.uniqueResult();
		return state;
	}

	public Address getAddress(State state) 
	{
		String addy = "FROM Address WHERE STATE_ID =:state";
		Query query = session.createQuery(addy);
		query.setParameter("state", state.getId());
		
		Address address = (Address) query.uniqueResult();
		
		return address;
	}

	/// CHANGE THIS TO TAKE CLIENT_TYPE_ID ///
	public ClientType getClientType(int clientTypeId) {
		String hql = "FROM ClientType WHERE id =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", clientTypeId);
		
		ClientType clientType = (ClientType) query.uniqueResult();
		return clientType;
	}

	
	public List<State> getAllStates() {
		
		String hql = "FROM State";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<State> states = query.list();
		return states;
	}

	public List<CategoryDescription> getAllCatDesc() {
		
		String hql = "FROM CategoryDescription";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<CategoryDescription> catDesc = query.list();
		return catDesc;
	}

	public Set<Product> getProductByName(String name) {
		String hql ="FROM Product WHERE name =:name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		Set<Product> madeProduct = new HashSet<Product>();
		madeProduct.add((Product) query.uniqueResult());
		return madeProduct;
	}

	public CategoryDescription getCatChoiceById(int catDescId) {
		String hql ="FROM CategoryDescription WHERE id =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", catDescId);
		CategoryDescription cd = (CategoryDescription) query.uniqueResult();
		return cd;
	}



	

	///////////////////////// END ADD CLIENT SECTION ///////////////////////////////////////


	

	

}