package worms.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of positions involving an x-coordinate and a y-coordinate.
 * 
 * @Invar	The x-coordinate of each position must be a valid x-coordinate for any position.
 * 		|	isValidCoordinate(getX())
 * @Invar	The y-coordinate of each position must be a valid y-coordinate for any position.
 * 		|	isValidCoordinate(getY())
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
	 * 			The x-coordinate for this new position, expressed in metres.
	 * @param	y
	 * 			The y-coordinate for this new position, expressed in metres.
	 * @post 	The new x-coordinate for this position is equal to the given x-coordinate.
	 * 		|	new.getX() = x
	 * @effect	The new x-coordinate for this position is a valid x-coordinate.
	 * 		|	isValidCoordinate(new.getX())
	 * @post 	The new y-coordinate for this position is equal to the given y-coordinate.
	 * 		|	new.getY() = y
	 * @effect	The new y-coordinate for this position is a valid y-coordinate.
	 * 		|	isValidCoordinate(new.getY())
	 * @throws 	IllegalArgumentException("Invalid x-coordinate!")
	 * 			The given x-coordinate is not a valid coordinate for any position.
	 * 		|	! isValidCoordinate(x)
	 * @throws 	IllegalArgumentException("Invalid y-coordinate!")
	 * 			The given y-coordinate is not a valid coordinate for any position.
	 * 		|	! isValidCoordinate(y)
	 * 
	 */
	public Position (double x, double y) throws IllegalArgumentException {
		if (!isValidCoordinate(x)){
			throw new IllegalArgumentException("Invalid x-coordinate!");
		}
		if (!isValidCoordinate(y)){
			throw new IllegalArgumentException("Invalid y-coordinate!");
		}
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Return the x-coordinate of this position.
	 */
	@Basic @Immutable
	public double getX() {
		return x;
	}		
	
	/**
	 * Return the y-coordinate of this position.
	 */
	@Basic @Immutable
	public double getY() {
		return y;
	}	
	
	/**
	 * Check whether the given coordinate is a valid coordinate for any position.
	 * 
	 * @param 	coordinate
	 * 			The coordinate to check.
	 * @return	True if and only if the given coordinate is a valid number.
	 * 		|	result == (!Double.isNaN(coordinate))
	 */
	@Model
	private boolean isValidCoordinate (double coordinate) {
		return !Double.isNaN(coordinate);
	}
	
	/**
	 * Variable referencing the x-coordinate of this position.
	 */
	private final Double x;
	
	/**
	 * Variable referencing the y-coordinate of this position.
	 */
	private final Double y;
	
	/**
	 * Check whether this position is equal to the given object.
	 * 
	 * @param	other
	 * 			The other object to compare with.
	 * @return	True if and only if the given object is effective, if this position and the given object belong to the same class,
	 * 			and if this position and the other object interpreted as a position have equal x-coordinates and equal y-coordinates.
	 * 		|	result == ((other != null) && (this.getClass() == other.getClass()) && (this.getX() == (Position other).getX()) && (this.getY() == (Position other).getY())
	 */
	@Override
	public boolean equals (Object other) {
		if (other == null) {
			return false;
		}
		if (this.getClass() != other.getClass()) {
			return false;
		}
		Position otherPosition = (Position) other;
		return ((this.getX() == otherPosition.getX()) && (this.getY() == otherPosition.getY()));
	}
	
	/**
	 * Return the hash code for this position.
	 */
	@Override
	public int hashCode () {
        long x = Double.doubleToLongBits(getX());
        long y = Double.doubleToLongBits(getY());
        return (int)(x ^ (x >> 32) ^ y ^ (y >> 32));
	}
	
	/**
	 * Return a textual representation for this position.
	 * 
	 * @return	A string consisting of the textual representation of the x-coordinate and the y-coordinate of this position, separated by a comma and enclosed in brackets.
	 * 		|	result.equals("(" + getX().toString() + "," + getY().toString() + ")")
	 */
	@Override
	public String toString () {
		return "(" + getX() + "," + getY() + ")";
	}

}
