package me.bsc23me.sao;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PoisonDagger implements Listener{

	public static Main plugin;

	@EventHandler
	public static void onEntityHit(EntityDamageByEntityEvent event){
		Entity damager = event.getDamager();
		Entity damaged = event.getEntity();
		if(damager instanceof LivingEntity && damaged instanceof LivingEntity){
			if(((LivingEntity) damager).getEquipment().getItemInHand() != null){
				if(ChatColor.stripColor(((LivingEntity) damager).getEquipment().getItemInHand().getItemMeta().getDisplayName()).equalsIgnoreCase("Poison Dagger")){
					((LivingEntity) damaged).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 1));
				}else{

				}
			}
		}
	}

}
