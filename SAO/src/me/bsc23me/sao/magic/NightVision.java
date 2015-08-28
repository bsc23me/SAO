package me.bsc23me.sao.magic;

import me.bsc23me.sao.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVision implements Listener{

	public static Main plugin;
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		String message = event.getMessage();
		if(message.equalsIgnoreCase("mgc lgt")){
			event.setCancelled(true);
			player.removePotionEffect(PotionEffectType.NIGHT_VISION);
			player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 400, 1, true));
		}else if(message.equalsIgnoreCase("mgc lgt eng tol")){
			event.setCancelled(true);
			player.removePotionEffect(PotionEffectType.NIGHT_VISION);
			player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 6000, 1, true));
		}else if(message.equalsIgnoreCase("dmg lgt eng tol dst mov")){
			event.setCancelled(true);
			player.removePotionEffect(PotionEffectType.NIGHT_VISION);
			player.removePotionEffect(PotionEffectType.SLOW);
			player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 24000, 1, true));
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 24000, 0, true));
		}
	}
	
}
