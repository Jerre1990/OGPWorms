package worms.model.programs;

public class BooleanExpression implements Expression {

	private boolean booleanExpression;

	public BooleanExpression(boolean booleanExpression) {
		this.booleanExpression = booleanExpression;
	}
	
	public boolean getContrary () {
		return ! this.booleanExpression;
	}
	
	@Override
	public Boolean evaluate() {
		return booleanExpression;
	}

}