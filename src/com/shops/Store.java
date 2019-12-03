package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Store {
	
	//Variables
	private int storeID;
	private String storeName;
	private String founded;
	
	//Accesor Methods
	public int getStoreID() {
		return storeID;
	}
	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getFounded() {
		return founded;
	}
	public void setFounded(String founded) {
		this.founded = founded;
	}
	
	
	//Override toString
}
