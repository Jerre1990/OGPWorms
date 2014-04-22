package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @Invar	isValidName(getName())
 * 
 * @version 2.0
 * @author Jonas Thys & Jeroen Reinenbergh
 */

public abstract class Identifiable {
	
	/**
	 * @param	name
	 * @post 	new.getName() = name
	 * @effect	isValidName(new.getName())
	 * @throws 	IllegalArgumentException("Invalid name!")
	 * 		|	! isValidName(name)
	 */
	public Identifiable(String name){
		this.setName(name);
	}
	
	@Basic
	public String getName() {
		return name;
	}	
	
	/**
	 * @param	name
	 * @post	new.getName() == name
	 * @effect	isValidName(new.getName())
	 * @throws 	IllegalArgumentException("Invalid name!")
	 * 		|	! isValidName(name)
	 */	
	@Raw
	public void setName(String name) throws IllegalArgumentException {
		if (!this.isValidName(name))
			throw new IllegalArgumentException("Invalid name!");
		this.name = name;
	}
	
	@Model @Raw
	protected abstract boolean isValidName(String name);

	private String name;

}
