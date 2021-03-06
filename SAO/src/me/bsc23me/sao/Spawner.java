package me.bsc23me.sao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Spawner implements Listener{

	public static Main plugin;

	static List<List<Block>> spawners = new ArrayList<List<Block>>();

	static ItemStack silk = customItem(new ItemStack(Material.STRING), ChatColor.WHITE+"Spider Silk", ChatColor.GRAY+"A durable material used in crafting");
	
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
	public void onPlayerMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		Location to = event.getTo();
		for(List<Block> list : spawners){
			for(Block b : list){
				if(b.equals(to.getBlock())){
					player.sendMessage("You have entered a circle");
				}
			}
		}
	}
	
	/*@EventHandler
	public void onEntitySpawn(CreatureSpawnEvent event){
		final Creature c = (Creature) event.getEntity();
		final Location l = c.getLocation();
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin.getPlugin(Main.class), new Runnable(){
			public void run(){
				List<Entity> close = c.getNearbyEntities(l.getX() + 5, l.getY() + 5, l.getZ() + 5);
				for(Entity e : close){
					if(e instanceof Player){
						target(c, (Player) e);
					}
				}
			}
		}, 0, 20);
	}*/

	public static List<Block> createCircle(Block center, int r){
		List<Block> circle = new ArrayList<Block>();
		World w = center.getWorld();
		int xc = center.getX();
		int yc = center.getY();
		int zc = center.getZ();

		for(int x = xc - r; x <= xc + r; x++){
			for(int z = zc - r; z <= zc + r; z++){
				double distance = ((x - xc) * (x - xc)) + ((z - zc) * (z - zc));
				if(distance < r*r){
					Location l = new Location(w, x, yc, z);
					Block b = l.getBlock();
					circle.add(b);
				}
			}
		}
		return circle;
	}

	public static void saveCircles(){
		File spawnerAreasFile = new File("plugins/SAO/spawners.yml");
		FileConfiguration spawnerAreas = new YamlConfiguration();
		if(spawnerAreasFile.exists()){
			try{
				/*for(List<Block> list : spawners){
					
				}*/
				spawnerAreas.save(spawnerAreasFile);
			}catch(Exception e){
				
			}
		}else{
			try {
				spawnerAreasFile.createNewFile();
				spawnerAreas.createSection("Spawners");
				spawnerAreas.set("Spawners", spawners);
				spawnerAreas.save(spawnerAreasFile);
			} catch (IOException e) {
				
			}
		}
	}
	
	public static void spawnMob(String mob, Location l){
		Entity e;
		Creature ce;
		switch(mob){
			case "FrenzyBoar":
				e = l.getWorld().spawnEntity(l, EntityType.PIG);
				ce = (Creature) e;
				//Location ceLoc = ce.getLocation();
				ce.setCustomName("Frenzy Boar");
				ce.setCustomNameVisible(true);
				ce.setMaxHealth(100d);
				ce.setHealth(ce.getMaxHealth());
				//ce.setTarget((LivingEntity) ce.getNearbyEntities(ceLoc.getX() + 5, ceLoc.getY() + 5, ceLoc.getZ() + 5).get(0));
				break;
			case "Spider":
				e = l.getWorld().spawnEntity(l, EntityType.SPIDER);
				ce = (Creature) e;
				ce.setCustomName("Spider Drone");
				ce.setCustomNameVisible(true);
				ce.setMaxHealth(100d);
				ce.setHealth(ce.getMaxHealth());
				ce.getEquipment().setItemInHand(customItem(new ItemStack(Material.IRON_SWORD), ChatColor.WHITE+"Steel Sword", ChatColor.RED+"DMG: 25"));
				ce.getEquipment().setBoots(silk);
				ce.getEquipment().setLeggings(silk);
				ce.getEquipment().setChestplate(silk);
				ce.getEquipment().setHelmet(silk);
				break;
			case "EliteSpider":
				e = l.getWorld().spawnEntity(l, EntityType.SPIDER);
				ce = (Creature) e;
				ce.setCustomName("Elite Spider");
				ce.setCustomNameVisible(true);
				ce.setMaxHealth(2500d);
				ce.setHealth(ce.getMaxHealth());
				break;
		}
	}
	
	public static void target(Creature e, Player target){
		e.setTarget((LivingEntity)target);
	}

}
