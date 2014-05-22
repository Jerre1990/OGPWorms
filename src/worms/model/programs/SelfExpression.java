package worms.model.programs;

public class SelfExpression implements Expression {

	WormEntity self;
	
	public SelfExpression(){
		self = new WormEntity(program.getWorm());
		
	}
	
	@Override
	public Type evaluate() {
		return self;
	}

}
