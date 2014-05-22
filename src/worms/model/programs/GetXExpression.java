package worms.model.programs;

import java.util.Map;

public class GetXExpression implements Expression {
	
	Expression e;
	
	public GetXExpression(Expression e){
		this.e = e;
	}

	public Type evaluate(Map<String,Type> context) {
		EntityType eType = (EntityType) e.evaluate(context);
		return new DoubleType(eType.getValue().getX());
	}

}
