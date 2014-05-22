package worms.model.programs;

import java.util.Map;

public class VariableAccesExpression implements Expression {
	
	private String name;
	
	public VariableAccesExpression(String name){
		this.name = name;
	}

	@Override
	public Type evaluate(Map<String,Type> context) {
		return context.get(name);
	}
	
	

}
