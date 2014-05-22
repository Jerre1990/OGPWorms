package worms.model.programs;

public class GetYExpression implements Expression{
		
	DoubleType yCoordinate;
	
	public GetYExpression(EntityExpression e){
		EntityType eType = (EntityType) e.evaluate();
		this.yCoordinate = new DoubleType(eType.getValue().getY());
	}

	@Override
	public Type evaluate() {
		return yCoordinate;
	}

}
