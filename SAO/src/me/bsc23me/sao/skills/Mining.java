package me.bsc23me.sao.skills;

import java.util.Arrays;
import java.util.List;

import me.bsc23me.sao.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Mining implements Listener{

	public static Main plugin;
	
	int taskID;
	
	public static ItemStack customItem(ItemStack item, String name){
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		item.setItemMeta(im);
		return item;
	}

	public static ItemStack customItem(ItemStack item, String name, String l1){
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		List<String> lore = Arrays.asList(new String[] {l1});
		im.setLore(lore);
		item.setItemMeta(im);
		return item;
	}

	public static ItemStack customItem(ItemStack item, String name, String l1, String l2){
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		List<String> lore = Arrays.asList(new String[] {l1, l2});
		im.setLore(lore);
		item.setItemMeta(im);
		return item;
	}
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		Player player = event.getPlayer();
		final Block b = event.getBlock();
		if(b.getType() == Material.IRON_ORE){
			event.setCancelled(true);
			b.setType(Material.STONE);
			player.getInventory().addItem(customItem(new ItemStack(Material.IRON_ORE), ChatColor.AQUA+"Iron Ore", ChatColor.GRAY+"A piece of "+ChatColor.UNDERLINE+"iron"+ChatColor.GRAY+" ore"));
			taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin.getPlugin(Main.class), new Runnable(){
				public void run(){
					b.setType(Material.IRON_ORE);
				}
			}, 200);
		}
	}
	
}
