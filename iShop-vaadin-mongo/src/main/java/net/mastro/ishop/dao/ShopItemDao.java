package net.mastro.ishop.dao;

import java.util.List;

import net.mastro.ishop.Dao;
import net.mastro.ishop.model.ShopItem;
import net.mastro.ishop.model.User;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class ShopItemDao extends AbstractDao<ShopItem, ObjectId> {
	
	public ShopItemDao(MongoClient mongo, Morphia morphia) {
		super(mongo, morphia, "dematerializzazione");
		morphia.map(ShopItem.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> suggestName(String text) {
		Mongo mongo = Dao.getMongo();
		DB db = mongo.getDB("dematerializzazione");
		DBCollection collection = db.getCollection("dematerializzazione");
		DBObject query = new BasicDBObject("name", new BasicDBObject("$regex", text));
		return collection.distinct("name", query);
	}

	@SuppressWarnings("unchecked")
	public List<String> suggestBrand(String text) {
		Mongo mongo = Dao.getMongo();
		DB db = mongo.getDB("dematerializzazione");
		DBCollection collection = db.getCollection("dematerializzazione");
		DBObject query = new BasicDBObject("brand", new BasicDBObject("$regex", text));
		return collection.distinct("brand", query);
	}

	public List<ShopItem> getShopItems(User user) {
		Query<ShopItem> query = createQuery().field("user").equal(user);
		return find(query).asList();
	}
	
	public void add(ShopItem item, User user) {
		item.setUser(getDatastore().getKey(user));
		save(item);
	}
	
	public void update(ShopItem item) {
		Query<ShopItem> updateQuery = getDatastore().createQuery(ShopItem.class).field("_id").equal(item.getId());
		UpdateOperations<ShopItem> ops = getDatastore().createUpdateOperations(ShopItem.class);
		ops.set("name", item.getName()).
			set("brand", item.getBrand()).
			set("quantity", item.getQuantity()).
			set("type", item.getType()).
			set("bought", item.isBought()).
			set("note", item.getNote()).
			set("order", item.getOrder());
		update(updateQuery, ops);
	}
	
}
