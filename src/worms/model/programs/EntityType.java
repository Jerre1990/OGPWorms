package worms.model.programs;

import worms.model.GameObject;

public class EntityType implements Type {

protected GameObject o;
	
	public EntityType(GameObject o){
		this.o = o;
	}
	
	
	public GameObject getValue() {
		return o;
	}
	
}
