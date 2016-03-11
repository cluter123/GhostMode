package me.cluter.passivemode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commands implements CommandExecutor {
	Main pl;

	public commands(Main instance) {
		pl = instance;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players may use this command.");
			return false;
		}
		Player p = (Player) sender;
		if (label.equalsIgnoreCase("ghostmode")) {
			if (!(p.hasPermission("gm.use") || p.hasPermission("gm.admin"))) {
				p.sendMessage(ChatColor.RED + "You do not have permission to use GhostMode.");
				return false;
			}
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED
						+ "You must specify an argument. Use </ghostmode help> to list all the arguments.");
				return false;
			}
			if (args[0].equalsIgnoreCase("help")) {
				p.sendMessage(ChatColor.GREEN + "GhostMode commands:");
				p.sendMessage(ChatColor.GREEN + "/ghostmode on - Turns GhostMode on.");
				p.sendMessage(ChatColor.GREEN + "/ghostmode off - Turns GhostMode off.");
				if (p.hasPermission("gm.admin")) {
					p.sendMessage(ChatColor.GREEN + "/ghostmode list - Lists who is currently in ghostmode");
					p.sendMessage(ChatColor.GREEN + "/ghostmode toggle <player> - Toggles GhostMode for a player.");
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("on")) {
				if (pl.cd.contains(p.getName())) {
					p.sendMessage(
							ChatColor.RED + "This command is still on cooldown. (You may use it every 10 seconds)");
					return false;
				}
				if (pl.enabled.contains(p.getName())) {
					p.sendMessage(ChatColor.RED + "You already have GhostMode enabled!");
					return false;
				} else {
					pl.enabled.add(p.getName());
					p.sendMessage(ChatColor.GREEN + "You have enabled GhostMode.");

					return true;
				}
			}
			if (args[0].equalsIgnoreCase("off")) {
				if (pl.enabled.contains(p.getName())) {
					pl.enabled.remove(p.getName());
					p.sendMessage(ChatColor.GREEN + "You have disabled GhostMode.");
					if (p.hasPermission("gm.cooldown.bypass")) {
						return true;
					}
					else {
					pl.cd.add(p.getName());
					Bukkit.getScheduler().scheduleAsyncDelayedTask(pl, new Runnable() {
						public void run() {
							pl.cd.remove(p.getName());
						}
					}, 200);
						return true;
					}
				}
				 else {
					p.sendMessage(ChatColor.RED + "You do not have GhostMode enabled!");
					return false;
				}
			}
			
			if (args[0].equalsIgnoreCase("list")) {
				if (!p.hasPermission("gm.admin")) {
					p.sendMessage(ChatColor.RED + "You do not have permission to do this.");
					return false;
				}
				p.sendMessage(ChatColor.GREEN + "Players with GhostMode enabled:");
				for (String s : pl.enabled) {
					p.sendMessage(ChatColor.GREEN + "- " + s);
				}
				return false;
			}
			if (args[0].equalsIgnoreCase("toggle")) {
				if (args[1] == null) {
					p.sendMessage(ChatColor.RED + "Please specify a player");
					return false;
				}
				if (pl.enabled.contains(args[1])) {
					pl.enabled.remove(args[1]);
					p.sendMessage(ChatColor.GREEN + args[1] + ChatColor.GREEN + "'s GhostMode has been disabled!");
					return true;
				} else if (Bukkit.getServer().getPlayer(args[1]) == null) {
					p.sendMessage(ChatColor.RED + args[1] + ChatColor.RED
							+ " was not found. (Perhaps you misspelled the name?)");
					return false;
				} else {
					pl.enabled.add(args[1]);
					p.sendMessage(ChatColor.GREEN + "You have enabled GhostMode for " + ChatColor.GREEN + args[1]);
					return true;
				}
			}
			p.sendMessage(ChatColor.RED + "Invalid arguments. Use </ghostmode help> to list all the arguments.");
		}

	return false;
}

}
