package worms.model.programs;

import java.util.Map;

public class GetYExpression implements Expression{
		
	Expression e;
	
	public GetYExpression(Expression e){
		this.e = e;
	}

	@Override
	public Type evaluate(Map<String,Type> context) {
		EntityType eType = (EntityType) e.evaluate(context);
		return new DoubleType(eType.getValue().getY());
	}

}
