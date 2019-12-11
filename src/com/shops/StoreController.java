package com.shops;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

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
		try {
			dao.delete(storeID);
		} 
		catch(MySQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage("Error: Store " + storeID + " has products");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Load Stores from DAO
	public String loadStores() {
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

	//Add Store
	public String addStore(Store s) {
		try {
			dao.addStore(s);
			return "list_stores"; // if everything ok go to manage stores
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage("Error: Store " + s.getStoreName() + " already exists");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null; // if there is an error stay where you are
		}
	}
	
}
