package worms.model.programs;

public class BooleanExpression implements Expression {

	private BooleanType booleanType;

	public BooleanExpression(boolean booleanType) {
		this.booleanType = new BooleanType(booleanType);
	}
	
	@Override
	public BooleanType evaluate() {
		return booleanType;
	}

}