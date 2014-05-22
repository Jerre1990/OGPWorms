package worms.model;

import java.util.List;

import be.kuleuven.cs.som.annotate.*;

/**
 * @Invar	canHaveAsRadius(getRadius())
 * @Invar	isValidDirection(getDirection())
 * 
 * @version 2.0
 * @author Jonas Thys & Jeroen Reinenbergh
 */

public abstract class MovableGameObject extends GameObject{
	
	/**
	 * @param	position
	 * @param	radius
	 * @param	lowerBound
	 * @param	direction
	 * @Pre		isValidDirection(direction)
	 * @post	new.getPosition() = position
	 * @post	new.getRadius() = radius
	 * @post	new.getLowerBoundOfRadius() = lowerBound
	 * @post 	new.getDirection() = direction
	 * @effect	canHaveAsRadius(new.getRadius())
	 * @effect	isValidLowerBoundOfRadius(new.getLowerBoundOfRadius())
	 * @effect	isValidDirection(new.getDirection())
	 * @effect	isValidPosition(new.getPosition())
	 * @effect	new.getX() = position.getX()
	 * @effect	new.getY() = position.getY()
	 * @effect	getPosition().isValidCoordinate(new.getX())
	 * @effect	getPosition().isValidCoordinate(new.getY())
	 * @throws 	IllegalArgumentException("Invalid radius!")
	 * 		|	! this.canHaveAsRadius(radius)
	 * @throws	IllegalArgumentException("Invalid lower bound of radius!")
	 * 		|	! this.isValidLowerBoundOfRadius(lowerBound)
	 * @throws	IllegalArgumentException("Invalid position!")
	 * 		|	! isValidPosition(position)
	 */
	public MovableGameObject(Position position, double radius, double lowerBound, double direction) throws IllegalArgumentException{
		super(position, radius, lowerBound);
		this.setDirection(direction);
	}
	
	/**
	 * @return 	result == getMass(getRadius())
	 */
	public double getMass() {
		return this.getMass(this.getRadius());
	}

	/**
	 * @param	radius
	 * @return	result == (super.canHaveAsRadius(radius) && isValidMass(radius))
	 */
	@Override @Model @Raw
	protected boolean canHaveAsRadius(double radius){
		return (super.canHaveAsRadius(radius) && this.isValidMass(radius));
	}

	/**
	 * @param	radius
	 * @return	result == ((getMass(radius) >= 0) && isValidNumber(getMass(radius)))
	 */
	@Raw
	protected boolean isValidMass(double radius){
		return ((this.getMass(radius) >= 0) && this.isValidNumber(getMass(radius)));
	}
	
	/**
	 * @param	radius
	 * @return 	result == (1062 * (4 / 3) * Math.PI * radius^3)
	 */
	@Model @Raw
	private double getMass(double radius) {
		double p = 1062;
		return ((p * 4.0 * Math.PI * Math.pow(radius, 3)) / 3.0);
	}
	
	/**
	 * @param 	turnByAngle
	 * @Pre		isValidDirection(getDirection() + turnByAngle)
	 * @post	new.getDirection() == this.getDirection() + turnByAngle
	 */
	public void turn(double turnByAngle){
		assert this.isValidDirection(this.getDirection() + turnByAngle) :
			"Precondition: Valid sum of current direction and given angle to turn by";		
		setDirection(this.getDirection() + turnByAngle);
	}

	@Basic
	public double getDirection() {
		return this.direction;
	}
	
	/**
	 * @param	direction
	 * @return	result == (isValidNumber(direction) && (direction >= 0) && (direction < (2 * pi)))
	 */
	@Model @Raw
	protected boolean isValidDirection(double direction){
		return (this.isValidNumber(direction) && (direction >= 0) && (direction < (2 * Math.PI)));
	}

	/**
	 * @param	direction
	 * @Pre		isValidDirection(direction)
	 * @post	new.getDirection() = direction
	 */
	@Raw
	protected void setDirection(double direction) {
		assert this.isValidDirection(direction) :
			"Precondition: Valid direction";
		this.direction = direction;
	}

	private double direction;
	
	/**
	 * @param	timeStep
	 * @post	new.getPosition = new Position(jumpStepOnXAxis(jumpTime(timeStep)), jumpStepOnYAxis(jumpTime(timeStep)))
	 * @effect	new.getX() == jumpStepOnXAxis(jumpTime(timeStep))
	 * @effect	new.getY() == jumpStepOnYAxis(jumpTime(timeStep))
	 * @throws 	UnsupportedOperationException("Cannot jump!")
	 * 		|	! canJump()
	 */	
	public void jump(double timeStep){
		if (! this.canJump(timeStep))
			throw new UnsupportedOperationException("Cannot "+ this.getCustomText() +"!");
		this.setPosition(this.jumpStepOnXAxis(this.jumpTime(timeStep)), this.jumpStepOnYAxis(this.jumpTime(timeStep)));
	}

	/**
	 * @param	timeStep
	 * @return	result == jumpTime
	 * 		|		in	jumpTime = timeStep;
	 *		|			oldY = getY();
	 *		|			newY = jumpStepOnYAxis(jumpTime);
	 *		|			goingUp = (newY > getY());
	 *		|			newPosition = new Position(jumpStepOnXAxis(jumpTime), newY);
	 *		|			while (getWorld().isPassable(newPosition, getRadius()) && !this.stopConditionDuringJump(newPosition, goingUp)){
	 *		|				jumpTime += timeStep;
	 *		|				oldY = newY;
	 *		|				newY = jumpStepOnYAxis(jumpTime);
	 *		|				goingUp = (newY > oldY);
	 *		|				newPosition = new Position(jumpStepOnXAxis(jumpTime),newY);
	 *		|			}
	 */
	public double jumpTime(double timeStep){
		double jumpTime = timeStep;
		double oldY = this.getY();
		double newY = this.jumpStepOnYAxis(jumpTime);
		boolean goingUp = (newY > oldY);
		Position newPosition = new Position(this.jumpStepOnXAxis(jumpTime), newY);
		while (this.getWorld().isPassable(newPosition,this.getRadius()) && !this.stopConditionDuringJump(newPosition, goingUp)){
			jumpTime += timeStep;
			oldY = newY;
			newY = this.jumpStepOnYAxis(jumpTime);
			goingUp = (newY > oldY);
			newPosition = new Position(this.jumpStepOnXAxis(jumpTime),newY);
		}
		return jumpTime;
	}

	/**
	 * @param	timePassed
	 * @return	result[0] == jumpStepOnXAxis(timePassed)
	 * 		|	result[1] == jumpStepOnYAxis(timePassed)
	 */	
	public double[] jumpStep(double timePassed){
		double [] coordinatesAfterJumpStep = {this.jumpStepOnXAxis(timePassed),this.jumpStepOnYAxis(timePassed)};
		return coordinatesAfterJumpStep;
	}

	protected abstract double getInitialForce();
	
	@Model
	protected boolean stopConditionDuringJump(Position inFlightPosition, boolean goingUp){
		return (this.getWorld().isAdjacentToImpassableTerrain(inFlightPosition, this.getRadius()));
	}
	
	@Model
	protected boolean canJump(double timeStep){
		return ((this.jumpTime(timeStep) != 0) && this.getWorld().isPassable(this.getPosition(), this.getRadius()));
	}
	
	protected abstract String getCustomText();

	/**
	 * @return	result == (getInitialForce() / getMass()) * 0.5
	 */
	public double initialVelocity(){
		return ((this.getInitialForce() / this.getMass()) * 0.5);
	}

	/**
	 * @param	timePassed
	 * @return	result == getX() + (initialVelocity() * Math.cos(getDirection()) * timePassed)
	 */	
	@Model
	public double jumpStepOnXAxis(double timePassed){
		return (this.getX() + (this.initialVelocity() * Math.cos(this.getDirection()) * timePassed));
	}

	/**
	 * @param	timePassed
	 * @return	result == getY() + ((initialVelocity() * Math.sin(getDirection()) * timePassed) - ((1/2) * EARTHS_STANDARD_ACCELERATION * timePassed^2))
	 */		
	@Model
	public double jumpStepOnYAxis(double timePassed){
		return (this.getY() + ((this.initialVelocity() * Math.sin(this.getDirection()) * timePassed) - ((0.5) * EARTHS_STANDARD_ACCELERATION * Math.pow(timePassed, 2))));
	}
	
	public GameObject searchNearestObjectInGivenDirection(double theta){
		double stepSize = Math.min(this.getWorld().getPixelHeight(), this.getWorld().getPixelWidth());
		return searchNearestObjectInGivenDirection(theta, stepSize, this.getPosition());
	}
	
	private GameObject searchNearestObjectInGivenDirection(double theta, double stepSize, Position previousPosition){
		Position newPosition = new Position(previousPosition.getX() + Math.cos(theta)*stepSize, previousPosition.getY() + Math.sin(theta)*stepSize);
		if(this.getWorld().isLocatedInWorld(newPosition, this.getRadius())){
			List<GameObject> overlap = this.IntermediateOverlapWith(newPosition);
			if(overlap.get(0) == null){
				return searchNearestObjectInGivenDirection(theta, stepSize, newPosition);
			}
			else return overlap.get(0);
		}
		else return null;
	}

	public static double EARTHS_STANDARD_ACCELERATION = 9.80665;

}
