package worms.model;

import java.util.List;

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
	
	boolean isTerminated;

	public void terminate(){
		this.isTerminated =true;
		worm.removeWeapon(this);
		projectile.removeWeapon(this);
	}

	public boolean canHaveAsWorm(Worm worm){
		return (worm != null && worm.canHaveAsWeapon(this));
	}
	
	public Worm getWorm(){
		return this.worm;
	}
	
	public boolean hasAsWorm(Worm worm) throws IllegalArgumentException {
		if (worm == this.worm){
			return true;
		}
		else throw new IllegalArgumentException("wrong worm");
	}
	
	public boolean hasProperWorm(){
		if (! canHaveAsWorm(worm))
			return false;
		if (! worm.getAllWeapons().contains(this))
			return false;
		else
			return true;
			
	}
	
	public void addAsWorm(Worm worm) throws IllegalArgumentException {
		if (canHaveAsWorm(worm)){
			this.worm = worm;
			worm.addWeapon(this);
		}
		else throw new IllegalArgumentException("Not a good worm");
	}
	
	public void removeAsWorm(Worm worm) throws IllegalArgumentException {
		if (this.worm == worm){
			worm.removeWeapon(this);
			this.worm = null;
		
		}
		else throw new IllegalArgumentException("wrong worm");
	}
	
	Worm worm;
	
	public boolean canHaveAsProjectile(Projectile projectile){
		return (projectile != null && projectile.canHaveAsWeapon(this));
	}
	
	public Projectile getProjectile(){
		return this.projectile;
	}
	
	public boolean hasAsProjectile(Projectile projectile) throws IllegalArgumentException {
		if (this.projectile == projectile)
			return true;
		else throw new IllegalArgumentException("Not a projectile");
	}
	
	public void addAsProjectile(Projectile projectile) throws IllegalArgumentException {
		if (canHaveAsProjectile(projectile)){
			this.projectile = projectile;
			projectile.setWeapon(this);
		}
		else throw new IllegalArgumentException("Not a good worm");
	}
	
	public void removeAsProjectile(Projectile projectile) throws IllegalArgumentException {
		if (this.projectile == projectile){
			projectile.setWeapon(null);
			this.projectile = null;
		
		}
		else throw new IllegalArgumentException("wrong worm");
	}
	
	Projectile projectile;
	
	
	
}
