package com.shyndard.over2craft.teambattle.entity;

public class PlayerProfile {

	private Team team;
	private int score;
	private boolean dead;
	private long lastDeadTime;

	public PlayerProfile(Team team) {
		this.team = team;
		this.score = 0;
		this.dead = false;
		this.lastDeadTime = 0;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore() {
		score++;
	}

	public boolean isDead() {
		return dead;
	}

	public PlayerProfile setDead(boolean dead) {
		this.dead = dead;
		return this;
	}

	public long getLastDeadTime() {
		return lastDeadTime;
	}

	public void setLastDeadTime(long lastDeadTime) {
		this.lastDeadTime = lastDeadTime;
	}

	public PlayerProfile dead() {
		dead = true;
		lastDeadTime = System.currentTimeMillis()/1000;
		return this;
	}

}
