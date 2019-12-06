package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class HeadOffice {
	
	//Variables
	private int sid;
	private String loc;
	
	//Assessor Methods
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	
}
