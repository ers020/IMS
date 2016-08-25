package com.revature.dataAccess;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.revature.beans.Address;
import com.revature.beans.CategoryDescription;
import com.revature.beans.Client;
import com.revature.beans.ClientType;
import com.revature.beans.Invoice;
import com.revature.beans.Order;
import com.revature.beans.Product;
import com.revature.beans.State;

public class DataLayerAccess
{
	private Session session;
	private ManagementDAO dao;
	//private static Logger log = Logger.getRootLogger();
	
	//I changed this for testing, we should change it back to how it was.
	//DON'T WORRY, WE WILL.
	
	public DataLayerAccess()
	{
		super();
		
	//	log.info("Get the session");
		
		session = SessionFactoryManager.getInstance().openSession();
		
	//	log.info("Got the session");
		
		dao = new ManagementDAO(session);
		
	}
	//close session
	public void close()
	{
	//	log.info("Closing the session");
		if(session != null)
			session.close();

	//	log.info("Closed the session");
	}
	
	///INSERT STATEMENT///
	
	public void createObject(Object obj)
	{
	//	log.info("Open transaction.");
		Transaction tx = session.beginTransaction();
		
		try
		{
			dao.insertObject(obj); // can call multiple daos or dao methods
			tx.commit(); // commit 
	//		log.info("Transaction completed successfully.");
		}
		
		catch(Throwable t)
		{// or
			tx.rollback();// rollback (depending on result)
	//		log.error("Transaction rolled back.");
		}
	}
	
	/////////////////////CLIENT MANAGEMENT/////////////////////////
	public boolean terminateClient(Client obj){
		
	//	log.info("'Terminating' client");
		Transaction tx = session.beginTransaction();
		
		try{
			
			dao.deleteClient(obj);
			tx.commit();
	//		log.info("Client 'termination' successful");
			return true;
		}catch(Throwable t){
			tx.rollback();
	//		log.error("Client 'termination' failed");
			return false;
		}
	}
	
	public List<Client> getAllClients(){
	//	log.info("Getting All Clients");
		
		Transaction tx = session.beginTransaction();
		
		try{
		
			List<Client> currentClients = dao.getAllClients();
			tx.commit();
			return currentClients;
		
		}catch(Throwable t){
			tx.rollback();
			return null;
		}
		
	}
	
	public List<Client> allClients(){
	//	log.info("Getting All Clients");
		
		Transaction tx = session.beginTransaction();
		
		try{
			List<Client> allClients = dao.getAllClients();
			tx.commit();
			return allClients;
		}catch(Throwable t){
			tx.rollback();
			return null;
		}
	}

///////////////////// END CLIENT MANAGEMENT /////////////////////////
	
	//////////////////// INVOICE SECTION /////////////////////////////
	
	public List<Invoice> getAllInvoices(){
	//	log.info("Getting All Invoices");
		Transaction tx = session.beginTransaction();
		
		try{
			List<Invoice> invoices = dao.getInvoices();
			tx.commit();
			return invoices;
		}catch(Throwable t){
			tx.rollback();
			return null;
		}
			
	}
	
	public Client getClientByName(String parameter) {
		
		Transaction tx = session.beginTransaction();
		
		try{
			Client client = dao.getClient(parameter);
			tx.commit();
			return client;
		} catch(Throwable t)
		{
			tx.rollback();
			return null;
		}
		
	}
	
	public Client getClientById(int parameter)
	{	
		Transaction tx = session.beginTransaction();
		
		try{
			Client client = dao.getClient(parameter);
			tx.commit();
			return client;
		} catch(Throwable t)
		{
			tx.rollback();
			return null;
		}
		
	}
	
	public List<Invoice> getClientInvoices(String clientName){
	//	log.info("Getting Orders based on Client Id");
		
		Transaction tx = session.beginTransaction();
		
		try{
			List<Invoice> orders = dao.getClientInvoices(clientName);
			tx.commit();
	//		log.info("Orders grab successful");
			return orders;
		}catch(Throwable t){
			tx.rollback();
	//		log.error("Orders grab failed");
			return null;
		}
		
	}
	
	/////////////////// END INVOICE SECTION ///////////////////////
	
	////////////////// BEGIN PRODUCT ///////////////////////
	public void deleteProduct(Product product) {
	//	log.info("'Terminating' client");
		Transaction tx = session.beginTransaction();
		
		try{
			
			dao.deleteProduct(product);
			tx.commit();
			System.err.println("success");
	//		log.info("Product 'deletion' successful");
		}catch(Throwable t){
			tx.rollback();
			System.err.println("FAILURE!");
	//		log.error("Product 'deletion' failed");
		}
		
	}
	
	public List<Product> getProdsByClient(int clientId)
	{
		Transaction tx = session.beginTransaction();
		try{
			List<Product> results = dao.getClientProds(clientId);
			tx.commit();
			return results;
		
		}catch(Throwable t){
			tx.rollback();
			return null;
		}
		
	}
	
	///////////////// END PRODUCT //////////////////////////
	
	
////////////////// BEGIN INCOMING AND OUTGOING SECTION //////////////////	
	//in-going and outgoing invoices
	
	//  client type list
	public List<ClientType> clientTypeList(){
	//	log.info("Getting All Client Types");
		Transaction tx = session.beginTransaction();
		try{
			List<ClientType> types = dao.getTypes();
			tx.commit();
		 	return types;
		}catch(Throwable t){
			tx.rollback();
			return null;
		}
	}
	
	//	client list
	public List<Client> clientList(int clientTypeId){
	//	log.info("Getting List of Clients based on Client Type");
		Transaction tx = session.beginTransaction();
		try{
			List<Client> clients = dao.getClients(clientTypeId);
			tx.commit();
		return clients;
		}catch(Throwable t){
			tx.rollback();
			return null;
		}
		
	}
	//drop down of items
	public List<Product> getAllProducts(){
	//	log.info("Getting List of all Products");
		Transaction tx = session.beginTransaction();
		try{
		
			List<Product> products = dao.getAllProducts();
			tx.commit();
			
			return products;
		}catch(Throwable t){
			tx.rollback();
			return null;
		}
	}
	
	//retrieve newly created order
	public Order getNewOrder(Date orderDate) {
	//	log.info("Getting Newly Created Order");
		Transaction tx = session.beginTransaction();
		
		try{
		
			Order order = dao.getCurrentOrder(orderDate);
			tx.commit();
			return order;
		}catch(Throwable t){
			tx.rollback();
			return null;
		}
	}
	
	//add or subtract quantity
	public void change(int amount, int productId){
	//	log.info("Changing the quantity of a product based on the product id");
		Transaction tx = session.beginTransaction();
		
		try{
			dao.changeStock(amount, productId);
			tx.commit();
	//		log.info("Transaction successful for Product quantity change");
		}catch(Throwable t){
			tx.rollback();
	//		log.info("Transaction failed for Product quantity change");
		}
		return;
	
	}
	
	//create invoice
	public void createInvoice(Invoice invoice){
		Transaction tx = session.beginTransaction();
		try{
			dao.insertObject(invoice);
			tx.commit();
		}catch(Throwable t){
			tx.rollback();
		}
	}
	
	////////////////END INCOMING AND OUTGOING SECTION //////////////////	
	
	///////////////////////// ADD NEW ITEMS/PRODUCTS ///////////////////////////////
	
	public Set<CategoryDescription> getCatChoice(int catDescId) {
	//	log.info("Get list of Category Description for the type of category.");
		Transaction tx = session.beginTransaction();
		try{
			Set<CategoryDescription> catDesc = dao.getCatChoice(catDescId);
			tx.commit();
			return catDesc;
		}catch(Throwable t){
			tx.rollback();
			return null;
		}
	}
	
	public Product getNewProduct(String newItemName) {
	//	log.info("Get newly created product based on the name.");
		Transaction tx = session.beginTransaction();
		try{
			Product product = dao.getNewProduct(newItemName);
			tx.commit();
			return product;
		}catch(Throwable t){
			tx.rollback();
			return null;
		}
		
	}
	//////////////////////// END ADD NEW ITEMS/PRODUCTS //////////////////////////
	
	////// ADDITIONS TO ADDING CLIENTS ///////
	public State getState(int id) {
	//	log.info("Get State.");
		Transaction tx = session.beginTransaction();
		try{
			State state = dao.getState(id);
			tx.commit();
			return state;
		}catch(Throwable t){
			tx.rollback();
			return null;
		}
		
	}
	
	public Address getAddress(State state) {
	//	log.info("Get Address based on State code.");
		Transaction tx = session.beginTransaction();
		try{
			Address address = dao.getAddress(state);
			tx.commit();
			return address;
		}catch(Throwable t){
			tx.rollback();
			return null;
		}
	}
	
	public ClientType getClientType(int clientTypeId) {
	//	log.info("Get Client Type.");
		Transaction tx = session.beginTransaction();
		try{
			ClientType clientType = dao.getClientType(clientTypeId);
			tx.commit();
			return clientType;
		}catch (Throwable t){
			tx.rollback();
			return null;
		}
	}
	////// END ADDITIONS TO ADDING CLIENTS ///////
	public List<State> getAllStates() {
		List<State> states = dao.getAllStates();
		return states;
	}
	public List<CategoryDescription> getAllCatDesc() {
		Transaction tx = session.beginTransaction();
		try{
			List<CategoryDescription> catDesc = dao.getAllCatDesc();
			tx.commit();
			return catDesc;
		}catch (Throwable t){
			tx.rollback();
			return null;
		}
	
	}
	public Set<Product> getProductByName(String name) {
		Set<Product> madeProduct = dao.getProductByName(name);
		return madeProduct;
	}
	public CategoryDescription getCatChoiceById(int catDescId) {
		CategoryDescription getCat = dao.getCatChoiceById(catDescId);
		return getCat;
	}
	public Product getProduct(String productName) {
		Product product = dao.getProduct(productName);
		return product;
	}
	

	
	
}
	

	
	

