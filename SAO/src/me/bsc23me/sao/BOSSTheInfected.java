package me.bsc23me.sao;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BOSSTheInfected implements Listener{

	public static Main plugin;
	
	public static LivingEntity customMob(LivingEntity e, String name, double health){
		((LivingEntity) e).setCustomName(name);
		((LivingEntity) e).setCustomNameVisible(true);
		e.setMaxHealth(health);
		e.setHealth(e.getMaxHealth());
		return (LivingEntity) e;
	}
	
	@EventHandler
	public void wormPoison(EntityDamageByEntityEvent event){
		Entity damager = event.getDamager();
		Entity damaged = event.getEntity();
		if(damaged instanceof Player && damager instanceof LivingEntity){
			LivingEntity worm = (LivingEntity) damager;
			LivingEntity player = (LivingEntity) damaged;
			if(worm.getCustomName().equalsIgnoreCase("Worm")){
				player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
				worm.setHealth(0d);
			}else{
				
			}
		}else{
			
		}
	}
	
	@EventHandler
	public void onMinionDeath(EntityDeathEvent event){
		LivingEntity e = event.getEntity();
		if(e.getCustomName() == null){
			
		}else if(e.getCustomName().equalsIgnoreCase("Infected Dead")){
			LivingEntity goop1 = customMob((LivingEntity) e.getWorld().spawnEntity(e.getLocation(), EntityType.SLIME), "Infected Goop", 20);
			LivingEntity goop2 = customMob((LivingEntity) e.getWorld().spawnEntity(e.getLocation(), EntityType.SLIME), "Infected Goop", 20);
			LivingEntity goop3 = customMob((LivingEntity) e.getWorld().spawnEntity(e.getLocation(), EntityType.SLIME), "Infected Goop", 20);
			((Slime) goop1).setSize(2);
			goop1.setMaxHealth(20d);
			goop1.setHealth(goop1.getMaxHealth());
			((Slime) goop2).setSize(2);
			goop2.setMaxHealth(20d);
			goop2.setHealth(goop2.getMaxHealth());
			((Slime) goop3).setSize(2);
			goop3.setMaxHealth(20d);
			goop3.setHealth(goop3.getMaxHealth());
		}else if(e.getCustomName().equalsIgnoreCase("Infected Goop")){
			LivingEntity goop = customMob((LivingEntity) e.getWorld().spawnEntity(e.getLocation(), EntityType.SLIME), "Small Infected Goop", 10);
			customMob((LivingEntity) e.getWorld().spawnEntity(e.getLocation(), EntityType.SILVERFISH), "Worm", 5);
			customMob((LivingEntity) e.getWorld().spawnEntity(e.getLocation(), EntityType.SILVERFISH), "Worm", 5);
			customMob((LivingEntity) e.getWorld().spawnEntity(e.getLocation(), EntityType.SILVERFISH), "Worm", 5);
			((Slime) goop).setSize(1);
			goop.setMaxHealth(10d);
			goop.setHealth(goop.getMaxHealth());
		}else if(e.getCustomName().equalsIgnoreCase("Small Infected Goop")){
			customMob((LivingEntity) e.getWorld().spawnEntity(e.getLocation(), EntityType.SILVERFISH), "Worm", 5);
			customMob((LivingEntity) e.getWorld().spawnEntity(e.getLocation(), EntityType.SILVERFISH), "Worm", 5);
		}else{
			
		}
	}
	
	@EventHandler
	public void onSlimeSplit(SlimeSplitEvent event){
		event.setCancelled(true);
	}
	
}
