package com.shyndard.over2craft.teambattle.entity;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Team {

	private String name;
	private int score = 0;
	private Location spawn;
	private List<Player> players = new ArrayList<>();

	public Team(String name) {
		this.name = name;
		this.score = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public Location getSpawn() {
		return spawn;
	}
	
	public void setSpawn(Location spawn) {
		this.spawn = spawn;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Color getColor() {
		if("blue".equalsIgnoreCase(name)) {
			return Color.BLUE;
		}
		if("red".equalsIgnoreCase(name)) {
			return Color.RED;
		}
		if("orange".equalsIgnoreCase(name)) {
			return Color.ORANGE;
		}
		if("green".equalsIgnoreCase(name)) {
			return Color.GREEN;
		}
		return Color.WHITE;
	}
}
