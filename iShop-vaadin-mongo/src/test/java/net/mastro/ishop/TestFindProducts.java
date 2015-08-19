package net.mastro.ishop;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class TestFindProducts {
	
	public static void main(String[] args) throws UnknownHostException, MongoException {
		
		String text = "silli";
		
		Mongo mongo = Dao.getMongo();
		
		DB db = mongo.getDB("shopItem");
		
		DBCollection collection = db.getCollection("shopItem");
		
		DBObject query = new BasicDBObject("name", new BasicDBObject("$regex", text));

		System.out.println(collection.distinct("name", query));

	}

}
