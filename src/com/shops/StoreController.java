package com.shops;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

//Interface between view and DAO
@ManagedBean
@SessionScoped
public class StoreController {

	DAO dao;
	ArrayList<Store> stores;

	//No Argument constructor
	public StoreController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Delete
	public void delete(int storeID) throws Exception {
		//System.out.println(id);
		dao.delete(storeID);
	}
	

	//Load Stores from DAO
	public String loadStores() {
		// System.out.println("In LoadProducts()");
		try {
			stores = dao.loadStores();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Get List of Stores
	public ArrayList<Store> getStores() {
		return stores;
	}

	//Add Stores
	public String addStore(Store s) {
		// System.out.println(p.prodid + " " + p.descrip);
		try {
			dao.addStore(s);
			return "index"; // if everything ok go to index.xhtml
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage("Error: Store ID already exists");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null; // id there is an error stay where you are
		}
	}
	
}
