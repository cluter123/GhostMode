package me.cluter.passivemode;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(new pluginListener(this) , this);
		commandEnable();
	}
	ArrayList<String> enabled = new ArrayList<String>();
	ArrayList<String> cd = new ArrayList<String>();
	
	public void commandEnable() {
		commands cl = new commands(this);
		getCommand("ghostmode").setExecutor(cl);
	}

}
