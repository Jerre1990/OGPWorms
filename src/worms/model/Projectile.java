package worms.model;

import be.kuleuven.cs.som.annotate.Model;

public class Projectile extends MovableGameObject {

	public Projectile(Worm controllingWorm, double radiusOfProjectile, double initialForce) {
		super((1.1 * controllingWorm.getRadius() * Math.cos(controllingWorm.getDirection())), (1.1 * controllingWorm.getRadius() * Math.sin(controllingWorm.getDirection())), radiusOfProjectile, 0, controllingWorm.getDirection());
		this.controllingWorm = controllingWorm;
		this.initialForce = initialForce;
	}
	
	public void adjustProjectile(Worm controllingWorm){
		this.setX(1.1 * controllingWorm.getRadius() * Math.cos(controllingWorm.getDirection()));
		this.setY(1.1 * controllingWorm.getRadius() * Math.sin(controllingWorm.getDirection()));
		this.setDirection(controllingWorm.getDirection());
	}
	
	public Worm getControllingWorm(){
		return this.controllingWorm;
	}
		
	private final Worm controllingWorm;

	@Model @Override
	protected double getInitialForce(){
		return this.initialForce;
	}
	
	private final double initialForce;

	@Override
	protected boolean stopConditionDuringJump(Position inFlightPosition, boolean goingUp) {
		
	}
	
}
