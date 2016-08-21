package com.revature.dataAccess;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryManager 
{

	// One SF per database
	private static SessionFactory sf;
	
	
	private SessionFactoryManager(){}
	
	public static synchronized SessionFactory getInstance()
	{
		if(sf == null)
		{
		
			// Instantiation of SessionFactory (3.6)
			sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		
		return sf;
	}
}