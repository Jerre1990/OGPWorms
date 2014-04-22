package worms.model;

import worms.util.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * @Invar	canHaveAsRadius(getRadius())
 * @Invar	isValidLowerBoundOfRadius(getLowerBoundOfRadius())
 * @Invar	isValidPosition(getPosition())
 * 
 * @version 2.0
 * @author Jonas Thys & Jeroen Reinenbergh
 */

public abstract class GameObject {
	
	/**
	 * @param	position
	 * @param	radius
	 * @param	lowerBound
	 * @post 	new.getRadius() = radius
	 * @post 	new.getLowerBoundOfRadius() = lowerBound
	 * @post 	new.getPosition() = position
	 * @effect	canHaveAsRadius(new.getRadius())
	 * @effect	isValidLowerBoundOfRadius(new.getLowerBoundOfRadius())
	 * @effect	isValidPosition(new.getPosition())
	 * @effect	new.getX() = position.getX()
	 * @effect	new.getY() = position.getY()
	 * @effect	getPosition().isValidCoordinate(new.getX())
	 * @effect	getPosition().isValidCoordinate(new.getY())
	 * @throws 	IllegalArgumentException("Invalid radius!")
	 * 		|	! canHaveAsRadius(radius)
	 * @throws	IllegalArgumentException("Invalid lower bound of radius!")
	 * 		|	! isValidLowerBoundOfRadius(lowerBound)
	 * @throws	IllegalArgumentException("Invalid position!")
	 * 		|	! isValidPosition(position)
	 */
	public GameObject(Position position, double radius, double lowerBound) throws IllegalArgumentException {
		this.setPosition(position);
		this.setRadius(radius);
		if (!this.isValidLowerBoundOfRadius(lowerBound))
			throw new IllegalArgumentException("Invalid lower bound of radius!");
		this.lowerBoundOfRadius = lowerBound;
	}

	/**
	 * @return	result == getPosition.getX()
	 */
	public double getX() {
		return this.getPosition().getX();
	}

	/**
	 * @return	result == getPosition.getY()
	 */
	public double getY() {
		return this.getPosition().getY();
	}

	@Basic @Model
	protected Position getPosition() {
		return this.position;
	}
	
	/**
	 * @param 	otherPosition
	 * @param 	otherRadius
	 * @return	result == (getPosition().distanceFromPosition(otherPosition) < (getRadius() + otherRadius))
	 */
	protected boolean partialOverlapWith(Position otherPosition, double otherRadius){
		return (this.getPosition().distanceFromPosition(otherPosition) < (this.getRadius() + otherRadius));
	}

	/**
	 * @param 	position
	 * @return	result == (position != null)
	 */
	@Model
	protected boolean isValidPosition(Position position){
		return (position != null);
	}

	/**
	 * @param 	newPosition
	 * @post	new.getPosition = newPosition
	 * @effect	new.getX() = newPosition.getX()
	 * @effect	new.getY() = newPosition.getY()
	 * @effect	isValidPosition(new.getPosition())
	 * @throws 	IllegalArgumentException("Invalid position!")
	 * 		|	! isValidPosition(newPosition)
	 */
	@Raw
	protected void setPosition(Position newPosition) throws IllegalArgumentException {
		if(!this.isValidPosition(newPosition))
			throw new IllegalArgumentException("Invalid position!");
		position = newPosition;
	}

	/**
	 * @param	x
	 * @param	y
	 * @post	new.getPosition = new Position(x,y)
	 * @effect	new.getX() == x
	 * @effect	new.getY() == y
	 * @effect	new.getPosition().isValidCoordinate(new.getX())
	 * @effect	new.getPosition().isValidCoordinate(new.getY())
	 * @effect	isValidPosition(new.getPosition())
	 * @throws 	IllegalArgumentException("Invalid x-coordinate!")
	 * 		|	! getPosition().isValidCoordinate(x)
	 * @throws 	IllegalArgumentException("Invalid y-coordinate!")
	 * 		|	! getPosition().isValidCoordinate(y)
	 */
	protected void setPosition(double x, double y) throws IllegalArgumentException {
		this.setPosition(new Position (x,y));
	}
	
	/**
	 * @param	x
	 * @post	new.getX() == x
	 * @post	new.getY() == this.getY()
	 * @effect	new.getPosition = new Position(x,this.getY())
	 * @effect	new.getPosition().isValidCoordinate(new.getX())
	 * @effect	new.getPosition().isValidCoordinate(new.getY())
	 * @effect	isValidPosition(new.getPosition())
	 * @throws 	IllegalArgumentException("Invalid x-coordinate!")
	 * 		|	! getPosition().isValidCoordinate(x)
	 */
	protected void setX(double x) throws IllegalArgumentException{
		this.setPosition(x,getY());
	}

	/**
	 * @param	y
	 * @post	new.getY() == y
	 * @post	new.getX() == this.getX()
	 * @effect	new.getPosition = new Position(this.getX(),y)
	 * @effect	new.getPosition().isValidCoordinate(new.getX())
	 * @effect	new.getPosition().isValidCoordinate(new.getY())
	 * @effect	isValidPosition(new.getPosition())
	 * @throws 	IllegalArgumentException("Invalid y-coordinate!")
	 * 		|	! getPosition().isValidCoordinate(y)
	 */
	protected void setY(double y) throws IllegalArgumentException{
		this.setPosition(getX(),y);
	}

	private Position position;

	@Basic
	public double getRadius() {
		return this.radius;
	}

	@Basic @Immutable
	public double getLowerBoundOfRadius() {
		return this.lowerBoundOfRadius;
	}

	/**
	 * @param	radius
	 * @post	new.getRadius() == radius
	 * @effect	canHaveAsRadius(new.getRadius())
	 * @throws 	IllegalArgumentException("Invalid radius!")
	 * 		|	! canHaveAsRadius(radius)
	 */
	@Raw
	public void setRadius(double radius) throws IllegalArgumentException {
		if (!this.canHaveAsRadius(radius))
			throw new IllegalArgumentException("Invalid radius!");
		this.radius = radius;
	}

	/**
	 * @param	number
	 * @return	result == (!Double.isNaN(number))
	 */
	@Raw
	protected boolean isValidNumber(Double number) {
		return (!Double.isNaN(number));
	}

	/**
	 * @param	lowerBound
	 * @return	result == isValidNumber(lowerBound)
	 */
	@Raw
	protected boolean isValidLowerBoundOfRadius(double lowerBound){
		return this.isValidNumber(lowerBound);
	}

	/**
	 * @param	radius
	 * @return	result == (isValidNumber(radius) && (radius >= getLowerBoundOfRadius()) && (radius <= Double.MAX_VALUE))
	 */
	@Model @Raw
	protected boolean canHaveAsRadius(double radius){
		return (this.isValidNumber(radius) && Util.fuzzyGreaterThanOrEqualTo(radius, this.getLowerBoundOfRadius()) && (radius <= Double.MAX_VALUE));
	}

	private double radius;

	private final double lowerBoundOfRadius;

	@Basic	@Raw
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * @return	result == (this.canHaveAsWorld(this.getWorld()) && getWorld().hasAsGameObject(this))
	 */
	@Raw
	protected boolean hasProperWorld(){
		return (this.canHaveAsWorld(this.getWorld()) && getWorld().hasAsGameObject(this));
	}

	/**
	 * @param 	gameWorld
	 * @return	result == ((gameWorld != null) && gameWorld.canHaveAsGameObject(this))
	 */
	@Raw
	protected boolean canHaveAsWorld(World gameWorld){
		return ((gameWorld != null) && gameWorld.canHaveAsGameObject(this));
	}
	
	/**
	 * @param	gameWorld
	 * @post	new.getWorld() == gameWorld
	 * @post	this.canHaveAsWorld(new.getWorld())
	 * @throws 	IllegalArgumentException("Invalid game world!")
	 * 		|	(! canHaveAsWorld(gameWorld) || ! gameWorld.hasAsGameObject(this))
	 */
	@Raw
	protected void setWorld(World gameWorld) throws IllegalArgumentException {
		if (! canHaveAsWorld(gameWorld))
			throw new IllegalArgumentException("Invalid world!");
		else this.world = gameWorld;
	}
	
	private World world;
	
	/**
	 * @post	new.isRemovedFromWorld()
	 * @effect	for each object in getAllGameObjects:
	 *			if (! object.isTerminated())
	 *				then getWorld().removeAsGameObject(object) 	
	 */
	public void removeFromWorld() {
		this.world = null;
	}
	
}
