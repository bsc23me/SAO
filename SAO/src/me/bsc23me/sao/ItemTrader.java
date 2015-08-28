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
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ItemTrader implements Listener{

	public static Main plugin;

	static Inventory inv = Bukkit.createInventory(null, 9, "Item Trader");

	int taskID;

	public static int modulus(int dividend, int divisor){
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
	public void onEnable(PluginEnableEvent event){
		Plugin plugin = event.getPlugin();
		if(plugin.getName().equalsIgnoreCase("SAO")){
			taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin.getPlugin(Main.class), new Runnable(){
				int i = 10;
				double s;
				double m;
				public void run(){
					s = modulus(i, 60);
					m = i/60;
					if(i > 60){
						inv.setItem(0, customItem(new ItemStack(Material.DIAMOND_BLOCK),
								ChatColor.AQUA+""+ChatColor.BOLD+"Warp Crystal: "+ChatColor.AQUA+""+ChatColor.UNDERLINE+"White Havens",
								ChatColor.GRAY+"Teleports user to "+ChatColor.UNDERLINE+"White Havens",
								ChatColor.RED+"Out of stock: "+(int)m+"m"+(int)s+"s"));
						i--;
					}else if(i > 0){
						inv.setItem(0, customItem(new ItemStack(Material.DIAMOND_BLOCK),
								ChatColor.AQUA+""+ChatColor.BOLD+"Warp Crystal: "+ChatColor.AQUA+""+ChatColor.UNDERLINE+"White Havens",
								ChatColor.GRAY+"Teleports user to "+ChatColor.UNDERLINE+"White Havens",
								ChatColor.RED+"Out of stock: "+i+"s"));
						i--;
					}else if(i == 0){
						inv.setItem(0, customItem(new ItemStack(Material.DIAMOND_BLOCK),
								ChatColor.AQUA+""+ChatColor.BOLD+"Warp Crystal: "+ChatColor.AQUA+""+ChatColor.UNDERLINE+"White Havens",
								ChatColor.GRAY+"Teleports user to "+ChatColor.UNDERLINE+"White Havens",
								ChatColor.GREEN+"Item in stock"));
						Bukkit.getScheduler().cancelTask(taskID);
					}
				}
			}, 0, 20);
		}
	}

	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent event){
		Player player = event.getPlayer();
		Entity e = event.getRightClicked();
		if(e instanceof HumanEntity){
			if(((HumanEntity) e).getName().equalsIgnoreCase("Item Trader")){
				player.openInventory(inv);
			}
		}
	}

	@SuppressWarnings("static-access")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		Player player = (Player) event.getWhoClicked();
		Inventory inventory = event.getInventory();
		ItemStack item = event.getCurrentItem();
		if(inventory.getName().equalsIgnoreCase("Item Trader")){
			if(item.getType() == Material.DIAMOND_BLOCK){
				event.setCancelled(true);
				String rawName = ChatColor.stripColor(item.getItemMeta().getDisplayName());
				String name = rawName.substring(14);
				switch(name){
				case "White Havens":
					List<String> lore = item.getItemMeta().getLore();
					String stock = ChatColor.stripColor(lore.get(1));
					if(stock.equalsIgnoreCase("Item in stock")){
						if(CustomPlayer.getPlayerMoney(player) >= 1000){
							CustomPlayer.setPlayerMoney(player, CustomPlayer.getPlayerMoney(player) - 1000);
							player.getInventory().addItem(customItem(new ItemStack(Material.DIAMOND_BLOCK),
									ChatColor.AQUA+""+ChatColor.BOLD+"Warp Crystal: "+ChatColor.AQUA+""+ChatColor.UNDERLINE+"White Havens",
									ChatColor.GRAY+"Teleports user to "+ChatColor.UNDERLINE+"White Havens"));
							taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin.getPlugin(Main.class), new Runnable(){
								int i = 120;
								double s;
								double m;
								public void run(){
									s = modulus(i, 60);
									m = i/60;
									if(i > 60){
										inv.setItem(0, customItem(new ItemStack(Material.DIAMOND_BLOCK),
												ChatColor.AQUA+""+ChatColor.BOLD+"Warp Crystal: "+ChatColor.AQUA+""+ChatColor.UNDERLINE+"White Havens",
												ChatColor.GRAY+"Teleports user to "+ChatColor.UNDERLINE+"White Havens",
												ChatColor.RED+"Out of stock: "+(int)m+"m"+(int)s+"s"));
										i--;
									}else if(i > 0){
										inv.setItem(0, customItem(new ItemStack(Material.DIAMOND_BLOCK),
												ChatColor.AQUA+""+ChatColor.BOLD+"Warp Crystal: "+ChatColor.AQUA+""+ChatColor.UNDERLINE+"White Havens",
												ChatColor.GRAY+"Teleports user to "+ChatColor.UNDERLINE+"White Havens",
												ChatColor.RED+"Out of stock: "+i+"s"));
										i--;
									}else if(i == 0){
										inv.setItem(0, customItem(new ItemStack(Material.DIAMOND_BLOCK),
												ChatColor.AQUA+""+ChatColor.BOLD+"Warp Crystal: "+ChatColor.AQUA+""+ChatColor.UNDERLINE+"White Havens",
												ChatColor.GRAY+"Teleports user to "+ChatColor.UNDERLINE+"White Havens",
												ChatColor.GREEN+"Item in stock"));
										Bukkit.getScheduler().cancelTask(taskID);
									}
								}
							}, 0, 20);
						}else{
							player.sendMessage(ChatColor.RED+"You do "+ChatColor.UNDERLINE+"NOT"+ChatColor.RED+" have enough mythril!");
						}
					}else{
						player.sendMessage("Item out of stock");
					}
					break;
				}
			}
		}
	}

}
