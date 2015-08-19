package net.mastro.ishop.utility;

import net.mastro.ishop.model.ShopItem;

import org.bson.BSONObject;
import org.bson.BasicBSONObject;

public class Mapper {
	
	public static BSONObject map(ShopItem item) {
		BSONObject object = new BasicBSONObject();
		object.put("id", item.getId().toString());
		object.put("name", item.getName());
		object.put("brand", item.getBrand());
		object.put("quantity", item.getQuantity());
		object.put("type", item.getType());
		object.put("bought", item.isBought());
		object.put("note", item.getNote());
		return object;
	}
	
}
