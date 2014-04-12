package worms.model;

public abstract class GameObject {

@Basic	
public World getWorld(){
	return this.world;
}
	
World world;

/**
 * 
 * @param 	gameWorld
 * 			The game world to be set.
 * @post	new.getWorld() = gameWorld
 * @throws 	IllegalArgumentException
 * 			! canHaveAsWorld(gameWorld)
 */

public void setWorld(World gameWorld) throws IllegalArgumentException {
	if (!canHaveAsWorld(gameWorld))
		throw new IllegalArgumentException("This is not a valid world");
	else this.world = gameWorld;
}

/**
 * Terminate this world.
 * 
 *@post	new.isTerminated()
 * 
*@effect	for each object in getAllGameObjects:
 *			if (! object.isTerminated())
 *				then getWorld().removeAsGameObject(object) 	
 * 
 * 
 */
public void terminate() {
	if (! isTerminated){
		getWorld().removeAsGameObject(this);
		this.isTerminated = true;
	}
}

@Basic @Raw
public boolean isTerminated(){
	return this.isTerminated;
}

private boolean isTerminated;


/**
 * Check whether this game object can have the given world as its world.
 * 
 * @param 	gameWorld
 * 			The game world to check.
 * @return	result ==
 * 				( (gameWorld == null)
 * 				|| gameWorld.canHaveAsGameObject(this) )
 */
public boolean canHaveAsWorld(World gameWorld){
	return ( (gameWorld ==null)
			|| gameWorld.canHaveAsGameObject(this));	
}


/**
 * 
 * @return	result == 
 * 				(canHaveAsWorld(getWorld())
				&& ( (getWorld() == null))
					|| getWorld().hasAsGameObject(this) )
 */
public boolean hasProperGameWorld(){
	return (canHaveAsWorld(getWorld())
			&& ( (getWorld() == null))
				|| getWorld().hasAsGameObject(this) );
	
}
Double radius;

}
