package com.shyndard.over2craft.teambattle.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

import com.shyndard.over2craft.teambattle.service.PlayerService;

public class ConnectionEvent implements Listener {

	private static final String KICK_WHITELIST = "Server in preparation";
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		PlayerService.getInstance().add(event.getPlayer());
		event.setJoinMessage(null);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		PlayerService.getInstance().remove(event.getPlayer());
		event.setQuitMessage(null);
	}
	
	@EventHandler
	public void onPlayerPreConnect(PlayerLoginEvent event) {
		if(event.getResult() == Result.KICK_WHITELIST) {
			event.setKickMessage(KICK_WHITELIST);
		}
	}
}
