package worms.model;

import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @Invar	isValidName(getName())
 * 
 * @version 2.0
 * @author Jonas Thys & Jeroen Reinenbergh
 */


public class Team extends Identifiable{
	
	/**
	 * @param	name
	 * @post 	new.getName() = name
	 * @effect	isValidName(new.getName())
	 * @throws 	IllegalArgumentException("Invalid name!")
	 * 		|	! isValidName(name)
	 */
	public Team(String name) {
		super(name);
	}

	/**
	 * @param	name
	 * @return	result == name.matches("[A-Z]"+"[A-Za-z]+")
	 */
	@Override @Model @Raw
	protected boolean isValidName(String name) {
		return name.matches("[A-Z]"+"[A-Za-z]+");
	}
	
	List<Worm> worms = new ArrayList<Worm>();
	
	/**
	 * Terminate this team.
	 * 
	 * @post	new.isTerminated()
	 * @effect	for each worm in getAllWorms():
	 * 					then this.removeAsWorm(worm) && worm.setTeam(null)
	 */
	public void terminate(){
		this.isTerminated = true;
		for (Worm worm: this.worms) {
				worm.setTeam(null);
				this.worms.remove(worm);
			
		}
	}
	
	public boolean isTerminated() {
		return isTerminated;
	}
	
	/**
	 * Variable registering whether or not this team is terminated.
	 */
	boolean isTerminated;
		
	/**
	 * Check whether this team can have the given worm as its worm.
	 * @param 	worm
	 * 			The worm to check.
	 * @return	if (worm == null || this.isTerminated)
	 * 				result == false
	 * 			else result == true
	 */
	@Raw
	public boolean canHaveAsWorm(Worm worm){
		if (worm == null || this.isTerminated)
		return false;
		else return true;
	}
	/**
	 * Check whether this team has the given worm as one of the worms attached to it.
	 * @param 	worm
	 * 			The worm to be checked
	 * @return	(this.worms.contains(worm))
	 */
	@Raw @Basic
	public boolean hasAsWorm(Worm worm){
		return (this.worms.contains(worm));
	}
	/**
	 * Check whether this team has proper worms attached to it.
	 * @return	result == 
	 * 				for each worm in worms
	 * 					if (canHaveAsWorm(worm) && worm.getTeam() = this )
	 */
	@Raw
	public boolean hasProperWorms(){
		for (Worm worm: this.worms) {
			if (! canHaveAsWorm(worm) || worm.getTeam() != this )
				return false;	
		}
		return true;
	}
	/**
	 * Add the given worm to this team.
	 * @param 	worm
	 * 			The worm to be added.
	 * @post	worms.contains(worm)
	 * @post	(new worm).getTeam() == this
	 * @throws 	IllegalArgumentException
	 * 			(! canHaveAsWorm(worm) || worm.getTeam() != null)
	 */
	public void addAsWorm(Worm worm) throws IllegalArgumentException {
		if (! canHaveAsWorm(worm) || worm.getTeam() != null)
			throw new IllegalArgumentException("Not a valid worm");
		else 	this.worms.add(worm);
				worm.setTeam(this);
	}
	/**
	 * Remove the given worm from this team.
	 * @param 	worm
	 * 			The worm to be removed
	 * @post	! hasAsWorm(worm)
	 * @post	worm.getTeam() == null
	 */
	public void removeAsWorm(Worm worm){
		if (hasAsWorm(worm)){
			this.worms.remove(worm);
			worm.setTeam(null);
		}
	}
	/**
	 * Return a set collecting all worms associated with this team.
	 * @return	! result.contains(null)
	 * @return	for each worm in worms
	 * 				(result.contains(worm) == this.hasAsWorm(worm))
	 */
	public List<Worm> getAllWorms(){
		return this.worms;
	}
	
	public List<Worm> getAllLiveWorms(){
		List<Worm> liveWorms = this.getAllWorms();
		for(Worm worm : liveWorms){
			if(!worm.isAlive())
				liveWorms.remove(worm);
		}
		return liveWorms;
	}
	
}
