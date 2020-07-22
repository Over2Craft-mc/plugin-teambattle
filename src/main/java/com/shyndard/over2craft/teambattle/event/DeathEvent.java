package com.shyndard.over2craft.teambattle.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.shyndard.over2craft.teambattle.service.PlayerService;

public class DeathEvent implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		event.getDrops().clear();
		PlayerService.getInstance().dies(event.getEntity(), event.getEntity().getKiller());
		event.setDeathMessage(null);
		event.setCancelled(true);
	}
	
}
