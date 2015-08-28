package me.bsc23me.sao.magic;

import me.bsc23me.sao.Main;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Warp implements Listener{

	public static Main plugin;

	public static void crystalWarp(Player player){
		ItemStack held = player.getItemInHand();
		if(held.hasItemMeta()){
			String name = held.getItemMeta().getDisplayName();
			name = ChatColor.stripColor(name);
			String dest = name.substring(14);
			Location loc;
			World w = player.getWorld();
			Location from = player.getLocation();
			w.strikeLightning(from);
			switch(dest){
			case "Alne/World Tree":
				loc = new Location(w, 0.5, 64.5, 0.5, 0, 0);
				player.teleport(loc);
				break;
			case "White Havens":
				loc = new Location(w, 11.5, 103, 749.5, 180, 0);
				player.teleport(loc);
				break;
			case "Ziggurat":
				loc = new Location(w, -799.5, 21, 200.5, -90, 0);
				player.teleport(loc);
				break;
			}
			player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1, true));
			if(held.getAmount() > 1){
				player.getInventory().getItemInHand().setAmount(held.getAmount() - 1);
			}else{
				player.getInventory().remove(held);
			}

		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Action a = event.getAction();
		if(a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)){
			ItemStack held = player.getItemInHand();
			if(held.getType() == Material.DIAMOND_BLOCK){
				event.setCancelled(true);
				crystalWarp(player);
			}
		}
	}

}
