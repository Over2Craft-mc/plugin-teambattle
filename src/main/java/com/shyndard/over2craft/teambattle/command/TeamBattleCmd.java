package com.shyndard.over2craft.teambattle.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.shyndard.over2craft.teambattle.service.TeamService;

public class TeamBattleCmd implements CommandExecutor {

	private static final String noPermission = "No permission.";
	private static final String playerOnly = "Player command only.";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 2 && args[0].equalsIgnoreCase("setspawn")) {
			if (!sender.hasPermission("teambattle.admin")) {
				sender.sendMessage(noPermission);
				return true;
			}
			if (!(sender instanceof Player)) {
				sender.sendMessage(playerOnly);
				return true;
			}
			if ("red".equalsIgnoreCase(args[1]) || "blue".equalsIgnoreCase(args[1])) {
				Player player = (Player) sender;
				TeamService.getInstance().setSpawn(args[1], player.getLocation());
				sender.sendMessage(ChatColor.GREEN + args[1] + " team spawn set.");
			} else {
				sender.sendMessage("Usage : /tb setspawn <red|blue>");
			}
			return true;
		} else if (args.length == 3 && args[0].equalsIgnoreCase("setpoint")) {
			if (!sender.hasPermission("teambattle.admin")) {
				sender.sendMessage(noPermission);
				return true;
			}
			if (!"red".equalsIgnoreCase(args[1]) && !"blue".equalsIgnoreCase(args[1])) {
				sender.sendMessage("Usage : /tb setlives <red|blue> <lives>");
			}
			try {
				int point = Integer.parseInt(args[2]);
				TeamService.getInstance().setPoint(args[1], point);
				sender.sendMessage(ChatColor.GREEN + args[1] + " team point set.");
			} catch (Exception ex) {
				sender.sendMessage("Param 'lives' need to be an integer.");
			}
			return true;
		}
		return false;
	}
}