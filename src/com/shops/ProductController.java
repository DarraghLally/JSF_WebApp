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
public class ProductController {

	DAO dao;
	ArrayList<Product> products;

	//No Argument constructor
	public ProductController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Load Products
	public String loadProducts() {
		// System.out.println("In LoadProducts()");
		try {
			products = dao.loadProducts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Get List of Products
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	
	//Delete
	public void deleteProduct(int pid) throws Exception {
		System.out.println(pid);
		dao.deleteProduct(pid);
	}

	/*
	//Add Product
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
	*/
	
}
