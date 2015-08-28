package me.bsc23me.sao.magic;

import me.bsc23me.sao.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class FireWall implements Listener{

	public static Main plugin;
	
	@SuppressWarnings({ "static-access", "deprecation" })
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		String message = event.getMessage();
		if(message.equalsIgnoreCase("mgc flm")){
			event.setCancelled(true);
			Location l = player.getLocation();
			final World w = player.getWorld();
			Location target;
			final Block b;
			double yaw = l.getYaw();
			
			if(yaw <= 45){
				//south, z+
				target = new Location(w, l.getX(), l.getY(), l.getZ() + 2);
				b = target.getBlock();
				for(int x = b.getX() - 1; x < b.getX() + 2; x++){
					for(int y = b.getY(); y < b.getY() + 3; y++){
						Location place = new Location(w, x, y, b.getZ());
						place.getBlock().setTypeIdAndData(11, (byte) 0, false);
					}
				}
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin.getPlugin(Main.class), new Runnable(){
					public void run(){
						for(int x = b.getX() - 1; x < b.getX() + 2; x++){
							for(int y = b.getY(); y < b.getY() + 3; y++){
								Location place = new Location(w, x, y, b.getZ());
								place.getBlock().setType(Material.AIR);
							}
						}
					}
				}, 60);
			}else if(yaw <= 135){
				//west, x-
				player.sendMessage("west");
			}else if(yaw <= 225){
				//north, z-
				target = new Location(w, l.getX(), l.getY(), l.getZ() - 2);
				b = target.getBlock();
				for(int x = b.getX() - 1; x < b.getX() + 2; x++){
					for(int y = b.getY(); y < b.getY() + 3; y++){
						Location place = new Location(w, x, y, b.getZ());
						place.getBlock().setTypeIdAndData(11, (byte) 0, false);
					}
				}
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin.getPlugin(Main.class), new Runnable(){
					public void run(){
						for(int x = b.getX() - 1; x < b.getX() + 2; x++){
							for(int y = b.getY(); y < b.getY() + 3; y++){
								Location place = new Location(w, x, y, b.getZ());
								place.getBlock().setType(Material.AIR);
							}
						}
					}
				}, 60);
			}else if(yaw <= 285){
				//east, x+
				player.sendMessage("east");
			}else if(yaw > 285){
				//south, z+
				target = new Location(w, l.getX(), l.getY(), l.getZ() + 2);
				b = target.getBlock();
				for(int x = b.getX() - 1; x < b.getX() + 2; x++){
					for(int y = b.getY(); y < b.getY() + 3; y++){
						Location place = new Location(w, x, y, b.getZ());
						place.getBlock().setTypeIdAndData(11, (byte) 0, false);
					}
				}
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin.getPlugin(Main.class), new Runnable(){
					public void run(){
						for(int x = b.getX() - 1; x < b.getX() + 2; x++){
							for(int y = b.getY(); y < b.getY() + 3; y++){
								Location place = new Location(w, x, y, b.getZ());
								place.getBlock().setType(Material.AIR);
							}
						}
					}
				}, 60);
			}else{
				player.sendMessage("error");
			}
		}
	}
	
}
