package worms.model;

import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;

public class Team {

	/**
	 * Return the name of the team.
	 * 	The name expresses the alphabetic identification of the team.
	 */
	@Basic
	public String getName() {
		return name;
	}	
	
	/**
	 * Check whether the given name is a valid name for any team.
	 * 
	 * @param	name
	 * 			The name to check.
	 * @return	True if and only if the given name contains at least 2 charachters,
	 * 			starts with an uppercase letter and only contains letters.
	 * 		|	result == (name.matches("[A-Z]"+"[A-Za-z]+"))
	 */
	@Model
	private boolean isValidName(String name) {
		return name.matches("[A-Z]"+"[A-Za-z]+");
	}	
	
	/**
	 * Set the name of this team to the given name

	 * @param	name
	 * 			The new name of this team.
	 * @post	The new name of this team is equal to the given name.
	 * 		|	new.getName() == name
	 * @effect	The new name of this team is a valid name for any team.
	 * 		|	this.isValidName(new.getName())
	 * @throws 	IllegalArgumentException("Name is not valid!")
	 * 			The name of this team is not a valid name for any team.
	 * 		|	! this.isValidName(name)
	 */	
	public void setName(String name) throws IllegalArgumentException {
		if (!this.isValidName(name))
			throw new IllegalArgumentException("Name is not valid!");
		else this.name = name;
	}
	
	/**
	 * Variable registering the name of this team.
	 */
	private String name;
	
	public void addWorm(Worm worm){
		
	}
	
	public List<Worm> queryLiveWorms(){
		
	}
	
}
