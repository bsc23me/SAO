package me.bsc23me.sao;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerListener implements Listener{

	public static Main plugin;

	Inventory raceSel = Bukkit.createInventory(null, 9, "Choose Race");
	Inventory clazzSel = Bukkit.createInventory(null, 9, "Choose Class");
	
	public ItemStack customItem(ItemStack item, String name, String l1){
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		List<String> lore = Arrays.asList(new String[] {l1});
		im.setLore(lore);
		item.setItemMeta(im);
		return item;
	}
	
	@EventHandler
	public void worldLimit(PlayerMoveEvent event){
		Player player = event.getPlayer();
		Location l = event.getTo();
		if(l.getBlockX() < -900|| l.getBlockX() > 900 || l.getBlockZ() < -900 || l.getBlockZ() > 900){
			if(!player.isOp()){
				event.setCancelled(true);
			}else{

			}
		}else{

		}
		
		if(l.getBlockY() < 0){
			player.teleport(l.add(0, 100, 0));
		}else if(l.getBlockY() > 225){
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerFall(EntityDamageEvent event){
		Entity e = event.getEntity();
		if(e instanceof Player){
			if(event.getCause() == DamageCause.FALL){
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		player.teleport(new Location(player.getWorld(), 0, -2, 0));
		player.setAllowFlight(true);
		player.setFlying(true);
		File playerDataFile = new File("plugins/SAO/players/"+player.getName()+".yml");
		FileConfiguration playerData = new YamlConfiguration();
		if(playerDataFile.exists()){
			try{
				playerData.load(playerDataFile);
				switch(playerData.getInt("Player.hollow")){
				case 0:
					player.setHealthScale(20);
					break;
				case 1:
					player.setHealthScale(16);
					break;
				case 2:
					player.setHealthScale(12);
					break;
				case 3:
					player.setHealthScale(8);
					break;
				case 4:
					player.setHealthScale(4);
					break;
				case 5:
					player.setHealthScale(20);
					player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2000000000, 1));
					player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2000000000, 1));
					player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 2000000000, 1));
					break;
				default:
					player.setHealthScale(20);
				}
				int level = playerData.getInt("Player.level");
				CustomPlayer.setPlayerLevel(player, level);

				CustomPlayer.setPlayerMaxHealth(player, CustomPlayer.getPlayerMaxHealth(player));

				
				
			}catch (Exception e) {
				Main.log.warning("[SAO] Failed to load "+player.getName()+"'s player data!");
			}
		}else{
			try{
				playerDataFile.createNewFile();
				Main.log.info("[SAO] Created "+player.getName()+"'s data file!");
				playerData.createSection("Player.health");
				playerData.createSection("Player.money");
				playerData.createSection("Player.level");
				playerData.createSection("Player.xp");
				playerData.createSection("Player.hollow");
				playerData.createSection("Player.race");
				playerData.createSection("Player.class");
				CustomPlayer.setPlayerHealth(player, 20);
				playerData.set("Player.health", 100);
				playerData.set("Player.money", 0);
				CustomPlayer.setPlayerLevel(player, 1);
				playerData.set("Player.level", 1);
				playerData.set("Player.xp", 0);
				CustomPlayer.setPlayerMaxHealth(player, 100);
				playerData.set("Player.hollow", 0);
				raceSel.setItem(0, customItem(new ItemStack(Material.IRON_HELMET), ChatColor.WHITE+"Man", ChatColor.GRAY+"Swordsmen / Combat Magic"));
				raceSel.setItem(2, customItem(new ItemStack(Material.NETHER_STAR), ChatColor.AQUA+"Elf", ChatColor.GRAY+"Exelent Archers / Healing magic"));
				raceSel.setItem(4, customItem(new ItemStack(Material.DIAMOND), ChatColor.YELLOW+"Dwarf", ChatColor.GRAY+"Strong Axes / Defencive Magic"));
				raceSel.setItem(4, customItem(new ItemStack(Material.YELLOW_FLOWER), ChatColor.GREEN+"Halfling", ChatColor.GRAY+"Small Spaces / Effects Magic"));
				raceSel.setItem(8, customItem(new ItemStack(Material.ROTTEN_FLESH), ChatColor.RED+"Orc", ChatColor.GRAY+"Night Good / Magic?"));
				player.openInventory(raceSel);
				playerData.save(playerDataFile);
				player.setHealthScale(20);
			}catch(IOException e){
				Main.log.info("[SAO] File Creation failed!");
			}
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		try{
			CustomPlayer.savePlayerHealth(player);
			CustomPlayer.setPlayerLevel(player, CustomPlayer.getPlayerLevel(player));
		}catch(Exception e){

		}

	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		Player player = event.getEntity();
		CustomPlayer.setPlayerHollow(player, CustomPlayer.getPlayerHollow(player) + 1);
	}

}
