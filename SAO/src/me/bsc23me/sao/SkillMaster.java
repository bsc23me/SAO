package me.bsc23me.sao;

import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class SkillMaster implements Listener{

	public static Main plugin;
	
	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent event){
		Player player = event.getPlayer();
		Entity e = event.getRightClicked();
		if(e instanceof HumanEntity){
			if(((HumanEntity) e).getName().equalsIgnoreCase("Skill Master")){
				player.sendMessage("<Skill Master> I have no idea what I'm doing here!");
			}
		}
	}
	
}
