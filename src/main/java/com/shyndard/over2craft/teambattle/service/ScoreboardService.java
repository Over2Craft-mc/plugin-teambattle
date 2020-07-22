package com.shyndard.over2craft.teambattle.service;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardService {

	private static ScoreboardService instance;
	private Scoreboard scoreboard;

	public static ScoreboardService getInstance() {
		if (instance == null) {
			instance = new ScoreboardService();
		}
		return instance;
	}

	public ScoreboardService() {
		scoreboard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
		Objective objective = scoreboard.registerNewObjective("SidebarName", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName("Your title");
	}

	public void addTo(Player player) {
		player.setScoreboard(scoreboard);
	}

}
