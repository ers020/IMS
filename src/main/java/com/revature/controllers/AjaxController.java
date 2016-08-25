
package com.revature.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.beans.Address;
import com.revature.beans.CategoryDescription;
import com.revature.beans.Client;
import com.revature.beans.ClientType;
import com.revature.beans.Invoice;
import com.revature.beans.Product;
import com.revature.beans.State;
import com.revature.dataAccess.BusinessLayer;

@Controller
public class AjaxController 
{
	//@Autowired
	//private ServletContext servletContext; //instance var

	private BusinessLayer bl = new BusinessLayer();
	/*private List<Client> clients = new Vector<Client>();
	private List<Client> invoices = new Vector<Client>();
	private List<Client> products = new Vector<Client>();*/
	
	private List<Client> clients = bl.getAllClients();
	private List<Invoice> invoices = bl.getInvoices();
	private List<Product> products = bl.getAllProducts();

		// write return value directly to HTTP response
					// in the specified content-type (produces=content-type)
	
	/* =================================================================
	 * ================!!!!!CLIENT CONTOLLER STUFFS!!!!!================
	 * =================================================================
	 */
	
	@RequestMapping(value="clientsPage.do", method=RequestMethod.GET)
	public String clientPage(HttpServletRequest req){

		req.setAttribute("clients",clients); // commandName=this blank object
		List<State> states = bl.getAllStates();
		req.setAttribute("states", states);
		
		List<ClientType> clientTypes = bl.getClientTypes();
		req.setAttribute("clientTypes", clientTypes);
		return "clientsPage";
	}
	
	@RequestMapping(value="insertClient.do", method=RequestMethod.POST)
	@ResponseBody
	public String addClient(HttpServletRequest req, HttpServletRequest resp, @RequestBody Client client){
		
		//	The system puts all information into a custom Client object
		//		and is parsed on this side, to get all of the items needed.
		//		
		
		State state = bl.getState(Integer.parseInt(client.getStateId()));
		Address address = new Address(client.getAddLine1(), client.getAddLine2(),
				client.getCity(), state, client.getZip());
		ClientType clientType = bl.getClientType(Integer.parseInt(client.getClientTypeId()));
		Client newClient = new Client(client.getName(), client.getEmail(),
				client.getPocName(), client.getPhone(), client.getFax(),
				address, clientType);
		bl.addClient(newClient);
		clients = bl.getAllClients();
		req.setAttribute("clients",clients); // commandName=this blank object
		List<State> states = bl.getAllStates();
		req.setAttribute("states", states);
	
		List<ClientType> clientTypes = bl.getClientTypes();
		req.setAttribute("clientTypes", clientTypes);
		
		return "clientsPage";
	}
	
	@RequestMapping(value="editClient.do", method=RequestMethod.POST)
	@ResponseBody
	public String editClient(HttpServletRequest req, HttpServletRequest resp, @RequestBody Client client){
		
		//	The system puts all information into a custom Client object
		//		and is parsed on this side, to get all of the items needed.
		//		
		
		System.out.println(client.getStrAddId());

		State state = bl.getState(Integer.parseInt(client.getStateId()));
		Address address = new Address(Integer.parseInt(client.getStrAddId()), client.getAddLine1(), client.getAddLine2(), 
			client.getCity(), state, client.getZip());
		ClientType clientType = bl.getClientType(Integer.parseInt(client.getClientTypeId()));
		Client newClient = new Client(Integer.parseInt(client.getStrId()), client.getName(), client.getEmail(),
				client.getPocName(), client.getPhone(), client.getFax(),
				address, clientType);
		bl.addClient(newClient);
		clients = bl.getAllClients();
		req.setAttribute("clients",clients); // commandName=this blank object
		List<State> states = bl.getAllStates();
		req.setAttribute("states", states);
	
		List<ClientType> clientTypes = bl.getClientTypes();
		req.setAttribute("clientTypes", clientTypes);
		
		return "clientsPage";
	}
	
	@RequestMapping(value="deleteClient.do", method=RequestMethod.POST)
	@ResponseBody
	public String deleteClient(HttpServletRequest req, HttpServletRequest resp, @RequestBody Client client)
	{	
		//	The system puts all information into a custom Client object
		//		and is parsed on this side, to get all of the items needed.
		//		

		String clientName = client.getDelName();
		
		//System.out.println(clientName);
		
		Client newClient = bl.getClient(clientName);

		bl.deleteClient(newClient);
		
		clients = bl.getAllClients();
		req.setAttribute("clients",clients); // commandName=this blank object
		List<State> states = bl.getAllStates();
		req.setAttribute("states", states);
	
		List<ClientType> clientTypes = bl.getClientTypes();
		req.setAttribute("clientTypes", clientTypes);
		
		return "clientsPage";
	}
	
	@RequestMapping(value="getClientsByType.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Client[] listClients(HttpServletRequest req, HttpServletRequest resp, @RequestBody String type)
	{	
		//	The system puts all information into a custom Client object
		//		and is parsed on this side, to get all of the items needed.
		//		
		
//		String cType = (String) type;

//		int cType = Integer.parseInt(type.substring(type.lastIndexOf(':')+1,	type.lastIndexOf(':')+2));
		
//		System.out.println("Type: " + cType);
		
		List<Client> clients = bl.getClientList(1);
//		
		Client[] clientArray = new Client[clients.size()];
		
		for(int x = 0; x < clients.size(); x++){
			
			clientArray[x] = clients.get(x);
		}
		
//		for(Client c : clients)
//		{
//			System.out.println(c.getName());
//		}
//		
//		System.out.println(clients.size());
		//req.setAttribute("specClients", clients);
//		req.getSession().setAttribute("clients", clients);
		
		return clientArray;
	}
	
	@RequestMapping(value="clientInfo.do", method=RequestMethod.GET)
	@ResponseBody
	public Client getClientInfo(HttpServletRequest request, HttpServletResponse response,  @RequestParam String clientName) throws IOException  //This class will be used to sort client lists...hopefully it works as planned
	{
		Client info = bl.getClient(clientName);

		/*request.setAttribute("eClient", info);

		response.sendRedirect("http://localhost:9001/IMS/clientInfo.do");*/
		
		return info;
	}
/*	@RequestMapping(
			method=RequestMethod.GET, 
			value="getAll.do", 
			produces="application/json")
	@ResponseBody
	public List<Client> getClients(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("clients", clients);
		
		
		return clients;
	}

	@RequestMapping(value="clientInfo.do", method=RequestMethod.GET)
	public Client getClientInfo(HttpServletRequest request, HttpServletResponse response)  //This class will be used to sort client lists...hopefully it works as planned
	{
		Client info = bl.getClient(request.getParameter("name"));

		request.setAttribute("id", info.getId());
		request.setAttribute("name", info.getName());
		request.setAttribute("email", info.getEmail());
		request.setAttribute("pocName", info.getPocName());
		request.setAttribute("phone", info.getPhone());
		request.setAttribute("fax", info.getFax());
		request.setAttribute("address", info.getAddress());
		request.setAttribute("address", info.getClientType());
		
		return null;
	}

	@RequestMapping(value="clientList.do", method=RequestMethod.GET)
	public ModelAndView showClients(
						HttpServletRequest request,
						HttpServletResponse response)
	{
		int type = 1;
		if(request.getParameter("clientType").equals("Retailer")) type = 2;
		
	
		Vector<Client> clients = (Vector<Client>) bl.getClientList(type);
		
		this.servletContext.setAttribute("Clients", clients); //update clients
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("clientView"); // view.jsp IRVR
		mv.addObject("clientView", clients); // request-scoped variables
		
		return mv;
	}

	@RequestMapping(value="regClient.do", method=RequestMethod.GET)
	public ModelAndView registerClient(
						@ModelAttribute("client") @Valid Client client,
						BindingResult bindingResult,
						HttpServletRequest request,
						HttpServletResponse response)
	{
		if(bindingResult.hasErrors())
		{
			return new ModelAndView("Whoops!");
		}
		
		@SuppressWarnings("unchecked")
		Vector<Client> clients = (Vector<Client>)this.servletContext.getAttribute("clients");
		clients.add(client);
		
		this.servletContext.setAttribute("Clients", clients); //update peeps
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("clientView"); // view.jsp IRVR
		mv.addObject("clientView", client); // request-scoped variables
		
		request.getSession().setAttribute("user", client.getName());
		return mv;
	}

	/* =================================================================
	 * ================!!!!!CLIENT CONTOLLER STUFFS!!!!!================
	 * =================================================================
	 */
	

	/* ==================================================================
	 * ================!!!!!INVOICE CONTOLLER STUFFS!!!!!================
	 * ==================================================================
	 */
	@RequestMapping(value="getProds.do", method=RequestMethod.GET, headers = "Accept=application/json", produces="application/json")
	@ResponseBody
	public String[] getProds(HttpServletRequest req, HttpServletRequest resp)
	{	
		//	The system puts all information into a custom Client object
		//		and is parsed on this side, to get all of the items needed.
		//		
		
//		String cType = (String) type;

//		int cType = Integer.parseInt(type.substring(type.lastIndexOf(':')+1,	type.lastIndexOf(':')+2));
		
//		System.out.println("Type: " + cType);
		
		List<Product> prods = bl.getAllProducts();
		String[] prodNames = new String[prods.size()];
		int i = 0;
		
		for(Product c : prods)
		{
			prodNames[i] = c.getName();
			System.out.println(c.getName());
			i++;
		}
		
		System.out.println(prods.size());
	
//		req.getSession().setAttribute("clientNames", prodNames);
		
		return prodNames;
	}

	@RequestMapping(value="getProdPrice.do", method=RequestMethod.GET, consumes="application/json", produces="application/json")
	@ResponseBody
	public String[] getProdPrice (HttpServletRequest req, HttpServletRequest resp, @RequestParam(value="var") String type)
	{	
		//	The system puts all information into a custom Client object
		//		and is parsed on this side, to get all of the items needed.
		//		
		
//		String cType = (String) type;

//		int cType = Integer.parseInt(type.substring(type.lastIndexOf(':')+1,	type.lastIndexOf(':')+2));
		
//		System.out.println("Type: " + cType);
		
		System.out.println("Getting price.");
		
		double price = bl.getPrice(type);
		
		String strPrice = String.valueOf(price);
		
		String[] retStr = new String[1];
		
		retStr[0] = strPrice;
		
		System.out.println("Price" + strPrice);
	
//		req.getSession().setAttribute("clientNames", prodNames);
		
		return retStr;
	}	
/*	public List<Invoice> getInvoices()
	{
		return invoices;
	}*/
	
/*	@RequestMapping(value="getInvoice.do", method=RequestMethod.GET)
	public ModelAndView getInvoiceByClient(
			HttpServletRequest request,
			HttpServletResponse response)
	{		
		List<Invoice> invoices = bl.getClientInvoices(request.getParameter("clientName"));

		this.servletContext.setAttribute("Invoices", invoices); //update invoices
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("invoiceView"); // view.jsp IRVR
		mv.addObject("invoiceView", invoices); // request-scoped variables
		return mv;
	}

	// FIXED!!!!

	@RequestMapping(value="regInvoice.do", method=RequestMethod.GET)
	public ModelAndView registerInvoice(
			@ModelAttribute("Invoice") @Valid Invoice invoice,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response)
	{
		if(bindingResult.hasErrors())
		{
			return new ModelAndView("Whoops!");
		}
		
		@SuppressWarnings("unchecked")
		Vector<Invoice> invoices = 
			(Vector<Invoice>)this.servletContext.getAttribute("people");
		invoices.add(invoice);
		this.servletContext.setAttribute("Invoices", invoices); //update peeps
		ModelAndView mv = new ModelAndView();
		mv.setViewName("invoiceView"); // view.jsp IRVR
		mv.addObject("success", "Successfully added invoice!"); // request-scoped variables
		return mv;
	}

	/* ==================================================================
	 * ================!!!!!INVOICE CONTOLLER STUFFS!!!!!================
	 * ==================================================================
	 */

	
	/* ==================================================================
	 * ================!!!!!PRODUCT CONTOLLER STUFFS!!!!!================
	 * ==================================================================
	 */

	@RequestMapping(value="productPage.do", method=RequestMethod.GET)
	public String getProducts(HttpServletRequest req)
	{
		req.setAttribute("products",products); // commandName=this blank object
		List<CategoryDescription> catDesc = bl.getAllCatDesc();
		req.setAttribute("catDesc", catDesc);
		
		return "productPage";
	}
	
	@RequestMapping(value="insertProduct.do", method=RequestMethod.POST)
	@ResponseBody
	public String addProduct(HttpServletRequest req, HttpServletRequest resp, @RequestBody Product product){
		
		//do Product
		Set<CategoryDescription> descOptions = new HashSet<CategoryDescription>();
		//for future use, because Sets are a pain to step through
		List<CategoryDescription> descFuture = new ArrayList<CategoryDescription>();
		String[] descList = product.getCatDescId();
		
		for (int x = 0; x < descList.length; x++){
			descOptions.add(bl.getCatDescById(Integer.parseInt(descList[x])));
			descFuture.add(bl.getCatDescById(Integer.parseInt(descList[x])));
		}
		
		//get everything set for product
		Product newProduct = new Product(product.getName(), product.getsName(),
				product.getDescription(), Double.parseDouble(product.getStrCost()), 
				product.getStrSize(), Integer.parseInt(product.getStrStock()), 
				Integer.parseInt(product.getStrPreQuantity()), Double.parseDouble(product.getStrRetailPrice()),
				descOptions);
		
		//save it
		bl.addProduct(newProduct);
		
		//grab it
		Set<Product> savedProduct = bl.getProductByName(product.getName());
		
		//Do Categories
		for(int x = 0; x < descFuture.size(); x++)
		{
			CategoryDescription temp = descFuture.get(x);
			CategoryDescription saveCD = new CategoryDescription(temp.getId(), temp.getDescription(), savedProduct);
			bl.updateCatDesc(saveCD);
		}
		
		products = bl.getAllProducts();
		req.setAttribute("products",products); // commandName=this blank object
		List<CategoryDescription> catDesc = bl.getAllCatDesc();
		req.setAttribute("catDesc", catDesc);
		
		return "productPage";
	}
	
	@RequestMapping(value="productInfo.do", method=RequestMethod.GET)
	@ResponseBody
	public Product getProductInfo(HttpServletRequest request, HttpServletResponse response,  @RequestParam String productName) throws IOException  //This class will be used to sort client lists...hopefully it works as planned
	{
		//Cannot simply pass on the Product. Infinite loop, and over loads the get request... severely. 
		//Must make the String-like constructor and send it back through.
		Product info = bl.getProduct(productName);
		
		List<CategoryDescription> prodCats = new ArrayList<CategoryDescription>(info.getCategoryDesc());
		int prodCatId[] = new int[prodCats.size()];
		
		for(int x = 0; x < prodCats.size(); x++){
			 prodCatId[x] = prodCats.get(x).getId();
		}
		
		Product product = new Product(info.getId(), info.getName(), info.getsName(),
							info.getDescription(), info.getCost(), info.getSize(),
							info.getStock(), info.getQuantity(), info.getMsrp(), prodCatId);
		
		List<CategoryDescription> catDesc = bl.getAllCatDesc();
		request.setAttribute("catDesc", catDesc);
		
		return product;
	}
	
	@RequestMapping(value="editProduct.do", method=RequestMethod.POST)
	@ResponseBody
	public String editProduct(HttpServletRequest req, HttpServletRequest resp, @RequestBody Product product){
		
		//	The system puts all information into a custom Client object
		//		and is parsed on this side, to get all of the items needed.
		//		
		
		//update Product's Set<CategoryDescription>
				//List<CategoryDescription> descOptions = new ArrayList<CategoryDescription>();
 		
		Product catDescSourceProduct = bl.getProduct(product.getName());
		
		Product updateProduct = new Product(Integer.parseInt(product.getStrId()), product.getName(),
											product.getsName(), product.getDescription(), Double.parseDouble(product.getStrCost()),
											product.getStrSize(), Integer.parseInt(product.getStrStock()),
											Integer.parseInt(product.getStrPreQuantity()), Double.parseDouble(product.getStrRetailPrice()),
											catDescSourceProduct.getCategoryDesc());

		bl.addProduct(updateProduct);
		
		//System.out.println(product.getCatDescId().toString());
		
		//int[] descList = new int[product.getCatDescIntId().length];

				
//				for(int x = 0; x < product.getCatDescIntId().length; x++){
//					descList[x] = product.getCatDescIntId(x);
//				}
//				
//				
//				for (int x = 0; x < descList.length; x++){
//					descOptions.add(bl.getCatDescById(descList[x]));
//				}
//
//				Product updateProduct = new Product(Integer.parseInt(product.getStrId()),
//						product.getName(), product.getsName(), product.getDescription(),
//						Double.parseDouble(product.getStrCost()), product.getStrSize(), 
//						Integer.parseInt(product.getStrStock()), Integer.parseInt(product.getStrPreQuantity()),
//						Double.parseDouble(product.getStrRetailPrice()), descOptions);
//				
//				bl.addProduct(updateProduct);
		
//		req.setAttribute("products",products); // commandName=this blank object
//		List<CategoryDescription> catDesc = bl.getAllCatDesc();
//		req.setAttribute("catDesc", catDesc);
		
		return "productPage";
	}
	
	
	@RequestMapping(value="deleteProduct.do", method=RequestMethod.POST)
	@ResponseBody
	public String deleteProduct(HttpServletRequest req, HttpServletRequest resp, @RequestBody Product product)
	{	
		//	Pulled the name, id, and CategoryDescription array associated
		//		with that item. As we have to manually disconnect the Product
		//		from the CategoryDescription (i.e. go into the Category Description's
		//		set of Products, and remove them from the set. Then, we should
		//		be able to delete the product.
		//	Could do something similar for the update if the CategoryDescription's change

		Product delProduct = bl.getProduct(product.getDelName());
		Set<CategoryDescription> temp = new HashSet<CategoryDescription>(delProduct.getCategoryDesc());

		for(CategoryDescription cd : delProduct.getCategoryDesc()){
			
			temp.remove(cd);
			
		}


		delProduct.setCategoryDesc(temp);
		
		System.err.println(delProduct.getCategoryDesc().toString());
		
		bl.addProduct(delProduct);
		
		System.err.println(delProduct.getCategoryDesc().toString());
		
		bl.deleteProduct(delProduct);
	

		products = bl.getAllProducts();
		req.setAttribute("products",products); // commandName=this blank object
		List<CategoryDescription> catDesc = bl.getAllCatDesc();
		req.setAttribute("catDesc", catDesc);
		
		return "productPage";

	}
/*	public List<Product> getProductsByClient(
			HttpServletRequest request,
			HttpServletResponse response)
	{

		List<Product> results = (List<Product>) bl.getProdsByClient(Integer.parseInt(request.getParameter("id")));
		
		return results;
	}

	@RequestMapping(value="regProduct.do", method=RequestMethod.GET)
	public ModelAndView registerProduct(
			@ModelAttribute("Product") @Valid Product product,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response)
	{
		if(bindingResult.hasErrors())
		{
			return new ModelAndView("Whoops!");
		}
		
		@SuppressWarnings("unchecked")
		Vector<Product> products = 
			(Vector<Product>)this.servletContext.getAttribute("product");
		products.add(product);
		this.servletContext.setAttribute("Products", products); //update peeps
		ModelAndView mv = new ModelAndView();
		mv.setViewName("productView"); // view.jsp IRVR
		mv.addObject("success", "Successfully added product!"); // request-scoped variables
		return mv;
	}

	
	/* ==================================================================
	 * ================!!!!!PRODUCT CONTOLLER STUFFS!!!!!================
	 * ==================================================================
	 */
	
	
	

	
}
