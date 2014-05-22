package worms.model.programs;

import worms.model.GameObject;
import worms.model.Team;
import worms.model.Worm;

public class WormEntityType extends EntityType{
	
	public WormEntityType(Worm w){
		super(w);
	}
	
	public Worm getValue() {
		return (Worm) o;
	}
	
	public Team getTeam() {
		return this.getValue().getTeam();
	}
	
	public GameObject searchNearestObjectInGivenDirection(DoubleType theta){
		return this.getValue().searchNearestObjectInGivenDirection(theta.getValue());
	}

	
}
