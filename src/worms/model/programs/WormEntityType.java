package worms.model.programs;

import worms.model.GameObject;
import worms.model.Worm;

public class WormEntityType extends EntityType{
	
	public WormEntityType(Worm w){
		super(w);
	}
	
	public Worm getValue() {
		return (Worm) o;
	}
	
}
