package util;

import java.util.Vector;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import model.Cliente;
import model.Room;

public class MongoHelper {
	
	 public void insertClient(Cliente client) {
		
		MongoClient mongo = new MongoClient("localhost");
		
		MongoDatabase database = mongo.getDatabase("Hostal");
		
	    MongoCollection<Document> collection = database.getCollection("clients");
	    Document document = new Document("_id", new ObjectId())
	    		.append("name", client.getName())
	    		.append("checkIn", client.getCheckIn())
	    		.append("checkOut", client.getCheckOut())
	    		.append("Habitacion", client.getRoomNum())
	    		.append("entradas", client.isEntradas())
	    		.append("repetidor", client.isactivo());
	    
	    collection.insertOne(document);
	}
	 
	public Vector<String> getClientsName() {
		
		Vector<String> clientsNames = new Vector<String>();
		
		MongoClient mongo = new MongoClient("localhost");
		
		DB db = mongo.getDB("Hostal");
		DBCollection collection = db.getCollection("clients");
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("name", "Adrian de la torre");
		DBCursor cursor = collection.find();

		while (cursor.hasNext()) {
		    String data = cursor.next().get("name").toString();
		    clientsNames.add(data);
		}
		
		return clientsNames;
		
	}
	
	public String getCheckIn(String name) {
		MongoClient mongo = new MongoClient("localhost");
		String data = null;
		
		DB db = mongo.getDB("Hostal");
		DBCollection collection = db.getCollection("clients");
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("name", name);
		DBCursor cursor = collection.find();

		while (cursor.hasNext()) {
		    data = cursor.next().get("checkIn").toString();
		    
		    
		}
		
		return data ;
		
	}
	
	public String getCheckOut(String name) {
		MongoClient mongo = new MongoClient("localhost");
		String data = null;
		
		DB db = mongo.getDB("Hostal");
		DBCollection collection = db.getCollection("clients");
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("name", name);
		DBCursor cursor = collection.find(searchQuery);

		while (cursor.hasNext()) {
		    data = cursor.next().get("checkOut").toString();
		    
		    
		}
		
		return data ;
		
	}
	
	public boolean getIfEntradas(String name) {
		MongoClient mongo = new MongoClient("localhost");
		boolean data = false;
		
		DB db = mongo.getDB("Hostal");
		DBCollection collection = db.getCollection("clients");
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("name", name);
		DBCursor cursor = collection.find();

		while (cursor.hasNext()) {
		    data = (boolean) cursor.next().get("entradas");
		    
		    
		}
		
		return data ;
		
	}
	
	 public void insertRoom(Room room) {
			
			MongoClient mongo = new MongoClient("localhost");
			
			MongoDatabase database = mongo.getDatabase("Hostal");
			
		    MongoCollection<Document> collection = database.getCollection("rooms");
		    Document document = new Document("_id", new ObjectId())
		    		.append("number", room.getNumber())
		    		.append("client", room.getClient())
		    		.append("type", room.getType())
		    		.append("free", room.isFree())
		    		.append("clened", room.isCleaned());
		    
		    collection.insertOne(document);
		}
	 
	 public Vector<String> getRoomsNumbers() {
			
			Vector<String> roomNumbers = new Vector<String>();
			
			MongoClient mongo = new MongoClient("localhost");
			
			DB db = mongo.getDB("Hostal");
			DBCollection collection = db.getCollection("rooms");
			
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("name", "Adrian de la torre");
			DBCursor cursor = collection.find();

			while (cursor.hasNext()) {
			    String data = cursor.next().get("number").toString();
			    
			    roomNumbers.add(data);
			}
			
			return roomNumbers;
			
		}
	 	
	 public String getRoomClient(String number) {
			MongoClient mongo = new MongoClient("localhost");
			String data = null;
			
			DB db = mongo.getDB("Hostal");
			DBCollection collection = db.getCollection("rooms");
			
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("number", number);
			DBCursor cursor = collection.find();

			while (cursor.hasNext()) {
			    data =  cursor.next().get("client").toString();
			    
			    
			}
			
			return data ;
			
		}
	 public boolean getIfCleaned(String number) {
			MongoClient mongo = new MongoClient("localhost");
			boolean data = false;
			
			DB db = mongo.getDB("Hostal");
			DBCollection collection = db.getCollection("rooms");
			
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("number", number);
			DBCursor cursor = collection.find();

			while (cursor.hasNext()) {
			    data =  (boolean) cursor.next().get("clened");
			    
			    
			}
			
			return data ;
			
		}

}
