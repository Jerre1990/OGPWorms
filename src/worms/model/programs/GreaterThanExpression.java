package worms.model.programs;

import java.util.Map;

public class GreaterThanExpression implements Expression {

	Expression e1, e2;
	
	public GreaterThanExpression(Expression e1, Expression e2){
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public Type evaluate(Map<String,Type> context){
		return new BooleanType(((DoubleType) e1.evaluate(context)).getValue() > ((DoubleType) e2.evaluate(context)).getValue());
	}

}
