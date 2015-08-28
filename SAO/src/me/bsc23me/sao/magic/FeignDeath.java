package me.bsc23me.sao.magic;

import me.bsc23me.sao.Main;

import org.bukkit.EntityEffect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FeignDeath implements Listener{

	public static Main plugin;
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		String message = event.getMessage();
		if(message.equalsIgnoreCase("mgc cps")){
			event.setCancelled(true); 
			player.playEffect(EntityEffect.HURT);
			player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100, 1, true));
		}else if(message.equalsIgnoreCase("mgc cps eng mov")){
			event.setCancelled(true); 
			player.playEffect(EntityEffect.HURT);
			player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 400, 1, true));
			player.setVelocity(player.getLocation().getDirection().multiply(2));
		}
	}
	
}
