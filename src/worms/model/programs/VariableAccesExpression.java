package worms.model.programs;

public class VariableAccesExpression implements Expression {
	
	private String name;
	
	public VariableAccesExpression(String name){
		this.name = name;
	}

	@Override
	public Type evaluate() {
		return name;
	}
	
	

}
