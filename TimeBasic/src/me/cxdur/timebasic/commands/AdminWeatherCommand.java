package me.cxdur.timebasic.commands;

import me.cxdur.timebasic.TimeBasic;

import org.bukkit.ChatColor;
import org.bukkit.WeatherType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminWeatherCommand implements CommandExecutor {

private final TimeBasic plugin;
	
	public AdminWeatherCommand(TimeBasic instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command is not supported from console.");
			return true;
		}

		if (!sender.hasPermission("timebasic.adweather") && (!sender.isOp())) {
			sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
			return true;
		}	

		Player p = (Player) sender;

		if (args.length == 0) {
			p.sendMessage("---------------- " + ChatColor.GOLD + "Admin Weather Menu " + ChatColor.WHITE + "----------------");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "adweather clear " + ChatColor.GRAY + " - set the weather to clear in your world.");
			p.sendMessage(ChatColor.GOLD + "/" + ChatColor.GREEN + "adweather downfall " + ChatColor.GRAY + " - set the weather to downfall in your world.");
			return true;
		} else if (args.length == 1) {
			switch (args[0].toLowerCase()) {
			case "clear":
				p.setPlayerWeather(WeatherType.CLEAR);
				p.sendMessage(ChatColor.GOLD + "Weather was set to clear in your world.");
				break;
			case "downfall":
				p.setPlayerWeather(WeatherType.DOWNFALL);
				p.sendMessage(ChatColor.GOLD + "Weather was set to downfall in your world.");
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