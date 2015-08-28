package me.bsc23me.sao;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ExpBottleEvent;

public class MobListener implements Listener{

	public static Main plugin;
	
	@EventHandler
	public void onMobDeath(EntityDeathEvent event){
		event.setDroppedExp(0);
		event.getDrops().clear();
	}

	@EventHandler
	public void onXpDrop(ExpBottleEvent event){
		event.setExperience(0);
	}

	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent event){

		LivingEntity mob = event.getEntity();
		
		/*if(mob.getType() == EntityType.PIG){
			Pig pig = (Pig) mob;
			CustomPig cp = new CustomPig(((CraftWorld)pig.getWorld()).getHandle());
			cp.setLocation(pig.getLocation().getX(), pig.getLocation().getY(), pig.getLocation().getZ(), pig.getLocation().getYaw(), pig.getLocation().getPitch());
			((CraftWorld) pig.getWorld()).getHandle().addEntity(cp);
			pig.remove();
		}*/
		
		mob.setHealth(mob.getMaxHealth());
		
		if(event.getSpawnReason() == SpawnReason.NATURAL){
			event.setCancelled(true);
		}else if(event.getSpawnReason() == SpawnReason.DEFAULT){
			if(event.getEntity().getCustomName() == null){
				event.setCancelled(false);
			}else{

			}
		}else if(event.getSpawnReason() == SpawnReason.SPAWNER){
			if(event.getEntity().getCustomName() == null){
				event.setCancelled(false);
			}else{
				Entity e = event.getEntity();
				int i = 2;
				boolean empty = false;
				while(i < 7 && empty == false){
					if(e.getLocation().add(0, i, 0).getBlock().isEmpty()){
						e.teleport(e.getLocation().add(0, i, 0));
						empty = true;
					}else{
						i++;
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onSheepEat(EntityChangeBlockEvent event){
		if(event.getEntityType() == EntityType.SHEEP){
			if(event.getBlock().getType() == Material.GRASS){
				event.setCancelled(true);
			}
		}
	}

}
