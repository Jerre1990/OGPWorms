package worms.model.programs;

import worms.model.Worm;

public class Program {
	
	Worm worm;
	
	public Program(){
		
	}
	
	public void setWorm(Worm worm){
		this.worm = worm;
	}
	
	public Worm getWorm(){
		return this.worm;
	}

}
