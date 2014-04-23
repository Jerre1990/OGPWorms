package worms.model;

import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @Invar	isValidName(getName())
 * @Invar	selected.size() <= 1
 * 			in	selected = getWorm().getAllWeapons()
 * 				for(Weapon weapon : selected)
 * 					if(weapon.isSelected == false)
 * 						selected.remove(weapon)
 * @Invar	getAmmo() >= -1
 * @Invar	getHitPointReduction() >= 0
 * @Invar	getCostInActionPoints() >= 0
 * @version 2.0
 * @author Jonas Thys & Jeroen Reinenbergh
 */

public class Weapon extends Identifiable{
	
	/**
	 * @param	name
	 * @param	ammo
	 * @param	hitPoints
	 * @param	actionPoints
	 * @param	radius
	 * @post 	new.getName() = name
	 * @post	if(ammo < 0)
	 * 				new.getAmmo() = -1
	 * 			else
	 * 				new.getAmmo() = ammo
	 * @post	if(hitPoints < 0)
	 * 				new.getHitPointReduction() = 0
	 * 			else
	 * 				new.getHitPointReduction() = hitPoints
	 * @post	if(actionPoints < 0)
	 * 				new.getCostInActionPoints() = 0
	 * 			else
	 * 				new.getCostInActionPoints() = actionPoints
	 * @post	new.getRadiusOfProjectile() = radius
	 * @post	new.isSelected() = false
	 * @effect	isValidName(new.getName())
	 * @effect	new.getAmmo() >= -1
	 * @effect	new.getHitPointReduction() >= 0
	 * @effect	new.getCostInActionPoints() >= 0
	 */
	public Weapon(String name, int ammo, int hitPoints, int actionPoints, double radius) {
		super(name);
		this.setAmmo(ammo);
		this.setHitPointReduction(hitPoints);
		this.setCostInActionPoints(actionPoints);
		this.setRadiusOfProjectile(radius);
		this.deselect();
	}
	
	/**
	 * @param	name
	 * @return	result == true
	 */
	@Override @Model @Raw
	protected boolean isValidName(String name){
		return true;
	}
	
	@Basic
	public boolean isSelected(){
		return this.selected;
	}
	
	/**
	 * @post	new.isSelected() = true
	 * @effect	for(Weapon weapon : getWorm().getAllWeapons)
	 * 				if(weapon != this)
	 * 					weapon.isSelected() = false
	 */
	public void select(){
		this.deselectAllWeapons();
		this.setSelected(true);
	}
	
	/**
	 * @post	new.isSelected() = false
	 */
	@Raw @Model
	private void deselect(){
		this.setSelected(false);
	}
	
	/**
	 * @param 	flag
	 * @post	new.isSelected() = flag
	 */
	@Raw @Model
	private void setSelected(boolean flag){
		this.selected = flag;
	}

	/**
	 * @post	for(Weapon weapon : getWorm().getAllWeapons)
	 * 				weapon.isSelected() = false
	 */
	@Model
	private void deselectAllWeapons(){
		List<Weapon> weapons = this.getWorm().getAllWeapons();
		for (Weapon weapon : weapons)
			weapon.deselect();
	}
	
	private boolean selected;
	
	@Basic
	public int getAmmo(){
		return this.ammo;
	}
	
	/**
	 * @param 	newAmmo
	 * @post	if(newAmmo < 0)
	 * 				new.getAmmo() = -1
	 * 			else
	 * 				new.getAmmo() = newAmmo
	 */
	@Raw @Model
	protected void setAmmo(int newAmmo){
		if(newAmmo < -1)
			newAmmo = -1;
		this.ammo = newAmmo;
	}
	
	private int ammo;
	
	@Basic
	public int getHitPointReduction(){
		return this.hitPointReduction;
	}
	
	/**
	 * @param 	hitPointsReducedUponHit
	 * @post	if(hitPointsReducedUponHit < 0)
	 * 				new.getHitPointReduction() = 0
	 * 			else
	 * 				new.getHitPointReduction() = hitPointsReducedUponHit
	 */
	@Raw @Model
	protected void setHitPointReduction(int hitPointsReducedUponHit){
		if (hitPointsReducedUponHit < 0)
			hitPointsReducedUponHit = 0;
		this.hitPointReduction = hitPointsReducedUponHit;
	}
	
	private int hitPointReduction;
	
	@Basic
	public int getCostInActionPoints(){
		return this.costInActionPoints;
	}
	
	/**
	 * @param 	cost
	 * @post	if(cost < 0)
	 * 				new.getCostInActionPoints() = 0
	 * 			else
	 * 				new.getCostInActionPoints() = cost
	 */
	@Raw @Model
	protected void setCostInActionPoints(int cost){
		if (cost < 0)
			cost = 0;
		this.costInActionPoints = cost;
	}
	
	private int costInActionPoints;
	
	/**
	 * @param 	propYield
	 * @return	if (getName().equals("Bazooka"))
	 * 				result == 2.5 + (0.07 * propYield)
	 * 			else
	 * 				result == 1.5
	 */
	@Model
	protected double getInitialForceOfProjectile(int propYield){
		if (this.getName().equals("Bazooka"))
			return (2.5 + (0.07 * propYield));
		else return 1.5;
	}
	
	@Basic @Model
	protected double getRadiusOfProjectile(){
		return this.radiusOfProjectile;
	}
	
	/**
	 * @param 	radiusOfProjectile
	 * @post	new.getRadiusOfProjectile() = radiusOfProjectile
	 */
	@Raw @Model
	protected void setRadiusOfProjectile(double radiusOfProjectile){
		this.radiusOfProjectile = radiusOfProjectile;
	}
	
	private double radiusOfProjectile;
	
	/**
	 * @param 	propulsionYield
	 * @effect	if(this.getAmmo() < 0)
	 * 				new.getAmmo() = -1
	 * 			else
	 * 				new.getAmmo() = this.getAmmo() - 1
	 * @effect	if(this.getWorm().getNumberOfActionPoints() <= getCostInActionPoints())
	 * 				new.getWorm().getNumberOfActionPoints() = 0
	 * 			else
	 * 				new.getWorm().getNumberOfActionPoints() = this.getWorm().getNumberOfActionPoints() - getCostInActionPoints()
	 * @effect	getWorm().getWorld().getObjects().contains(projectile)
	 * 				in	projectile = new Projectile(new Position(x,y), getWorm().getDirection(), getRadiusOfProjectile(), getInitialForceOfProjectile(propulsionYield))
	 * 					x = getWorm().getX() + (1.1 * getWorm().getRadius() * Math.cos(getWorm().getDirection()))
	 * 					y = getWorm().getY() + (1.1 * getWorm().getRadius() * Math.sin(getWorm().getDirection())
	 * @throws 	UnsupportedOperationException("Cannot shoot!")
	 * 		|	! canShoot()
	 */
	public void shoot(int propulsionYield) throws UnsupportedOperationException{
		if (!this.canShoot())
			throw new UnsupportedOperationException("Cannot shoot!");
		this.setAmmo(this.getAmmo() - 1);
		this.getWorm().decreaseNumberOfActionPointsBy(this.getCostInActionPoints());
		double relativeDistanceFromWorm = 0.1;
		double x = this.getWorm().getX() + ((1 + relativeDistanceFromWorm) * this.getWorm().getRadius() * Math.cos(this.getWorm().getDirection()));
		double y = this.getWorm().getY() + ((1 + relativeDistanceFromWorm) * this.getWorm().getRadius() * Math.sin(this.getWorm().getDirection()));
		this.addAsProjectile(new Projectile(new Position(x,y), this.getWorm().getDirection(), this.getRadiusOfProjectile(), this.getInitialForceOfProjectile(propulsionYield)));
	}
	
	/**
	 * @return	result == ((getAmmo() != 0) && (getWorm().getNumberOfActionPoints() >= 0) && (getWorm().getWorld().isPassable(getWorm().getPosition(), getWorm().getRadius())))
	 */
	@Model
	private boolean canShoot(){
		return ((this.getAmmo() != 0) && (this.getWorm().getNumberOfActionPoints() > 0) && (this.getWorm().getWorld().isPassable(this.getWorm().getPosition(), this.getWorm().getRadius())));
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
	 * @post	new.getWorm().getAllWeapons().contains(this))
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
