package worms.model.programs;

import java.util.Map;

public class GetRadiusExpression implements Expression {
	
	Expression e;
	
	public GetRadiusExpression(Expression e){

	}

	public Type evaluate(Map<String,Type> context) {
		WormEntityType eType = (WormEntityType) e.evaluate(context);
		return new DoubleType(eType.getValue().getRadius());
	}
}
