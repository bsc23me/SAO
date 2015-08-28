package me.bsc23me.sao.magic;

import me.bsc23me.sao.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class WaterWalker implements Listener{

	public static Main plugin;
	
	boolean enabled;
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		String message = event.getMessage();
		if(message.equalsIgnoreCase("mgc wtr mov")){
			player.getLocation();
		}
	}
	
}
