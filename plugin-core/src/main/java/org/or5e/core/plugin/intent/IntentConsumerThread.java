package org.or5e.core.plugin.intent;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.or5e.core.BaseObject;

public class IntentConsumerThread extends BaseObject implements Runnable {
	private BlockingQueue<Intent> defaultQueue;
	Map<String, List<ConsumeIntent>> consumerIntentMap;
	public IntentConsumerThread(BlockingQueue<Intent> defaultQueue, Map<String, List<ConsumeIntent>> consumerIntentMap) {
		this.defaultQueue = defaultQueue;
		this.consumerIntentMap = consumerIntentMap;
	}

	public Boolean runConsumerThread = Boolean.TRUE;
	public volatile int delay = 2;

	@Override public void run() {
		debug("Running the consumer Thread.");
		while(runConsumerThread) {
			try {
				Intent take = this.defaultQueue.poll(delay, TimeUnit.SECONDS);
				if(take != null && consumerIntentMap.containsKey(take.getIntentName())) {
					consumerIntentMap.get(take.getIntentName()).forEach(consumeIntent -> {
						debug("Got a Event "+take.getIntentName()+" Calling the handler."+consumeIntent);
							consumeIntent.consumeIntent(take);
					});
				}
			} catch (InterruptedException e) {
				debug("IMPORTANT: Consumer Thread Stopped...");
				e.printStackTrace();
			}
		}
		debug("Exiting consumer Thread...");
	}
	public void stopThread() {
		this.runConsumerThread = Boolean.FALSE;
		this.delay = 1;
//		Thread.currentThread().interrupt();
	}
	@Override public String getName() {
		return "org.or5e.core.plugin.intent.IntentConsumerThread";
	}
}