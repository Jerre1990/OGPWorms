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
	/**
	 * Check whether the given worm is a valid worm for this weapon.
	 * @param 	worm
	 * 			The worm to be checked.
	 * @return	(worm != null && worm.canHaveAsWeapon(this))
	 */
	public boolean canHaveAsWorm(Worm worm){
		return (worm != null && worm.canHaveAsWeapon(this));
	}
	/**
	 * Return the worm attached to this weapon.
	 */
	public Worm getWorm(){
		return this.worm;
	}
	
	/**
	 * Check whether the given worm is attached to this weapon.
	 * @param	worm
	 * 			The worm to be checked.
	 * @return	(worm == this.worm)
	 */
	public boolean hasAsWorm(Worm worm){
		return (worm == this.worm);
	}
	/**
	 * Check whether this team has a proper worm attached to it.
	 * @return	(canHaveAsWorm(worm) || worm.getAllWeapons().contains(this))
	 */
	public boolean hasProperWorm(){
		if (! canHaveAsWorm(worm))
			return false;
		if (! worm.getAllWeapons().contains(this))
			return false;
		else
			return true;	
	}
	
	/**
	 * Add the given worm to this weapon.
	 * @param 	worm
	 * 			The worm to be added.
	 * @post	new.getWorm() = worm;
	 * @post	(new worm).getAllWeapons().contains(this)
	 * @throws 	IllegalArgumentException
	 * 			(! canHaveAsWorm(worm))
	 */
	public void addAsWorm(Worm worm) throws IllegalArgumentException {
		if (canHaveAsWorm(worm)){
			this.worm = worm;
			worm.setWeapon(this);
		}
		else throw new IllegalArgumentException("Not a good worm");
	}
	
	/**
	 * Remove the given worm from this weapon.
	 * @param 	worm
	 * 			The worm to be removed.
	 * @post	new.getWorm() == null
	 * @post	! (new worm).getAllWeapons().contains(this)
	 * @throws 	IllegalArgumentException
	 * 			(! this.worm == worm)
	 */
	public void removeAsWorm(Worm worm) throws IllegalArgumentException {
		if (this.worm == worm){
			worm.getAllWeapons().remove(this);
			this.worm = null;
		
		}
		else throw new IllegalArgumentException("wrong worm");
	}
	
	Worm worm;
	/**
	 * Check whether the given projectile is a valid projectile for this world.
	 * @param 	projectile
	 * 			The projectile to be checked.
	 * @return	(projectile != null && projectile.canHaveAsWeapon(this)&& worm == null)
	 */
	public boolean canHaveAsProjectile(Projectile projectile){
		return (projectile != null && projectile.canHaveAsWeapon(this) && worm != null);
	}
	
	/**
	 * Return the projectile attached to this weapon.
	 */
	public Projectile getProjectile(){
		return this.projectile;
	}
	
	/**
	 * Check whether the given projectile is attached to this weapon.
	 * @param 	projectile
	 * 			The projectile to be checked.
	 * @return	(this.projectile == projectile)
	 */
	public boolean hasAsProjectile(Projectile projectile) throws IllegalArgumentException {
		return (this.projectile == projectile);

	}
	
	/**
	 * Add the given projectile to this weapon as its projectile.
	 * @param 	projectile
	 * 			The projectile to be added.
	 * @post	new.getProjectile() == projectile
	 * @post	(new projectile).getWeapon() == this
	 * @throws 	IllegalArgumentException
	 * 			(! canHaveAsProjectile(projectile))
	 */
	public void addAsProjectile(Projectile projectile) throws IllegalArgumentException {
		if (canHaveAsProjectile(projectile)){
			this.projectile = projectile;
			projectile.setWeapon(this);
			worm.getWorld().addAsGameObject(projectile); 
		}
		else throw new IllegalArgumentException("Not a good projectile");
	}
	
	/**
	 * Remove the given projectile from this weapon.
	 * @param 	projectile
	 * 			The projectile to be removed.
	 * @post	new.getProjectile() == null
	 * @post	(new projectile).getWeapon() == null
	 * @throws 	IllegalArgumentException
	 * 			(! this.projectile == projectile)
	 */
	public void removeAsProjectile(Projectile projectile) throws IllegalArgumentException {
		if (this.projectile == projectile){
			projectile.setWeapon(null);
			this.projectile = null;
		
		}
		else throw new IllegalArgumentException("wrong projectile");
	}
	
	Projectile projectile;
	
	
	
}
