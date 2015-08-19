package net.mastro.ishop.dao;

import net.mastro.ishop.model.User;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;

public class AuthDao extends AbstractDao<User, ObjectId> {

	public AuthDao(MongoClient mongo, Morphia morphia) {
		super(mongo, morphia, "dematerializzazione");
		morphia.map(User.class);
	}

	public User authorize(String username, String password) {
		Query<User> query = createQuery().field("username").equal(username).field("password").equal(password);
		return find(query).get();
	}
	
	public void register(String username, String password) {
		if ( authorize(username, password) != null )
			return;
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		save(user);
	}
	
}
