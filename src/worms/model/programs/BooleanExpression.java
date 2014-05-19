package worms.model.programs;

public class BooleanExpression implements Expression {

	private boolean booleanExpression;

	public BooleanExpression(boolean booleanExpression) {
		this.booleanExpression = booleanExpression;
	}

	public Object evaluate() {
		return booleanExpression;
	}

}