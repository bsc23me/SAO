package me.bsc23me.sao.magic;

import me.bsc23me.sao.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.potion.PotionEffect;

public class Nullify implements Listener{

	public static Main plugin;
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		String message = event.getMessage();
		if(message.equalsIgnoreCase("eng vod")){
			event.setCancelled(true);
			for(PotionEffect p : player.getActivePotionEffects()){
				player.removePotionEffect(p.getType());
			}
		}
	}
	
}
