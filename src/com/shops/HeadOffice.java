package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class HeadOffice {
	
	//Variables
	private int oid;
	private String loc;

	//Assessor Methods
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
}
