package org.or5e.core.plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.or5e.core.BaseObject;

public class PluginEventQueue extends BaseObject {
	private Map<String, List<PluginEventHandler>> _listeners = null;

	public PluginEventQueue() {
		_listeners = new HashMap<String, List<PluginEventHandler>>();
	}

	public void addListener(PluginEventHandler _handler, String... listenToCommands) {

	}

	public Set<String> getAllCommandsListened() {
		return _listeners.keySet();
	}

	@Override public String getName() {
		return "PluginEventQueue";
	}
}