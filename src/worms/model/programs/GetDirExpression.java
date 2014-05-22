package worms.model.programs;

import java.util.Map;

public class GetDirExpression implements Expression{
	Expression e;
	
	public GetDirExpression(Expression e){
		this.e = e;
	}

	@Override
	public Type evaluate(Map<String,Type> context) {
		WormEntityType eType = (WormEntityType) e.evaluate(context);
		return new DoubleType(eType.getValue().getDirection());
	}
}
