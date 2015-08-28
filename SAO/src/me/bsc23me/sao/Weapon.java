package me.bsc23me.sao;

public enum Weapon {

	STEEL_SWORD("Steel Sword", 25),
	POISON_DAGGER("Poison Dagger", 5, new String[] {"Poison"});
	
	private String name;
	private int dmg;
	private String[] attrs;
	
	private Weapon(String name, int dmg){
		this.name = name;
		this.dmg = dmg;
	}
	
	private Weapon(String name, int dmg, String[] attrs){
		this.name = name;
		this.dmg = dmg;
		this.attrs = attrs;
	}
	
	public String getName(){
		return name;
	}
	
	public int getDamage(){
		return dmg;
	}
	
	public String[] getAttributes(){
		return attrs;
	}
	
}
