package worms.model;

public class Food extends GameObject {
	
	/**
	 * @param 	x
	 * @param 	y
	 * @post	new.getX() = x
	 * @post	new.getY() = y
	 * @post	new.getRadius() = 0.2
	 * @post	new.getLowerBoundOfRadius() = 0.2
	 * @effect	new.getPosition() != null
	 * @effect	this.canHaveAsRadius(0.2)
	 * @effect	this.isValidLowerBoundOfRadius(0.2)
	 * @effect	this.getPosition.isValidCoordinate(new.getX())
	 * @effect	this.getPosition.isValidCoordinate(new.getY())
	 * @throws 	IllegalArgumentException("Invalid radius!")
	 * 		|	! this.canHaveAsRadius(radius)
	 * @throws	IllegalArgumentException("Invalid lower bound of radius!")
	 * 		|	! this.isValidLowerBoundOfRadius(lowerBound)
	 * @throws 	IllegalArgumentException("Invalid x-coordinate!")
	 * 		|	! this.getPosition().isvalidCoordinate(x)
	 * @throws 	IllegalArgumentException("Invalid y-coordinate!")
	 * 		|	! this.getPosition().isValidCoordinate(y)
	 */
	public Food(double x, double y){
		super(x, y, 0.2, 0.2);
	}
	
	

}
