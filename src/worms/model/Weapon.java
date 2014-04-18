package worms.model;

public class Weapon extends Identifiable{

	public Weapon(String name) {
		super(name);
		if (name.equals("Rifle")){
			this.setAmmo(-1);
		}
		else if (name.equals("Bazooka")){
			this.setAmmo(-1);
		}
		else this.setAmmo(0);
	}
	
	public int getAmmo(){
		return this.ammo;
	}
	
	protected void setAmmo(int newAmmo){
		this.ammo = newAmmo;
	}
	
	private int ammo;
	
	public boolean canShoot(){
		return (this.getAmmo() != 0);
	}
	
	public void shoot(){
		if (this.canShoot())
			this.setAmmo(this.getAmmo() - 1);
	}
}
