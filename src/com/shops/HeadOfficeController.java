package com.shops;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mongodb.MongoServerException;
import com.mongodb.MongoWriteException;

@ManagedBean
@SessionScoped
public class HeadOfficeController {
	
	DAO dao;
	Store s;
	MongoDAO mongoDao;
	ArrayList<HeadOffice> offices;
	
	//Constructor
	public HeadOfficeController() {
		super();
		try {
			mongoDao = new MongoDAO();
			dao = new DAO();
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
	
	//Add Head Office
	public String addHeadOffice(HeadOffice o) {
		
		try {
			int result = dao.checkMySQL(o.getOid()); //Check sql and return 1 if exists
			if(result > 0) {
				mongoDao.addHeadOffice(o);
			}else {
				System.out.println("Error: ID " + o.getOid() + " does not exist");
				FacesMessage message = new FacesMessage("Error: Store " + o.getOid() + " does not exist");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			return "list_offices";
		}
		catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			return null;
		}	
		catch(MongoWriteException e) { 
			FacesMessage message = new FacesMessage("Error: Office " + o.getOid() + " already has location");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
			return null;			
		}
		catch(MongoServerException e) {
				FacesMessage message = new FacesMessage("Error: Database not loaded");
				FacesContext.getCurrentInstance().addMessage(null, message);
				e.printStackTrace();
				return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	} 
	
	//Delete Office
	/*public void delete(HeadOffice o) {
		try {
			mongoDao.deleteHeadOffice(o);
		}
		catch(MongoWriteException e) { 
			FacesMessage message = new FacesMessage("Error: Office " + o.getOid());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();		
		}
		catch(MongoServerException e) {
				FacesMessage message = new FacesMessage("Error: Database not loaded");
				FacesContext.getCurrentInstance().addMessage(null, message);
				e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
