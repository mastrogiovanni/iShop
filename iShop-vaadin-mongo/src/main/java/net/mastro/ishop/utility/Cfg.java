package net.mastro.ishop.utility;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class Cfg {
	
	public static final String MONGO_HOST = "mongo.host";
	public static final String MONGO_PORT = "mongo.port";
	public static final String MONGO_AUTH = "mongo.auth";
	public static final String MONGO_USERNAME = "mongo.username";
	public static final String MONGO_PASSWORD = "mongo.password";
	
	public static final String DEBUG_USE_DEFAULT_USER = "debug.use.default.user";
	public static final String DEBUG_DEFAULT_USER = "debug.default.user";
	public static final String DEBUG_DEFAULT_PASS = "debug.default.pass";
	public static final String DEBUG_DISABLE_REST_AUTHENTICATION = "debug.disable.rest.authentication";

	private static Cfg cfg = new Cfg();
	
	private Properties props = new Properties();

	private Cfg() {
		try {
			props.load(Cfg.class.getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean present(String key) {
		return ! StringUtils.isBlank(get(key));
	}
	
	public static String get(String key) {
		return cfg.props.getProperty(key);
	}
	
	public static int getInt(String key, int defaultValue) {
		String value = get(key);
		try {
			return Integer.parseInt(value);
		}
		catch (NumberFormatException e) {
		}
		return defaultValue;
	}

	public static boolean getBoolean(String key) {
		String value = get(key);
		return Boolean.parseBoolean(value);
	}

	public static String get(String key, String defaultValue) {
		String value = get(key);
		if (StringUtils.isEmpty(value) && ! StringUtils.isEmpty(defaultValue))
			return defaultValue;
		return value;
	}

	public static Properties all() {
		return cfg.props;
	}

}
