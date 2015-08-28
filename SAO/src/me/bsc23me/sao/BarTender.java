package me.bsc23me.sao;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BarTender implements Listener{

	public static Main plugin;
	
	static Inventory inv = Bukkit.createInventory(null, 9, "Tavern");
	
	static{
		inv.setItem(2, customItem(new ItemStack(Material.BED), ChatColor.LIGHT_PURPLE+"Sleep", ChatColor.GRAY+"Regen all stats", ChatColor.GREEN+"Price: 200"));
		inv.setItem(6, customItem(new ItemStack(Material.MUSHROOM_SOUP), ChatColor.GREEN+"Eat", ChatColor.GRAY+"Food TOGO", ChatColor.GREEN+"Price: 50"));
	}
	
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
	
	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent event){
		Player player = event.getPlayer();
		Entity e = event.getRightClicked();
		if(e instanceof HumanEntity){
			if( ((HumanEntity) e).getName().equalsIgnoreCase("Bar Tender")){
				player.openInventory(inv);
			}
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		Player player = (Player) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory i = event.getInventory();
		if(i.getName().equalsIgnoreCase("Tavern")){
			if(clicked.getType() == Material.BED){
				event.setCancelled(true);
				if(CustomPlayer.getPlayerMoney(player) >= 200){
					CustomPlayer.setPlayerMoney(player, CustomPlayer.getPlayerMoney(player) - 200);
					CustomPlayer.setPlayerHealth(player, CustomPlayer.getPlayerMaxHealth(player));
					player.setFoodLevel(20);
					player.setSaturation(4);
					player.playSound(player.getEyeLocation(), Sound.LEVEL_UP, 1, 1);
					Wolf e = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
					e.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20, 1, true));
					e.playEffect(EntityEffect.WOLF_HEARTS);
					e.remove();
					player.closeInventory();
				}else{
					player.sendMessage(ChatColor.RED+"Not enough money!");
				}
			}else if(clicked.getType() == Material.MUSHROOM_SOUP){
				event.setCancelled(true);
				if(CustomPlayer.getPlayerMoney(player) >= 50){
					CustomPlayer.setPlayerMoney(player, CustomPlayer.getPlayerMoney(player) - 50);
					player.getInventory().addItem(customItem(new ItemStack(Material.MUSHROOM_SOUP), ChatColor.GREEN+"Meal", "Heals user's hunger to full"));
				}else{
					player.sendMessage(ChatColor.RED+"Not enough money!");
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Action a = event.getAction();
		
		if(a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK){
			if(player.getItemInHand().equals(customItem(new ItemStack(Material.MUSHROOM_SOUP), ChatColor.GREEN+"Meal", "Heals user's hunger to full"))){
				player.setFoodLevel(20);
				player.setSaturation(4);
			}
		}
	}
	
}
