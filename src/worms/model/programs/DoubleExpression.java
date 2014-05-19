package worms.model.programs;

public class DoubleExpression implements Expression {

	private double doubleExpression;

	public DoubleExpression(double doubleExpression) {
		this.doubleExpression = doubleExpression;
	}

	public Object evaluate() {
		return doubleExpression;
	}

}