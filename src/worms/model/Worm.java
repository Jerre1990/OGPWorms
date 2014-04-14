package worms.model;

import be.kuleuven.cs.som.annotate.*;
import worms.util.*;

/**
 * 
 * A class of worms involving a position, a radius, a lower bound of that radius, a direction, a name and a current number of action points.
 * 
 * @Invar	The name of each worm is a valid name.
 * 		|	isValidName(getName())
 * @Invar	Each worm can have its number of action points as its number of action points;
 * 		|	0 <= getNumberOfActionPoints() <= getMaxNumberOfActionPoints()
 * 
 * 
 * @version 2.0
 * @author Jonas Thys & Jeroen Reinenbergh
 * 
 */

public class Worm extends MovableGameObject {

	/**
	 * Initialize this new worm with given name, given x-coordinate, given y-coordinate,
	 * given direction and given radius.
	 * 
	 * @param	name
	 * 			The name of the worm.
	 * @param	x
	 * 			The x-coordinate of the worm expressed in metres.
	 * @param	y
	 * 			The y-coordinate of the worm expressed in metres.
	 * @param	direction
	 * 			The direction towards which the worm faces expressed in radians.
	 * @param	radius
	 * 			The radius of the spherical body of the worm expressed in metres.
	 * @post 	The new name of this worm is equal to the given name.
	 * 		|	new.getName() = name
	 * @effect	The new name of this worm is a valid name for any worm.
	 * 		|	this.isValidName(new.getName())
	 * @post 	The new radius of this worm is equal to the given radius.
	 * 		|	new.getRadius() = radius
	 * @effect	This worm can have the new radius as its radius.
	 * 		|	this.canHaveAsRadius(new.getRadius())
	 * @post 	The new direction of this worm is equal to the given direction.
	 * 		|	new.getDirection() = direction
	 * @effect	The new direction of this worm is a valid direction for any worm.
	 * 		|	this.isValidDirection(new.getDirection())
	 * @post 	The new position of this worm contains the given x-coordinate and y-coordinate.
	 * 		|	new.getX() = x
	 * 		|	new.getY() = y
	 * @post	The new position of this worm is an effective position.
	 * 		|	new.getPosition() != null
	 * @post	The new current number of action points of this worm is equal to the maximum number of action points of this worm.
	 * 		|	new.getNumberOfActionPoints() = new.getMaxNumberOfActionPoints()
	 * @effect	The new number of action points of this worm is a possible number of action points for any worm.
	 * 		|	0 <= new.getNumberOfActionPoints <= new.getMaxNumberOfActionPoints
	 * @throws 	IllegalArgumentException("Invalid radius!")
	 * 			This worm cannot have the given radius as its radius.
	 * 		|	! this.canHaveAsRadius(radius)
	 * @throws 	IllegalArgumentException("Name is not valid!")
	 * 			The given name is not a valid name for any worm.
	 * 		|	! this.isValidName(name)
	 * @throws 	IllegalArgumentException("Invalid x-coordinate!")
	 * 			The given x-coordinate is not a valid coordinate for the position of any worm.
	 * 		|	! this.getPosition().isvalidCoordinate(x)
	 * @throws 	IllegalArgumentException("Invalid y-coordinate!")
	 * 			The given y-coordinate is not a valid coordinate for the position of any worm.
	 * 		|	! this.getPosition().isValidCoordinate(y)
	 * 
	 */
	
	public Worm (String name, double radius, double direction, double x, double y) throws IllegalArgumentException {
		this.setRadius(radius);
		this.setDirection(direction);
		this.setName(name);
		this.setX(x);
		this.setY(y);
		this.setNumberOfActionPoints(this.getMaxNumberOfActionPoints());
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
	 * Check whether the given radius is a possible radius for any worm.
	 * 
	 * @param	radius
	 * 			The radius to check.
	 * @return	True if and only if this movable game object can have the given radius as its radius
	 * 			and if the newly calculated maximum number of action points of this worm is positive.
	 * 		|	result == (super.canHaveAsRadius(radius) && (getMaxNumberOfActionPoints(radius) >= 0))
	 */
	@Model @Override
	protected boolean canHaveAsRadius(double radius){
		return (super.canHaveAsRadius(radius) && (getMaxNumberOfActionPoints(radius) >= 0));
	}

	/**
	 * Set the radius of this worm to the given radius

	 * @param	radius
	 * 			The new radius of this worm.
	 * @post	The new radius of this worm is equal to the given radius.
	 * 		|	new.getRadius() == radius
	 * @effect	The new radius of this worm is a possible radius.
	 * 		|	isPossibleRadius(new.getRadius())
	 * @effect	The new mass of this worm is equal to the newly calculated mass.
	 * 			new.getMass() == (1062 * (4 / 3) * Math.PI * Math.pow(radius, 3))
	 * @effect	The new maximum number of action points of this worm is equal to the newly calculated maximum number of action points.
	 * 		|	new.getMaxNumberOfActionPoints() == Math.round(this.getMass())
	 * @effect	The new number of action points of this worm complies to its invariant (boundaries defined in the setter).
	 * 		|	new.setNumberOfActionPoints(this.getNumberOfActionPoints)
	 * @throws 	IllegalArgumentException("Invalid radius!")
	 * 			The given radius is not a possible radius for any worm.
	 * 		|	! isPossibleRadius(radius)
	 */
	public void setRadius(double radius) throws IllegalArgumentException {
		if (!canHaveAsRadius(radius))
			throw new IllegalArgumentException("Invalid radius!");
		else{
			this.radius = radius;
			setNumberOfActionPoints(numberOfActionPoints);
		}
	}

	/**
	 * Return the maximum number of action points of the worm.
	 * @param	mass
	 * 			The given mass.
	 * @return	Maximum number of action points of the worm based on calculations involving the given mass.
	 * 		|	if (Math.round(mass) > Integer.MAX_VALUE) result == Integer.MAX_VALUE
	 * 		|	else result = Math.round(mass)
	 */
	@Model
	private int getMaxNumberOfActionPoints(double mass){
		long longMass = Math.round(mass);
		int intMass;
		if(longMass > Integer.MAX_VALUE){
			intMass = Integer.MAX_VALUE;
		}
		else intMass = (int) longMass;
		return intMass;
	}
	
	/**
	 * Return the maximum number of action points of the worm.
	 * @return	Maximum number of action points of the worm based on calculations involving the mass of the worm.
	 * 		|	result == getMaxNumberOfActionPoints(this.getMass())
	 */
	@Raw
	public int getMaxNumberOfActionPoints(){
		return getMaxNumberOfActionPoints(getMass());
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
	 * 		|	if ((numberOfActionPoints >= 0) && (numberOfActionPoints <= this.getMaxNumberOfActionPoints())) new.getNumberOfActionPoints == numberOfActionPoints
	 * 		|	else if (numberOfActionPoints < 0) new.getNumberOfActionPoints == 0
	 * 		|	else if (numberOfActionPoints > this.getMaxNumberOfActionPoints()) new.getNumberOfActionPoints == this.getMaxNumberOfActionPoints
	 */
	private void setNumberOfActionPoints(int numberOfActionPoints){
		if(numberOfActionPoints < 0)
			numberOfActionPoints = 0;
		else if(numberOfActionPoints > this.getMaxNumberOfActionPoints())
			numberOfActionPoints = this.getMaxNumberOfActionPoints();
		this.numberOfActionPoints = numberOfActionPoints;
	}
	
	/**
	 * Variable registering the current number of action points of this worm.
	 */	
	private int numberOfActionPoints;
		
	/**
	 * Check whether the given number of steps is a possible number of steps for any worm.
	 * 
	 * @param	numberOfSteps
	 * 			The number of steps to check.
	 * @return	True if and only if the given number of steps is not smaller than zero.
	 * 		|	result == (numberOfSteps >= 0)
	 */
	@Model
	private boolean isPossibleNumberOfSteps(int numberOfSteps){
		return numberOfSteps >= 0;
	}

	/**
	 * Return the amount of action points this worm has to pay for moving the given number of steps.
	 * 
	 * @param 	numberOfSteps
	 * 			The number of steps to be taken by the worm in the current direction.
	 * @return	The amount of action points to be paid equals the number of steps times the weighted direction.
	 * 		|	result == numberOfSteps*(ceil(|cos(direction)|+|4*sin(direction)|))
	 * @throws 	IllegalArgumentException("Invalid number of steps!")
	 * 			The given number of steps is not a possible number of steps for any worm.
	 * 		|	! isPossibleNumberOfSteps(numberOfSteps)
	 */
	@Model
	private int amountOfActionPointsForMoving(int numberOfSteps ) throws IllegalArgumentException {
		if (!isPossibleNumberOfSteps(numberOfSteps)) 
			throw new IllegalArgumentException("Invalid number of steps!");
		else {
			double horizontalComponent = Math.abs(Math.cos(direction));
			double verticalComponent = Math.abs(4 * Math.sin(direction));
			int decrement = (int) Math.ceil(horizontalComponent + verticalComponent);
			return (numberOfSteps * decrement);
		}
	}

	/**
	 * Check whether the worm can move the given number of steps.
	 * 
	 * @param	numberOfSteps
	 * 			The number of steps to be taken by the worm in the current direction.
	 * @return	True if and only if the current number of action points is not smaller than the amount of action points required to move the worm with the given number of steps.
	 * 		|	result == (numberOfActionPoints >= amountOfActionPointsForMoving(numberOfSteps))
	 * @throws 	IllegalArgumentException("Invalid number of steps!")
	 * 			The given number of steps is not a possible number of steps for any worm.
	 * 		|	! isPossibleNumberOfSteps(numberOfSteps)
	 */	
	public boolean canMove(int numberOfSteps) throws IllegalArgumentException {
		if (! isPossibleNumberOfSteps(numberOfSteps))
			throw new IllegalArgumentException("Invalid number of steps!");
		else return (numberOfActionPoints >= amountOfActionPointsForMoving(numberOfSteps));
	}

	/**
	 * Move this worm in the current direction for the given number of steps.
	 * 
	 * @param	numberOfSteps
	 * 			The number of steps to be taken by the worm in the current direction.
	 * @post	The new X-coordinate of the worm is equal to the old X-coordinate plus the cosinus of the angle of the current direction, multiplied by the number of steps and the radius of this worm.
	 * 		|	new.getX() == this.getX() + Math.cos(direction)*numberOfSteps*radius
	 * @post	The new Y-coordinate of the worm is equal to the old Y-coordinate plus the sinus of the angle of the current direction, multiplied by the number of steps and the radius of this worm.
	 * 		|	new.getY() == this.getY() + Math.sin(direction)*numberOfSteps*radius
	 * @throws 	IllegalArgumentException("Number of steps is too small!")
	 * 			The given number of steps is not a valid number of steps for any worm.
	 * 		|	! isPossibleNumberOfSteps(numberOfSteps)
	 */
	@Model
	private void move(int numberOfSteps) throws IllegalArgumentException {
		if (!isPossibleNumberOfSteps(numberOfSteps)) 
			throw new IllegalArgumentException("Invalid number of steps!");
		else {
			double incrementX = Math.cos(direction) * radius;
			double incrementY = Math.sin(direction) * radius;
			double newX = getX() + (numberOfSteps * incrementX);
			double newY = getY() + (numberOfSteps * incrementY);
			setPosition (newX, newY);
		}
	}
	
	/** 
	 * Move this worm while paying the appropriate amount of action points.
	 * 
	 * @param 	numberOfSteps
	 * 			The number of steps to be taken by the worm in the current direction.
	 * @effect	The new X-coordinate of the worm is equal to the old X-coordinate plus the cosinus of the angle of the current direction, multiplied by the number of steps and the radius of this worm.
	 * 		|	new.getX() == this.getX() + Math.cos(direction)*numberOfSteps*radius
	 * @effect	The new Y-coordinate of the worm is equal to the old Y-coordinate plus the sinus of the angle of the current direction, multiplied by the number of steps and the radius of this worm.
	 * 		|	new.getY() == this.getY() + Math.sin(direction)*numberOfSteps*radius
	 * @post	The new number of action points equals the old number of action points reduced with the amount of action points to be paid for moving the given number of steps.
	 * 		|	new.getNumberOfActionPoints() == this.getNumberOfActionPoints - amountOfActionPointsForMoving(numberOfSteps)
	 * @throws 	UnsupportedOperationException("Cannot move!")
	 * 			The worm cannot move the given number of steps.
	 * 		|	! canMove(numberOfSteps)
	 * @throws 	IllegalArgumentException("Invalid number of steps!")
	 * 			The given number of steps is not a possible number of steps for any worm.
	 * 		|	! isPossibleNumberOfSteps(numberOfSteps)
	 */
	public void activeMove(int numberOfSteps) throws UnsupportedOperationException, IllegalArgumentException {
		if (! canMove(numberOfSteps))
			throw new UnsupportedOperationException("Cannot move!");
		else {
			move(numberOfSteps);
			setNumberOfActionPoints(numberOfActionPoints - amountOfActionPointsForMoving(numberOfSteps));
		}
	}
		
	/**
	 * Return the amount of action points this worm has to pay to turn by the given angle.
	 * 
	 * @param 	turnByAngle
	 * 			The angle by which this worm will be turned.
	 * @return	If the effective angle is equal to zero, the resulting amount of action points to be paid is also equal to zero.
	 * 			This effective angle is equal to the converted representative angle of the given angle to turn by.
	 * 		|	if (effectiveAngle == 0) result == 0
	 * @return	If the effective angle is not equal to zero, the resulting amount of action points to be paid is equal to the quotient of 60 and a factor that is calculated by dividing 2 times pi by the effective angle.
	 * 			This effective angle is equal to the converted representative angle of the given angle to turn by if it is not greater than pi, else it is equal to its radian complement.
	 * 		|	if (effectiveAngle != 0) result == ceil(60 / ((2*pi)/effectiveAngle))	
	 * 		|	if (convertToRepresentativeAngle(turnByAngle) > (2 * pi)) effectiveAngle == (2 * Math.PI) - convertToRepresentativeAngle(turnByAngle)
	 * 		|	else effectiveAngle == convertToRepresentativeAngle(turnByAngle)
	 */
	@Model
	private int amountOfActionPointsForTurning(double turnByAngle){
		double effectiveAngle = convertToRepresentativeAngle(turnByAngle);
		int decrement;
		if(effectiveAngle == 0){
			decrement = 0;
		}
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
	 * 		|	angle = result + (constant * 2 * pi)
	 * 		|	0 <= result < (2 * pi)
	 */	
	private double convertToRepresentativeAngle(double angle){
		while(angle < 0){
			angle += 2 * Math.PI;
		}
		return (angle % (2 * Math.PI));
	}

	/**
	 * Check whether the worm can turn by the given angle.
	 * 
	 * @param	turnByAngle
	 * 			The angle by which this worm will be turned.
	 * @pre		The sum of the representative angle of the given angle to turn by and the direction of this worm lies between zero and two times pi, including the former and excluding the latter.
	 * 		|	((turnByAngle + getDirection()) >= 0) && ((turnByAngle + getDirection()) < 2 * pi)
	 * @return	True if and only if the current number of action points is not smaller than the amount of action points required to turn the worm by the given angle.
	 * 		|	result == (numberOfActionPoints >= amountOfActionPointsForTurning(turnByAngle))
	 */
	public boolean canTurn(double turnByAngle){
		return (numberOfActionPoints >= amountOfActionPointsForTurning(turnByAngle));
	}

	/**
	 * Turn the direction of this worm by the given angle.
	 * 
	 * @param 	turnByAngle
	 * 			The angle by which this worm will be turned.
	 * @post	The new direction of this worm is equal to the old direction incremented with the given angle to turn by.
	 * 		|	new.getDirection() == this.getDirection() + turnByAngle
	 */
	@Model
	private void turn(double turnByAngle){
		setDirection(getDirection() + turnByAngle);
	}
	
	/**
	 * Turn this worm while paying the appropriate amount of action points.
	 * 
	 * @param 	turnByAngle
	 * 			The angle by which this worm will be turned.
	 * @pre		This worm can turn by the given angle.
	 * 		|	this.canTurn(turnByAngle)
	 * @post	The new number of action points equals the old number of action points reduced with the amount of action points to be paid for turning by the given angle.
	 * 		|	new.getNumberOfActionPoints() == this.getNumberOfActionPoints - amountOfActionPointsForTurning(turnByAngle)
	 * @post	The new direction of this worm is equal to the old direction incremented with the given angle.
	 * 		|	new.getDirection() == this.getDirection() + turnByAngle
	 */
	public void activeTurn(double turnByAngle){
		turn(turnByAngle);
		setNumberOfActionPoints(numberOfActionPoints - amountOfActionPointsForTurning(turnByAngle));
	}
	
	/**
	 * Return the horizontal distance covered by this worm during a jump.
	 * 
	 * @return	The horizontal jumping distance of this worm is equal to the product of its squared initial velocity, the sinus of its doubled direction and the inverse of Earth's standard acceleration coefficient.
	 * 		|	result == (initialVelocity()^2 * sin(direction * 2)) / standardAcceleration
	 */	
	@Model
	private double horizontalJumpDistance(){
		return ((Math.pow(initialVelocity(), 2) * Math.sin(direction * 2)) / standardAcceleration);
	}
	
	/**
	 * Return the time passed after a jump of this worm.
	 * 
	 * @return	The jump time equals the quotient of the worm's horizontal jump distance and the product of its initial velocity and the cosinus of its direction.
	 * 		|	result == horizontalJumpDistance() / (initialVelocity() * Math.cos(direction))
	 */	
	public double jumpTime(){
		return (horizontalJumpDistance() / (initialVelocity() * Math.cos(direction)));
	}
	
	/**
	 * Check whether the worm can jump.
	 * 
	 * @return	True if and only if the direction of this worm is not greater than pi and the time passed after a jump of this worm is not infinite, which implies that the direction of this worm is not equal to pi divided by two.
	 * 		|	result == ((direction <= Math.PI) && (direction != Math.PI/2))
	 */	
	@Model
	private boolean canJump(){
		return (Util.fuzzyLessThanOrEqualTo(direction, Math.PI) && (direction != Math.PI/2));
	}
}
