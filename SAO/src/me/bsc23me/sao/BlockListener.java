package me.bsc23me.sao;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockListener implements Listener{
	
	public static Main plugin;
	
	@EventHandler
	public void onBlockIgnite(BlockIgniteEvent event){
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		Player player = event.getPlayer();
			if(player.isOp() || player.hasPermission("sao.build")){
				
			}else{
				event.setCancelled(true);
			}
		
	}
	
	@EventHandler
	public void onBlockClick(PlayerInteractEvent event){
		Block b = event.getClickedBlock();
		Action a = event.getAction();
		if(a.equals(Action.RIGHT_CLICK_BLOCK)){
			if(b.getType() == Material.BEACON){
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onGrassSpead(BlockSpreadEvent event){
		Block from = event.getSource();
		if(from.getType() == Material.GRASS){
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDirtSpread(BlockFadeEvent event){
		if(event.getBlock().getType() == Material.DIRT){
			Bukkit.broadcastMessage("dirt spread");
		}
	}
	
	//@SuppressWarnings("deprecation")
	@EventHandler
	public void onEnderBreak(EntityExplodeEvent event){
		if(event.getEntityType() == EntityType.ENDER_CRYSTAL){
			event.setCancelled(true);
			Location loc = event.getLocation();
			event.getLocation().getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 6, false, false);
			loc.add(0, -1, 0).getBlock().setType(Material.FIRE);
		}
	}
	
	@EventHandler
	public void onItemframeInteract(PlayerInteractEntityEvent event){
		Entity e = event.getRightClicked();
		Player player = event.getPlayer();
		if(e.getType() == EntityType.ITEM_FRAME){
			if(!(player.isOp())){
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onItemFrameBreak(HangingBreakByEntityEvent event){
		Entity e = event.getEntity();
		Entity r = event.getRemover();
		if(e instanceof ItemFrame){
			if(r instanceof Player){
				if(!(((Player)r).isOp())){
					event.setCancelled(true);
				}
			}else{
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onItemFrameRemove(EntityDamageByEntityEvent event){
		Entity e = event.getEntity();
		Entity player = event.getDamager();
		if(e instanceof ItemFrame){
			if(player instanceof Player){
				if(!(((Player)player).isOp())){
					event.setCancelled(true);
				}
			}else{
				event.setCancelled(true);
			}
			
		}
	}
	
}
