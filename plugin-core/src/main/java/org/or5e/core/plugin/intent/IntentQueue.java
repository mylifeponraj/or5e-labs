package org.or5e.core.plugin.intent;

public interface IntentQueue {
	public void createQueue(String queueName);
	public void removeQueue(String queueName);
	public void registerForIntent(String... intentNameList);
	public void listenToIntent(ConsumeIntent intentHandler, String... intentNameList);
	public void raiseIntentToDefaultQueue(String intentName, Object intentMessage);
	public void raiseIntentToQueue(String intentName, Object intentMessage, String queueName);
	public void stopIntentQueue();
}
