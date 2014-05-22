package worms.model.programs;

public class EqualityExpression implements Expression {
	
	BooleanType result;
	
	public EqualityExpression(Expression e1, Expression e2){
		result =  new BooleanType(((DoubleType) e1.evaluate()).getValue() == ((DoubleType) e2.evaluate()).getValue()) ;
	}
	
	public Type evaluate(){
		return result;
	}

}
