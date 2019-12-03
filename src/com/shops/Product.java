package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Product {
	
	//Variables
	private int pid;
	private int sid;
	private String productName;
	private double price;
	
	//Accessor Methods
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
		
	//Override toString
}
