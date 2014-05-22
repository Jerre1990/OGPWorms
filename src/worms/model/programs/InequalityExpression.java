package worms.model.programs;

import java.util.Map;

public class InequalityExpression implements Expression {
	
	Expression e1;
	Expression e2;
	
	public InequalityExpression(Expression e1, Expression e2){
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public Type evaluate(Map<String,Type> context){
		return  new BooleanType(! ((e1.evaluate(context)) == ( e2.evaluate(context)))) ;
	}

}

