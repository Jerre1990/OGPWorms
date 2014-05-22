package worms.model.programs;

import java.util.Map;

public class GetMaxAPExpression implements Expression{
	Expression e;
	
	public GetMaxAPExpression(Expression e){
		this.e = e;
	}

	public Type evaluate(Map<String,Type> context) {
		WormEntityType eType = (WormEntityType) e.evaluate(context);
		return new DoubleType((double) eType.getValue().getMaxNumberOfActionPoints());
	}
}
