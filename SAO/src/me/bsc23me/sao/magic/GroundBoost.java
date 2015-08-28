package me.bsc23me.sao.magic;

import me.bsc23me.sao.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.util.Vector;

public class GroundBoost implements Listener{

	public static Main plugin;

	@SuppressWarnings({ "static-access", "deprecation" })
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		String message = event.getMessage();
		if(message.equalsIgnoreCase("mgc ter")){
			event.setCancelled(true);
			if(player.isOnGround()){
				final Location l = player.getLocation();
				final Location l1 = new Location(l.getWorld(), l.getX(), l.getY() + 1, l.getZ());
				final Location l2 = new Location(l.getWorld(), l.getX(), l.getY() + 2, l.getZ());
				final Location l3 = new Location(l.getWorld(), l.getX(), l.getY() + 2.5, l.getZ(), l.getYaw(), l.getPitch());
				player.teleport(l3);
				player.setVelocity(new Vector(0, 2, 0).multiply(1));
				l.getBlock().setType(Material.STONE);
				l1.getBlock().setType(Material.STONE);
				l2.getBlock().setType(Material.STONE);
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin.getPlugin(Main.class), new Runnable(){
					public void run(){
						l.getBlock().setType(Material.AIR);
						l1.getBlock().setType(Material.AIR);
						l2.getBlock().setType(Material.AIR);
					}
				}, 60);
			}else{
				
			}
		}
	}
}
