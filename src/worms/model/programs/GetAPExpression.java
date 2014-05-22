package worms.model.programs;

import java.util.Map;

public class GetAPExpression implements Expression{
	EntityExpression e;
	DoubleType ap;
	
	
	public GetAPExpression(EntityExpression e){
		this.e = e;
	}

	@Override
	public Type evaluate(Map<String,Type> context) {
		WormEntityType eType = (WormEntityType) e.evaluate(context);
		return new DoubleType((double) eType.getValue().getNumberOfActionPoints());
	}
}
