package net.mastro.ishop.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public abstract class SuggestWorker extends Thread {

	private SuggestFoundListener suggestFoundListener;

	private String text;

	protected abstract List<String> compute(String text);	

	private void notify(List<String> list) {
		suggestFoundListener.found(text, list);	
	}
	
	private void release() {
		suggestFoundListener.found(text, new ArrayList<String>());	
	}

	public void search(String text, SuggestFoundListener suggestFoundListener) {
		
		this.text = text;
		this.suggestFoundListener = suggestFoundListener;
		
		if ( Thread.interrupted() ) {
			release();
			return;
		}

		if (StringUtils.isBlank(text)) {
			release();
			return;
		}

		if (text.length() <= 3) {
			release();
			return;
		}

		if ( Thread.interrupted() ) {
			release();
			return;
		}

		List<String> list = compute(text);

		if ( list.size() <= 0 ) {
			release();
			return;
		}

		if ( Thread.interrupted() ) {
			//			System.out.println("interrupt 3");
			release();
			return;
		}

		notify(list);

	}

}
