package worms.model.programs;

public class DoubleExpression implements Expression {

	DoubleType doubleType;
	
	public DoubleExpression(double doubleType){
		this.doubleType = new DoubleType(doubleType);
	}
	
	@Override
	public Type evaluate() {
		return doubleType;
	}

}
