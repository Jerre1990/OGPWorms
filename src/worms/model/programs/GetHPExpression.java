package worms.model.programs;

import java.util.Map;

public class GetHPExpression implements Expression{
	EntityExpression e;
	
	public GetHPExpression(EntityExpression e){
		this.e = e;
	}

	@Override
	public Type evaluate(Map<String, Type> context) {
		WormEntityType eType = (WormEntityType) e.evaluate(context);
		return new DoubleType((double) eType.getValue().getNumberOfHitPoints());
	}
}
