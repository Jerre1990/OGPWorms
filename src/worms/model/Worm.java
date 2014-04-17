package worms.model;

import java.util.Arrays;

import be.kuleuven.cs.som.annotate.*;
import worms.util.*;

/**
 * 
 * A class of worms involving a position, a radius, a lower bound of that radius, a direction, a name, a current number of action points and a current number of hit points.
 * 
 * @Invar	The name of each worm is a valid name.
 * 		|	isValidName(getName())
 * @Invar	Each worm can have its current number of action points as its current number of action points.
 * 		|	0 <= getNumberOfActionPoints() <= getMaxNumberOfActionPoints()
 * @Invar	Each worm can have its current number of hit points as its current number of hit points.
 * 		|	0 <= getNumberOfHitPoints() <= getMaxNumberOfHitPoints()
 * 
 * 
 * @version 2.0
 * @author Jonas Thys & Jeroen Reinenbergh
 * 
 */

public class Worm extends MovableGameObject {

	/**
	 * Initialize this new worm with given x-coordinate, given y-coordinate, given radius, given lower bound of this radius, given direction and given name.
	 * 
	 * @param	x
	 * 			The x-coordinate of the worm expressed in metres.
	 * @param	y
	 * 			The y-coordinate of the worm expressed in metres.
	 * @param	radius
	 * 			The radius of the spherical body of the worm expressed in metres.
	 * @param	direction
	 * 			The direction towards which the worm faces expressed in radians.
	 * @Pre		The given direction is a valid direction for any worm
	 * 		|	this.isValidDirection(direction)
	 * @post	The new worm is initialized as a new movable game object with given x-coordinate, given y-coordinate, given direction, given radius and 0.25 as lower bound of this radius.
	 * 		|	new.getX() = x
	 * 		|	new.getY() = y
	 * 		|	new.getRadius() = radius
	 * 		|	new.getLowerBoundOfRadius() = 0.25
	 * 		|	new.getDirection() = direction
	 * @post 	The new name of this worm is equal to the given name.
	 * 		|	new.getName() = name
	 * @post	The new current number of action points of this worm is equal to the maximum number of action points of this worm.
	 * 		|	new.getNumberOfActionPoints() = new.getMaxNumberOfActionPoints()
	 * @post	The new current number of hit points of this worm is equal to the maximum number of hit points of this worm.
	 * 		|	new.getNumberOfHitPoints() = new.getMaxNumberOfHitPoints()
	 * @effect	The new name of this worm is a valid name for any worm.
	 * 		|	this.isValidName(new.getName())
	 * @effect	This worm can have the new number of action points as its current number of action points.
	 * 		|	0 <= new.getNumberOfActionPoints <= new.getMaxNumberOfActionPoints
	 * @effect	This worm can have the new number of hit points as its current number of hit points.
	 * 		|	0 <= new.getNumberOfActionPoints <= new.getMaxNumberOfActionPoints
	 * @effect	The new position of this worm is an effective position.
	 * 		|	new.getPosition() != null
	 * @effect	This worm can have the new radius as its radius.
	 * 		|	this.canHaveAsRadius(new.getRadius())
	 * @effect	The new lower bound of the radius of this worm is a valid lower bound for any worm.
	 * 		|	this.isValidLowerBoundOfRadius(new.getLowerBoundOfRadius())
	 * @effect	The new x-coordinate of this worm is a valid x-coordinate for any worm.
	 * 		|	this.getPosition.isValidCoordinate(new.getX())
	 * @effect	The new y-coordinate of this worm is a valid y-coordinate for any worm.
	 * 		|	this.getPosition.isValidCoordinate(new.getY())
	 * @effect	The new direction of this worm is a valid direction for any worm.
	 * 		|	this.isValidDirection(new.getDirection())
	 * @effect	The newly calculated mass of this worm is a valid mass for any worm.
	 * 		|	this.isValidMass(new.getMass())
	 * @throws 	IllegalArgumentException("Name is not valid!")
	 * 			The given name is not a valid name for any worm.
	 * 		|	! this.isValidName(name)
	 * @throws 	IllegalArgumentException("Invalid radius!")
	 * 			This worm cannot have the given radius as its radius.
	 * 		|	! this.canHaveAsRadius(radius)
	 * @throws	IllegalArgumentException("Invalid lower bound of radius!")
	 * 			The given lower bound is not a valid lower bound for the radius of any worm.
	 * 		|	! this.isValidLowerBoundOfRadius(lowerBound)
	 * @throws 	IllegalArgumentException("Invalid x-coordinate!")
	 * 			The given x-coordinate is not a valid coordinate for any worm.
	 * 		|	! this.getPosition().isvalidCoordinate(x)
	 * @throws 	IllegalArgumentException("Invalid y-coordinate!")
	 * 			The given y-coordinate is not a valid coordinate for any worm.
	 * 		|	! this.getPosition().isValidCoordinate(y)
	 * 
	 */
	
	public Worm (double x, double y, double radius, double direction, String name) throws IllegalArgumentException {
		super(x ,y ,radius ,0.25, direction);
		this.setName(name);
		this.setNumberOfActionPoints(this.getMaxNumberOfActionPoints());
		this.setNumberOfHitPoints(this.getMaxNumberOfHitPoints());
	}

	/**
	 * Return the name of the worm.
	 * 	The name expresses the alphabetic identification of the worm.
	 */
	@Basic
	public String getName() {
		return name;
	}	
	
	/**
	 * Check whether the given name is a valid name for any worm.
	 * 
	 * @param	name
	 * 			The name to check.
	 * @return	True if and only if the given name contains at least 2 charachters,
	 * 			starts with an uppercase letter and only contains letters, quotes and spaces.
	 * 		|	result == (name.matches("[A-Z]"+"[A-Za-z\"\' ]+"))
	 */
	@Model
	private boolean isValidName(String name) {
		return name.matches("[A-Z]"+"[A-Za-z\"\' ]+");
	}	
	
	/**
	 * Set the name of this worm to the given name

	 * @param	name
	 * 			The new name of this worm.
	 * @post	The new name of this worm is equal to the given name.
	 * 		|	new.getName() == name
	 * @effect	The new name of this worm is a valid name for any worm.
	 * 		|	this.isValidName(new.getName())
	 * @throws 	IllegalArgumentException("Name is not valid!")
	 * 			The name of this worm is not a valid name for any worm.
	 * 		|	! this.isValidName(name)
	 */	
	public void setName(String name) throws IllegalArgumentException {
		if (!this.isValidName(name))
			throw new IllegalArgumentException("Name is not valid!");
		else this.name = name;
	}
	
	/**
	 * Variable registering the name of this worm.
	 */
	private String name;

	/**
	 * Check whether this worm can have the given radius as its radius.
	 * 
	 * @param	radius
	 * 			The radius to check.
	 * @return	True if and only if this movable game object can have the given radius as its radius
	 * 			and if the newly calculated maximum number of action points and hit points of this worm are positive.
	 * 		|	result == (super.canHaveAsRadius(radius) && (getMaxNumberOfActionPoints(radius) >= 0) && (getMaxNumberOfHitPoints(radius) >= 0))
	 */
	@Model @Override
	protected boolean canHaveAsRadius(double radius){
		return (super.canHaveAsRadius(radius) && (getMaxNumberOfActionPoints(radius) >= 0) && (getMaxNumberOfHitPoints(radius) >= 0));
	}

	/**
	 * Set the radius of this worm to the given radius

	 * @param	radius
	 * 			The new radius of this worm.
	 * @post	The new radius of this worm is equal to the given radius.
	 * 		|	new.getRadius() == radius
	 * @post	This worm can have its new radius as its radius.
	 * 		|	this.canHaveAsRadius(new.getRadius())
	 * @effect	The new mass of this worm is equal to the newly calculated mass.
	 * 			new.getMass() == this.getMass(new.getRadius())
	 * @effect	The new maximum number of action points of this worm is equal to the newly calculated maximum number of action points.
	 * 		|	new.getMaxNumberOfActionPoints() == this.getMaxNumberOfActionPoints(new.getRadius())
	 * @effect	The new maximum number of hit points of this worm is equal to the newly calculated maximum number of hit points.
	 * 		|	new.getMaxNumberOfHitPoints() == this.getMaxNumberOfHitPoints(new.getRadius())
	 * @effect	The new number of action points of this worm complies to its invariant (boundaries defined in the setter).
	 * 		|	0 <= new.getNumberOfActionPoints() <= new.getMaxNumberOfActionPoints()
	 * @effect	The new number of hit points of this worm complies to its invariant (boundaries defined in the setter).
	 * 		|	0 <= new.getNumberOfHitPoints() <= new.getMaxNumberOfHitPoints()
	 * @throws 	IllegalArgumentException("Invalid radius!")
	 * 			This worm cannot have the given radius as its radius.
	 * 		|	! isValidRadius(radius)
	 */
	@Override
	public void setRadius(double radius) throws IllegalArgumentException {
		if (!canHaveAsRadius(radius))
			throw new IllegalArgumentException("Invalid radius!");
		else{
			super.setRadius(radius);
			setNumberOfActionPoints(numberOfActionPoints);
			setNumberOfHitPoints(numberOfHitPoints);
		}
	}

	/**
	 * Return the given double precision number, rounded to the nearest integer.
	 * @param	number
	 * 			The given double precision number.
	 * @return	The given double precision number, rounded to the nearest integer.
	 * 		|	if (Math.round(number) > Integer.MAX_VALUE) result == Integer.MAX_VALUE
	 * 		|	else result = Math.round(number)
	 */
	@Model
	private int roundedToNearestInteger(double number){
		long longNumber = Math.round(number);
		int intNumber;
		if(longNumber > Integer.MAX_VALUE){
			intNumber = Integer.MAX_VALUE;
		}
		else intNumber = (int) longNumber;
		return intNumber;
	}
	
	/**
	 * Return the maximum number of action points for a worm with given mass.
	 * @param	mass
	 * 			The given mass
	 * @return	Maximum number of action points for a worm based on calculations involving the given mass.
	 * 		|	result == roundedToNearestInteger(mass)
	 */
	private int getMaxNumberOfActionPoints(double mass){
		return roundedToNearestInteger(mass);
	}
	
	/**
	 * Return the maximum number of action points of the worm.
	 * @return	Maximum number of action points of the worm based on calculations involving the mass of the worm.
	 * 		|	result == getMaxNumberOfActionPoints(this.getMass())
	 */
	@Raw
	public int getMaxNumberOfActionPoints(){
		return getMaxNumberOfActionPoints(this.getMass());
	}
	
	/**
	 * Return the current number of action points of the worm.
	 * 	The current number of action points expresses the number of action points this worm has left.
	 */	
	@Basic
	public int getNumberOfActionPoints() {
		return numberOfActionPoints;
	}

	/**
	 * Set the number of action points of this worm to the given number of action points.
	 * 
	 * @param	numberOfActionPoints
	 * 			The new number of action points for this worm.
	 * @post	If the given number of action points is not below zero and not above the maximum number of action points,
	 * 			the new number of action points of this worm is equal to the given number of action points.
	 * 			If the given number of action points is negative, the new number of action points is equal to zero.
	 * 			If the given number of action points is greater than the maximum number of action points, the new number
	 * 			of action points is equal to the maximum number of action points.
	 * 		|	if ((numberOfActionPoints >= 0) && (numberOfActionPoints <= this.getMaxNumberOfActionPoints())) new.getNumberOfActionPoints() == numberOfActionPoints
	 * 		|	else if (numberOfActionPoints < 0) new.getNumberOfActionPoints() == 0
	 * 		|	else if (numberOfActionPoints > this.getMaxNumberOfActionPoints()) new.getNumberOfActionPoints() == this.getMaxNumberOfActionPoints()
	 */
	private void setNumberOfActionPoints(int numberOfActionPoints){
		if(numberOfActionPoints < 0)
			numberOfActionPoints = 0;
		else if(numberOfActionPoints > this.getMaxNumberOfActionPoints())
			numberOfActionPoints = this.getMaxNumberOfActionPoints();
		this.numberOfActionPoints = numberOfActionPoints;
	}
	
	/**
	 * Restore the current number of action points of this worm to its maximum.
	 * @post	The new number of action points of this worm is equal to the maximum number of action points of this worm.
	 * 		|	new.getNumberOfActionPoints() == this.getMaxNumberOfActionPoints()
	 * @effect	The new number of action points of this worm complies to its invariant (boundaries defined in the setter).
	 * 		|	0 <= new.getNumberOfActionPoints() <= new.getMaxNumberOfActionPoints()
	 */
	protected void restoreNumberOfActionPoints(){
		this.setNumberOfActionPoints(this.getMaxNumberOfActionPoints());
	}

	/**
	 * Variable registering the current number of action points of this worm.
	 */	
	private int numberOfActionPoints;

	/**
	 * Return the maximum number of hit points for a worm with given mass.
	 * @param	mass
	 * 			The given mass
	 * @return	Maximum number of hit points for a worm based on calculations involving the given mass.
	 * 		|	result == roundedToNearestInteger(mass)
	 */
	private int getMaxNumberOfHitPoints(double mass){
		return roundedToNearestInteger(mass);
	}
	
	/**
	 * Return the maximum number of hit points of the worm.
	 * @return	Maximum number of hit points of the worm based on calculations involving the mass of the worm.
	 * 		|	result == getMaxNumberOfHitPoints(this.getMass())
	 */
	@Raw
	public int getMaxNumberOfHitPoints(){
		return getMaxNumberOfHitPoints(this.getMass());
	}

	/**
	 * Return the current number of hit points of the worm.
	 * 	The current number of hit points expresses the number of hit points this worm has left.
	 */	
	@Basic
	public int getNumberOfHitPoints() {
		return numberOfHitPoints;
	}

	/**
	 * Set the number of action points of this worm to the given number of action points.
	 * 
	 * @param	numberOfHitPoints
	 * 			The new number of hit points for this worm.
	 * @post	If the given number of hit points is not below zero and not above the maximum number of hit points,
	 * 			the new number of hit points of this worm is equal to the given number of hit points.
	 * 			If the given number of hit points is negative, the new number of hit points is equal to zero.
	 * 			If the given number of hit points is greater than the maximum number of hit points, the new number
	 * 			of hit points is equal to the maximum number of hit points.
	 * 		|	if ((numberOfHitPoints >= 0) && (numberOfHitPoints <= this.getMaxNumberOfHitPoints())) new.getNumberOfHitPoints() == numberOfHitPoints
	 * 		|	else if (numberOfHitPoints < 0) new.getNumberOfHitPoints() == 0
	 * 		|	else if (numberOfHitPoints > this.getMaxNumberOfHitPoints()) new.getNumberOfHitPoints() == this.getMaxNumberOfHitPoints()
	 */
	private void setNumberOfHitPoints(int numberOfHitPoints){
		if(numberOfHitPoints < 0)
			numberOfHitPoints = 0;
		else if(numberOfHitPoints > this.getMaxNumberOfHitPoints())
			numberOfHitPoints = this.getMaxNumberOfHitPoints();
		this.numberOfHitPoints = numberOfHitPoints;
	}
	
	/**
	 * Decrease the current number of hit points of this worm by the given number.
	 * 
	 * @param 	decrement
	 * 			The given number to decrement the current number of hit points with.
	 * @post	The new number of hit points of this worm is equal to the old number of hit points, decremented with the given number.
	 * 		|	new.getNumberOfHitPoints() == this.getNumberOfHitPoints() - decrement
	 * @effect	The new number of hit points of this worm complies to its invariant (boundaries defined in the setter).
	 * 		|	0 <= new.getNumberOfHitPoints() <= new.getMaxNumberOfHitPoints()
	 */
	protected void decreaseNumberOfHitPointsBy(int decrement){
		this.setNumberOfHitPoints(this.getNumberOfHitPoints() - decrement);
	}
	
	/**
	 * Variable registering the current number of hit points of this worm.
	 */	
	private int numberOfHitPoints;
		
	/**
	 * Return the amount of action points this worm has to pay to turn by the given angle.
	 * 
	 * @param 	turnByAngle
	 * 			The angle by which this worm will be turned.
	 * @return	If the converted representative angle to turn by is equal to zero, the resulting amount of action points to be paid is also equal to zero.
	 * 		|	if (this.convertToRepresentativeAngle(turnByAngle) == 0) result == 0
	 * @return	If the converted representative angle to turn by is not equal to zero, the resulting amount of action points to be paid is equal to the quotient of 60 and a factor that is calculated by dividing 2 times pi by the converted representative angle to turn by.
	 * 		|	if (this.convertToRepresentativeAngle(turnByAngle) != 0) result == ceil(60 / ((2*pi)/effectiveAngle))
	 * 		|	where
	 * 		|		if (this.convertToRepresentativeAngle(turnByAngle) > (2 * pi)) effectiveAngle == (2 * Math.PI) - this.convertToRepresentativeAngle(turnByAngle)
	 * 		|		else effectiveAngle == this.convertToRepresentativeAngle(turnByAngle)
	 */
	@Model
	private int amountOfActionPointsForTurning(double turnByAngle){
		double effectiveAngle = this.convertToRepresentativeAngle(turnByAngle);
		int decrement;
		if(effectiveAngle == 0)
			decrement = 0;
		else{
			if(effectiveAngle > Math.PI)
				effectiveAngle = (2 * Math.PI) - effectiveAngle;
			double factor = (2 * Math.PI) / effectiveAngle;
			decrement = (int) Math.ceil(60 / factor);
		}
		return decrement;
	}

	/**
	 * Convert the given angle to a representative angle that is equal to or greater than zero and smaller than two times pi radians.
	
	 * @param	angle
	 * 			The angle to be converted.
	 * @return	The converted angle is a geometrically identical angle that lies between zero and two times pi, excluding the latter.
	 * 		|	if ((angle % (2 * pi)) < 0) result == (angle % (2 * pi)) + (2 * pi)
	 * 		|	else result == angle % (2 * pi)
	 */	
	@Model
	private double convertToRepresentativeAngle(double angle){
		double representativeAngle = angle % (2 * Math.PI);
		if (representativeAngle < 0)
			representativeAngle += 2 * Math.PI;
		return representativeAngle;
	}

	/**
	 * Check whether the worm can turn by the given angle.
	 * 
	 * @param	turnByAngle
	 * 			The angle by which this worm will be turned.
	 * @Pre		The sum of the worm's current direction and the given angle to turn by is a valid direction for any worm.
	 * 		|	this.isValidDirection(this.getDirection() + turnByAngle)
	 * @return	True if and only if the current number of action points is not smaller than the amount of action points required to turn the worm by the given angle.
	 * 		|	result == (this.getNumberOfActionPoints() >= this.amountOfActionPointsForTurning(turnByAngle))
	 */
	public boolean canTurn(double turnByAngle){
		return (this.getNumberOfActionPoints() >= this.amountOfActionPointsForTurning(turnByAngle));
	}

	/**
	 * Turn this worm while paying the appropriate amount of action points.
	 * 
	 * @param 	turnByAngle
	 * 			The angle by which this worm will be turned.
	 * @Pre		This worm can turn by the given angle.
	 * 		|	this.canTurn(turnByAngle)
	 * @post	The new number of action points equals the old number of action points reduced with the amount of action points to be paid for turning by the given angle.
	 * 		|	new.getNumberOfActionPoints() == this.getNumberOfActionPoints - this.amountOfActionPointsForTurning(turnByAngle)
	 * @post	The new direction of this worm is equal to the old direction incremented with the given angle.
	 * 		|	new.getDirection() == this.getDirection() + turnByAngle
	 */
	@Override
	public void turn(double turnByAngle){
		assert this.canTurn(turnByAngle) :
			"Precondition: The worm can turn by the given angle";
		super.turn(turnByAngle);
		setNumberOfActionPoints(this.getNumberOfActionPoints() - this.amountOfActionPointsForTurning(turnByAngle));
	}
	
	/**
	 * Return the initial force to be exerted on the worm.
	 * @return 	Initial force to be exerted on the worm.
	 * 		|	result == (5 * this.getNumberOfActionPoints()) + (this.getMass() * EARTHS_STANDARD_ACCELERATION)
	 */
	@Model @Override
	protected double getInitialForce(){
		return ((5 * this.getNumberOfActionPoints()) + (this.getMass() * EARTHS_STANDARD_ACCELERATION));
	}
	
	@Override
	protected boolean stopConditionDuringJump(Position inFlightPosition){
		return this.getWorld().isAdjacentToImpassableTerrain(inFlightPosition, this.getRadius());
	}
	
	@Model @ Override
	protected boolean canJump(double timeStep){
		double jumpTime = this.jumpTime(timeStep);
		double[] jumpStep = this.jumpStep(jumpTime);
		return (super.canJump(timeStep) && (this.getNumberOfActionPoints() > 0) && (this.getPosition().distanceFromPositionWithCoordinates(jumpStep[0], jumpStep[1]) < this.getRadius()));
	}
	
	@Override
	public void jump(double timeStep){
		super.jump(timeStep);
		this.setNumberOfActionPoints(0);
	}

	private double getSlopeOfPossibleMove(double xAfterMove, double yAfterMove){
		return Math.atan((this.getY() - yAfterMove)/(this.getX() - xAfterMove));
	}
	
	private double getDistanceOfPossibleMove(double xAfterMove, double yAfterMove){
		return this.getPosition().distanceFromPositionWithCoordinates(xAfterMove, yAfterMove);
	}
	
	private double getDivergenceOfPossibleMove(double xAfterMove, double yAfterMove){
		return Math.abs(this.getRadius() - this.getSlopeOfPossibleMove(xAfterMove, yAfterMove));
	}

	private int amountOfActionPointsForMoving(double xAfterMove, double yAfterMove){
		double slopeAfterMove = this.getSlopeOfPossibleMove(xAfterMove, yAfterMove);
		return (int) Math.ceil(Math.abs(Math.cos(slopeAfterMove)) + Math.abs(4 * Math.sin(slopeAfterMove)));
	}

	private boolean canMove(double xAfterMove, double yAfterMove){
		return (this.getNumberOfActionPoints() >= this.amountOfActionPointsForMoving(xAfterMove, yAfterMove));
	}
	
}
