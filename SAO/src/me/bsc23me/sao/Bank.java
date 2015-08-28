package me.bsc23me.sao;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Bank implements Listener{

	public static Main plugin;

	Inventory inv;
	
	@EventHandler
	public void onEnderChestClick(PlayerInteractEvent event){
		Player player = event.getPlayer();
		Action a = event.getAction();
		Block b = event.getClickedBlock();
		if(a.equals(Action.RIGHT_CLICK_BLOCK)){
			if(b.getType() == Material.ENDER_CHEST){
				event.setCancelled(true);

				File playerDataFile = new File("plugins/SAO/players/"+player.getName()+".yml");
				FileConfiguration playerData = new YamlConfiguration();

				try{
					playerData.load(playerDataFile);
					int size = 9;
					if(CustomPlayer.getPlayerMoney(player) < 10000){
						inv = Bukkit.createInventory(null, size, "Bank Chest         Mythril: "+CustomPlayer.getPlayerMoney(player));
					}else if(CustomPlayer.getPlayerMoney(player) < 11000){
						inv = Bukkit.createInventory(null, size, "Bank Chest          Mythril: 10k");
					}else if(CustomPlayer.getPlayerMoney(player) < 12000){
						inv = Bukkit.createInventory(null, size, "Bank Chest          Mythril: 11k");
					}else if(CustomPlayer.getPlayerMoney(player) < 13000){
						inv = Bukkit.createInventory(null, size, "Bank Chest          Mythril: 12k");
					}else if(CustomPlayer.getPlayerMoney(player) < 14000){
						inv = Bukkit.createInventory(null, size, "Bank Chest          Mythril: 13k");
					}else if(CustomPlayer.getPlayerMoney(player) < 15000){
						inv = Bukkit.createInventory(null, size, "Bank Chest          Mythril: 14k");
					}else if(CustomPlayer.getPlayerMoney(player) < 16000){
						inv = Bukkit.createInventory(null, size, "Bank Chest          Mythril: 15k");
					}else if(CustomPlayer.getPlayerMoney(player) < 17000){
						inv = Bukkit.createInventory(null, size, "Bank Chest          Mythril: 16k");
					}else if(CustomPlayer.getPlayerMoney(player) < 18000){
						inv = Bukkit.createInventory(null, size, "Bank Chest          Mythril: 17k");
					}else if(CustomPlayer.getPlayerMoney(player) < 19000){
						inv = Bukkit.createInventory(null, size, "Bank Chest          Mythril: 18k");
					}else if(CustomPlayer.getPlayerMoney(player) < 20000){
						inv = Bukkit.createInventory(null, size, "Bank Chest          Mythril: 19k");
					}else if(CustomPlayer.getPlayerMoney(player) < 21000){
						inv = Bukkit.createInventory(null, size, "Bank Chest          Mythril: 20k");
					}else{
						inv = Bukkit.createInventory(null, size, "Bank Chest            Mythril: \u221e");
					}
					//List<ItemStack> enderRaw = new ArrayList<ItemStack>();
					//List<String> configRaw = playerData.getStringList("Player.bank");
					if(inv.getContents().length > 0){
					inv.clear();
					}else{
						
					}
					for(int i = 0; i < size; i++){
						ItemStack item = playerData.getItemStack("Player.bank."+i);
						inv.setItem(i, item);
					}
					player.openInventory(inv);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	@EventHandler
	public void onEnderChestClose(InventoryCloseEvent event){
		Player player = (Player) event.getPlayer();
		Inventory inv = event.getInventory();

		File playerDataFile = new File("plugins/SAO/players/"+player.getName()+".yml");
		FileConfiguration playerData = new YamlConfiguration();

		if(inv.getName().contains("Bank Chest ")){
			try{
				playerData.load(playerDataFile);
				ItemStack[] enderRaw = inv.getContents();
				//List<String> enderChest = new ArrayList<String>();
				for(int i = 0; i < inv.getContents().length; i++){
					if(enderRaw[i] != null){
						ItemStack item = enderRaw[i];
						if(playerData.contains("Player.bank."+i)){
							playerData.set("Player.bank."+i, item);
							playerData.save(playerDataFile);
						}else{
							playerData.createSection("Player.bank."+i);
							playerData.set("Player.bank."+i, item);
							playerData.save(playerDataFile);
						}
					}else{
						if(playerData.contains("Player.bank."+i)){
							playerData.set("Player.bank."+i, null);
							playerData.save(playerDataFile);
						}else{
							playerData.createSection("Player.bank."+i);
							playerData.set("Player.bank"+i, null);
							playerData.save(playerDataFile);
						}
					}
				}
			}catch(Exception e){

			}
		}

	}



}
