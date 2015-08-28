package me.bsc23me.sao.magic;

import me.bsc23me.sao.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Speed implements Listener{

	public static Main plugin;
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		String message = event.getMessage();
		if(message.equalsIgnoreCase("mgc mov")){
			event.setCancelled(true);
			player.removePotionEffect(PotionEffectType.SPEED);
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 0, true));
		}else if(message.equalsIgnoreCase("mgc mov eng tvl")){
			event.setCancelled(true);
			player.removePotionEffect(PotionEffectType.SPEED);
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 1, true));
		}else if(message.equalsIgnoreCase("dmg mov eng tvl dst hng")){
			event.setCancelled(true);
			player.removePotionEffect(PotionEffectType.SPEED);
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 24000, 2, true));
			player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 1200, 0, true));
		}
	}
	
}
