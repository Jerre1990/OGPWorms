package worms.model;

import java.awt.IllegalComponentStateException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import worms.util.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

public class World {
	
	/**
	 * @param 	width
	 * 			The width for this new world.
	 * @param 	height
	 * 			The height for this new world.
	 * @param 	passableMap
	 * 			The passableMap for this new world.
	 * @param 	random
	 * 			The random value for this new world.
	 * @post	new.getWidth = width
	 * @post	new.getHeight = height
	 * @post	new.getPassableMap = passableMap
	 * @post	new.getRandom() = random
	 * @effect	this.isValidHeight(new.getHeight())
	 * @effect	this.isValidWidth(new.getWidth())
	 * @throws 	IllegalArgumentException("Invalid width!")
	 * 			! this.isValidWidth(width)
	 * @throws 	IllegalArgumentException("Invalid height!")
	 * 			! this.isValidHeight(height)
	 */
	public World (double width, double height, boolean[][] passableMap, Random random) throws IllegalArgumentException {
		if(!this.isValidWidth(width))
			throw new IllegalArgumentException("Invalid width!");
		if(!this.isValidHeight(height))
			throw new IllegalArgumentException("Invalid height!");
		this.width = width;
		this.height = height;
		this.setPassableMap(passableMap);
		this.setRandom(random);
		this.setStarted(false);
	}
	
	@Basic
	protected double getWidth(){
		return this.width;
	}

	@Basic
	protected double getHeight(){
		return this.height;
	}

	/**
	 * @param 	width
	 * @return 	result == ((width >= lowerBoundOfWidth) && (width <= upperBoundOfWidth))
	 */
	private boolean isValidWidth(double width){
		return ((width >= lowerBoundOfWidth) && (width <= upperBoundOfWidth));
	}

	/**
	 * @param 	height
	 * @return 	result == ((height >= lowerBoundOfHeight) && (height <= upperBoundOfHeight))
	 */
	private boolean isValidHeight(double height){
		return ((height >= lowerBoundOfHeight) && (height <= upperBoundOfHeight));
	}

	private final static double lowerBoundOfWidth = 0;

	private final static double lowerBoundOfHeight = 0;

	private final static double upperBoundOfWidth = Double.MAX_VALUE;

	private final static double upperBoundOfHeight = Double.MAX_VALUE;

	private final double width;

	private final double height;

	protected Random getRandom(){
		return this.random;
	}
	/**
	 * Set the random value for this world to the given random value.
	 * @param 	r
	 * 			The new random value for this world.
	 * @post	new.getRandom() = r
	 */
	protected void setRandom(Random r){
		this.random = r;
	}
	
	/**
	 * Variable registering the random value for this world. 
	 */
	private Random random;
	
	/**
	 * 
	 * @param 	center
	 * 			The center of the game object to check.
	 * @param 	radius
	 * 			The radius of the game object to check.
	 * @return	result == this.isPassablePartOfPixeledRadiusOfCircle(center, radius, 0, 0)
	 */
	public boolean isPassable(Position center, double radius){
		boolean result;
		try{
			result = this.isPassablePartOfPixeledRadiusOfCircle(center, radius, 0, 0);
		}
		catch (IllegalArgumentException exc){
			result = false;
		}
		return result;
	}
	/**
	 * Chech whether a game object with given center and radius is adjacent to impassable terrain.
	 * @param 	center
	 * 			The center of the game object to check.
	 * @param 	radius
	 * 			The radius of the game object to check.
	 * @return	result == isAdjacentToImpassableTerrain(center, radius, 0, 0)
	 */
	public boolean isAdjacentToImpassableTerrain(Position center, double radius){
		return this.isAdjacentToImpassableTerrain(center, radius, 0, 0);
	}
	/**
	 * Return a random position in this world.
	 * @param 	radius
	 * 			The radius of the game object to assign a random position to.
	 * @return
	 */
	protected Position getRandomPositionAdjacentToImpassableFloor(double radius){
		double stepSize = 1;
		double stepSizeX = this.getPixelWidth() * stepSize;
		double stepSizeY = this.getPixelHeight() * stepSize;
		double centerX = this.getWidth() / 2;
		double centerY = this.getHeight() / 2;
		double randomXCoordinate;
		double randomYCoordinate;
		Position testPosition = null;
		boolean positionFound = false;
		loops:
		for(int i=0;i<100;++i){
			randomXCoordinate = this.getRandom().nextDouble() * this.getWidth();
			randomYCoordinate = this.getRandom().nextDouble() * this.getHeight();
			testPosition = new Position(randomXCoordinate, randomYCoordinate);
			if(this.isLocatedInWorld(testPosition, radius)){
				while(!(Util.fuzzyGreaterThanOrEqualTo(stepSizeX, Math.abs(testPosition.getX() - centerX)) && Util.fuzzyGreaterThanOrEqualTo(stepSizeY, Math.abs(testPosition.getY() - centerY)))){
					if(this.isAdjacentToImpassableFloor(testPosition, radius)){
						positionFound = true;
						break loops;
					}
					testPosition = this.getPositionCloserToCenter(testPosition, stepSize);
				}
			}
		}
		if(positionFound)
			return testPosition;
		else return null;
	}

	/**
	 * Check whether a game object with given center and radius is located in this world.
	 * @param 	center
	 * 			The center of the circle to check.
	 * @param	radius
	 * 			
	 * @return	result == 
	 */
	protected boolean isLocatedInWorld(Position center, double radius){
		double x = center.getX();
		double y = center.getY();
		int MaxPixelCoordinates[] = this.getUncheckedPixelCoordinates(new Position((x + radius),(y + radius)));
		int MinPixelCoordinates[] = this.getUncheckedPixelCoordinates(new Position((x - radius),(y - radius)));
		return ((MinPixelCoordinates[0] >= 0) && (MinPixelCoordinates[1] >= 0) && (MaxPixelCoordinates[0] < this.getWidthInPixels()) && (MaxPixelCoordinates[1] < this.getHeightInPixels()));
	}

	/**
	 * Return the pixelcoordinates of the given position.
	 * @param	position
	 * 			The position of which the pixelcoordinates will be returned.
	 * @return	if (this.isLocatedInWorld(position, 0))
	 * 			result == position.getPixelCoordinates(this.getPixelWidth(), this.getPixelHeight())
	 * @throws	IllegalArgumentException("Object is not located in this world!")
	 * 			! this.isLocatedInWorld(position, 0)	
	 */
	protected int[] getPixelCoordinates(Position position) throws IllegalArgumentException{
		if (!this.isLocatedInWorld(position, 0))
			throw new IllegalArgumentException("Object is not located in this world!");
		return this.getUncheckedPixelCoordinates(position);
	}
	/**
	 * Check whether a game object with given center and given radius is adjacent to an impassable floor.
	 * @param 	center
	 * 			The center of the game object.
	 * @param 	radius
	 * 			The radius of the game object.
	 * @return	
	 */
	protected boolean isAdjacentToImpassableFloor(Position center, double radius){
		return this.isAdjacentToImpassableTerrain(center, radius, -((Math.PI / 4) + (Math.PI / 16)), -((Math.PI / 4) - (Math.PI / 16)));
	}

	/**
	 * Check whether a game object with given center and given radius is adjacent to an impassable ceiling.
	 * @param 	center
	 * 			The center of the game object.
	 * @param 	radius
	 * 			The radius of the game object.
	 * @return	
	 */
	protected boolean isAdjacentToImpassableCeiling(Position center, double radius){
		return this.isAdjacentToImpassableTerrain(center, radius, ((Math.PI / 4) - (Math.PI / 16)), ((Math.PI / 4) + (Math.PI / 16)));
	}

	@Basic
	protected boolean[][] getPassableMap(){
		return this.passableMap;
	}
	
	/**
	 * Return the width of this world in pixels.
	 * @return	result == this.getPassableMap()[0].length
	 */
	protected int getWidthInPixels(){
		return this.getPassableMap()[0].length;
	}
	
	/**
	 * Return the height of this world in pixels.
	 * @return	result == this.getPassableMap().length
	 */
	protected int getHeightInPixels(){
		return this.getPassableMap().length;
	}
	/**
	 * Return the width of a pixel.
	 * @return (this.getWidth()/this.getWidthInPixels())
	 */
	@Model
	protected double getPixelWidth(){
		return (this.getWidth()/this.getWidthInPixels());
	}

	/**
	 * Return the height of a pixel
	 * @return (this.getHeight()/this.getHeightInPixels())
	 */
	@Model
	protected double getPixelHeight(){
		return (this.getHeight()/this.getHeightInPixels());
	}
	
	/**
	 * Set the passable map of this world to the given passable map.
	 * @param 	map
	 * 			The passable map to be set.
	 * @throws 	IllegalArgumentExceptionIllegalArgumentException("Empty map!")
	 * 			(map.length == 0 || map[0].length == 0)	
	 */
	protected void setPassableMap(boolean[][] map) throws IllegalArgumentException{
		if (map.length == 0)
			throw new IllegalArgumentException("Empty map!");
		else if (map[0].length == 0)
			throw new IllegalArgumentException("Empty map!");
		this.passableMap = map;
	}
	
	/**
	 * Return the pixelcoordinates of the given position.
	 * @param 	position
	 * 			The position of which the pixelcoordinates will be returned.
	 * @return	result == position.getPixelCoordinates(this.getPixelWidth(), this.getPixelHeight())
	 */
	private int[] getUncheckedPixelCoordinates(Position position){
		return position.getPixelCoordinates(this.getPixelWidth(), this.getPixelHeight());
	}

	/**
	 * Check whether the given position is impassable.
	 * @param 	position
	 * 			The position to be checked.
	 * @return	result == !this.getPassableMap()[pixelCoordinates[1]][pixelCoordinates[0]]
	 */
	private boolean isImpassablePosition(Position position) {
		int[] pixelCoordinates = this.getPixelCoordinates(position);
		return !this.getPassableMap()[(this.getHeightInPixels() - 1 - pixelCoordinates[1])][pixelCoordinates[0]];
	}

	/**
	 * Other possible methods for testing basic passability of a circle piece.
	 * 
	
	private double getPositiveYCoordinateOfCircle(Position center, double x, double radius){
		return (Math.sqrt(Math.pow(radius, 2) - Math.pow((x - center.getX()), 2)) + center.getY());
	}

	private boolean isPassablePartOfPixeledHollowedCircle(Position center, double radiusOfCircle, double radiusOfVoid, boolean[] quadrantsToCheck) throws IllegalArgumentException{
		if (!this.isLocatedInWorld(center, radiusOfCircle))
			throw new IllegalArgumentException("Not fully located in world!");
		if (! (radiusOfVoid < radiusOfCircle))
			radiusOfVoid = 0;
		if (quadrantsToCheck.length != 4)
			quadrantsToCheck = new boolean[] {true,true,true,true};
		boolean isPassable = true;
		double centerX = center.getX();
		double centerY = center.getY();
		double centerXDoubled = 2 * centerX;
		double centerYDoubled = 2 * centerY;
		double x = centerX + radiusOfVoid;
		double y = centerY + radiusOfVoid;
		double xMax = centerX + radiusOfCircle;
		double yMax = centerY + radiusOfCircle;
		double pixelWidth = this.getPixelWidth();
		double pixelHeight = this.getPixelHeight();
		while (isPassable){
			while (x < xMax){
				while (y < yMax){
					if ((this.isImpassablePosition(new Position(x,y)) && quadrantsToCheck[0]) || (this.isImpassablePosition(new Position(x,(centerYDoubled - y))) && quadrantsToCheck[3]) || (this.isImpassablePosition(new Position((centerXDoubled - x),y)) && quadrantsToCheck[1]) || (this.isImpassablePosition(new Position((centerXDoubled - x),(centerYDoubled - y))) && quadrantsToCheck[2]))
						isPassable = false;
					y += pixelHeight;
				}
				x += pixelWidth;
				y = this.getPositiveYCoordinateOfCircle(center, x, radiusOfVoid);
				yMax = this.getPositiveYCoordinateOfCircle(center, x, radiusOfCircle);
			}	
		}
		return isPassable;
	}
	
	private boolean isPassablePartOfPixeledRadiusOfCircle(Position center, double radiusOfCircle, boolean[] quadrantsToCheck) throws IllegalArgumentException{
		if (!this.isLocatedInWorld(center, radiusOfCircle))
			throw new IllegalArgumentException("Not fully located in world!");
		if (quadrantsToCheck.length != 4)
			quadrantsToCheck = new boolean[] {true,true,true,true};
		boolean isPassable = true;
		double centerX = center.getX();
		double centerY = center.getY();
		double centerXDoubled = 2 * centerX;
		double centerYDoubled = 2 * centerY;
		double x = centerX;
		double y = centerY + radiusOfCircle;
		double xMax = centerX + radiusOfCircle;
		double stepSize = Math.min(this.getPixelWidth(), this.getPixelHeight());
		while (x < xMax){
			if ((this.isImpassablePosition(new Position(x,y)) && quadrantsToCheck[0]) || (this.isImpassablePosition(new Position(x,(centerYDoubled - y))) && quadrantsToCheck[3]) || (this.isImpassablePosition(new Position((centerXDoubled - x),y)) && quadrantsToCheck[1]) || (this.isImpassablePosition(new Position((centerXDoubled - x),(centerYDoubled - y))) && quadrantsToCheck[2])){
				isPassable = false;
				break;
			}
			x += stepSize;
			y = this.getPositiveYCoordinateOfCircle(center, x, radiusOfCircle);
		}	
		return isPassable;
	}*/
	/**
	 * Check whether a game object with given center, given radius, given lowerbound and given upperbound is at a passable position.
	 * @param 	center
	 * 			The center of the game object to check.
	 * @param 	radiusOfCircle
	 * 			The radius of the game object to check.
	 * @param 	lowerBound
	 * 			The lowerbound of the direction to check.
	 * @param 	upperBound
	 * 			The upperbound of the direction to check.
	 * @return	result == for each x coordinate in passableMap[][]=>(Math.cos(lowerBound) * radiusOfCircle) + center.getX() && center.getX()<(Math.cos(upperbound) * radiusOfCircle) + center.getX()
	 * 						for each y coordinate in passableMap[][]=>(Math.sin(lowerBound) * radiusOfCircle) + center.getY() && center.getX()<(Math.sin(upperbound) * radiusOfCircle) + center.getY()
	 * 						if (! isImpassablePosition(new Position(x, y))
	 * @throws 	IllegalArgumentException
	 */
	private boolean isPassablePartOfPixeledRadiusOfCircle(Position center, double radiusOfCircle, double lowerBound, double upperBound) throws IllegalArgumentException{
		boolean isPassable = true;
		if (!this.isLocatedInWorld(center, radiusOfCircle))
			throw new IllegalArgumentException("Not fully located in world!");
		lowerBound = Worm.convertToRepresentativeAngle(lowerBound);
		upperBound = Worm.convertToRepresentativeAngle(upperBound);
		if(Util.fuzzyEquals(upperBound, 0))
			upperBound = Math.PI * 2;
		if ((lowerBound > upperBound))
			return false;
		double stepSize = Math.min(this.getPixelWidth(), this.getPixelHeight());
		double angleStepSize = 5 * Math.asin(stepSize);
		double centerX = center.getX();
		double centerY = center.getY();
		double x;
		double y;
		Position testPosition;
		do{
			x = (Math.cos(lowerBound) * radiusOfCircle) + centerX;
			y = (Math.sin(lowerBound) * radiusOfCircle) + centerY;
			testPosition = new Position(x,y);
			if(this.isImpassablePosition(testPosition)){
				isPassable = false;
				break;
			}
			lowerBound += angleStepSize;
		}
		while(lowerBound <= upperBound);
		return isPassable;
	}

	/**
	 * Check whether a game object with given center, given radius, given startAngle and given stopAngle is adjacent to impassable terrain.
	 * @param 	center
	 * 			The center of the game object to check.
	 * @param 	radius
	 * 			The radius of the game object to check.
	 * @param 	startAngle
	 * 			The starting angle of the direction of the game object to check.
	 * @param 	stopAngle
	 * 			The stopping angle of the direction of the game object to check.
	 * @return	result == (isPassablePartOfPixeledRadiusOfCircle(center, radius, 0, 0) && ! this.isPassablePartOfPixeledRadiusOfCircle(center, (radius * 1.1), startAngle, stopAngle))
	 */
	private boolean isAdjacentToImpassableTerrain(Position center, double radius, double startAngle, double stopAngle){
		boolean result;
		try{
			boolean isPassable = this.isPassablePartOfPixeledRadiusOfCircle(center, radius, 0, 0);
			boolean isAdjacent = !this.isPassablePartOfPixeledRadiusOfCircle(center, (radius * 1.1), startAngle, stopAngle);
			result = (isPassable && isAdjacent);
		}
		catch (IllegalArgumentException exc){
			result = false;
		}
		return result;
	}
	
	/**
	 * 
	 * @param 	original
	 * 			The position of the game object to check.
	 * @param 	stepSize
	 * 			The size of the steps to check.
	 * @return	Position == if (original.getX() > (this.getWidth() / 2) && (original.getY() > (this.getHeight() / 2))
	 * 					return new Position((original.getX() - (this.getPixelWidth() * stepSize)),(original.getY() - (this.getPixelHeight() * stepSize)))  	
	 * 						else
	 * 					return  new Position((original.getX() + (this.getPixelWidth() * stepSize)), (original.getY() + (this.getPixelHeight() * stepSize))
	 */
	private Position getPositionCloserToCenter(Position original, double stepSize){
		double newX;
		double newY;
		if(original.getX() > (this.getWidth() / 2))
			newX = original.getX() - (this.getPixelWidth() * stepSize);
		else newX = original.getX() + (this.getPixelWidth() * stepSize);
		if(original.getY() > (this.getHeight() / 2))
			newY = original.getY() - (this.getPixelHeight() * stepSize);
		else newY = original.getY() + (this.getPixelHeight() * stepSize);
		return new Position(newX, newY);
	}
	
	/**
	 * Variable registering the passableMap of this world.
	 */
	private boolean[][] passableMap;
	
	/**
	 * Returns the active worm of this world.
	 * @return for each worm in this.getAllWorm()
	 * 				if(worm.isActive())
						return worm
				else return null
	 */
	public Worm getActiveWorm(){
		List<Worm> allWorms = this.getAllWorms();
		for(Worm worm : allWorms){
			if(worm.isActive())
				return worm;
		}
		return null;
	}
	
	/**
	 * Returns the active projectile in this world.
	 * @return	for each game object in this.getObjects()
	 * 				if (object.getClass().getName() == Projectile.class.getName())
	 * 					return object
	 * 			else return null
	 */
	public Projectile getActiveProjectile(){
		for(GameObject object : this.getObjects()){
			if(object.getClass().getName() == Projectile.class.getName())
				return (Projectile) object;
		}
		return null;
	}
	
	/**
	 * Returns all living worms of this world.
	 * @return	for each worm in this.getAllWorms()
	 * 				if (worm.isAlive())
	 * 					result.add(worm)
	 */
	public List<Worm> getAllLiveWorms(){
		List<Worm> result = this.getAllWorms();
		for(Worm worm : result){
			if(!worm.isAlive())
				result.remove(worm);
		}
		return result;
	}

	/**
	 * Returns all food of this world.
	 * @return	for each food in this.getAllObjectsFrom(Food.class.getName())
	 * 				result.add(food)
	 */
	public List<Food> getAllFood(){
		List<Food> resultFood = new ArrayList<Food>();
			for (GameObject object: this.getAllObjectsFrom(Food.class.getName())){
				try{
					Food snack = (Food) object;
					resultFood.add(snack);
				}
				catch (ClassCastException exc) {
					assert false;
				}
			}
			return resultFood;
	}

	/** Adds a random worm to this world.
	 * @return	worm = new Worm(this.getRandomPositionAdjacentToImpassableFloor(radius),radius, 0, "Joske")
	 * @post	this.hasAsGameObject(worm)
	 * @post	if (this.getRandom().nextDouble() > 0.5 && this.getTeams().size())
	 * 				this.getTeams().get(this.getRandom().nextInt(numberOfTeams)).hasAsWorm(worm)
	 * @effect	worm.getTeam() = this.getTeams().get(this.getRandom().nextInt(numberOfTeams))	
	 * 				
	 */
	public Worm addRandomWorm(){
		double radius = 1;
		Worm randomWorm = null;
		Position randomPosition = this.getRandomPositionAdjacentToImpassableFloor(radius);
		if(randomPosition != null){
			randomWorm = new Worm(randomPosition,radius, 0, "Joske");
			this.addAsGameObject(randomWorm);
			int numberOfTeams = this.getTeams().size();
			boolean assignToTeam = (this.getRandom().nextDouble() > 0.5);
			if((numberOfTeams > 0) && assignToTeam){
				this.getTeams().get(this.getRandom().nextInt(numberOfTeams)).addAsWorm(randomWorm);
			}
		}
		return randomWorm;
	}
	
	/**
	 * Adds a random piece of food to this world.
	 * @post this.hasAsGameObject(new Food(this.getRandomPositionAdjacentToImpassableFloor(0.2)))
	 * @effect	this.getObjects().indexOf(objects.size()-1).getWorld() = this
	 */
	public void addRandomFood(){
		Position randomPosition = this.getRandomPositionAdjacentToImpassableFloor(0.2);
		if(randomPosition != null){
			this.addAsGameObject(new Food(randomPosition));
		}
	}
	
	/**
	 * Return a list of worms of this world of which the game object with given position and given radius has an overlap with.
	 * @param 	p
	 * 			The position of the game object.
	 * @param 	radius
	 * 			The radius of the game object.
	 * @return	for each food in this.getObjects()
	 * 				if (food.partialOverlapWith(p, radius))
	 * 					resultFood.add(food)
	 * 			return resultFood
	 */
	protected List<Food> overlapWithFood(Position p, double radius){
		String className = Food.class.getName();
		List<GameObject> result = new ArrayList<GameObject>();
		List<Food> resultFood = new ArrayList<Food>();
		 result = this.getAllObjectsFrom(className, this.getObjects());
			for (GameObject object: result){
				try { Food food = (Food) object;
				if (food.partialOverlapWith(p, radius))
					resultFood.add(food);
				}	catch (ClassCastException exc) {
					assert false;
					}
			}
	
			return resultFood;
	}
	/**
	 * Return a list of worms of this world. of which the the game object with given position and given radius has an overlap.
	 * @param 	p
	 * 			The position of the game object.
	 * @param 	radius
	 * 			The radius of the game object.
	 * @return	for each worm in this.getObjects()
	 * 				if (food.partialOverlapWith(p, radius))
	 * 					resultWorm.add(food)
	 * 			return resultWorm
	 */
	protected List<Worm> overlapWithWorm(Position p, double radius){
		String className = Worm.class.getName();
		List<GameObject> result = new ArrayList<GameObject>();
		List<Worm> resultWorm = new ArrayList<Worm>();
			result = this.getAllObjectsFrom(className, this.getObjects());
			for (GameObject object: result){
				try { Worm worm = (Worm) object;
				if (worm.partialOverlapWith(p, radius))
					resultWorm.add(worm);
				}	catch (ClassCastException exc) {
					assert false;
					}
			
			}
			return resultWorm;
	}
	
	/**
	 * Return the list of all worms of this world.
	 * 
	 * @return	for each worm in this.getObjects()
	 * 				resultWorms.add(worm);
	 * 			return resultWorms
	 */
	protected List<Worm> getAllWorms(){
		List<Worm> resultWorms = new ArrayList<Worm>();
			for (GameObject object: this.getAllObjectsFrom(Worm.class.getName())){
				try{
					Worm worm = (Worm) object;
					resultWorms.add(worm);
				}
				catch (ClassCastException exc) {
					assert false;
				}
			}
			return resultWorms;
	}
	
	/**
	 * Return the list of all game objects of this world.
	 * 
	 * @return	this.objects
	 */
	protected List<GameObject> getObjects() {
		return this.objects;
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
	
	public static List<GameObject> getAllObjectsFrom(List<GameObject> gameObjects, Method method) throws IllegalArgumentException, InvocationTargetException {
		List<GameObject> result = new ArrayList<GameObject>();
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
		else {
			for (GameObject gameObject: gameObjects)
				try {
					result.add( method.invoke(gameObject));
				}	catch (IllegalAccessException exc) {
					assert false;
					}
			}	
	
		return result;
				
	}
	*/
	/**
	 * Returns a list of game objects of a specific class.
	 * @param 	className
	 * 			The name of the class of which the game objects will be returned.
	 * @param 	gameObjects
	 * 			The list of game objects to extract the game objects of the specified class.
	 * @return	for each  game object in gameObjects
	 * 				if( gameObject.getClass().getName() == className)
						result.add(gameObject)
					return result
	 */
	protected static List<GameObject> getAllObjectsFrom(String className, List<GameObject> gameObjects){
		List<GameObject> result = new ArrayList<GameObject>();
		for (GameObject gameObject: gameObjects)
			if( gameObject.getClass().getName() == className){
				result.add(gameObject);
			}
		return  result;
	}
	
	/**
	 * Returns a list of game objects of a specific class.
	 * @param 	className
	 * 			The name of the class of which the game objects will be returned.
	 * @return	return this.getAllObjectsFrom(className, this.getObjects())
	 */
	protected List<GameObject> getAllObjectsFrom(String className){
		return this.getAllObjectsFrom(className, this.getObjects());
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
	protected boolean hasProperGameObjects(){
	for (GameObject object: this.objects){
		if (! canHaveAsGameObject(object)){
			return false;
		}
		if (object.getWorld() != this){
			return false;
		}
	}
	return true;
	
	}

	/**
	 * @param	object
	 * 			The object to check.
	 */
	@Basic @Raw
	protected boolean hasAsGameObject(GameObject object){
		return this.objects.contains(object);
	}

	/**
	 * Check whether this world can have the given game object as one of the game objects attached to it.
	 * 
	 * @param 	object
	 * 			The game object to check.
	 * @return	if (object == null)
	 * 				then result == false
	 * 			if (! object.getWorld() == null)
	 * 				then result == false
	 * 			else result == 
	 * 				(! objects.equals(object))
	 */
	protected boolean canHaveAsGameObject(GameObject object) {
		return 	( (object != null)
				&& (! objects.contains(object)));
	}

	/**
	 * 
	 * @param 	object
	 * 			The game object to be added
	 * @post	new.hasAsGameObject(object)
	 * @post	(new object).getWorld() == this
	 * @throws	IllegalArgumentException
	 * 			(! canHaveAsGameObject(object))
	 * @throws 	IllegalArgumentException
	 * 			(object.getWorld() != null)
	 * @throws 	IllegalArgumentException
	 * 			(isStarted == true && ( object instanceof Worm || object instanceof Food ))
	 */
	protected void addAsGameObject(GameObject object) throws IllegalArgumentException {
		if (! canHaveAsGameObject(object)) 
			throw new IllegalArgumentException("This is not a proper object for this world");
		if (object.getWorld() != null)
			throw new IllegalArgumentException("This object appears in another world");
		if ((isStarted() == true) && ( object instanceof Worm || object instanceof Food )){
			throw new IllegalArgumentException("Cannot add worms or worm food during the game");
		}
		object.setWorld(this);
		this.objects.add(object);
	}

	/**
	 * @param	object
	 * 			The object to be removed
	 * @post	! new.hasAsGameObject(object)
	 * @post	((new object).getWorld() == this) 				
	 * @throws	IllegalArgumentException
	 * 			(! hasAsGameObject)
	 */
	protected void removeAsGameObject(GameObject object) throws IllegalArgumentException {
		if (hasAsGameObject(object)){
			object.removeFromWorld();
			this.objects.remove(object);
		}
		else throw new IllegalArgumentException("This object does not belong to this world");
		
	}

	/**
	 * @invar	objects != null
	 * @invar	for each gameobject in objects:
	 * 			( (object == null)
	 * 			|| canHaveAsGameObject(object) )
	 */
	private final List<GameObject> objects = new ArrayList<GameObject>();

	/**
	 * Return the teams of this world.
	 */
	protected List<Team> getTeams() {
		return this.teams;
	}

	/**
	 * Check whether the given team is a valid team for this world.
	 * @param 	team
	 * 			The team to be checked.
	 * @return	result == (! teams.contains(team) && teams.size() <= 9 && team.isValidName(team.getName()))
	 */
	protected boolean canHaveAsTeam(Team team) {
		return (! teams.contains(team) && teams.size() <= 9 && team.isValidName(team.getName()));
	}

	/**
	 * Remove the given team from this world.
	 * 
	 * @param 	team
	 * 			The team to be removed.
	 * @post	! new.getTeams().contains(team)	
	 */
	protected void removeAsTeam(Team team) {
		teams.remove(team);
		}

	/**
	 * Add the given team to this world.
	 * @param 	team
	 * 			The team to be added to this world.
	 * @post	new.getTeams().contains(team)
	 * @throws 	IllegalArgumentException
	 * 			(! canHaveAsTeam(team))
	 */
	protected void addAsTeam(Team team) throws IllegalArgumentException {
		if (! canHaveAsTeam(team)){
			throw new IllegalArgumentException("invalid team");
		}
		else teams.add(team);
	}
	/**
	 * Variable registering the teams of this world.
	 */
	private final List<Team> teams = new ArrayList<Team>();

	public void startGame(){
		setStarted(true);
		List<Worm> allWorms = this.getAllWorms();
		if(allWorms.size() > 0)
			allWorms.get(0).activate();
		else throw new IllegalComponentStateException("No worms in this world");
	}

	public void startNextTurn(){
		List<Worm> allWorms = this.getAllLiveWorms();
		int nextIndex = allWorms.indexOf(this.getActiveWorm()) + 1;
		if(nextIndex == allWorms.size())
			nextIndex = 0;
		allWorms.get(nextIndex).activate();
	}
	
	public boolean isFinished(){
		return (this.getWinners() != null);
	}
	
	public String getWinner(){
		List<Worm> winners = this.getWinners();
		if(winners == null)
			return "No winner!";
		else if((winners.size() == 1) && (winners.get(0).getTeam() == null))
			return winners.get(0).getName();
		else return winners.get(0).getTeam().getName();
	}

	private boolean isStarted(){
		return this.started;
	}

	private void setStarted(boolean flag){
		this.started = flag;
	}

	public List<Worm> getWinners(){
		List<Worm> allLiveWorms = this.getAllLiveWorms();
		List<Team> liveTeams = new ArrayList<Team>();
		for(Worm worm : allLiveWorms){
			liveTeams.add(worm.getTeam());
		}
		if(allLiveWorms.size() == 1)
			return allLiveWorms;
		else if((allLiveWorms.size() > 1) && (allLiveWorms.get(0).getTeam() != null)){
			Team team = allLiveWorms.get(0).getTeam();
			for(Team teamToCheck : liveTeams){
				if(teamToCheck != team)
					return new ArrayList<Worm>();
			}
			return allLiveWorms;
		}
		else return null;
	}
	
	private boolean started;
	
}


