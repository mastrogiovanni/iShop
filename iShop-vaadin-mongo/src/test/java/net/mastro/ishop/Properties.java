package net.mastro.ishop;

import java.io.IOException;

import net.mastro.ishop.utility.Cfg;

public class Properties {

	public static void main(String[] args) throws IOException {
		
		System.out.println(Cfg.get("mongo.port"));
		
	}

}
