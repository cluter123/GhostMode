package me.cluter.passivemode;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;


public class pluginListener implements Listener {
	Main pl;
	 
	public pluginListener(Main instance) {
	pl = instance;
	}

	@EventHandler
	public void onHit(EntityDamageEvent e) {
		if(!(e.getEntity() instanceof Player)) return;
		Player p = (Player)e.getEntity();
		if(!(p.hasPermission("gm.invincible"))) return;
		if(!(pl.enabled.contains(p.getName()))) return;
		e.setCancelled(true);
	}
	
	@EventHandler
	public void playerDamage(EntityDamageByEntityEvent e) {
		if(!(e.getDamager() instanceof Player)) return;
		Player p = (Player)e.getDamager();
		if(!(p.hasPermission("gm.damageothers"))) return;
		if(!(pl.enabled.contains(p.getName()))) return;
		e.setCancelled(true);
	}
	
	@EventHandler
	public void blockPlace(BlockPlaceEvent e) {
		if(!(e.getPlayer().hasPermission("gm.build"))) return;
		if(!(pl.enabled.contains(e.getPlayer().getName()))) return;
		e.setCancelled(true);
	}
	
	@EventHandler
	public void blockPlace(BlockBreakEvent e) {
		if(!(e.getPlayer().hasPermission("gm.build"))) return;
		if(!(pl.enabled.contains(e.getPlayer().getName()))) return;
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(!(e.getPlayer().hasPermission("gm.interact"))) return;
		if(!(pl.enabled.contains(e.getPlayer().getName()))) return;
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if(!(e.getPlayer().hasPermission("gm.inv"))) return;
		if(!(pl.enabled.contains(e.getPlayer().getName()))) return;
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		if(!(e.getPlayer().hasPermission("gm.inv"))) return;
		if(!(pl.enabled.contains(e.getPlayer().getName()))) return;
		e.setCancelled(true);
	}

}
