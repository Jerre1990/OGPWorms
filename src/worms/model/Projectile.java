package worms.model;

import be.kuleuven.cs.som.annotate.Model;

public class Projectile extends MovableGameObject {

	public Projectile(Worm controllingWorm, double projectileRadius, double initialForce) {
		super((controllingWorm.getX() + (1.1 * controllingWorm.getRadius() * Math.cos(controllingWorm.getDirection()))), (controllingWorm.getY() + (1.1 * controllingWorm.getRadius() * Math.sin(controllingWorm.getDirection()))), projectileRadius, projectileRadius, controllingWorm.getDirection());
		this.initialForce = initialForce;
	}
	
	public void adjustProjectile(){
		this.setX(1.1 * this.getWeapon().getWorm().getRadius() * Math.cos(this.getWeapon().getWorm().getDirection()));
		this.setY(1.1 * this.getWeapon().getWorm().getRadius() * Math.sin(this.getWeapon().getWorm().getDirection()));
		this.setDirection(this.getWeapon().getWorm().getDirection());
	}

	@Model @Override
	protected double getInitialForce(){
		return this.initialForce;
	}
	
	private final double initialForce;

	@Override
	protected boolean stopConditionDuringJump(Position inFlightPosition, boolean goingUp) {
		boolean adjacency = super.stopConditionDuringJump(inFlightPosition, goingUp);
		boolean overlap = this.getWorld().partialOverlapWithOtherWorm(inFlightPosition, this.getRadius(), this.getWeapon().getWorm());
		return (adjacency || overlap);
	}
	
	/**
	 * Check whether the given weapon is a valid weapon for this projectile.
	 * @param 	weapon
	 * 			The weapon to be checked.
	 * @return	(weapon == null || weapon.canHaveAsProjectile(this))
	 */
	public boolean canHaveAsWeapon(Weapon weapon){
		return (weapon == null || weapon.canHaveAsProjectile(this));
	}
	
	/**
	 * Check whether this projectile has a proper weapon as its weapon.
	 * @return	(canHaveAsWeapon(getWeapon()) && getWeapon().hasAsProjectile(this))
	 */
	public boolean hasProperWeapon(){
		return (canHaveAsWeapon(getWeapon()) && getWeapon().hasAsProjectile(this));
	}
	
	/**
	 * Return the weapon attached to this projectile.
	 */
	public Weapon getWeapon(){
		return this.weapon;
	}
	
	/**
	 * Set the given weapon as weapon of this projectile.
	 * @param 	weapon
	 * 			The weapon to be attached.
	 * @post	new.getWeapon() == weapon
	 * @throws	IllegalArgumentException
	 * 			(! canHaveAsWeapon(weapon))
	 */
	public void setWeapon(Weapon weapon){
		if(! canHaveAsWeapon(weapon)){
			throw new IllegalArgumentException("Not a valid weapon");
		}
		else this.weapon = weapon;
	}
	
	Weapon weapon;
	
}
