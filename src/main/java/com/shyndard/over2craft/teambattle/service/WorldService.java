package com.shyndard.over2craft.teambattle.service;

import org.bukkit.Bukkit;

public class WorldService {

	private static WorldService instance;

	public static WorldService getInstance() {
		if (instance == null) {
			instance = new WorldService();
		}
		return instance;
	}

	public void reset() {
		Bukkit.setWhitelist(true);
		// TODO : Reset world
	}

}
