package net.mastro.ishop.dao;

import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import com.mongodb.MongoClient;

public class AbstractDao<T, U> extends BasicDAO<T, U> {
	
	protected AbstractDao(MongoClient mongo, Morphia morphia, String dbName) {
		super(mongo, morphia, dbName);
	}

}
