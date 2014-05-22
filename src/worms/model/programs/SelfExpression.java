package worms.model.programs;

public class SelfExpression implements Expression {

	Entity self;
	
	public SelfExpression(){
		
	}
	
	@Override
	public Type evaluate() {
		return self;
	}

}
