package net.mastro.ishop;

import java.util.List;

import net.mastro.ishop.service.SuggestFactory;
import net.mastro.ishop.service.SuggestFoundListener;
import net.mastro.ishop.service.SuggestShopItemName;

public class TestSuggest implements SuggestFoundListener {
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		SuggestFactory factory = new SuggestFactory();
		
		factory.search(new SuggestShopItemName(), "fusill", new TestSuggest());
		
		factory.search(new SuggestShopItemName(), "pane", new TestSuggest());
		
	}

	@Override
	public void found(String text, List<String> list) {
		System.out.println("Found for: " + text + ": " + list);
	}

}
