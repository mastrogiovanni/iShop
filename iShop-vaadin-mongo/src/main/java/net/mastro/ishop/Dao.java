package net.mastro.ishop;

import java.util.Arrays;

import net.mastro.ishop.dao.AuthDao;
import net.mastro.ishop.dao.ShopItemDao;
import net.mastro.ishop.utility.Cfg;

import org.mongodb.morphia.Morphia;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;

public class Dao {
	
	private MongoClient mongo;
	
	private Morphia morphia;
	
	private ShopItemDao shopItemDao;
	
	private AuthDao authDao;
	
	private static Dao instance;
	
	public static AuthDao auth() {
		return instance().authDao;
	}
	
	public static ShopItemDao shopItem() {
		return instance().shopItemDao;
	}
	
	private static Dao instance() {
		if ( instance == null )
			instance = new Dao();
		return instance;
	}
	
	private Dao() {
		try {

//			if (Cfg.getBoolean("mongo.auth")) {
//				DB db = mongo.getDB(dbName);
//				boolean auth = db.authenticate(Cfg.get("mongo.username"), Cfg.get("mongo.password").toCharArray());
//				if (auth) {
//					logger.debug("Connection to db '" + dbName + "' authenticated");				
//				}			
//				else {
//					logger.debug("ERROR: connection to db '" + dbName + "' authentication failed");				
//				}
//			}
//			else {
//				logger.debug("Connection to db '" + dbName + "' (non authenticated) established");				
//			}

			MongoCredential credential = MongoCredential.createCredential("michele",
                    "dematerializzazione",
                    "pippo80".toCharArray());
			
			ServerAddress address = new ServerAddress(
					Cfg.get("mongo.host", "localhost"), 
					Cfg.getInt("mongo.port", 27017));
			
			mongo = new MongoClient(address, Arrays.asList(credential));
			
		} catch (MongoException e) {
			e.printStackTrace();
		}
		
		morphia = new Morphia();
		shopItemDao = new ShopItemDao(mongo, morphia);
		authDao = new AuthDao(mongo, morphia);
	}

	public static Mongo getMongo() {
		return instance().mongo;
	}

	public static Morphia getMorphia() {
		return instance().morphia;
	}
	
}
