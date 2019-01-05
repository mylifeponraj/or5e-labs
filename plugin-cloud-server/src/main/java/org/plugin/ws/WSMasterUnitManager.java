package org.plugin.ws;

import java.util.Map;

public class WSMasterUnitManager {
	private Map<String, MasterUnit> activeMasterUnit;
	private Map<String, MasterUnit> deactiveMasterUnit;
	public void registerMasterUnit(String masterUnitID, MasterUnit unit) {
		if(deactiveMasterUnit.containsKey(masterUnitID)) {
			deactiveMasterUnit.remove(masterUnitID);
		}
		activeMasterUnit.put(masterUnitID, unit);
	}
}
