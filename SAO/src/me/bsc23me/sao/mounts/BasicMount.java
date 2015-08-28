package me.bsc23me.sao.mounts;

import me.bsc23me.sao.Main;
import net.minecraft.server.v1_8_R1.GenericAttributes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftLivingEntity;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BasicMount implements Listener{

	public static Main plugin;

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Action a = event.getAction();
		if(a == Action.RIGHT_CLICK_BLOCK){
			if(player.getItemInHand().getType() == Material.SADDLE){
				event.setCancelled(true);

				if(player.getItemInHand().containsEnchantment(Enchantment.LURE)){
					for(Entity e : player.getNearbyEntities(5, 5, 5)){
						if(e instanceof Horse){
							if(((Horse) e).getCustomName().contains(player.getName())){
								e.remove();
							}
						}
					}
					player.getItemInHand().removeEnchantment(Enchantment.LURE);
				}else{
					player.getItemInHand().addUnsafeEnchantment(Enchantment.LURE, 1);
					Horse e = (Horse) player.getWorld().spawnEntity(event.getClickedBlock().getLocation().add(0, 1, 0), EntityType.HORSE);
					e.setLeashHolder(player);
					e.setAdult();
					e.setVariant(Variant.HORSE);
					if(player.isOp()){
						e.setCustomName(player.getName()+"'s Mearas");
						e.setColor(Color.WHITE);
					}else{
						e.setCustomName(player.getName()+"'s horse");
					}
					if(player.getItemInHand().getItemMeta().getLore() == null){
						
					}else if(ChatColor.stripColor(player.getItemInHand().getItemMeta().getLore().get(1)).equalsIgnoreCase("Rideable")){
						e.getInventory().setSaddle(new ItemStack(Material.SADDLE));
						e.setOwner(player);
						e.setMaxHealth(20d);
						if(player.isOp()){
							((CraftLivingEntity) e).getHandle().getAttributeInstance(GenericAttributes.d).setValue(0.5);
							e.setJumpStrength(1);
						}
					}else{
						
					}
				}
			}
		}else if(a == Action.RIGHT_CLICK_AIR){
			if(player.getItemInHand().getType() == Material.SADDLE){
				event.setCancelled(true);
				if(player.getItemInHand().containsEnchantment(Enchantment.LURE)){
					for(Entity e : player.getNearbyEntities(5, 5, 5)){
						if(e instanceof Horse){
							if(((Horse) e).getCustomName().contains(player.getName())){
								e.remove();
							}
						}
					}
					player.getItemInHand().removeEnchantment(Enchantment.LURE);
				}else{
					player.sendMessage("Must place on block");
				}
			}
		}
	}

	@EventHandler
	public void onPlayerEntityInteract(PlayerInteractEntityEvent event){
		Player player = event.getPlayer();
		Entity e = event.getRightClicked();
		if(e instanceof Horse){
			if(((Horse) e).getCustomName().contains(player.getName())){
				event.setCancelled(true);
				if(player.getItemInHand().getType() == Material.SADDLE){
					if(player.getItemInHand().containsEnchantment(Enchantment.LURE)){
						e.remove();
						player.getItemInHand().removeEnchantment(Enchantment.LURE);
					}else{
						
					}
				}else{
					if(((Horse) e).getOwner() != null && ((Horse) e).getOwner().equals(player)){
						e.setPassenger(player);
					}else{
						
					}
				}
			}
		}
	}

}
