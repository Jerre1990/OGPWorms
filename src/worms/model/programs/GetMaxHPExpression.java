package worms.model.programs;

public class GetMaxHPExpression implements Expression{
	DoubleType maxhp;
	
	public GetMaxHPExpression(EntityExpression e){
		WormEntityType eType = (WormEntityType) e.evaluate();
		this.maxhp = new DoubleType((double) eType.getValue().getMaxNumberOfHitPoints());
	}

	@Override
	public Type evaluate() {
		return maxhp;
	}
}
