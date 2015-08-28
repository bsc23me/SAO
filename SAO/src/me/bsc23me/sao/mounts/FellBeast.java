package me.bsc23me.sao.mounts;

import me.bsc23me.sao.Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class FellBeast implements Listener{

	public static Main plugin;
	
	int task;
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void onEggPlace(BlockPlaceEvent event){
		if(event.getBlock().getType() == Material.DRAGON_EGG){
			event.setCancelled(true);
			final Player player = event.getPlayer();
			final Entity dragon = player.getWorld().spawnEntity(player.getLocation(), EntityType.ENDER_DRAGON);
			dragon.setPassenger(player);
			dragon.setCustomName("Fell Beast");
			task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin.getPlugin(Main.class), new Runnable(){
				public void run(){
					
					if(dragon.getPassenger() == null){
						dragon.remove();
						Bukkit.getScheduler().cancelTask(task);
					}
				}
			}, 0, 20);
		}
	}
	
	@EventHandler
	public void onBeastClick(PlayerInteractEntityEvent event){
		Player player = event.getPlayer();
		Entity e = event.getRightClicked();
		if(player.getItemInHand().getType() == Material.DRAGON_EGG){
			if(e.getType() == EntityType.ENDER_DRAGON){
				e.remove();
			}
		}
	}
	
	@EventHandler
	public void onAirClick(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Action a = event.getAction();
		if(a == Action.RIGHT_CLICK_AIR){
			if(player.getItemInHand().getType() == Material.DRAGON_EGG){
				player.getVehicle().remove();
			}
		}
	}
	
	/*@EventHandler
	public void onPlayerDismount(PlayerMoveEvent event){
		Player player = event.getPlayer();
		
	}*/
	
}
