package me.bsc23me.sao.magic;

import me.bsc23me.sao.Main;

import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.util.Vector;

public class Magic implements Listener{
	
	public static Main plugin;
	
	public Vector getPlayerVector(Player player){
		double pitch = ((player.getLocation().getPitch() + 90) * Math.PI) / 180;
		double yaw = ((player.getLocation().getYaw() + 90) * Math.PI) / 180;
		
		double x = Math.sin(pitch) * Math.cos(yaw);
		double z = Math.sin(pitch) * Math.sin(yaw);
		double y = Math.cos(pitch);
		
		Vector vector = new Vector(x, y, z);
		return vector;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		String message = event.getMessage();
		if(message.equalsIgnoreCase("test")){
			event.setCancelled(true);
			player.sendMessage(message);
			Vector vector = getPlayerVector(player);
			//player.setVelocity(vector.multiply(2));
			player.launchProjectile(Fireball.class).setVelocity(vector.multiply(5));
		}
	}
	
}
