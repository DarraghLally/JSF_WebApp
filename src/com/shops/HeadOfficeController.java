package com.shops;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class HeadOfficeController {
	
	MongoDAO mongoDao;
	ArrayList<HeadOffice> offices;
	
	//Constructor
	public HeadOfficeController() {
		super();
		try {
			mongoDao = new MongoDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Load Products
	public String loadOffices() {
		// System.out.println("In LoadProducts()");
		try {
			offices = mongoDao.loadOffices();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Get List of Products
	public ArrayList<HeadOffice> getOffices() {
		return offices;
	}
}
