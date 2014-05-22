package worms.model.programs;

public class GetAPExpression implements Expression{
	DoubleType ap;
	
	public GetAPExpression(EntityExpression e){
		WormEntityType eType = (WormEntityType) e.evaluate();
		this.ap = new DoubleType((double) eType.getValue().getNumberOfActionPoints());
	}

	@Override
	public Type evaluate() {
		return ap;
	}
}
