package me.cxdur.timebasic.commands;

import me.cxdur.timebasic.TimeBasic;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeCommand implements CommandExecutor {

	private final TimeBasic plugin;
	
	public TimeCommand(TimeBasic instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command cannot be used from console.");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (!p.hasPermission("timebasic.time") && (!sender.isOp())) {
			p.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
			return true;
		}
		
		if (args.length == 0) {
			p.sendMessage("---------------- " + ChatColor.GOLD + "Time Menu " + ChatColor.WHITE + "----------------");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "time day " + ChatColor.GRAY + " - set the time to day for you.");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "time night " + ChatColor.GRAY + " - set the time to night for you.");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "time morning " + ChatColor.GRAY + " - set the time to morning for you.");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "time evening " + ChatColor.GRAY + " - set the time to evening for you.");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "time reset " + ChatColor.GRAY + " - set your time to servertime.");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "time info " + ChatColor.GRAY + " - gets your current time and the server time.");
			return true;
		} else if (args.length == 1) {
			switch (args[0].toLowerCase()) {
				case "day":
					p.setPlayerTime(6000, false);
					p.sendMessage(ChatColor.GOLD + "Your time was set to day!");
					break;
				case "night":
					p.setPlayerTime(14000, false);
					p.sendMessage(ChatColor.GOLD + "Your time was set to night!");
					break;
				case "morning":
					p.setPlayerTime(0, false);
					p.sendMessage(ChatColor.GOLD + "Your time was set to morning!");
					break;
				case "evening":
					p.setPlayerTime(13000, false);
					p.sendMessage(ChatColor.GOLD + "Your time was set to evening!");
					break;
				case "reset":
					p.resetPlayerTime();
					p.sendMessage(ChatColor.GOLD + "Your time was reset to server time!");
					break;
				case "info":
					p.sendMessage(ChatColor.GOLD + "There is a " + ChatColor.GRAY + p.getPlayerTimeOffset() + ChatColor.GOLD + " tick difference between your time and server time.");
					break;
				default:
					p.sendMessage(ChatColor.RED + "Unknown argument. Sending you to the menu.");
					p.performCommand("time");
					break;
			}
			return true;
		} else {
			p.sendMessage(ChatColor.RED + "Unknown argument. Sending you to the menu.");
			p.performCommand("time");
			return true;
		}
	}
}