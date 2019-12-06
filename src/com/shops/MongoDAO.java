package com.shops;

import java.util.ArrayList;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDAO {
	
	String mongoDB = "storeHeadOfficeDB";
	String mongoCollection = "storeHeadOffice";
	
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;

	public MongoDAO() throws Exception {
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(mongoDB);
		collection = database.getCollection(mongoCollection);
	}
	
	//Load Offices
	public ArrayList<HeadOffice> loadOffices() throws Exception {
		ArrayList<HeadOffice> headOfficeList = new ArrayList<HeadOffice>();
		FindIterable<Document> headOffices = collection.find();
	
		for (Document d : headOffices) {
			HeadOffice o = new HeadOffice();			
			o.setSid((int)(d.get("_id")));
			o.setLoc((String)(d.get("location")));
			headOfficeList.add(o);
		}
		
		return headOfficeList;
	}
	
	// Adds a new head office
	public void addHeadOffice(HeadOffice o) throws Exception {
		
		Document d = new Document();
		d.append("_id", o.getSid());	
		d.append("location", o.getLoc());

		collection.insertOne(d);	
	}
}
