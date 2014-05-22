package worms.model.programs;

import java.util.Map;

public class SelfExpression implements Expression {

	public SelfExpression(){
	}
	
	@Override
	public Type evaluate(Map<String,Type> context) {
		return context.get("self");
	}

}