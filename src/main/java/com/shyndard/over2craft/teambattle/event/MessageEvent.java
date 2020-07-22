package com.shyndard.over2craft.teambattle.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.md_5.bungee.api.ChatColor;

public class MessageEvent implements Listener {

	@EventHandler
	public void onPlayerJoin(AsyncPlayerChatEvent event) {
		event.setFormat( String.format("%s" + ChatColor.GRAY + ":" + ChatColor.WHITE + " %s", event.getPlayer().getDisplayName(), event.getMessage()));
	}

}
