package worms.model;

import java.util.List;

public class Weapon extends Identifiable{

	public Weapon(String name, int propulsionYield) {
/**
 * Moet bij de aanmaak van worms verdeeld worden in types en niet hier in constructor: nog aanpassen!
 */
		super(name);
		if (name.equals("Rifle")){
			this.setAmmo(-1);
			this.setHitPointReduction(20);
			this.setCostInActionPoints(10);
			this.setInitialForceOfProjectile(1.5);
			this.setRadiusOfProjectile(this.getRadius(10, 7800));
		}
		else if (name.equals("Bazooka")){
			this.setAmmo(-1);
			this.setHitPointReduction(80);
			this.setCostInActionPoints(50);
			this.setInitialForceOfProjectile(2.5 + (propulsionYield * 0.07));
			this.setRadiusOfProjectile(this.getRadius(300, 7800));
		}
	}
	
	public int getAmmo(){
		return this.ammo;
	}
	
	protected void setAmmo(int newAmmo){
		this.ammo = newAmmo;
	}
	
	private int ammo;
	
	public int getHitPointReduction(){
		return this.hitPointReduction;
	}
	
	protected void setHitPointReduction(int hitPointsReducedUponHit){
		if (hitPointsReducedUponHit < 0)
			hitPointsReducedUponHit = 0;
		this.hitPointReduction = hitPointsReducedUponHit;
	}
	
	private int hitPointReduction;
	
	public int getCostInActionPoints(){
		return this.costInActionPoints;
	}
	
	protected void setCostInActionPoints(int cost){
		if (cost < 0)
			cost = 0;
		this.costInActionPoints = cost;
	}
	
	private int costInActionPoints;
	
	public double getInitialForceOfProjectile(){
		return this.initialForce;
	}
	
	protected void setInitialForceOfProjectile(double initialForce){
		this.initialForce = initialForce;
	}
	
	private double initialForce;
	
	public double getRadiusOfProjectile(){
		return this.radiusOfProjectile;
	}
	
	private double getRadius(double mass, double p){
		return Math.cbrt((mass * 3.0) / (p * 4.0 * Math.PI));
	}
	
	public void setRadiusOfProjectile(double radiusOfProjectile){
		this.radiusOfProjectile = radiusOfProjectile;
	}
	
	private double radiusOfProjectile;
	
	private boolean canShoot(){
		return (this.getAmmo() != 0);
	}
	
	public void shoot(){
		if (this.canShoot()){
			this.setAmmo(this.getAmmo() - 1);
			this.addAsProjectile(new Projectile(this.getWorm(), this.getRadiusOfProjectile(), this.getInitialForceOfProjectile()));
		}
	}
	
	public void adjustWeapon(){
		this.getProjectile().adjustProjectile();
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
