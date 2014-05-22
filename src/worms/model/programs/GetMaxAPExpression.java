package worms.model.programs;

public class GetMaxAPExpression implements Expression{
	DoubleType maxap;
	
	public GetMaxAPExpression(EntityExpression e){
		WormEntityType eType = (WormEntityType) e.evaluate();
		this.maxap = new DoubleType((double) eType.getValue().getMaxNumberOfActionPoints());
	}

	@Override
	public Type evaluate() {
		return maxap;
	}
}
