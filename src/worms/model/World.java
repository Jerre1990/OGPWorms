package worms.model;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;

public class World {

@Raw	
public World(double width, double height, GameObject[] objectList) {
	int i = 0;
	this.width = width;
	this.height = height;
	while (objectList[i] != null) {
		addAsGameObject((objectList[i]));
		
	}
}
	

boolean[][] passableMap;

/**
 * Check whether the world can have the given width
 * 
 * @param 	width
 * @return 	result == (getLowerBound() < width < getUpperBound()) 
 */
public boolean canHaveAsWidth(double width){
	return width > lowerBound && width < upperBound;
}

@Basic
public double getLowerBound() {
	return lowerBound;
}

/**
 * Set the new lowerbound of the world.
 * 
 * @param	lowerBound
 * @post	new.getLowerBound() == lowerBound
 */

public void setLowerBound(double lowerBound) {
	this.lowerBound = lowerBound;
}

@Basic
public double getUpperBound() {
	return upperBound;
}

/**
 * Set the new upperbound of the world.
 * 
 * @param	upperBound
 * @post	new.getUpperBound() == upperBound
 */
public void setUpperBound(double upperBound) {
	this.upperBound = upperBound;
}

/**
 * Check whether the world can have the given height
 * 
 * @param 	height
 * @return 	result == (getLowerBound() < height < getUpperBound()) 
 */

public boolean canHaveAsHeight(double height){
	return height > lowerBound && height < upperBound;
}

@Basic
public double getWidth() {
		return width;
}

/**
 * Set the width of the world to the given width.
 * 
 * @param	width
 * @post	new.getWidth() == width
 * @throws	IllegalArgumentException("Width is not of the proper size")
 * 		|	! canHaveAsWidth(width)
 */
public void setWidth(double width) throws IllegalArgumentException {
	if (!canHaveAsWidth(width))
		throw new IllegalArgumentException("Width is not of the proper size");
	else
		this.width = width;
}

@Basic
public double getHeight() {
	return height;
}

/**
 * Set the height of the world to the given height.
 * 
 * @param	height
 * @post	new.getHeight() == height
 * @throws	IllegalArgumentException("Height is not of the proper size")
 * 		|	! canHaveAsHeight(height)
 */
public void setHeight(double height) throws IllegalArgumentException {
	if (!canHaveAsWidth(height))
		throw new IllegalArgumentException("Height is not of the proper size");
	else
		this.height = height;
}

double lowerBound = 0;	
	
double upperBound = Double.MAX_VALUE;	

double width;

double height;

/**
 * @return	
 */
public int getNbGameObjects() {
	return objects.size();
}

private void setNbGameObjects() {
	
}

/**
 * 
 * @return	result == 
 * 				for each object in GameObject
 * 					( if (this.hasAsGameObject(object))
 * 						then canHaveAsGameObject(object)
 * 						&& (object.getWorld() == this) )	
 */

@Raw
public boolean hasProperGameObjects(){
for (GameObject object: this.objects){
	if (!canHaveAsGameObject(object)){
		return false;
	}
	if (object.getWorld() != this){
		return false;
	}
	return false;
}
}

/**
 * @param	object
 * 			The object to check.
 */
@Basic @Raw
public boolean hasAsGameObject(GameObject object){
	return this.objects.contains(object);
}

/**
 * @param	object
 * 			The object to be removed
 * @post	! new.hasAsGameObject(object)
 * @post	if (hasAsGameObject(object))
 * 				((new object).getWorld() == null)
 * @throws	(! hasAsGameObject)
 */
public void removeAsGameObject(GameObject object) throws IllegalArgumentException {
	if (hasAsGameObject(object)) {
		this.objects.remove(object);
		object.setWorld(null);
	}
	else throw new IllegalArgumentException("This object does not belong to this world");
	
}

/**
 * Terminate this world.
 * 
 * @post	new.isTerminated()
 * 
 *@effect	for each object in getAllGameObjects:
 *			if (! object.isTerminated())
 *				then this.removeAsGameObject(object) 	
 * 
 */
public void terminate() {
	for (GameObject object: objects)
		if (! object.isTerminated()) {
			object.setWorld(null);
			this.objects.remove(object);
			this.isTerminated = true;
	}
}
/**
 * List collecting references to the gameobjects of this world.
 * 
 * @invar	objects != null
 * @invar	for each gameobject in objects:
 * 			( (object == null)
 * 			|| canHaveAsGameObject(object) )
 */
private final Set<GameObject> objects = new HashSet<GameObject>();

@Basic @Raw
public boolean isTerminated(){
	return this.isTerminated;
}


/**
 * 
 * @param 	object
 * 			The game object to be added
 * @post	new.hasAsGameObject(object)
 * @post	(new object).getWorld() == this
 * @throws 	IllegalArgumentException
 * 			! canHaveAsGameObject(object)
 * @throws	IllegalArgumentException
 * 			( (object != null)
 * 			&& (object.getWorld() != null) )
 */
public void addAsGameObject(GameObject object) throws IllegalArgumentException {
	if (! canHaveAsGameObject(object)) 
		throw new IllegalArgumentException("This is not a proper object for this world");
	if (object.getWorld() != null)
		throw new IllegalArgumentException("This object appears in another world");
	else
		this.objects.add(object);
		object.setWorld(this);
}

private boolean isTerminated;


/**
 * Check whether this world can have the given game object as one of the game objects attached to it.
 * 
 * @param 	object
 * 			The game object to check.
 * @return	if (object == null)
 * 				then result == false
 * 			if (! this.isTerminated())
 * 				then result == false
 * 			if (! object.isTerminated())
 * 				then result == false
 * 			else result == 
 * 				(! objects.equals(object))
 */
public boolean canHaveAsGameObject(GameObject object) {
	return ( (object != null)
			&& (! this.isTerminated())
			&& (! object.isTerminated())
			&& (! objects.equals(object)));
}




/**
 * 
 * @param 	gameObjects
 * 			The collection of game objects to examine.
 * @param 	method
 * 			The method to invoke against all game objects.
 * @return 	for each object in (Object union {null})
 * 				result.contains(object) == 
 * 				for some gameObject in gameObjects:
 * 					method.invoke(object).equals(object)
 * @throws 	IllegalArgumentException
 * 			gameObjects == null
 * @throws 	IllegalArgumentException
 * 			! Arrays.AsList(GameObject.class.getMethods()).contains(method)
 * @throws 	IllegalArgumentException
 * 			(method.isVarArgs()) ? 
				(method.getParameterTypes().length != 1) :
				(method.getParameterTypes().length != 0) 
 * @throws 	IllegalArgumentException
 * 			method.getReturnType() == void.class
 * @throws 	InvocationTargetException
 */
public static Collection<Object> getAllObjectsFrom(Collection<GameObject> gameObjects, Method method) throws IllegalArgumentException, InvocationTargetException {
	if (gameObjects == null){
		throw new IllegalArgumentException();
	}
	if (! Arrays.asList(GameObject.class.getMethods()).contains(method)){
		throw new IllegalArgumentException();
	}
	if ( (method.isVarArgs()) ? 
			(method.getParameterTypes().length != 1) :
			(method.getParameterTypes().length != 0) )
		throw new IllegalArgumentException();
	if (method.getReturnType() == void.class) 
		throw new IllegalArgumentException() ;
	
	Collection<Object> result = new HashSet<Object>();
	for (GameObject gameObject: gameObjects)
		try {
			result.add(method.invoke(gameObject));		
		}	catch (IllegalAccessException exc) {
			assert false;
		}
	
	return result;		
}


public Collection<Object> getAllCoordinatesOnRadius(double x, double y, double radius){
	Collection<Object> result = new HashSet<Object>();
	double direction = 0;
	double xStart = x + radius*(Math.cos(direction));
	double yStart = y + radius*(Math.sin(direction));
	for (direction = 0; direction <= 2* (Math.PI); )
		if (passableMap[yStart][xStart]){
			direction = direction + Double.MIN_VALUE;
		}
		else return false
	return result;
}

public boolean isImpassable(double x, double y, double radius){
	Collection<Object> result = new HashSet<Object>();
	double direction = 0;
	double xStart = x + radius*(Math.cos(direction));
	double yStart = y + radius*(Math.sin(direction));
	for (direction = 0; direction <= 2* (Math.PI); )
		if (passableMap[yStart][xStart]){
			direction = direction + Double.MIN_VALUE;
			 xStart = x + radius*(Math.cos(direction));
			 yStart = y + radius*(Math.sin(direction));
		}
		else return true;

	return false;
}

public boolean isAdjacentLocation(double x, double y, double radius, GameObject object){
	double radiusExtended = radius*0.1;
	if (isImpassable(x, y, radiusExtended )){
		return false;
	}
	return true;
	
}
}


