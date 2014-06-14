package me.cxdur.timebasic.commands;

import me.cxdur.timebasic.TimeBasic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminTimeCommand implements CommandExecutor {

	private final TimeBasic plugin;

	public AdminTimeCommand(TimeBasic instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You cannot use this command from console.");
			return true;
		}

		if (!sender.hasPermission("timebasic.adtime") && (!sender.isOp())) {
			sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
			return true;
		}

		Player p = (Player) sender;

		if (args.length == 0) {
			p.sendMessage("---------------- " + ChatColor.GOLD + "Admin time Menu " + ChatColor.WHITE + "----------------");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "adtime day " + ChatColor.GRAY + " - set the time to day in your world.");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "adtime night " + ChatColor.GRAY + " - set the time to night in your world.");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "adtime morning " + ChatColor.GRAY + " - set the time to morning in your world.");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "adtime evening " + ChatColor.GRAY + " - set the time to evening in your world.");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "adtime reset " + ChatColor.GRAY + " - reset everyone to servertime.");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "adtime info " + ChatColor.GRAY + " - gets the time in your world.");
			return true;
		} else if (args.length == 1) {
			switch (args[0].toLowerCase()) {
			case "day":
				p.getWorld().setTime(6000);
				p.sendMessage(ChatColor.GOLD + "Time in your world was set to day!");
				plugin.print(p.getName() + " just set the time in " + p.getWorld().getName() + " to day.");
				break;
			case "night":
				p.getWorld().setTime(14000);
				p.sendMessage(ChatColor.GOLD + "Time in your world was set to night!");
				plugin.print(p.getName() + " just set the time in " + p.getWorld().getName() + " to night.");
				break;
			case "morning":
				p.getWorld().setTime(0);
				p.sendMessage(ChatColor.GOLD + "Time in your world was set to morning!");
				plugin.print(p.getName() + " just set the time in " + p.getWorld().getName() + " to morning.");
				break;
			case "evening":
				p.getWorld().setTime(13000);
				p.sendMessage(ChatColor.GOLD + "Time in your world was set to evening!");
				plugin.print(p.getName() + " just set the time in " + p.getWorld().getName() + " to evening.");
				break;
			case "reset":
				for (Player online: Bukkit.getOnlinePlayers()) {
					online.resetPlayerTime(); 
				}
				p.sendMessage(ChatColor.GOLD + "Everyone was reset to servertime!");
				plugin.print(p.getName() + " just reset everyone to servertime.");
				break;
			case "info":
				p.sendMessage(ChatColor.GOLD + "The current time in your world is: " + ChatColor.GRAY + p.getWorld().getTime() + ChatColor.GOLD + ".");
				break;
			default:
				p.sendMessage(ChatColor.RED + "Unknown argument. Sending you to the menu.");
				p.performCommand("adtime");
				break;
			}
			return true;
		} else {
			p.sendMessage(ChatColor.RED + "Unknown argument. Sending you to the menu.");
			p.performCommand("adtime");
			return true;
		}
	}
}