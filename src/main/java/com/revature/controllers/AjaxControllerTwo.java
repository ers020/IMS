/*package com.revature.controllers;

import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.beans.Client;
import com.revature.dataAccess.BusinessLayer;

@Controller
public class AjaxControllerTwo {
	
	private BusinessLayer bl = new BusinessLayer();
	private List<Client> clients = new Vector<Client>(bl.getAllClients());
	
	@RequestMapping(method=RequestMethod.GET, value="getAll.do")
	@ResponseBody
	public List<Client> getClients(){
		return clients;
	}
	
	
}*/
