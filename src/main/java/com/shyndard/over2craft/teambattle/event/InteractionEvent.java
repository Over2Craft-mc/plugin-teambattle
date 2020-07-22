package com.shyndard.over2craft.teambattle.event;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class InteractionEvent implements Listener {

	private static final List<Material> WHITELIST_BLOCK = Arrays
			.asList(new Material[] { Material.DIRT, Material.GRASS_BLOCK, Material.TORCH });

	@EventHandler
	public void breakBlock(BlockBreakEvent event) {
		if (!WHITELIST_BLOCK.contains(event.getBlock().getType())) {
			event.setCancelled(true);
		}
	}
}
