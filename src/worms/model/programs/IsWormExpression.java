package worms.model.programs;

import java.util.Map;

public class IsWormExpression implements Expression{
	
	Expression e;
	
	public IsWormExpression(Expression e){
		this.e = e;
	}
	

	public Type evaluate(Map<String,Type> context) {
		if (e.evaluate(context) instanceof WormEntityType)
		return new BooleanType(true);
		else return new BooleanType(false);
	}

}
