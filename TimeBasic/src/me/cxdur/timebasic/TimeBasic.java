package me.cxdur.timebasic;

import me.cxdur.timebasic.commands.AdminTimeCommand;
import me.cxdur.timebasic.commands.AdminWeatherCommand;
import me.cxdur.timebasic.commands.TimeCommand;
import me.cxdur.timebasic.commands.WeatherCommand;

import org.bukkit.plugin.java.JavaPlugin;

public class TimeBasic extends JavaPlugin {

	@Override
	public void onEnable() {
		registerCommands();
		print("Plugin has been enabled.");
	}

	@Override
	public void onDisable() {
		print("Plugin has been disabled.");
	}
	
	public void registerCommands() {
		getCommand("time").setExecutor(new TimeCommand(this));
		getCommand("adtime").setExecutor(new AdminTimeCommand(this));
		getCommand("weather").setExecutor(new WeatherCommand(this));
		getCommand("adweather").setExecutor(new AdminWeatherCommand(this));
		print("Commands have been registered.");
	}
	
	public void print(String message) {
		System.out.println("[TimeBasic] " + message);
	}
}
