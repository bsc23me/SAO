package me.bsc23me.sao.magic;

import java.util.List;

import me.bsc23me.sao.Main;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Slow implements Listener{

	public static Main plugin;
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		String message = event.getMessage();
		if(message.equalsIgnoreCase("mgc slm")){
			event.setCancelled(true);
			List<Entity> close = player.getNearbyEntities(5, 5, 5);
			for(Entity e : close){
				if(e instanceof Player){
					((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));
				}else if(e instanceof Monster){
					((Monster) e).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));
				}
			}
		}
	}
	
}
