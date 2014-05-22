package worms.model.programs;

public class GetDirExpression implements Expression{
	DoubleType dir;
	
	public GetDirExpression(EntityExpression e){
		WormEntityType eType = (WormEntityType) e.evaluate();
		this.dir = new DoubleType(eType.getValue().getDirection());
	}

	@Override
	public Type evaluate() {
		return dir;
	}
}
