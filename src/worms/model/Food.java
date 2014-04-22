package worms.model;

/**
 * @version 2.0
 * @author Jonas Thys & Jeroen Reinenbergh
 */

public class Food extends GameObject {
	
	/**
	 * @param 	position
	 * @post	new.getPosition() = position
	 * @post	new.getRadius() = 0.2
	 * @post	new.getLowerBoundOfRadius() = 0.2
	 * @post	new.getPercentualIncreaseOfRadius() = 0.1
	 * @effect	canHaveAsRadius(0.2)
	 * @effect	isValidLowerBoundOfRadius(0.2)
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
	public Food(Position position){
		super(position, 0.2, 0.2);
		this.setPercentualIncreaseOfRadius(0.1);
	}
	
	public double getPercentualIncreaseOfRadius(){
		return this.percentualIncreaseOfRadiusWhenEaten;
	}
	
	public void setPercentualIncreaseOfRadius(double increase){
		this.percentualIncreaseOfRadiusWhenEaten = increase;
	}
	
	private double percentualIncreaseOfRadiusWhenEaten;

}
