package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;

public abstract class Identifiable {
	
	public Identifiable(String name){
		this.setName(name);
	}
	
	@Basic
	public String getName() {
		return name;
	}	
	
	/**
	 * @param	name
	 * @return	Result == true
	 */
	@Model
	protected boolean isValidName(String name){
		return true;
	}
	
	/**
	 * @param	name
	 * @post	new.getName() == name
	 * @effect	this.isValidName(new.getName())
	 * @throws 	IllegalArgumentException("Name is not valid!")
	 * 		|	! this.isValidName(name)
	 */	
	public void setName(String name) throws IllegalArgumentException {
		if (!this.isValidName(name))
			throw new IllegalArgumentException("Name is not valid!");
		else this.name = name;
	}
	
	private String name;

}
