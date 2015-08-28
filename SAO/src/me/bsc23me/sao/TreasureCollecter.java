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
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TreasureCollecter implements Listener{

	public static Main plugin;

	static Inventory inv = Bukkit.createInventory(null, 9, "Trading Tom");

	public static void resetInv(){
		inv.setItem(8, customItem(new ItemStack(Material.SULPHUR), ChatColor.GRAY+"Confirm Trade"));
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
			if(((HumanEntity) e).getName().equalsIgnoreCase("Trading Tom")){
				inv.clear();
				inv.setItem(8, customItem(new ItemStack(Material.SULPHUR), ChatColor.GRAY+"Accept Trade"));
				player.openInventory(inv);
			}
		}
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event){
		Player player = (Player) event.getPlayer();
		Inventory i = event.getInventory();
		if(i.getName().equalsIgnoreCase("Trading Tom")){
			if(inv.getContents().length > 0){
				for(ItemStack item : inv.getContents()){
					if(item == null || item.getType() == Material.SULPHUR){
						
					}else{
						player.getInventory().addItem(item);
					}
				}
			}
		}
	}

	//@SuppressWarnings("unused")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		Player player = (Player) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory i = event.getInventory();
		if(i.getName().equalsIgnoreCase("Trading Tom")){
			if(clicked.getType() == Material.SULPHUR){
				event.setCancelled(true);
				if(inv.getContents().length > 1){
					for(ItemStack item : inv.getContents()){
						if(item == null){
							
						}else{
							if(item.hasItemMeta() == true){
								String name;
								name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
								switch(name){
								//accept
								case "Accept Trade":
									player.sendMessage(ChatColor.GREEN+"Trade Accepted");
									break;
								//Warp Crystals
								case "Warp Crystal: Creasant Gulf":
									CustomPlayer.setPlayerMoney(player, CustomPlayer.getPlayerMoney(player) + 500 * item.getAmount());
									break;
								case "Warp Crystal: Ziggurat":
									CustomPlayer.setPlayerMoney(player, CustomPlayer.getPlayerMoney(player) + 500 * item.getAmount());
									break;
									//Weapons
								case "Elucidator":
									CustomPlayer.setPlayerMoney(player, CustomPlayer.getPlayerMoney(player) + 10000 * item.getAmount());
									break;
								case "Dark Repulser":
									CustomPlayer.setPlayerMoney(player, CustomPlayer.getPlayerMoney(player) + 500000 * item.getAmount());
									break;
									//Anything else
								default:
									CustomPlayer.setPlayerMoney(player, CustomPlayer.getPlayerMoney(player) + 1 * item.getAmount());
									break;
								}
								inv.remove(item);
							}else{

							}
						}
					}
				}else{

				}
				player.closeInventory();
			}else{

			}
		}else{

		}
	}
}
