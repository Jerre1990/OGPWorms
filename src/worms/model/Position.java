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
	 * Return the x-coordinate of this position, expressed in pixels with given width.
	 * 
	 * @param 	pixelWidth
	 * 			The given width of a pixel
	 * @return	The x-coordinate of this position, expressed in pixels
	 * 		|	Result == 
	 */
	private int getPixelCoordinateOfX(double pixelWidth) throws UnsupportedOperationException{
		long longResult = Math.round(this.getX()/pixelWidth);
		if (longResult > Integer.MAX_VALUE)
			throw new UnsupportedOperationException("Pixelcoordinate is too big!");
		return (int)longResult;
	}
	
	/**
	 * Return the y-coordinate of this position, expressed in pixels with given height.
	 * 
	 * @param 	pixelHeight
	 * 			The given height of a pixel
	 * @return	The y-coordinate of this position, expressed in pixels
	 * 		|	Result == 
	 */
	private int getPixelCoordinateOfY(double pixelHeight) throws UnsupportedOperationException{
		long longResult = Math.round(this.getY()/pixelHeight);
		if (longResult > Integer.MAX_VALUE)
			throw new UnsupportedOperationException("Pixelcoordinate is too big!");
		return (int)longResult;
	}
	
	/**
	 * Return the coordinates of this position expressed in pixels with given width and height.
	 * 
	 * @param 	pixelWidth
	 * 			The given width of a pixel
	 * @param 	pixelHeight
	 * 			The given height of a pixel
	 * @return	The coordinates of this position, expressed in pixels
	 * 		|	Result == {this.getPixelCoordinateOfX(pixelWidth), this.getPixelCoordinateOfY(pixelHeight)}
	 */
	public int[] getPixelCoordinates(double pixelWidth, double pixelHeight){
		int[] pixelCoordinates = {this.getPixelCoordinateOfX(pixelWidth), this.getPixelCoordinateOfY(pixelHeight)};
		return pixelCoordinates;
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
	
	/**
	 * Return the euclidean distance between two positions of which the coordinates are given.
	 * 
	 * @param	x1
	 * 			The x-coordinate of the first position
	 * @param	x2
	 * 			The x-coordinate of the second position
	 * @param	y1
	 * 			The y-coordinate of the first position
	 * @param	y2
	 * 			The y-coordinate of the second position
	 * @return	The euclidean distance between the two positions, defined by the given coordinates.
	 * 		|	result == Math.sqrt(Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2))
	 */
	public static double euclideanDistance(double x1, double x2, double y1, double y2){
		return Math.sqrt(Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2));
	}
	
	/**
	 * Return the euclidean distance between this position and the given position.
	 * 
	 * @param	otherPosition
	 * 			The given position to which the euclidean distance shall be calculated starting from this position
	 * @return	The euclidean distance between this position and the given position
	 * 		|	result == euclideanDistance(this.getX(), otherPosition.getX(), this.getY(), otherPosition.getY())
	 */
	public double distanceFromPosition(Position otherPosition){
		return euclideanDistance(this.getX(), otherPosition.getX(), this.getY(), otherPosition.getY());
	}
	
	/**
	 * Return the euclidean distance between this position and the given position which is explicitly expressed in coordinates.
	 * 
	 * @param	x
	 * 			The x-coordinate of the given position to which the euclidean distance shall be calculated starting from this position
	 * @param	y
	 * 			The y-coordinate of the given position to which the euclidean distance shall be calculated starting from this position
	 * @return	The euclidean distance between this position and the given position
	 * 		|	result == euclideanDistance(this.getX(), x, this.getY(), y)
	 */
	public double distanceFromPositionWithCoordinates(double x, double y){
		return euclideanDistance(this.getX(), x, this.getY(), y);
	}

}
