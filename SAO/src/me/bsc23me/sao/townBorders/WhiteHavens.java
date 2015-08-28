package me.bsc23me.sao.townBorders;

import me.bsc23me.sao.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WhiteHavens implements Listener{

	public static Main plugin;
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		World w = player.getWorld();
		Location to = event.getTo();
		Location from = event.getFrom();
		if(w == Bukkit.getWorld("MiddleEarth") && to.getX() < 100.5 && to.getX() > -87.5 && to.getZ() < 794.5 && to.getZ() > 673.5){
			if(!(from.getX() < 100.5 && from.getX() > -87.5 && from.getZ() < 794.5 && from.getZ() > 673.5)){
				if(!(player.isOp())){
					player.setOp(true);
					Bukkit.dispatchCommand(player, "title "+player.getName()+" title {text:'Entering: White Havens',color:green,bold:true,underlined:false,italic:false,strikethrough:false,obfuscated:false}");
					player.setOp(false);
				}else{
					Bukkit.dispatchCommand(player, "title "+player.getName()+" title {text:'Entering: White Havens',color:green,bold:true,underlined:false,italic:false,strikethrough:false,obfuscated:false}");
				}
			}
		}
	}
	
}
