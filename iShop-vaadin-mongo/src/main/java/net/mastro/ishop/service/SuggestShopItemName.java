package net.mastro.ishop.service;

import java.util.List;

import net.mastro.ishop.Dao;


public class SuggestShopItemName extends SuggestWorker {
	
	@Override
	protected List<String> compute(String text) {
		return Dao.shopItem().suggestName(text);
	}
		
}
