package worms.model;

import be.kuleuven.cs.som.annotate.Model;

public class Projectile extends MovableGameObject {

	public Projectile(Weapon weapon, double radiusOfProjectile, double initialForce) {
		super((1.1 * weapon.getWorm().getRadius() * Math.cos(weapon.getWorm().getDirection())), (1.1 * weapon.getWorm().getRadius() * Math.sin(weapon.getWorm().getDirection())), radiusOfProjectile, 0, weapon.getWorm().getDirection());
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
		
	}
	
	public boolean canHaveAsWeapon(Weapon weapon){
		return (weapon == null || weapon.canHaveAsProjectile(this));
	}
	
	
	public boolean hasProperWeapon(){
		return ( canHaveAsWeapon(getWeapon()) && getWeapon().hasAsProjectile(this));
	}
	
	public Weapon getWeapon(){
		return this.weapon;
	}
	
	public void setWeapon(Weapon weapon){
		if(! canHaveAsWeapon(weapon)){
			throw new IllegalArgumentException("Not a valid weapon");
		}
		else this.weapon = weapon;
	}
	
	Weapon weapon;
	
}
