package worms.model.programs;

import java.util.Map;

public class DoubleExpression implements Expression {

	DoubleType doubleType;
	
	public DoubleExpression(double doubleType){
		this.doubleType = new DoubleType(doubleType);
	}
	
	@Override
	public Type evaluate(Map<String,Type> context) {
		return doubleType;
	}

}
