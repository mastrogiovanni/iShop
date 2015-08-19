package net.mastro.ishop;

import java.net.UnknownHostException;

import net.mastro.ishop.dao.AuthDao;
import net.mastro.ishop.dao.ShopItemDao;
import net.mastro.ishop.model.ShopItem;
import net.mastro.ishop.model.User;

import org.mongodb.morphia.Key;

import com.mongodb.MongoException;

public class Test {

	/**
	 * @param args
	 * @throws MongoException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, MongoException {
		
		AuthDao authDao = Dao.auth();

		// Remove all users
		authDao.deleteByQuery(authDao.createQuery());
		System.out.println(authDao.find().asList());
		
		User user = new User();
		user.setUsername("michele");
		user.setPassword("pippo80");
		Key<User> userKey = authDao.save(user);

		// List of users
		System.out.println(authDao.find().asList());

		ShopItemDao shopItemDao = Dao.shopItem();
		System.out.println(shopItemDao.getShopItems(user));
		
		ShopItem item = new ShopItem();
		item.setUser(userKey);
		item.setName("Name");
		item.setBrand("Brand");
		item.setQuantity(1.0f);
		item.setType("L");
		item.setBought(false);
		item.setNote("Note");
		item.setOrder(1);
		shopItemDao.save(item);
		
		System.out.println(shopItemDao.getShopItems(user));
		
		User u = authDao.getDatastore().getByKey(User.class, shopItemDao.getShopItems(user).get(0).getUser());
		System.out.println(u);
		
	}

}
