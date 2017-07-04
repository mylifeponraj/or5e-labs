package org.or5e.hma.controller;

import org.or5e.hma.messages.HeartbeatRQ;
import org.or5e.hma.messages.HeartbeatRS;

public class HeartbeatServiceSPI implements HeartbeatService {

	@Override public HeartbeatRS updateHealth(HeartbeatRQ req) {
		return new HeartbeatRS();
	}


}
