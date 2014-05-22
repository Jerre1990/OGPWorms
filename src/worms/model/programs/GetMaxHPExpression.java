package worms.model.programs;

import java.util.Map;

public class GetMaxHPExpression implements Expression{
	
	EntityExpression e;
	
	public GetMaxHPExpression(EntityExpression e){
		this.e = e;
	}

	public Type evaluate(Map<String,Type> context) {
		WormEntityType eType = (WormEntityType) e.evaluate(context);
		return new DoubleType((double) eType.getValue().getMaxNumberOfHitPoints());
	}
}
