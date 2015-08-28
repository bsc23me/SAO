package me.bsc23me.sao.mounts;

import net.minecraft.server.v1_8_R1.EntityEnderDragon;
import net.minecraft.server.v1_8_R1.EntityHuman;
import net.minecraft.server.v1_8_R1.World;

public class CustomDragon extends EntityEnderDragon{

	public CustomDragon(World world) {
		super(world);
	}

	@Override
	public void e(float sideMot, float forMot){
		if(this.passenger == null || !(this.passenger instanceof EntityHuman)){
			super.e(sideMot, forMot);
			return;
		}
		
		//EntityHuman human = (EntityHuman) this.passenger;
		
		this.lastYaw = this.yaw = this.passenger.yaw;
		this.pitch = this.passenger.pitch * 0.5f;
		
		this.f(this.yaw, this.pitch);
		this.aO = (int) this.yaw;
	}
	
}
