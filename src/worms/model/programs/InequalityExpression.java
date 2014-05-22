package worms.model.programs;

public class InequalityExpression implements Expression {
	
	BooleanType result;
	
	public InequalityExpression(Expression e1, Expression e2){
		result =  new BooleanType(! ((e1.evaluate()) == ( e2.evaluate()))) ;
	}
	
	public Type evaluate(){
		return result;
	}

}

