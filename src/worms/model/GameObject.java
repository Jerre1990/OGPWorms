package worms.model;

import java.util.Collection;
import java.util.HashSet;

import worms.util.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * A class of game objects involving a position, a radius and a lower bound of that radius.
 * 
 * @Invar	The position of each game object is effective.
		|	this.getPosition() != null
 * @Invar	Each game object can have its radius as its radius.
 * 		|	this.canHaveAsRadius(this.getRadius())
 * @Invar	Each game object has a valid lower bound of its radius.
 * 		|	this.isValidLowerBoundOfRadius(this.getLowerBoundOfRadius())
 * 
 * @version 2.0
 * @author Jonas Thys & Jeroen Reinenbergh
 * 
 */

public abstract class GameObject {
	
	/**
	 * Initialize this new game object with given x-coordinate, given y-coordinate, given radius and given lower bound of this radius.

	 * @param	x
	 * 			The x-coordinate of the game object expressed in metres.
	 * @param	y
	 * 			The y-coordinate of the game object expressed in metres.
	 * @param	radius
	 * 			The radius of the spherical body of the game object expressed in metres.
	 * @param	lowerBound
	 * 			The lower bound of the radius of the spherical body of the game object expressed in metres.
	 * @post 	The new radius of this game object is equal to the given radius.
	 * 		|	new.getRadius() = radius
	 * @post 	The new lower bound of the radius of this game object is equal to the given lower bound.
	 * 		|	new.getLowerBoundOfRadius() = lowerBound
	 * @post 	The new position of this game object contains the given x-coordinate and y-coordinate.
	 * 		|	new.getX() = x
	 * 		|	new.getY() = y
	 * @effect	The new position of this game object is an effective position.
	 * 		|	new.getPosition() != null
	 * @effect	This game object can have the new radius as its radius.
	 * 		|	this.canHaveAsRadius(new.getRadius())
	 * @effect	The new lower bound of the radius of this game object is a valid lower bound for any game object.
	 * 		|	this.isValidLowerBoundOfRadius(new.getLowerBoundOfRadius())
	 * @effect	The new x-coordinate of this game object is a valid x-coordinate for any game object.
	 * 		|	this.getPosition.isValidCoordinate(new.getX())
	 * @effect	The new y-coordinate of this game object is a valid y-coordinate for any game object.
	 * 		|	this.getPosition.isValidCoordinate(new.getY())
	 * @throws 	IllegalArgumentException("Invalid radius!")
	 * 			This game object cannot have the given radius as its radius.
	 * 		|	! this.canHaveAsRadius(radius)
	 * @throws	IllegalArgumentException("Invalid lower bound of radius!")
	 * 			The given lower bound is not a valid lower bound for the radius of any game object.
	 * 		|	! this.isValidLowerBoundOfRadius(lowerBound)
	 * @throws 	IllegalArgumentException("Invalid x-coordinate!")
	 * 			The given x-coordinate is not a valid coordinate for any game object.
	 * 		|	! this.getPosition().isvalidCoordinate(x)
	 * @throws 	IllegalArgumentException("Invalid y-coordinate!")
	 * 			The given y-coordinate is not a valid coordinate for any game object.
	 * 		|	! this.getPosition().isValidCoordinate(y)
	 */
	public GameObject(double x, double y, double radius, double lowerBound) throws IllegalArgumentException {
		this.setX(x);
		this.setY(y);
		this.setRadius(radius);
		this.setLowerBoundOfRadius(lowerBound);
	}

	/**
	 * Return the position of the game object.
	 * 	The position expresses the position at which the game object is located
	 * 	and contains the x-coordinate and the y-coordinate of the game object respectively.
	 */
	@Basic
	public Position getPosition() {
		return this.position;
	}

	/**
	 * Return the x-coordinate of the game object.
	 * @return	X-coordinate of the game object, derived from its position.
	 * 		|	result == this.getPosition.getX()
	 */
	public double getX() {
		return this.getPosition().getX();
	}

	/**
	 * Return the y-coordinate of the game object.
	 * @return	Y-coordinate of the game object, derived from its position.
	 * 		|	result == this.getPosition.getY()
	 */
	public double getY() {
		return this.getPosition().getY();
	}

	/**
	 * Set the position of this game object to the given position
	 * 
	 * @param	position
	 * 			The new position of this game object.
	 * @post	The new x-coordinate of this game object is equal to the given x-coordinate.
	 * 		|	new.getX() == x
	 * @post	The new y-coordinate of this game object is equal to the given y-coordinate.
	 * 		|	new.getY() == y
	 * @effect	The new x-coordinate of this game object is a valid coordinate for any game object.
	 * 		|	new.getPosition().isValidCoordinate(new.getX())
	 * @effect	The new y-coordinate of this game object is a valid coordinate for any game object.
	 * 		|	new.getPosition().isValidCoordinate(new.getY())
	 * @throws 	IllegalArgumentException("Invalid x-coordinate!")
	 * 			The given x-coordinate is not a valid coordinate for the position of any game object.
	 * 		|	! this.getPosition().isValidCoordinate(x)
	 * @throws 	IllegalArgumentException("Invalid y-coordinate!")
	 * 			The given y-coordinate is not a valid coordinate for the position of any game object.
	 * 		|	! this.getPosition().isValidCoordinate(y)
	 */
	protected void setPosition(double x, double y) throws IllegalArgumentException {
		position = new Position (x,y);
	}
	
	protected void setPosition(Position newPosition) throws IllegalArgumentException {
		position = newPosition;
	}
	
	/**
	 * Set the x-coordinate of this game object to the given x-coordinate
	 * 
	 * @param	x
	 * 			The new x-coordinate of this game object.
	 * @post	The new x-coordinate of this game object is equal to the given x-coordinate.
	 * 		|	new.getX() == x
	 * @post	The new y-coordinate of this game object is equal to the old y-coordinate.
	 * 		|	new.getY() == this.getY()
	 * @effect	The new x-coordinate of this game object is a valid coordinate for any game object.
	 * 		|	new.getPosition().isValidCoordinate(new.getX())
	 * @effect	The new y-coordinate of this game object is a valid coordinate for any game object.
	 * 		|	new.getPosition().isValidCoordinate(new.getY())
	 * @throws 	IllegalArgumentException("Invalid x-coordinate!")
	 * 			The given x-coordinate is not a valid coordinate for the position of any game object.
	 * 		|	! this.getPosition().isValidCoordinate(x)
	 */
	protected void setX(double x) throws IllegalArgumentException{
		this.setPosition(x,getY());
	}

	/**
	 * Set the y-coordinate of this game object to the given y-coordinate
	 * 
	 * @param	y
	 * 			The new y-coordinate of this game object.
	 * @post	The new y-coordinate of this game object is equal to the given y-coordinate.
	 * 		|	new.getY() == y
	 * @post	The new x-coordinate of this game object is equal to the old x-coordinate.
	 * 		|	new.getX() == this.getX()
	 * @effect	The new x-coordinate of this game object is a valid coordinate for any game object.
	 * 		|	new.getPosition().isValidCoordinate(new.getX())
	 * @effect	The new y-coordinate of this game object is a valid coordinate for any game object.
	 * 		|	new.getPosition().isValidCoordinate(new.getY())
	 * @throws 	IllegalArgumentException("Invalid y-coordinate!")
	 * 			The given y-coordinate is not a possible coordinate for the position of any game object.
	 * 		|	! this.getPosition().isValidCoordinate(y)
	 */
	protected void setY(double y) throws IllegalArgumentException{
		this.setPosition(getX(),y);
	}

	/**
	 * Variable registering the position of this game object, consisting of an x-coordinate and a y-coordinate.
	 */
	private Position position;

	/**
	 * Return the radius of the game object.
	 * 	The radius expresses the radius of the spherical body of the game object.
	 */
	@Basic
	public double getRadius() {
		return this.radius;
	}

	/**
	 * Return the lower bound of the radius of the game object.
	 * 	The lower bound of the radius expresses the lower bound of the radius of the spherical body of the game object.
	 */
	@Basic
	public double getLowerBoundOfRadius() {
		return this.lowerBoundOfRadius;
	}

	/**
	 * Check whether this game object can have the given radius as its radius.
	 * 
	 * @param	radius
	 * 			The radius to check.
	 * @return	True if and only if the given radius is a valid number,
	 * 			if it is not smaller than the lower bound of the radius of this game object
	 * 			and if it is finite.
	 * 		|	result == (this.isValidNumber(radius) && (radius >= this.getLowerBoundOfRadius()) && (radius <= Double.MAX_VALUE))
	 */
	@Model
	protected boolean canHaveAsRadius(double radius){
		return (this.isValidNumber(radius) && Util.fuzzyGreaterThanOrEqualTo(radius, this.getLowerBoundOfRadius()) && (radius <= Double.MAX_VALUE));
	}
	
	/**
	 * Check whether the given lower bound is a valid lower bound of a radius for any game object.
	 * 
	 * @param	lowerBound
	 * 			The lower bound of a radius to check.
	 * @return	True if and only if the given lower bound of a radius is a valid number
	 * 		|	result == this.isValidNumber(lowerBound)
	 */
	@Model
	protected boolean isValidLowerBoundOfRadius(double lowerBound){
		return this.isValidNumber(lowerBound);
	}

	/**
	 * Check whether the given number is a valid number for any Double-variable.
	 * 
	 * @param	number
	 * 			The number to check.
	 * @return	True if and only if the given number is not categorized as 'Not A Number' in Double-representation.
	 * 		|	result == (!Double.isNaN(number))
	 */
	@Model
	protected boolean isValidNumber(Double number) {
		return (!Double.isNaN(number));
	}

	/**
	 * Set the radius of this game object to the given radius
	
	 * @param	radius
	 * 			The new radius of this game object.
	 * @post	The new radius of this game object is equal to the given radius.
	 * 		|	new.getRadius() == radius
	 * @post	This game object can have the new radius as its radius.
	 * 		|	this.canHaveAsRadius(new.getRadius())
	 * @throws 	IllegalArgumentException("Invalid radius!")
	 * 			This game object cannot have the given radius as its radius.
	 * 		|	! this.canHaveAsRadius(radius)
	 */
	public void setRadius(double radius) throws IllegalArgumentException {
		if (!this.canHaveAsRadius(radius))
			throw new IllegalArgumentException("Invalid radius!");
		else{
			this.radius = radius;
		}
	}
	
	/**
	 * Set the lower bound of the radius of this game object to the given lower bound
	 * 
	 * @param	lowerBound
	 * 			The new lower bound of the radius of this game object.
	 * @post	The new lower bound of the radius of this game object is equal to the given lower bound.
	 * 		|	new.getLowerBoundOfRadius() == lowerBound
	 * @post	The new lower bound of the radius of this game object is a valid lower bound for any game object.
	 * 		|	this.isValidLowerBoundOfRadius(new.getLowerBoundOfRadius())
	 * @throws 	IllegalArgumentException("Invalid lower bound of radius!")
	 * 			The given lower bound is not a valid lower bound for any game object.
	 * 		|	! this.isValidLowerBoundOfRadius(lowerBound)
	 */
	protected void setLowerBoundOfRadius(double lowerBound) throws IllegalArgumentException {
		if (!this.isValidLowerBoundOfRadius(lowerBound))
			throw new IllegalArgumentException("Invalid lower bound of radius!");
		else{
			this.lowerBoundOfRadius = lowerBound;
		}
	}

	/**
	 * Variable registering the radius of this game object.
	 */
	private double radius;

	/**
	 * Variable registering the lower bound of the radius of this game object.
	 */	
	private double lowerBoundOfRadius;
	
	protected boolean partialOverlapWith(GameObject other){
		return (this.getPosition().distanceFromPosition(other.getPosition()) < (this.getRadius() + other.getRadius()));
	}

	/**
	 * Return the current game world of this game object.
	 * 	The current game world of a game object is the game world in which this game object currently resides.
	 */
	@Basic	
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Check whether this game object can have the given game world as its game world.
	 * 
	 * @param 	gameWorld
	 * 			The game world to check.
	 * @return	result == ((gameWorld != null) && gameWorld.canHaveAsGameObject(this))
	 */
	protected boolean canHaveAsWorld(World gameWorld){
		return ((gameWorld != null) && gameWorld.canHaveAsGameObject(this));
	}
	
	public boolean hasProperWorld(){
		return (this.canHaveAsWorld(this.getWorld()) || (this.getWorld() == null));
	}
	
	/**
	 * Set the game world of this game object to the given game world
	 * 
	 * @param	gameWorld
	 * 			The new game world of this game object.
	 * @post	The new game world of this game object is equal to the given game world.
	 * 		|	new.getWorld() == gameWorld
	 * @post	This game object can have the given game world as its game world.
	 * 		|	this.canHaveAsWorld(new.getWorld())
	 * @throws 	IllegalArgumentException("Invalid game world!")
	 * 			This game object cannot have the given game world as its game world.
	 * 		|	! this.canHaveAsWorld(gameWorld)
	 */
	public void setWorld(World gameWorld) throws IllegalArgumentException {
		if (!canHaveAsWorld(gameWorld))
			throw new IllegalArgumentException("Invalid world!");
		else this.world = gameWorld;
	}
	
	/**
	 * Variable registering the game world in which this game object currently resides.
	 */
	private World world;
	
	/**
	 * Remove this game object from its game world.
	 * 
	 * @post	new.isRemovedFromWorld()
	 * 			The game object no longer resides in any game world.
	 * @effect	for each object in getAllGameObjects:
	 *			if (! object.isTerminated())
	 *				then getWorld().removeAsGameObject(object) 	
	 */
	public void removeFromWorld() {
		if (!this.isRemovedFromWorld()){
			getWorld().removeAsGameObject(this);
			this.removedFromWorld = true;
		}
	}
	
	/**
	 * Check whether the game object has been removed from its game world.
	 * 	If the game object has been removed from its game world, it no longer resides in any game world.
	 */
	@Basic
	public boolean isRemovedFromWorld(){
		return this.removedFromWorld;
	}
	
	private boolean removedFromWorld;
	
	
}
