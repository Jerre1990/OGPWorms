package worms.model.programs;

import java.util.Map;

public class GetYExpression implements Expression{
		
	EntityExpression e;
	
	public GetYExpression(EntityExpression e){
		this.e = e;
	}

	@Override
	public Type evaluate(Map<String,Type> context) {
		EntityType eType = (EntityType) e.evaluate(context);
		return new DoubleType(eType.getValue().getY());
	}

}
