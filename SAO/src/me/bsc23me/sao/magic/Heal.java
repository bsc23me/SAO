package me.bsc23me.sao.magic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.bsc23me.sao.Main;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Heal implements Listener{

	public static Main plugin;

	@EventHandler
	public void textSpell(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		String text = event.getMessage();
		if(text.equalsIgnoreCase("hel")){
			event.setCancelled(true);
			heal(player);
			player.sendMessage("you have been healed");
			player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 2);
		}else if(text.equalsIgnoreCase("mgc hel eng")){
			event.setCancelled(true);
			powerfulHeal(player);
			player.sendMessage("you have been healed");
			player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 2);
		}else if(text.equalsIgnoreCase("dmg hel eng lif hng")){
			event.setCancelled(true);
			player.setFoodLevel(0);
			ultimateHeal(player);
			player.sendMessage("you have been healed");
			player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 2);
		}else if(text.equalsIgnoreCase("dmg hel eng lif grd hmn die")){
			event.setCancelled(true);
			List<Entity> close = player.getNearbyEntities(5, 5, 5);
			List<Player> closePlayers = new ArrayList<Player>();
			for(Entity e : close){
				if(e instanceof Player){
					closePlayers.add((Player) e);
				}
			}
			
			if(!closePlayers.isEmpty()){
				Random r = new Random();
				Player target = closePlayers.get(r.nextInt(closePlayers.size() - 1));
				target.setHealth(0d);
				fullHeal(player);
				player.sendMessage("you have been healed");
				player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 2);
			}else{
				
			}
		}else{
			
		}
	}
	
	public static void heal(Player player){
		player.setHealth(player.getHealth() + 20);
	}
	
	public static void powerfulHeal(Player player){
		player.setHealth(player.getHealth() + 6860);
	}
	
	public static void ultimateHeal(Player player){
		player.setHealth(player.getHealth() + 20000);
	}
	
	public static void fullHeal(Player player){
		player.setHealth(player.getMaxHealth());
	}
	
	
	
	public static void crystalHeal(Player player){
		ItemStack held = player.getItemInHand();
			String name = held.getItemMeta().getDisplayName();
			name = ChatColor.stripColor(name);
			if(name.equalsIgnoreCase("Heal Crystal")){
				player.sendMessage("Heal Crystal Detected");

				player.playEffect(EntityEffect.WOLF_HEARTS);
				
				player.setHealth(player.getMaxHealth());
				if(player.getItemInHand().getAmount() > 1){
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
			if(held.getType() == Material.REDSTONE_BLOCK && held.hasItemMeta()){
				event.setCancelled(true);
				crystalHeal(player);
			}
		}
	}

}
