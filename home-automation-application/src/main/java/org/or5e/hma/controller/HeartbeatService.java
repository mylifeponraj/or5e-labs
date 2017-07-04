package org.or5e.hma.controller;

import org.or5e.hma.messages.HeartbeatRQ;
import org.or5e.hma.messages.HeartbeatRS;

public interface HeartbeatService {
	public HeartbeatRS updateHealth(HeartbeatRQ req);
}
