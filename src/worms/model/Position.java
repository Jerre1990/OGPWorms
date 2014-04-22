package worms.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * @Invar	isValidCoordinate(getX())
 * @Invar	isValidCoordinate(getY())
 * 
 * @version 1.0
 * @author Jonas Thys & Jeroen Reinenbergh
 */

@Value
public class Position {
	/**
	 * Initialize this new position with the given x-coordinate and y-coordinate.
	 * 
	 * @param	x
	 * @param	y
	 * @post 	new.getX() = x
	 * @post 	new.getY() = y
	 * @effect	isValidCoordinate(new.getX())
	 * @effect	isValidCoordinate(new.getY())
	 * @throws 	IllegalArgumentException("Invalid x-coordinate!")
	 * 		|	! isValidCoordinate(x)
	 * @throws 	IllegalArgumentException("Invalid y-coordinate!")
	 * 		|	! isValidCoordinate(y)
	 */
	public Position (double x, double y) throws IllegalArgumentException {
		if (!isValidCoordinate(x)){
			throw new IllegalArgumentException("Invalid x-coordinate!");
		}
		this.x = x;
		if (!isValidCoordinate(y)){
			throw new IllegalArgumentException("Invalid y-coordinate!");
		}
		this.y = y;
	}
	
	@Basic @Immutable
	public double getX() {
		return this.x;
	}		
	
	@Basic @Immutable
	public double getY() {
		return y;
	}	
	
	/**
	 * @param 	pixelWidth
	 * @return	Result == (int) Math.round(getX()/pixelWidth)
	 * @throws	UnsupportedOperationException("Pixelcoordinate is too big!")
	 * 		|	Math.round(getX()/pixelWidth) > Integer.MAX_VALUE
	 */
	@Model
	private int getPixelCoordinateOfX(double pixelWidth) throws UnsupportedOperationException{
		long longResult = Math.round(this.getX()/pixelWidth);
		if (longResult > Integer.MAX_VALUE)
			throw new UnsupportedOperationException("Pixelcoordinate is too big!");
		return (int)longResult;
	}
	
	/**
	 * @param 	pixelHeight
	 * @return	Result == (int) Math.round(getY()/pixelHeight)
	 * @throws	UnsupportedOperationException("Pixelcoordinate is too big!")
	 * 		|	Math.round(getY()/pixelHeight) > Integer.MAX_VALUE
	 */
	@Model
	private int getPixelCoordinateOfY(double pixelHeight) throws UnsupportedOperationException{
		long longResult = Math.round(this.getY()/pixelHeight);
		if (longResult > Integer.MAX_VALUE)
			throw new UnsupportedOperationException("Pixelcoordinate is too big!");
		return (int)longResult;
	}
	
	/**
	 * @param 	pixelWidth
	 * @param 	pixelHeight
	 * @return	Result == {getPixelCoordinateOfX(pixelWidth), getPixelCoordinateOfY(pixelHeight)}
	 * @throws	UnsupportedOperationException("Pixelcoordinate is too big!")
	 * 		|	Math.round(getX()/pixelWidth) > Integer.MAX_VALUE 
	 * 		|	|| Math.round(getY()/pixelHeight) > Integer.MAX_VALUE
	 */
	public int[] getPixelCoordinates(double pixelWidth, double pixelHeight){
		int[] pixelCoordinates = {this.getPixelCoordinateOfX(pixelWidth), this.getPixelCoordinateOfY(pixelHeight)};
		return pixelCoordinates;
	}
	
	/**
	 * @param 	coordinate
	 * @return	result == (!Double.isNaN(coordinate))
	 */
	@Model
	private boolean isValidCoordinate (double coordinate) {
		return !Double.isNaN(coordinate);
	}
	
	private final Double x;
	
	private final Double y;
	
	/**
	 * @param	x1
	 * @param	x2
	 * @param	y1
	 * @param	y2
	 * @return	result == Math.sqrt(Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2))
	 */
	public static double euclideanDistance(double x1, double x2, double y1, double y2){
		return Math.sqrt(Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2));
	}
	
	/**
	 * @param	otherPosition
	 * @return	result == euclideanDistance(getX(), otherPosition.getX(), getY(), otherPosition.getY())
	 */
	public double distanceFromPosition(Position otherPosition){
		return euclideanDistance(this.getX(), otherPosition.getX(), this.getY(), otherPosition.getY());
	}
	
	/**
	 * @param	x
	 * @param	y
	 * @return	result == euclideanDistance(getX(), x, getY(), y)
	 */
	public double distanceFromPositionWithCoordinates(double x, double y){
		return euclideanDistance(this.getX(), x, this.getY(), y);
	}

	/**
	 * @param	other
	 * @return	result == ((other != null) && (getClass() == other.getClass()) && (getX() == (Position other).getX()) && (getY() == (Position other).getY())
	 */
	@Override
	public boolean equals (Object other) {
		if ((other == null) || (this.getClass() != other.getClass())) {
			return false;
		}
		Position otherPosition = (Position) other;
		return ((this.getX() == otherPosition.getX()) && (this.getY() == otherPosition.getY()));
	}

	/**
	 * @return	result == (int)(x ^ (x >> 32) ^ y ^ (y >> 32))
	 * 				in	x == Double.doubleToLongBits(getX())
	 * 					y == Double.doubleToLongBits(getY())
	 */
	@Override
	public int hashCode () {
	    long x = Double.doubleToLongBits(getX());
	    long y = Double.doubleToLongBits(getY());
	    return (int)(x ^ (x >> 32) ^ y ^ (y >> 32));
	}

	/**
	 * @return	result == "(" + getX().toString() + "," + getY().toString() + ")"
	 */
	@Override
	public String toString () {
		return "(" + this.getX() + "," + this.getY() + ")";
	}

}
