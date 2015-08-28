package me.bsc23me.sao;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Magician implements Listener{

	public static Main plugin;

	Inventory inv = Bukkit.createInventory(null, 9, "Magician");

	public ItemStack customItem(ItemStack item, String name, String l){
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		List<String> lore = Arrays.asList(new String[] {l});
		im.setLore(lore);
		item.setItemMeta(im);
		return item;
	}

	public ItemStack customItem(ItemStack item, String name, String l1, String l2){
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
			if(((HumanEntity) e).getName().equalsIgnoreCase("Magician")){
				player.openInventory(inv);
				
				if(!player.getInventory().contains(customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.GRAY+"Spells: 0 / 1"))){
					inv.setItem(0, customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.RED+"Price: 316 col", ChatColor.GRAY+"Spells: 0 / 1"));
				}else if(player.getInventory().contains(customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.GRAY+"Spells: 0 / 1"))){
					inv.setItem(0, customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.RED+"Price: 894 col", ChatColor.GRAY+"Spells: 0 / 2"));
				}else if(player.getInventory().contains(customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.GRAY+"Spells: 0 / 2"))){
					inv.setItem(0, customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.RED+"Price: 1643 col", ChatColor.GRAY+"Spells: 0 / 3"));
				}else if(player.getInventory().contains(customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.GRAY+"Spells: 0 / 3"))){
					inv.setItem(0, customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.RED+"Price: 2529 col", ChatColor.GRAY+"Spells: 0 / 4"));
				}else if(player.getInventory().contains(customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.GRAY+"Spells: 0 / 4"))){
					inv.setItem(0, customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.RED+"Price: 3535 col", ChatColor.GRAY+"Spells: 0 / 5"));
				}else if(player.getInventory().contains(customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.GRAY+"Spells: 0 / 5"))){
					inv.setItem(0, customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.RED+"Price: 4647 col", ChatColor.GRAY+"Spells: 0 / 6"));
				}else if(player.getInventory().contains(customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.GRAY+"Spells: 0 / 6"))){
					inv.setItem(0, customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.RED+"Price: 5856 col", ChatColor.GRAY+"Spells: 0 / 7"));
				}else if(player.getInventory().contains(customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.GRAY+"Spells: 0 / 7"))){
					inv.setItem(0, customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.RED+"Price: 7155 col", ChatColor.GRAY+"Spells: 0 / 8"));
				}else if(player.getInventory().contains(customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.GRAY+"Spells: 0 / 8"))){
					inv.setItem(0, customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.RED+"Price: 8538 col", ChatColor.GRAY+"Spells: 0 / 9"));
				}else{
					inv.setItem(0, customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.GREEN+"You have fully upgraded your wand!"));
				}
				
				
			}
		}
	}

	@EventHandler
	public void onInventoryInteract(InventoryClickEvent event){
		Player player = (Player) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory inv = event.getInventory();
		if(inv.getName().equalsIgnoreCase("Magician")){
			event.setCancelled(true);
			if(clicked.equals(customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.RED+"Price: 316 col", ChatColor.GRAY+"Spells: 0 / 1"))){
				if(player.getInventory().containsAtLeast(new ItemStack(Material.COAL), 316)){
					player.getInventory().addItem(customItem(new ItemStack(Material.STICK), ChatColor.GRAY+"Starter Wand", ChatColor.GRAY+"Spells: 0 / 1"));
					player.getInventory().remove(new ItemStack(Material.COAL, 316));
				}else{
					player.sendMessage("you do not have col");
				}
			}
		}
	}

}
