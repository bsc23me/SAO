package me.bsc23me.sao.skills;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.bsc23me.sao.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Smithing implements Listener{

	public static Main plugin;

	static Inventory anvilInv = Bukkit.createInventory(null, 9, "Smithing");


	static List<Material> weaponmats = new ArrayList<Material>();

	ItemStack cursor;

	int taskID;

	public static void resetAnvil(){
		anvilInv.clear();
		anvilInv.setItem(8, customItem(new ItemStack(Material.SULPHUR), ChatColor.GRAY+"Accept Trade"));
	}

	public static void resetFurnace(){

	}

	ItemStack steelPlate = customItem(new ItemStack(Material.IRON_PLATE), ChatColor.WHITE+"Steel Plate", ChatColor.GRAY+"+1 Steel Weapon/Armor");
	ItemStack steel = customItem(new ItemStack(Material.IRON_INGOT), ChatColor.WHITE+"Iron Ingot", ChatColor.GRAY+"A bar of "+ChatColor.UNDERLINE+"iron");
	Block b;


	private void weaponMats(){
		weaponmats.add(Material.WOOD_SWORD);
		weaponmats.add(Material.STONE_SWORD);
		weaponmats.add(Material.IRON_SWORD);
		weaponmats.add(Material.GOLD_SWORD);
		weaponmats.add(Material.DIAMOND_SWORD);

		weaponmats.add(Material.WOOD_AXE);
		weaponmats.add(Material.STONE_AXE);
		weaponmats.add(Material.IRON_AXE);
		weaponmats.add(Material.GOLD_AXE);
		weaponmats.add(Material.DIAMOND_AXE);

		weaponmats.add(Material.BOW);
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
	public void onBlockClick(PlayerInteractEvent event){
		weaponMats();
		Player player = event.getPlayer();
		Action a = event.getAction();
		b = event.getClickedBlock();
		/*Location l = b.getLocation();
		World w = b.getWorld();
		int num = weaponmats.size();
		boolean found = false;*/
		if(a == Action.RIGHT_CLICK_BLOCK && b.getType() == Material.ANVIL){
			event.setCancelled(true);
			/*for(Entity e : w.getEntities()){
				Location eloc = e.getLocation();
				if(eloc.equals(new Location(w, l.getX() + .5, l.getY() + 1, l.getZ() + .5)) == false){

				}else{
					player.sendMessage("Entity found!");
					found = true;
				}
			}

			do{
				if(player.getItemInHand().getType() == weaponmats.get(num - 1)){
					w.dropItem(new Location(w, l.getX(), l.getY() + 1, l.getZ()), player.getItemInHand());
					player.getItemInHand().setType(Material.AIR);
					found = true;
				}else{
					num--;
				}
			}while(num > 0 && found == false);*/
			resetAnvil();
			player.openInventory(anvilInv);
			anvilInv.setMaxStackSize(2);
		}
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event){
		Player player = (Player) event.getPlayer();
		Inventory anvilInv = event.getInventory();
		if(anvilInv.getName().equalsIgnoreCase("Smithing")){
			for(ItemStack i : anvilInv.getContents()){
				if(i == null || i.getType() == Material.SULPHUR){

				}else{
					player.getInventory().addItem(i);
				}
			}
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		Player player = (Player) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory i = event.getInventory();
		if(i.getName().equalsIgnoreCase("Smithing")){
			if(clicked.getType() == Material.SULPHUR){
				event.setCancelled(true);
				if(anvilInv.getContents().length > 1){
					for(ItemStack item : anvilInv.getContents()){
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
									//raw
								case "Iron Ingot":
									if(item.getAmount() == 2){
										player.getInventory().addItem(steelPlate);
										player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
										player.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 42);
									}else{

									}
									break;
								case "Steel Plate":
									if(anvilInv.contains(Material.IRON_SWORD)){
										player.sendMessage("Sword Detected");
									}else{
										player.getInventory().addItem(customItem(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE+"Steel Sword", ChatColor.RED+"DMG: 25"));
										player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
										player.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 42);
									}
									break;
									//Weapons
								case "Steel Sword":
									if(anvilInv.contains(steelPlate)){
										player.getInventory().addItem(customItem(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE+"+1 Steel Sword", ChatColor.RED+"DMG: 26"));
										player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
										player.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 42);
									}else if(anvilInv.contains(steel)){
										player.getInventory().addItem(customItem(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE+"Steel Sword", ChatColor.RED+"DMG: 25"));
										player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
										player.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 42);
									}else{
										player.getInventory().addItem(item);
									}
									break;
								case "+1 Steel Sword":
									if(anvilInv.contains(steelPlate)){
										player.getInventory().addItem(customItem(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE+"+2 Steel Sword", ChatColor.RED+"DMG: 29"));
										player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
										player.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 42);
									}else if(anvilInv.contains(steel)){
										player.getInventory().addItem(customItem(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE+"+1 Steel Sword", ChatColor.RED+"DMG: 26"));
										player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
										player.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 42);
									}else{
										player.getInventory().addItem(item);
									}
									break;
								case "+2 Steel Sword":
									if(anvilInv.contains(steelPlate)){
										player.getInventory().addItem(customItem(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE+"+3 Steel Sword", ChatColor.RED+"DMG: 33"));
										player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
										player.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 42);
									}else if(anvilInv.contains(steel)){
										player.getInventory().addItem(customItem(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE+"+2 Steel Sword", ChatColor.RED+"DMG: 29"));
										player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
										player.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 42);
									}else{
										player.getInventory().addItem(item);
									}
									break;
								case "+3 Steel Sword":
									if(anvilInv.contains(steelPlate)){
										player.getInventory().addItem(customItem(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE+"+4 Steel Sword", ChatColor.RED+"DMG: 40"));
										player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 10, 1);
										player.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 42);
									}else if(anvilInv.contains(steel)){
										player.getInventory().addItem(customItem(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE+"+3 Steel Sword", ChatColor.RED+"DMG: 33"));
										player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
										player.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 42);
									}else{
										player.getInventory().addItem(item);
									}
									break;
								case "+4 Steel Sword":
									if(anvilInv.contains(steelPlate)){
										player.getInventory().addItem(customItem(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE+"+5 Steel Sword", ChatColor.RED+"DMG: 50"));
										player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 10, 1);
										player.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 42);
									}else{
										player.getInventory().addItem(item);
									}
									break;
								case "+5 Steel Sword":
									if(anvilInv.contains(steelPlate)){
										player.getInventory().addItem(customItem(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE+"+6 Steel Sword", ChatColor.RED+"DMG: 65"));
										player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 10, 1);
										player.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 42);
									}else{
										player.getInventory().addItem(item);
									}
									break;
								case "+6 Steel Sword":
									if(anvilInv.contains(steelPlate)){
										player.getInventory().addItem(customItem(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE+"+7 Steel Sword", ChatColor.RED+"DMG: 87"));
										player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 10, 1);
										player.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 42);
									}else{
										player.getInventory().addItem(item);
									}
									break;
								case "+7 Steel Sword":
									if(anvilInv.contains(steelPlate)){
										player.getInventory().addItem(customItem(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE+"+8 Steel Sword", ChatColor.RED+"DMG: 122"));
										player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 10, 1);
										player.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 42);
									}else{
										player.getInventory().addItem(item);
									}
									break;
								case "+8 Steel Sword":
									if(anvilInv.contains(steelPlate)){
										player.getInventory().addItem(customItem(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE+"+9 Steel Sword", ChatColor.RED+"DMG: 177"));
										player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 10, 1);
										player.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 42);
									}else{
										player.getInventory().addItem(item);
									}
									break;
								case "+9 Steel Sword":
									if(anvilInv.contains(steelPlate)){
										player.getInventory().addItem(customItem(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE+"+10 Steel Sword", ChatColor.RED+"DMG: 266"));
										player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 10, 1);
										player.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, 42);
									}else{
										player.getInventory().addItem(item);
									}
									break;
								case "+10 Steel Sword":
									player.getInventory().addItem(item);
									break;
									/*case "Warp Crystal: Ziggurat":
									CustomPlayer.setPlayerMoney(player, CustomPlayer.getPlayerMoney(player) + 500 * item.getAmount());
									break;
								case "Elucidator":
									CustomPlayer.setPlayerMoney(player, CustomPlayer.getPlayerMoney(player) + 10000 * item.getAmount());
									break;
								case "Dark Repulser":
									CustomPlayer.setPlayerMoney(player, CustomPlayer.getPlayerMoney(player) + 500000 * item.getAmount());
									break;*/
									//Anything else
								default:
									break;
								}
							}else{

							}
						}
					}
				}else{

				}
				anvilInv.clear();
				player.closeInventory();
			}else{

			}
		}else{

		}
	}
	//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	//Smelting
	//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	/*@EventHandler
	public void onFurnaceClick(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Action a = event.getAction();
		if(a == Action.RIGHT_CLICK_BLOCK){
			Block b = event.getClickedBlock();
			if(b.getType() == Material.FURNACE || b.getType() == Material.LAVA){
				event.setCancelled(true);
				if(player.getItemInHand().getType() == Material.IRON_ORE){
					Inventory furnaceInv = Bukkit.createInventory(null, 9, "Smeltery");
					furnaceInv.clear();
					furnaceInv.setItem(0, customItem(new ItemStack(Material.THIN_GLASS), ChatColor.GRAY+" "));
					furnaceInv.setItem(1, customItem(new ItemStack(Material.THIN_GLASS), ChatColor.GRAY+" "));
					furnaceInv.setItem(2, customItem(new ItemStack(Material.THIN_GLASS), ChatColor.GRAY+" "));
					furnaceInv.setItem(3, customItem(new ItemStack(Material.THIN_GLASS), ChatColor.GRAY+" "));
					furnaceInv.setItem(4, customItem(new ItemStack(player.getItemInHand().getType()), player.getItemInHand().getItemMeta().getDisplayName(), "1"));
					furnaceInv.setItem(5, customItem(new ItemStack(Material.THIN_GLASS), ChatColor.GRAY+" "));
					furnaceInv.setItem(6, customItem(new ItemStack(Material.THIN_GLASS), ChatColor.GRAY+" "));
					furnaceInv.setItem(7, customItem(new ItemStack(Material.THIN_GLASS), ChatColor.GRAY+" "));
					furnaceInv.setItem(8, customItem(new ItemStack(Material.THIN_GLASS), ChatColor.GRAY+" "));
					player.openInventory(furnaceInv);
					if(player.getItemInHand().getAmount() > 1){
						player.getInventory().remove(player.getItemInHand());
					}else{
						player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
					}
				}
			}
		}
	}*/
}
