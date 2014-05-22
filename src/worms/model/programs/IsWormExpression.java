package worms.model.programs;

public class IsWormExpression implements Expression{
	
	BooleanType isWorm;
	
	public IsWormExpression(Expression e){
		if (e.evaluate() instanceof WormEntityType)
		isWorm = new BooleanType(true);
		else isWorm = new BooleanType(false);
	}
	

	@Override
	public Type evaluate() {
		return isWorm;
	}

}
