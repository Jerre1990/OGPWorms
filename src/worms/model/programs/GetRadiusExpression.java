package worms.model.programs;

public class GetRadiusExpression implements Expression {
	DoubleType radius;
	
	public GetRadiusExpression(EntityExpression e){
		WormEntityType eType = (WormEntityType) e.evaluate();
		this.radius = new DoubleType(eType.getValue().getRadius());
	}

	@Override
	public Type evaluate() {
		return radius;
	}
}
