package worms.model;

import java.util.List;

import be.kuleuven.cs.som.annotate.Model;

public class Projectile extends MovableGameObject {

	public Projectile(Position position, double direction, double radius, double initialForce) {
		super(position, radius, radius, direction);
		this.initialForce = initialForce;
	}

	@Model @Override
	protected double getInitialForce(){
		return this.initialForce;
	}
	
	private final double initialForce;

	@Override
	protected boolean stopConditionDuringJump(Position inFlightPosition, boolean goingUp) {
		boolean adjacency = super.stopConditionDuringJump(inFlightPosition, goingUp);
		List<Worm> worms = this.getWorld().overlapWithWorm(inFlightPosition, this.getRadius());
		worms.remove(this.getWeapon().getWorm());
		boolean otherWormIsHit = !worms.isEmpty();
		return (adjacency || otherWormIsHit);
	}
		
	@Override
	protected boolean canJump(double timeStep){
		return true;
	}
	
	@Override
	public void jump(double timeStep){
		super.jump(timeStep);
		this.hitWorms();
		this.getWeapon().removeAsProjectile(this);
	}
	
	@Override
	protected String getCustomText(){
		return "shoot";
	}
	
	private void hitWorms(){
		List<Worm> worms = this.getWorld().overlapWithWorm(this.getPosition(), this.getRadius());
		worms.remove(this.getWeapon().getWorm());
		for(Worm hitWorm : worms)
			hitWorm.decreaseNumberOfHitPointsBy(this.getWeapon().getHitPointReduction());
	}
	
	/**
	 * Check whether the given weapon is a valid weapon for this projectile.
	 * @param 	weapon
	 * 			The weapon to be checked.
	 * @return	(weapon == null || weapon.canHaveAsProjectile(this))
	 */
	private boolean canHaveAsWeapon(Weapon weapon){
		return true;
	}
	
	/**
	 * Check whether this projectile has a proper weapon as its weapon.
	 * @return	(canHaveAsWeapon(getWeapon()) && getWeapon().hasAsProjectile(this))
	 */
	protected boolean hasProperWeapon(){
		return (canHaveAsWeapon(getWeapon()) && getWeapon().hasAsProjectile(this));
	}
	
	/**
	 * Return the weapon attached to this projectile.
	 */
	private Weapon getWeapon(){
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
	protected void setWeapon(Weapon weapon){
		if(! canHaveAsWeapon(weapon)){
			throw new IllegalArgumentException("Not a valid weapon");
		}
		else this.weapon = weapon;
	}
	
	Weapon weapon;
	
}
