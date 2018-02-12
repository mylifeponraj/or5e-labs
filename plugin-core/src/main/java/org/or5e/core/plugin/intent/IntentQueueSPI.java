package org.or5e.core.plugin.intent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.or5e.core.BaseObject;

public class IntentQueueSPI extends BaseObject implements IntentQueue {
	private static Map<String, BlockingQueue<Intent>> queueMap;
	private BlockingQueue<Intent> defaultQueue;
	private Map<String, List<ConsumeIntent>> consumerIntentMap;
	private Map<String, IntentConsumerThread> consumerThreadMap;
	
	private static IntentQueue intendQueue;
	static {
		queueMap = new HashMap<>();
	}
	private IntentQueueSPI() {
		debug("Initilizing the Queue API,");
		defaultQueue = new ArrayBlockingQueue<Intent>(20);
		consumerThreadMap = new HashMap<>();
		consumerIntentMap = new HashMap<>();
		IntentConsumerThread defaultDeQueue = new IntentConsumerThread(defaultQueue, consumerIntentMap);
		queueMap.put("default", defaultQueue);
		consumerThreadMap.put("default", defaultDeQueue);
		debug("About the start the Queue consumer thread.");
		Thread consumeThread = new Thread(defaultDeQueue);
		consumeThread.start();
	}
	public static IntentQueue getIntentQueue() {
		if(intendQueue == null) {
			intendQueue = new IntentQueueSPI();
		}
		return intendQueue;
	}
	@Override public String getName() {
		return "org.or5e.core.plugin.intent.IntentQueueSPI";
	}
	@Override public void createQueue(String queueName) {
		if(!queueMap.containsKey(queueName)) {
			BlockingQueue<Intent> queue = new ArrayBlockingQueue<Intent>(20);
			queueMap.put(queueName, queue);
			IntentConsumerThread deQueue = new IntentConsumerThread(defaultQueue, consumerIntentMap);
			consumerThreadMap.put(queueName, deQueue);
			Thread consumeThread = new Thread(deQueue);
			consumeThread.start();
		}
	}
	@Override public void removeQueue(String queueName) {
		if(queueMap.containsKey(queueName)) {
			BlockingQueue<Intent> remove = queueMap.remove(queueName);
			consumerThreadMap.get(queueName).stopThread();
			remove.clear();
		}		
	}
	@Override public synchronized void registerForIntent(String... intentNameList) {
		int len = intentNameList.length;
		for(int index=0 ; index < len ; index ++) {
			String intentName = intentNameList[index];
			if(!consumerIntentMap.containsKey(intentName)) {
				this.consumerIntentMap.put(intentName, new LinkedList<ConsumeIntent>());
			}
		}
	}
	@Override public synchronized void listenToIntent(ConsumeIntent intentHandler, String... intentNameList) {
		for(int index=0 ; index <= intentNameList.length ; index ++) {
			String intentName = intentNameList[index];
			if(consumerIntentMap.containsKey(intentName)) {
				consumerIntentMap.get(intentName).add(intentHandler);
			}
			else {
				List<ConsumeIntent> contentList = new LinkedList<>();
				contentList.add(intentHandler);
				this.consumerIntentMap.put(intentName, contentList);
			}
		}
	}
	@Override public void stopIntentQueue() {
		consumerThreadMap.forEach((k,v) -> {
			IntentConsumerThread intentThread = (IntentConsumerThread) v;
			intentThread.stopThread();
		});
	}
	@Override public synchronized void raiseIntentToDefaultQueue(String intentName, Object intentMessage) {
		debug("Raising Intent: "+intentName);
		defaultQueue.add(new Intent(intentName, intentMessage));
	}
	@Override public void raiseIntentToQueue(String intentName, Object intentMessage, String queueName) {
		debug("Raising Intent: "+intentName);
		if(queueMap.containsKey(queueName)) {
			queueMap.get(queueName).add(new Intent(intentName, intentMessage));
		}
	}
	public static void main(String[] args)  {
		IntentQueue queue = IntentQueueSPI.getIntentQueue();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		queue.stopIntentQueue();
	}

}