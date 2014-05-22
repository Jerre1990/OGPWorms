package worms.model.programs;

import java.util.Map;

import worms.model.GameObject;
import worms.model.MovableGameObject;

public class SearchObjExpression implements Expression{
	
	Expression e;
	
	public SearchObjExpression(Expression e){
		this.e = e;
	}
	
	@Override
	public Type evaluate(Map<String, Type> context) {
		if (e instanceof DoubleExpression){
			DoubleType d = ((DoubleType) e.evaluate(context));
			MovableGameObject m = (MovableGameObject)((EntityType) context.get("self")).getValue();
			return new EntityType(m.searchNearestObjectInGivenDirection(d.getValue()));}
		else return null;
	}
}
