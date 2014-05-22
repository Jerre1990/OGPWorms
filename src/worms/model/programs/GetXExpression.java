package worms.model.programs;

public class GetXExpression implements Expression {
	
	DoubleType xCoordinate;
	
	public GetXExpression(EntityExpression e){
		EntityType eType = (EntityType) e.evaluate();
		this.xCoordinate = new DoubleType(eType.getValue().getX());
	}

	@Override
	public Type evaluate() {
		return xCoordinate;
	}

}
