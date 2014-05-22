package worms.model.programs;

import java.util.Map;

public class isFoodExpression implements Expression {
	
	Expression e;
	
	public isFoodExpression(Expression e){
		this.e = e;
	}

	public Type evaluate(Map<String,Type> context) {
		if (e.evaluate(context) instanceof FoodEntityType){
			return new BooleanType(true);
		}
		else return new BooleanType(false);
	}

}
