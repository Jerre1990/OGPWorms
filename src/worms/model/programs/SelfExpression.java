package worms.model.programs;

public class SelfExpression implements Expression {

	WormEntityType self;
	
	Program program;
	
	public SelfExpression(){
		self = new WormEntityType(program.getWorm());
		
	}
	
	@Override
	public Type evaluate() {
		return self;
	}

}
