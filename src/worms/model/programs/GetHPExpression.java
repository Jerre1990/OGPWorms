package worms.model.programs;

public class GetHPExpression implements Expression{
	DoubleType hp;
	
	public GetHPExpression(EntityExpression e){
		WormEntityType eType = (WormEntityType) e.evaluate();
		this.hp = new DoubleType((double) eType.getValue().getNumberOfHitPoints());
	}

	@Override
	public Type evaluate() {
		return hp;
	}
}
