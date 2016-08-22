package com.revature.app;


import java.io.FileNotFoundException;
import java.util.List;

import com.revature.beans.Client;
import com.revature.beans.Product;
import com.revature.dataAccess.BusinessLayer;
import com.revature.dataAccess.DataLayerAccess;

public class Test 
{

	//private static Logger log = Logger.getRootLogger();
	

	public static void main(String[] args) 
	{		
		DataLayerAccess dla = new DataLayerAccess();
		
		List<Client> client = dla.getAllClients();
		System.out.println(client);
		
	}
	
	
	//previous tests//
	
	
	///Adding States///
	
//	File file1 = new File("states.txt");
//	File file2 = new File("statsAbbr.txt");
//	Scanner in1 = new Scanner(file1);
//	Scanner in2 = new Scanner(file2);
//	
//	State object = new State();
//	DataLayerAccess dla = new DataLayerAccess();
//	
//	int counter = 1;
//	while(in.hasNext())
//	{
//		object.setId(counter);
//		object.setName(in1.nextLine());
//		object.setIntial(in2.nextLie());
//		dla.createObject(object);
//		counter++;
//	}
	
	
	///ADD CLIENT TYPES///
//	File file = new File("clientType.txt");
//	Scanner in = new Scanner(file);
//	
//	ClientType object = new ClientType();
//	DataLayerAccess dla = new DataLayerAccess();
//	
//	int counter = 1;
//	while(in.hasNext())
//	{
//		object.setId(counter);
//		object.setClientList(in.nextLine());
//		dla.createObject(object);
//		counter++;
//	}
	
	/// ADD PRODUCT CATEGORY ///
	
//	File file = new File("prodCat.txt");
//	@SuppressWarnings("resource")
//	Scanner in = new Scanner(file);
//	
//	CategoryDescription object = new CategoryDescription();
//	DataLayerAccess dla = new DataLayerAccess();
//	
//	int counter = 1;
//	while(in.hasNext()){
//		object.setId(counter);
//		object.setDescription(in.nextLine());
//		dla.createObject(object);
//		counter++;
//	}
	
	/// ADD PRODUCT///
	
//	Product object = new Product(1, "Shavette Straight Razor", "Carbon steel, interchangable blade, straight razor.", 
//			  "ShStR", 507.97,  "10 Items/Box", 300, 500, 55.00);
//	  
//	  DataLayerAccess dla = new DataLayerAccess();
//	  
//	  dla.createObject(object);

	//ADD CLIENTS//
	
//	//Create new Client
//	  int cType = 1; 
//	  State state = new State();
//	  DataLayerAccess dla = new DataLayerAccess();
//	  
//	  String stateName ="Virginia";
//	  
//	  state = dla.getState(stateName);
//	  
//	  Address address = new Address(50, "305 FairView Lane", "Suite# 302", "Minot", state, "58701");
//	  
//	  dla.createObject(address);
//	  
//	  ClientType clientType = dla.getClientType(cType);
//	  
//	  Client object = new Client(1, "Tom Dillinger", "tdill@gmail.com", "Martha Smith", "(555)555-5689", "(555)555-4587", address, clientType);
//	  
//	  dla.createObject(object);
	
	
//	//////INCOMING AND OUTGOING CODE//////
//	//pass clientType for the dropdownlist
//	DataLayerAccess dla = new DataLayerAccess();
//	Set<ClientType> clientType = dla.clientTypeList();
//	
//	//get choice and pass on the list of Clients that follow that choice
//	String choice = "Retailer";
//	ClientType choiceType = new ClientType();
//	
//	for(ClientType ct: clientType){
//		if(ct.getClientList().equals(choice)){
//			 choiceType = ct;
//		}
//	}
//	
//	//get Client list for drop down based on the user's choice
//	Set<Client> clients = dla.clientList(choiceType.getId());
//	
//	//choose Client
//	int id = 100;
//	
//	Client client = new Client();
//	for(Client cl : clients){
//		if(cl.getId() == id){
//			client = cl;
//		}
//	}
//	
//	int prodId = 50;
//	Product prod = new Product();
//	//choice attachment for buying items, pass up products 
//	// mock set up for "user" choice
//	Set<Product> products = dla.getProducts();
//	
//	for(Product p : products){
//		if(p.getId() == prodId){
//			prod = p;
//		}
//	}
//	
//	int quantity = -100;
//	
//	//change quantity of product
//	prod.setQuantity(prod.getQuantity() + quantity);
//	dla.createObject(prod);
//
//	
//	//create invoice
//	//set date upside of the object to retrieve the 
//	// correct order number, so that the invoice can be properly made
//	// just need to subtract or add stock value of product; depending on the return
//	Date orderDate = new Date();
//	
//	Order orderObj = new Order(1, 125.54, orderDate, 15.24, (125.54+15.24), client);
//	dla.createObject(orderObj);
//	
//	//re-grab orderObj as the new orderId has changed
//	orderObj = dla.getNewOrder(orderDate);
//	
//	//make Invoice PK
//	InvoicePK invoicePk = new InvoicePK(orderObj, 1);
//	dla.createObject(invoicePk);
//
//	//make invoice
//	Invoice invoice = new Invoice(invoicePk,(125.54+15.24), 150, prod);
//	dla.createObject(invoice);
	
	
//	//////////////////////////////// BEGIN PRODUCT ADDITION THROUGH FORM ///////////////////////////
//	//WORKING!!!///
//	///SORT OF WORKS. ENDS UP PLASTERING THE ITEM INTO ALL CATEGORIES
//	///FIXED THE ABOVE ISSUE!
//	DataLayerAccess dla = new DataLayerAccess();
//	
//	//need drop down selection for categories
//	String categoryChoice = "Grooming Tools";
//	
//	//get set for the categoryDescription based on the choice
//	Set<CategoryDescription> categoryDesc = dla.getCatChoice(categoryChoice);
//	
//	String newItemName = "Gillete Shaving Gel";
//	
//	Product product = new Product(1, newItemName, "GiShG",
//			"Shaving gel in an arosole can.", 40.95, "20 per box", 100,
//			25, 4.99, categoryDesc);
//	
//	dla.createObject(product);
//	
//	Set<Product> products = dla.getProducts();
//	
//	CategoryDescription categoryDescription = new CategoryDescription(
//			1, "Beard Care", products);
//	
//	dla.createObject(categoryDescription);
//	
//	//////////////////////// END PRODUCT ADDITION /////////////////////////////////
	
//	//delete Client - Working now
//	
//	BusinessLayer bl = new BusinessLayer();
//	
//	Client client = bl.getClient("Tom Dillinger");
//	
//	bl.deleteClient(client);
	
}
