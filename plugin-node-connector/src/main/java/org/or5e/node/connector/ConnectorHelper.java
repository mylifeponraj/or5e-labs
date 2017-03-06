package org.or5e.node.connector;

import java.util.Set;

import org.or5e.core.BaseObject;
import org.reflections.Reflections;

public class ConnectorHelper extends BaseObject {
	public void loadAllProcessors () {
		Reflections reflections = new Reflections("org.or5e.node.connector.processors");
		Set<Class<? extends Processors>> processors = reflections.getSubTypesOf(Processors.class);
		for (Class<? extends Processors> processor : processors) {
			try {
				Processors processorInstance = processor.newInstance();
				System.out.println(processorInstance);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	@Override public String getName() {
		return "ConnectorHelper";
	}
	
	public static void main(String[] args) {
		new ConnectorHelper().loadAllProcessors();
	}

}
