package me.bsc23me.sao.cutscenes;

import me.bsc23me.sao.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Intro implements Listener{

	public static Main plugin;

	private static int task;

	@SuppressWarnings("static-access")
	public static void play(final Player player){
		player.sendMessage(ChatColor.RED+"Please do not move during cutscene!");
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin.getPlugin(Main.class), new Runnable(){
			public void run(){
				player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 42, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 40, 1));
				player.setGameMode(GameMode.SPECTATOR);
				player.teleport(new Location(Bukkit.getWorld("MiddleEarth"), -7.5, 99.5, 890.5, 180, -90));

				task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin.getPlugin(Main.class), new Runnable(){
					int i = 0;
					public void run(){
						if(i < 45){
							player.teleport(new Location(Bukkit.getWorld("MiddleEarth"), -7.5, 99.5, 890.5, -180+i*2, -90+i*2));
							i++;
						}else{
							Bukkit.getScheduler().cancelTask(task);
							task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin.getPlugin(Main.class), new Runnable(){
								double i = 0;
								public void run(){
									if(i < 0.51){
										player.teleport(new Location(Bukkit.getWorld("MiddleEarth"), -7.5+i*2, 99.5+i, 890.5, -90, 0));
										i += 0.1;
									}else{
										Bukkit.getScheduler().cancelTask(task);
										Bukkit.getScheduler().scheduleSyncDelayedTask(plugin.getPlugin(Main.class), new Runnable(){
											public void run(){
												player.setGameMode(GameMode.ADVENTURE);
												if(!(player.isOp())){
													player.setOp(true);
													Bukkit.dispatchCommand(player, "title "+player.getName()+" title {text:'Welcome to',color:green,bold:true,underlined:false,italic:false,strikethrough:false,obfuscated:false}");
													Bukkit.dispatchCommand(player, "title "+player.getName()+" subtitle {text:'Middle Earth',color:gold,bold:true,underlined:true,italic:false,strikethrough:false,obfuscated:false}");
													player.setOp(false);
												}else{
													Bukkit.dispatchCommand(player, "title "+player.getName()+" title {text:'Welcome to',color:green,bold:true,underlined:false,italic:false,strikethrough:false,obfuscated:false}");
													Bukkit.dispatchCommand(player, "title "+player.getName()+" subtitle {text:'Middle Earth',color:gold,bold:true,underlined:true,italic:false,strikethrough:false,obfuscated:false}");
												}
											}
										}, 20);
									}
								}
							}, 20, 1);
						}
					}
				}, 60, 1);
			}
		}, 5*20);
	}
}
