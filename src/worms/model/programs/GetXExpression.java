package worms.model.programs;

public class GetXExpression implements Expression {
	
	DoubleType xCoordinate;

	@Override
	public Type evaluate() {
		return xCoordinate;
	}

}
