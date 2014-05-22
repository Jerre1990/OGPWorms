package worms.model.programs;

import java.util.Map;

public class GetAPExpression implements Expression{
	Expression e;
	DoubleType ap;
	
	
	public GetAPExpression(Expression e){
		this.e = e;
	}

	@Override
	public Type evaluate(Map<String,Type> context) {
		WormEntityType eType = (WormEntityType) e.evaluate(context);
		return new DoubleType((double) eType.getValue().getNumberOfActionPoints());
	}
}
