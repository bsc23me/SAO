package me.bsc23me.sao;

import org.bukkit.entity.LivingEntity;

public class CustomMob {

	static int health = 100;
	
	public static int getMobHealth(LivingEntity mob){
		return health;
	}
	
	public static void setMobHealth(LivingEntity mob, double hp){
		mob.setMaxHealth(hp);
	}
	
}
