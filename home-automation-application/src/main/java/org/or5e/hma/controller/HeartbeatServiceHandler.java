package org.or5e.hma.controller;

import org.or5e.hma.messages.HeartbeatRQ;
import org.or5e.hma.messages.HeartbeatRS;
import org.springframework.beans.factory.annotation.Autowired;

public class HeartbeatServiceHandler extends AbstractController<HeartbeatRQ, HeartbeatRS> {
	private HeartbeatService _service;

	@Autowired
	public HeartbeatServiceHandler(HeartbeatService serivce) {
		super(HeartbeatRQ.class);
		this._service = serivce;
	}

	@Override
	public HeartbeatRS requrestReceived(HeartbeatRQ req) {
		System.out.println("Request Received: "+req);
		HeartbeatRS response = _service.updateHealth(req);
		return response;
	}

	
}
