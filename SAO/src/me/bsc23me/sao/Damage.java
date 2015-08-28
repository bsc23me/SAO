package me.bsc23me.sao;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Damage implements Listener{

	public static Main plugin;

	static List<Material> weaponmats = new ArrayList<Material>();

	static List<String> weapons = new ArrayList<String>();

	private void Weaponmats(){
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

	@EventHandler
	public void onEntityDamageEntity(EntityDamageByEntityEvent event){
		Weaponmats();

		//Player Damage

		if(event.getDamager() instanceof Player){
			Player player = (Player) event.getDamager();
			ItemStack held = player.getItemInHand();
			for(Material m : weaponmats){
				if(held.getType() == m){
					if(held.hasItemMeta()){
						String name = held.getItemMeta().getDisplayName();
						String s = ChatColor.stripColor(name);
						switch(s){
						//custom blades
						case "Sting":
							event.setDamage(525d);
							break;
						case "Glamdring":
							event.setDamage(10000d);
							break;
						case "Orcrist":
							event.setDamage(10000d);
							break;
							//steel upgrades
						case "Steel Sword":
							event.setDamage(25d);
							break;
						case "+1 Steel Sword":
							event.setDamage(26d);
							break;
						case "+2 Steel Sword":
							event.setDamage(29d);
							break;
						case "+3 Steel Sword":
							event.setDamage(33d);
							break;
						case "+4 Steel Sword":
							event.setDamage(40d);
							break;
						case "+5 Steel Sword":
							event.setDamage(50d);
							break;
						case "+6 Steel Sword":
							event.setDamage(65d);
							break;
						case "+7 Steel Sword":
							event.setDamage(87d);
							break;
						case "+8 Steel Sword":
							event.setDamage(122d);
							break;
						case "+9 Steel Sword":
							event.setDamage(177d);
							break;
						case "+10 Steel Sword":
							event.setDamage(266d);
							break;
						case "Plain Raiper":
							break;
						default:
							event.setDamage(1d);
							break;
						}
					}else{

					}
				}else{

				}
			}

			//Monster Damage

		}else if(event.getDamager() instanceof Monster){
			Monster monster = (Monster) event.getDamager();
			String name = monster.getCustomName();
			if(name != null){
				switch(name){
				case "Wild Spider":
					event.setDamage(20d);
					break;
				case "Elite Spider":
					event.setDamage(20d);
					break;
				case "Bandit":
					event.setDamage(20d);
					break;
				case "Bandit Chief":
					event.setDamage(20d);
					break;
				case "":
					event.setDamage(20d);
					break;
				default:
					event.setDamage(event.getDamage());
					break;
				}
			}else{
				event.setDamage(1d);
			}
		}
	}

}
