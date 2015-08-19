package net.mastro.ishop;

import net.mastro.ishop.model.ShopItem;
import net.mastro.ishop.model.User;
import net.mastro.ishop.utility.Mapper;

import org.bson.types.BasicBSONList;

public class TestBSon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		User user = Dao.auth().authorize("michele", "pippo80");
		
		BasicBSONList list = new BasicBSONList();
		for ( ShopItem item : Dao.shopItem().getShopItems(user)) {
			list.add(Mapper.map(item));
		}
				
		System.out.println(list);

	}

}
