package com.shops;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class StoreProductController {

	DAO dao;
	ArrayList<StoreProduct> storeProducts;	
	
	public StoreProductController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Get Store products
	public ArrayList<StoreProduct> getStoreProducts() {
		return storeProducts;
	}
		
	//Loading
	public String loadStoreProducts(int sid) {
		//System.out.println("StoreProductController: In LoadStoreProducts()");
		try {
			storeProducts = dao.loadStoreProducts(sid);
			return "list_store_products";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
