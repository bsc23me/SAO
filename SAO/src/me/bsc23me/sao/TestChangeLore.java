package me.bsc23me.sao;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TestChangeLore implements Listener{

	public static Main plugin;

	public static int Modulus(int dividend, int divisor){
		double modulus = (Math.abs(dividend) - (Math.abs(divisor) * (Math.floor(Math.abs(dividend) / Math.abs(divisor)))));
		if(dividend > 0){
			return (int) modulus;
		}else{
			modulus = modulus * -1;
			return (int) modulus;
		}
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
	public void onPlayerChat(AsyncPlayerChatEvent event){
		final Player player = event.getPlayer();
		String message = event.getMessage();
		if(message.equalsIgnoreCase("item test")){
			event.setCancelled(true);
			Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin.getPlugin(Main.class), new Runnable(){
				int i = 180;
				double m;
				double s;
				public void run(){
					if(i > 60){
						s = Modulus(i, 60);
						m = (i - s) / 60;
						player.getInventory().setItem(4, customItem(new ItemStack(Material.DIAMOND_BLOCK), ChatColor.AQUA+"Testing", ChatColor.RED+"Cool Down: "+(int)m+"m"+(int)s+"s"));
					}else if(i > 0){
						player.getInventory().setItem(4, customItem(new ItemStack(Material.DIAMOND_BLOCK), ChatColor.AQUA+"Testing", ChatColor.RED+"Cool Down: "+i+"s"));
					}else{
						Bukkit.getScheduler().cancelAllTasks();
						player.getInventory().setItem(4, customItem(new ItemStack(Material.DIAMOND_BLOCK), ChatColor.AQUA+"Testing", ChatColor.GREEN+"Item in stock!"));
						player.sendMessage("Task Complete!");
					}
					i--;
				}
			}, 20, 20);

		}
	}

}
