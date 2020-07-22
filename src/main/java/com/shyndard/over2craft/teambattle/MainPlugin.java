package com.shyndard.over2craft.teambattle;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.shyndard.over2craft.teambattle.command.TeamBattleCmd;
import com.shyndard.over2craft.teambattle.event.ConnectionEvent;
import com.shyndard.over2craft.teambattle.event.DeathEvent;
import com.shyndard.over2craft.teambattle.event.InteractionEvent;
import com.shyndard.over2craft.teambattle.event.MessageEvent;
import com.shyndard.over2craft.teambattle.service.TeamService;

public class MainPlugin extends JavaPlugin {

	private static MainPlugin instance;

	public static MainPlugin getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;

		// Init config file
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();

		// Init services
		TeamService.getInstance().init();

		// Init event
		getServer().getPluginManager().registerEvents(new ConnectionEvent(), this);
		getServer().getPluginManager().registerEvents(new InteractionEvent(), this);
		getServer().getPluginManager().registerEvents(new DeathEvent(), this);
		getServer().getPluginManager().registerEvents(new MessageEvent(), this);
		
		// Register command
		this.getCommand("tb").setExecutor(new TeamBattleCmd());
		
		// Init scheduler
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new AutoExecute(), 0L, 20L);
	}

	@Override
	public void onDisable() {

	}

}