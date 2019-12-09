package com.shops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {

	// Variables
	private DataSource mysqlDS;

	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/shops";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	// Load Stores
	public ArrayList<Store> loadStores() throws Exception {
		//System.out.println("In DAO loadStores()");
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();
		String sql = "select * from store";
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery(sql);

		// Create Storage
		ArrayList<Store> stores = new ArrayList<Store>();
		// process result set
		while (myRs.next()) {
			// System.out.println(myRs.getString("PRODID"));
			Store s = new Store();
			s.setStoreID(myRs.getInt("id"));
			s.setStoreName(myRs.getString("name"));
			s.setFounded(myRs.getString("founded"));
			stores.add(s);
		}
		myConn.close();
		myStmt.close();
		myRs.close();
		return stores;
	}// loadStore()

	// Add Store
	public void addStore(Store store) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		myConn = mysqlDS.getConnection();
		String sql = "insert into store values (?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, store.getStoreID());
		myStmt.setString(2, store.getStoreName());
		myStmt.setString(3, store.getFounded());
		myStmt.execute();
		
		myConn.close();
		myStmt.close();
	}// addStore()

	// Add Delete Store
	public void delete(int storeID) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		myConn = mysqlDS.getConnection();
		String sql = "delete from store where id = ?";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, storeID);
		myStmt.execute();
		
		myConn.close();
		myStmt.close();
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Load products
	public ArrayList<Product> loadProducts() throws Exception {
		System.out.println("In DAO loadProducts()");

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();
		String sql = "select * from product";
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery(sql);

		// Create Storage
		ArrayList<Product> products = new ArrayList<Product>();
		// process result set
		while (myRs.next()) {
			// System.out.println(myRs.getString("PRODID"));
			Product p = new Product();
			p.setPid(myRs.getInt("pid"));
			p.setSid(myRs.getInt("sid"));
			p.setProductName(myRs.getString("prodName"));
			p.setPrice(myRs.getDouble("price"));
			products.add(p);
		}
		myConn.close();
		myStmt.close();
		myRs.close();
		return products;
	}

	// Add Delete Product
	public void deleteProduct(int pid) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		myConn = mysqlDS.getConnection();
		String sql = "delete from product where pid = ?";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, pid);
		myStmt.execute();		
	}
	
	// Load products for specific store
	public ArrayList<StoreProduct> loadStoreProducts(int sid) throws Exception {
		//System.out.println("In DAO loadStoreProducts()");
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();
		String sql = "select s.id, s.name, s.founded, p.pid, p.prodName, p.price "
				+ "from store s "
				+ "inner join product p "
				+ "on s.id = p.sid "
				+ "where s.id = " + sid; 		
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery(sql);
		
		// Create Storage
		ArrayList<StoreProduct> storeProducts = new ArrayList<StoreProduct>();
		// process result set
		while (myRs.next()) {
			//System.out.println("DAO - loadStoreProducts()");
			StoreProduct sp = new StoreProduct();
			sp.setSid(myRs.getInt("id"));
			sp.setStoreName(myRs.getString("name"));
			sp.setFounded(myRs.getString("founded"));
			sp.setPid(myRs.getInt("pid"));
			sp.setProduct(myRs.getString("prodName"));
			sp.setPrice(myRs.getDouble("price"));
			storeProducts.add(sp);
		}		
		myConn.close();
		myStmt.close();
		myRs.close();
		return storeProducts;		
	}
	
}
