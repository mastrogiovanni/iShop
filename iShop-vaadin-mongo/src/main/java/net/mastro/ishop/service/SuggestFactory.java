package net.mastro.ishop.service;


public class SuggestFactory extends Thread {

	// To synchronize multiple calls
	private Object semaphore = new Object();

	// Current worker
	private Thread currentWorker;
	
	// Search
	public void search(SuggestWorker worker, String text, SuggestFoundListener suggestFoundListener) {
		
		synchronized (semaphore) {

			if ( currentWorker != null ) {
				currentWorker.interrupt();
			}

			currentWorker = worker;
			currentWorker.start();

		}

	}
	
}
