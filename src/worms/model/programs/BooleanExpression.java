package worms.model.programs;

import java.util.Map;

public class BooleanExpression implements Expression {

	private BooleanType booleanType;

	public BooleanExpression(boolean booleanType) {
		this.booleanType = new BooleanType(booleanType);
	}
	
	@Override
	public BooleanType evaluate(Map<String,Type> context) {
		return booleanType;
	}

}