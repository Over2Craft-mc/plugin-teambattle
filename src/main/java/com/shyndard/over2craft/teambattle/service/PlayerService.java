package com.shyndard.over2craft.teambattle.service;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.util.Vector;

import com.shyndard.over2craft.teambattle.entity.PlayerProfile;
import com.shyndard.over2craft.teambattle.entity.Team;

public class PlayerService {

	private static PlayerService instance;
	private Map<Player, PlayerProfile> playerProfiles = new HashMap<>();

	public Map<Player, PlayerProfile> getPlayerProfiles() {
		return playerProfiles;
	}

	public static PlayerService getInstance() {
		if (instance == null) {
			instance = new PlayerService();
		}
		return instance;
	}

	public void add(Player player) {
		Team team = TeamService.getInstance().affectPlayer(player);
		if (team == null)
			return;
		playerProfiles.put(player, new PlayerProfile(team));
		player.setDisplayName((team.getColor() == Color.RED ? ChatColor.RED : ChatColor.BLUE) + player.getName());
		ScoreboardService.getInstance().addTo(player);
		prepareAndspawnPlayer(player);
	}
	
	private void prepareAndspawnPlayer(Player player) {
		Team team = playerProfiles.get(player).getTeam();
		heal(player);
		equip(player, team);
		player.teleport(team.getSpawn());
		player.sendMessage(ChatColor.GREEN + "Au combat guerrier(re) !");
	}

	public void remove(Player player) {
		PlayerProfile playerProfile = playerProfiles.get(player);
		if (playerProfile == null)
			return;
		TeamService.getInstance().remove(player, playerProfile.getTeam());
		playerProfiles.remove(player);
	}

	private void heal(Player player) {
		player.setVelocity(new Vector(0, 0, 0));
		player.setHealth(20);
		player.setFoodLevel(20);
		player.setArrowsStuck(0);
		player.getInventory().clear();
		player.getActivePotionEffects().clear();
		player.setGameMode(GameMode.SURVIVAL);
	}

	private void equip(Player player, Team team) {
		ItemStack casque = new ItemStack(
				"red".equalsIgnoreCase(team.getName()) ? Material.RED_WOOL : Material.BLUE_WOOL);

		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
		chestplateMeta.setColor(team.getColor());
		chestplateMeta.setUnbreakable(true);
		chestplate.setItemMeta(chestplateMeta);

		ItemStack leggins = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta kambieresMeta = (LeatherArmorMeta) leggins.getItemMeta();
		kambieresMeta.setColor(team.getColor());
		kambieresMeta.setUnbreakable(true);
		leggins.setItemMeta(kambieresMeta);

		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
		bootsMeta.setColor(team.getColor());
		bootsMeta.setUnbreakable(true);
		boots.setItemMeta(bootsMeta);

		ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
		ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.setUnbreakable(true);
		swordMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		sword.setItemMeta(swordMeta);

		ItemStack shovel = new ItemStack(Material.IRON_SHOVEL);
		ItemMeta shovelMeta = shovel.getItemMeta();
		shovelMeta.setUnbreakable(true);
		shovelMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		shovel.setItemMeta(shovelMeta);

		ItemStack bow = new ItemStack(Material.BOW);
		ItemMeta bowMeta = bow.getItemMeta();
		bowMeta.setUnbreakable(true);
		bowMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		bow.setItemMeta(bowMeta);

		ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, 2);
		ItemStack torch = new ItemStack(Material.TORCH, 15);

		player.getInventory().setHelmet(casque);
		player.getInventory().setChestplate(chestplate);
		player.getInventory().setLeggings(leggins);
		player.getInventory().setBoots(boots);
		player.getInventory().setItem(0, sword);
		player.getInventory().setItem(1, bow);
		player.getInventory().setItem(2, apple);
		player.getInventory().setItem(7, torch);
		player.getInventory().setItem(8, shovel);
		player.setNoDamageTicks(100);
		player.updateInventory();
	}

	public void dies(Player player, Player killer) {
		if (killer == null || player == killer) {
			player.sendMessage("Le suicide c'est mal.");
		} else {
			Bukkit.broadcastMessage(killer.getDisplayName() + ChatColor.GRAY + " a tué " + player.getDisplayName());
			playerProfiles.get(killer).addScore();
		}
		player.getInventory().clear();
		player.setGameMode(GameMode.SPECTATOR);
		playerProfiles.put(player, playerProfiles.get(player).dead());
	}

	public void respawn(Player player) {
		playerProfiles.put(player, playerProfiles.get(player).setDead(false));
		prepareAndspawnPlayer(player);
	}

}
