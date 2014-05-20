package worms.model.programs;

import worms.model.GameObject;
import worms.model.Team;
import worms.model.Worm;

public class WormExpression implements Expression {
	
	private Worm worm;
	
	public WormExpression(Worm worm) {
		this.worm = worm;
	}

	@Override
	public Object evaluate() {
		return worm;
	}
	
	public double getAP(){
		return worm.getNumberOfActionPoints();
	}
	
	public double getX(){
		return worm.getX();
	}
	
	public double getY(){
		return worm.getY();
	}
	
	public double getRadius(){
		return worm.getRadius();
	}
	
	public double getMaxAP(){
		return worm.getMaxNumberOfActionPoints();
	}
	
	public double getHP(){
		return worm.getNumberOfHitPoints();
	}
	
	public double getMaxHP(){
		return worm.getMaxNumberOfHitPoints();
	}
	
	public double getDirection(){
		return worm.getDirection();
	}
	
	public Team getTeam(){
		return worm.getTeam();
	}
	
	public GameObject searchNearestObjectInGivenDirection(double theta){
		return worm.searchNearestObjectInGivenDirection(theta);
	}
	

}
