package worms.model.programs;

import java.util.Map;

public class SameTeamExpression implements Expression {
	
	Expression e;
	
	SameTeamExpression(Expression e){	
		
	}

	@Override
	public Type evaluate(Map<String, Type> context) {
		if(((WormEntityType) e.evaluate(context)).getTeam() == (((WormEntityType) context.get("self")).getTeam())){
			return new BooleanType(true);
		}
		else return new BooleanType(false);
	}
	

}
