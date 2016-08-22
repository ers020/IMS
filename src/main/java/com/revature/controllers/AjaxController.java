
package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.beans.Address;
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

	
/*	public String getProducts(HttpServletRequest req)
	{
		req.setAttribute("products",products); // commandName=this blank object
		return "productPage";
	}
		
	public List<Product> getProductsByClient(
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
