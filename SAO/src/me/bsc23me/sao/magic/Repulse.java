package me.bsc23me.sao.magic;

import me.bsc23me.sao.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Repulse implements Listener{

	public static Main plugin;
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		String message = event.getMessage();
		if(message.equalsIgnoreCase("mgc wtr")){
			event.setCancelled(true);
			final Location l = player.getLocation();
			l.getBlock().setType(Material.WATER);
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin.getPlugin(Main.class), new Runnable(){
				public void run(){
					l.getBlock().setType(Material.AIR);
				}
			}, 20);
		}
	}
	
}
