package com.kamesuta.mc.worldpictures.repository;

import com.kamesuta.mc.worldpictures.proxy.ClientProxy;

public class Agent {
	public final String id;
	public final String token;
	public Agent(String id, String token) {
		this.id = id;
		this.token = token;
	}

	public static Agent getCurrentAgent() {
		return new Agent(
			ClientProxy.MINECRAFT.getSession().getPlayerID(),
			ClientProxy.MINECRAFT.getSession().getToken()
		);
	}
}
