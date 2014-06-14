package me.cxdur.timebasic.commands;

import me.cxdur.timebasic.TimeBasic;

import org.bukkit.ChatColor;
import org.bukkit.WeatherType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeatherCommand implements CommandExecutor {

	private final TimeBasic plugin;
	
	public WeatherCommand(TimeBasic instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command is not supported from console.");
			return true;
		}

		if (!sender.hasPermission("timebasic.weather") && (!sender.isOp())) {
			sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
			return true;
		}	

		Player p = (Player) sender;

		if (args.length == 0) {
			p.sendMessage("---------------- " + ChatColor.GOLD + "Weather Menu " + ChatColor.WHITE + "----------------");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "weather clear " + ChatColor.GRAY + " - set the weather to clear for you.");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "weather downfall " + ChatColor.GRAY + " - set the weather to downfall for you.");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "weather reset " + ChatColor.GRAY + " - reset your weather to world weather.");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "weather info " + ChatColor.GRAY + " - get info about the weather.");
			return true;
		} else if (args.length == 1) {
			switch (args[0].toLowerCase()) {
			case "clear":
				p.setPlayerWeather(WeatherType.CLEAR);
				p.sendMessage(ChatColor.GOLD + "Your weather was set to clear.");
				break;
			case "downfall":
				p.setPlayerWeather(WeatherType.DOWNFALL);
				p.sendMessage(ChatColor.GOLD + "Your weather was set to downfall.");
				break;
			case "reset":
				p.resetPlayerWeather();
				p.sendMessage(ChatColor.GOLD + "Your weather has been reset to world weather.");
				break;
			case "info":
				if (p.getPlayerWeather() == WeatherType.CLEAR) {
					p.sendMessage(ChatColor.GOLD + "Your weather is currently set to clear.");
				} else {
					p.sendMessage(ChatColor.GOLD + "Your weather is currently set to downfall.");
				}
				break;
			default:
				p.sendMessage(ChatColor.RED + "Unknown argument, sending you to the menu.");
				p.performCommand("weather");
				break;
			}
			return true;
		} else {
			p.sendMessage(ChatColor.RED + "Unknown argument, sending you to the menu.");
			p.performCommand("weather");
			return true;
		}
	}
}