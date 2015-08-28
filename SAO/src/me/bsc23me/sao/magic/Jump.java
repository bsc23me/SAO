package me.bsc23me.sao.magic;

import me.bsc23me.sao.Main;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Jump implements Listener{

	public static Main plugin;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		String message = event.getMessage();
		if(message.equalsIgnoreCase("mgc tvl")){
			event.setCancelled(true);
			Location from = player.getLocation();
			Location targetb = player.getTargetBlock(null, 20).getLocation();
			Location target = new Location(targetb.getWorld(),
					targetb.getX() + .5,
					targetb.getY(),
					targetb.getZ() + .5,
					player.getLocation().getYaw(),
					player.getLocation().getPitch());
			if(target.getBlock().getType() != Material.AIR){
				player.teleport(new Location(target.getWorld(), target.getX(), target.getY() + 1, target.getZ(), target.getYaw(), target.getPitch()));
			}else{
				player.teleport(target);
			}
			player.getWorld().playEffect(from, Effect.EXTINGUISH, 1);
			player.getWorld().playEffect(from, Effect.ENDER_SIGNAL, 1);
			player.getWorld().playEffect(target, Effect.BOW_FIRE, 1);
		}
	}
	
}
