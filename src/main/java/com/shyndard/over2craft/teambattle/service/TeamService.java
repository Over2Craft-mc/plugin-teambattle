package com.shyndard.over2craft.teambattle.service;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.shyndard.over2craft.teambattle.MainPlugin;
import com.shyndard.over2craft.teambattle.entity.Team;

public class TeamService {

	private static TeamService instance;
	private Map<String, Team> teams = new HashMap<>();

	public static TeamService getInstance() {
		if (instance == null) {
			instance = new TeamService();
		}
		return instance;
	}

	public void init() {
		loadTeam("red");
		loadTeam("blue");
	}

	public Team affectPlayer(Player player) {
		Team affectedTeam = null;
		for (Team team : teams.values()) {
			if (affectedTeam == null || team.getPlayers().size() < affectedTeam.getPlayers().size()) {
				affectedTeam = team;
			}
		}
		if(affectedTeam == null) {
			player.sendMessage(ChatColor.RED + "Aucune équipe configurée.");
			return null;
		}
		teams.get(affectedTeam.getName()).getPlayers().add(player);
		Bukkit.broadcastMessage(ChatColor.GRAY + player.getName() + ChatColor.YELLOW + " a rejoint l'équipe " + ChatColor.RED + affectedTeam.getName());
		return affectedTeam;
	}

	public void remove(Player player, Team team) {
		teams.get(team.getName()).getPlayers().remove(player);
	}

	public void setPoint(String teamName, int point) {
		teams.get(teamName.toLowerCase()).setScore(point);
	}
	
	private void loadTeam(String name) {
		Team team = new Team(name);
		World world = Bukkit.getWorld(MainPlugin.getInstance().getConfig().getString("location.spawn." + name + ".world"));
		double x = MainPlugin.getInstance().getConfig().getDouble("location.spawn." + name + ".x");
		double y = MainPlugin.getInstance().getConfig().getDouble("location.spawn." + name + ".y");
		double z = MainPlugin.getInstance().getConfig().getDouble("location.spawn." + name + ".z");
		Double yaw = MainPlugin.getInstance().getConfig().getDouble("location.spawn." + name + ".yaw");
		Double pitch = MainPlugin.getInstance().getConfig().getDouble("location.spawn." + name + ".pitch");
		team.setSpawn(new Location(world, x, y, z, yaw.floatValue(), pitch.floatValue()));
		teams.put(name, team);
	}

	public void setSpawn(String teamName, Location location) {
		teamName = teamName.toLowerCase();
		MainPlugin.getInstance().getConfig().set("location.spawn." + teamName + ".world",
				location.getWorld().getName());
		MainPlugin.getInstance().getConfig().set("location.spawn." + teamName + ".x", location.getBlockX() + 0.5);
		MainPlugin.getInstance().getConfig().set("location.spawn." + teamName + ".y", location.getY());
		MainPlugin.getInstance().getConfig().set("location.spawn." + teamName + ".z", location.getBlockZ() + 0.5);
		MainPlugin.getInstance().getConfig().set("location.spawn." + teamName + ".pitch", location.getPitch());
		MainPlugin.getInstance().getConfig().set("location.spawn." + teamName + ".yaw", location.getYaw());
		MainPlugin.getInstance().saveConfig();
		init();
	}

}
