package worms.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * A class of movable game objects involving a position, a radius, a lower bound of that radius and a direction.
 * 
 * @Invar	Each movable game object can have its radius as its radius.
 * 		|	this.canHaveAsRadius(this.getRadius())
 * @Invar	Each movable game object has a valid direction.
 * 		|	this.isValidDirection(this.getDirection())
 * 
 * 
 * @version 2.0
 * @author Jonas Thys & Jeroen Reinenbergh
 * 
 */

public abstract class MovableGameObject extends GameObject{
	
	/**
	 * Initialize this new movable game object with given x-coordinate, given y-coordinate, given radius, given lower bound of this radius and given direction.
	 * 
	 * @param	x
	 * 			The x-coordinate of the movable game object expressed in metres.
	 * @param	y
	 * 			The y-coordinate of the movable game object expressed in metres.
	 * @param	radius
	 * 			The radius of the spherical body of the movable game object expressed in metres.
	 * @param	lowerBound
	 * 			The lower bound of the radius of the spherical body of the movable game object expressed in metres.
	 * @param	direction
	 * 			The direction towards which the movable game object faces expressed in radians.
	 * @Pre		The given direction is a valid direction for any movable game object
	 * 		|	this.isValidDirection(direction)
	 * @post	The new movable game object is initialized as a new game object with given x-coordinate, given y-coordinate, given radius and given lower bound of this radius.
	 * 		|	new.getX() = x
	 * 		|	new.getY() = y
	 * 		|	new.getRadius() = radius
	 * 		|	new.getLowerBoundOfRadius() = lowerBound
	 * @post 	The new direction of this movable game object is equal to the given direction.
	 * 		|	new.getDirection() = direction
	 * @effect	The new position of this movable game object is an effective position.
	 * 		|	new.getPosition() != null
	 * @effect	This movable game object can have the new radius as its radius.
	 * 		|	this.canHaveAsRadius(new.getRadius())
	 * @effect	The new lower bound of the radius of this movable game object is a valid lower bound for any movable game object.
	 * 		|	this.isValidLowerBoundOfRadius(new.getLowerBoundOfRadius())
	 * @effect	The new x-coordinate of this movable game object is a valid x-coordinate for any movable game object.
	 * 		|	this.getPosition.isValidCoordinate(new.getX())
	 * @effect	The new y-coordinate of this movable game object is a valid y-coordinate for any movable game object.
	 * 		|	this.getPosition.isValidCoordinate(new.getY())
	 * @effect	The new direction of this movable game object is a valid direction for any movable game object.
	 * 		|	this.isValidDirection(new.getDirection())
	 * @effect	The newly calculated mass of this movable game object is a valid mass for any movable game object.
	 * 		|	this.isValidMass(new.getMass())
	 * @throws 	IllegalArgumentException("Invalid radius!")
	 * 			This movable game object cannot have the given radius as its radius.
	 * 		|	! this.canHaveAsRadius(radius)
	 * @throws	IllegalArgumentException("Invalid lower bound of radius!")
	 * 			The given lower bound is not a valid lower bound for the radius of any movable game object.
	 * 		|	! this.isValidLowerBoundOfRadius(lowerBound)
	 * @throws 	IllegalArgumentException("Invalid x-coordinate!")
	 * 			The given x-coordinate is not a valid coordinate for any movable game object.
	 * 		|	! this.getPosition().isvalidCoordinate(x)
	 * @throws 	IllegalArgumentException("Invalid y-coordinate!")
	 * 			The given y-coordinate is not a valid coordinate for any movable game object.
	 * 		|	! this.getPosition().isValidCoordinate(y)
	 */
	public MovableGameObject(double x, double y, double radius, double lowerBound, double direction){
		super(x, y, radius, lowerBound);
		this.setDirection(direction);
	}
	
	/**
	 * Check whether the calculated mass is a possible mass for any movable game object.
	 * 
	 * @param	radius
	 * 			The radius used in the calculations of the mass to check.
	 * @return	True if and only if the calculated mass is a possible positive number.
	 * 		|	result == ((this.getMass(radius) >= 0) && this.isValidNumber(this.getMass(radius)))
	 */
	@Model
	protected boolean isValidMass(double radius){
		return ((this.getMass(radius) >= 0) && this.isValidNumber(getMass(radius)));
	}
	
	/**
	 * Check whether this movable object can have the given radius as its radius.
	 * 
	 * @param	radius
	 * 			The radius to check.
	 * @return	True if and only if this game object can have the given radius as its radius
	 * 			and if the newly calculated mass is a valid mass for any movable game object.
	 * 		|	result == (super.canHaveAsRadius(radius) && this.isValidMass(radius))
	 */
	@Override @Model
	protected boolean canHaveAsRadius(double radius){
		return (super.canHaveAsRadius(radius) && this.isValidMass(radius));
	}
	
	/**
	 * Return the mass of a movable game object with given radius.
	 * @param	radius
	 * 			The given radius.
	 * @return 	Mass of the movable game object based on calculations involving the given radius.
	 * 		|	result == (1062 * (4 / 3) * Math.PI * Math.pow(radius, 3))
	 */
	@Model
	private double getMass(double radius) {
		double p = 1062;
		return ((p * 4.0 * Math.PI * Math.pow(radius, 3)) / 3.0);
	}
	
	/**
	 * Return the mass of the movable game object.
	 * @return 	Mass of the worm based on calculations involving the radius of the worm.
	 * 		|	result == this.getMass(this.getRadius())
	 */
	public double getMass() {
		return this.getMass(this.getRadius());
	}
	
	/**
	 * Return the direction of the movable game object.
	 * 	The direction expresses the direction towards which the movable game object is faced.
	 */
	@Basic
	public double getDirection() {
		return this.direction;
	}
	
	/**
	 * Check whether the given direction is a valid direction for any movable game object.
	 * 
	 * @param	direction
	 * 			The direction to check.
	 * @return	True if and only if the given direction is a valid number
	 * 			and lies between zero and two times pi, including the former and excluding the latter.
	 * 		|	result == (this.isValidNumber(direction) && (direction >= 0) && (direction < (2 * pi)))
	 */
	@Model
	protected boolean isValidDirection(double direction){
		return (this.isValidNumber(direction) && (direction >= 0) && (direction < (2 * Math.PI)));
	}

	/**
	 * Set the direction of this movable game object to the given direction
	 * 
	 * @param	direction
	 * 			The new direction of this movable game object.
	 * @Pre		The given direction is a valid direction
	 * 		|	this.isValidDirection(direction)
	 * @post	The new direction of this movable game object is equal to the given direction.
	 * 		|	new.getDirection() == direction
	 */
	protected void setDirection(double direction) {
		assert this.isValidDirection(direction) :
			"Precondition: Valid direction";
		this.direction = direction;
	}

	/**
	 * Turn the direction of this movable game object by the given angle.
	 * 
	 * @param 	turnByAngle
	 * 			The angle by which this movable game object will be turned.
	 * @Pre		The sum of the movable game object's current direction and the given angle to turn by is a valid direction for any movable game object.
	 * 		|	this.isValidDirection(this.getDirection() + turnByAngle)
	 * @post	The new direction of this movable game object is equal to the old direction incremented with the given angle to turn by.
	 * 		|	new.getDirection() == this.getDirection() + turnByAngle
	 */
	@Model
	protected void turn(double turnByAngle){
		assert this.isValidDirection(this.getDirection() + turnByAngle) :
			"Precondition: Valid sum of current direction and given angle to turn by";		
		setDirection(this.getDirection() + turnByAngle);
	}

	/**
	 * Variable registering the direction of this movable game object.
	 */
	private double direction;
	
	/**
	 * Return the initial force to be exerted on the movable game object.
	 * @return 	Initial force to be exerted on the movable game object.
	 */
	@Model
	protected abstract double getInitialForce();
	
	/**
	 * Return the initial velocity of this movable game object during a jump.
	 * 
	 * @return	The initial velocity of the movable game object equals the quotient of the movable game object's initial force and the movable game object's mass, divided by two.
	 * 		|	result == (this.getInitialForce() / this.getMass()) * 0.5
	 */
	@Model
	private double initialVelocity(){
		return ((this.getInitialForce() / this.getMass()) * 0.5);
	}
	
	@Model
	protected abstract boolean stopConditionDuringJump(Position inFlightPosition);
		
	/**
	 * Return the time passed after a jump of this movable game object.
	 * 
	 * @param	timeStep
	 * 			An elementary time interval during which it may be assumed that the movable game object will not completely move through a piece of impassable terrain.
	 * @return	The time passed after a jump of this movable game object during which it did not pass completely through any impassable terrain.
	 * 		|	
	 */
	public double jumpTime(double timeStep){
		double jumpTime = 0.0;
		Position newPosition = this.getPosition();
		while (this.getWorld().isPassable(newPosition,this.getRadius())){
			jumpTime += timeStep;
			newPosition = new Position(this.jumpStepOnXAxis(jumpTime + timeStep),this.jumpStepOnYAxis(jumpTime + timeStep));
			if (this.stopConditionDuringJump(newPosition))
				break;
		}
		return jumpTime;
	}
	
	/**
	 * Return the x-coordinate of this movable game object during a jump after the given amount of time that has already passed.
	 * 
	 * @param	timePassed
	 * 			The amount of time that has already passed during the jump.
	 * @return	The in-jump x-coordinate of this movable game object after the given amount of time that has passed is equal to the movable game object's initial x-coordinate
	 * 			incremented with the product of its initial velocity, the cosinus of its direction and the given time that has passed.
	 * 		|	result == this.getX() + (this.initialVelocity() * Math.cos(direction) * timePassed)
	 */	
	@Model
	private double jumpStepOnXAxis(double timePassed){
		return (this.getX() + (this.initialVelocity() * Math.cos(this.getDirection()) * timePassed));
	}
	
	/**
	 * Return the y-coordinate of this movable game object during a jump after the given amount of time that has already passed.
	 * 
	 * @return	The in-jump y-coordinate of this movable game object after the given amount of time that has passed is equal to the movable game object's initial y-coordinate
	 * 			incremented with the product of its initial velocity, the sinus of its direction and the given time that has passed,
	 * 			and decremented with the product of Earth's standard acceleration coefficient, the squared time that has passed and the constant 0.5.
	 * 		|	result == this.getY() + ((this.initialVelocity() * Math.sin(direction) * timePassed) - ((1/2) * EARTHS_STANDARD_ACCELERATION * timePassed^2))
	 */		
	@Model
	private double jumpStepOnYAxis(double timePassed){
		return (this.getY() + ((this.initialVelocity() * Math.sin(this.getDirection()) * timePassed) - ((0.5) * EARTHS_STANDARD_ACCELERATION * Math.pow(timePassed, 2))));
	}	
	
	/**
	 * Return the coordinates of this movable game object during a jump after the given amount of time that has already passed.
	 * 
	 * @return	The array of in-jump coordinates of this movable game object after the given amount of time that has passed consists of the appropriate x-coordinate and y-coordinate respectively.
	 * 			Both coordinates equal the distance traveled on the appropriate axis during the jump after the given time that has already passed.
	 * 		|	result[0] == jumpStepOnXAxis(timePassed)
	 * 		|	result[1] == jumpStepOnYAxis(timePassed)
	 */	
	public double[] jumpStep(double timePassed){
		double [] coordinatesAfterJumpStep = {this.jumpStepOnXAxis(timePassed),this.jumpStepOnYAxis(timePassed)};
		return coordinatesAfterJumpStep;
	}
	
	protected boolean canJump(double timeStep){
		return (this.jumpTime(timeStep) != 0.0);
	}
	
	/**
	 * Make this movable game object jump in the current direction.
	 * 
	 * @param	timeStep
	 * 			An elementary time interval during which it may be assumed that the movable game object will not completely move through a piece of impassable terrain.
	 * @post	The new X-coordinate of the movable game object is equal the distance traveled on the x-axis during the time of the jump.
	 * 		|	new.getX() == jumpStepOnXAxis(jumpTime(timeStep))
	 * @post	The new Y-coordinate of the movable game object is equal the distance traveled on the y-axis during the time of the jump.
	 * 		|	new.getY() == jumpStepOnYAxis(jumpTime(timeStep))
	 * @throws 	UnsupportedOperationException("Cannot jump!")
	 * 			The movable game object cannot jump.
	 * 		|	! this.canJump()
	 */	
	public void jump(double timeStep){
		if (this.canJump(timeStep))
			throw new UnsupportedOperationException("Cannot jump!");
		this.setPosition(this.jumpStepOnXAxis(this.jumpTime(timeStep)), this.jumpStepOnYAxis(this.jumpTime(timeStep)));
	}
	
	/**
	 * Constant representing the approximated value of Earth's standard acceleration coefficient.
	 */	
	public static double EARTHS_STANDARD_ACCELERATION = 9.80665;

}
