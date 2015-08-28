package me.bsc23me.sao.mounts;

import net.minecraft.server.v1_8_R1.EntityHorse;
import net.minecraft.server.v1_8_R1.World;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.entity.Horse;

public class CustomPig extends EntityHorse{

	public CustomPig(World world) {
		super(world);
	}
	
	/*@Override
	public void e(float sideMot, float forMot){
		if(this.passenger == null || !(this.passenger instanceof EntityHuman)){
			super.e(sideMot, forMot);
			this.S = 0.5f;
			return;
		}
		
		this.lastYaw = this.yaw = this.passenger.yaw;
		this.pitch = this.passenger.pitch * 0.5f;
		
		this.setYawPitch(this.yaw, this.pitch);
		this.aI = this.aG = this.yaw;
	}
	*/
	public static Horse spawnCustomPig(Location loc){
		CustomPig pig = new CustomPig(((CraftWorld) loc.getWorld()).getHandle());
		pig.setPosition(loc.getX(), loc.getY(), loc.getZ());
		((CraftWorld)loc.getWorld()).getHandle().addEntity(pig);
		return (Horse) pig;
	}
	
}
