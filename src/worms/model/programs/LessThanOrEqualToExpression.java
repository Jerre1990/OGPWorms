package worms.model.programs;

public class LessThanOrEqualToExpression  implements Expression {
	
	BooleanType result;
	
	public LessThanOrEqualToExpression(Expression e1, Expression e2){
		result =  new BooleanType((((DoubleType) e1.evaluate()).getValue() < ((DoubleType) e2.evaluate()).getValue()) ||(((DoubleType) e1.evaluate()).getValue() == ((DoubleType) e2.evaluate()).getValue()) );
	}
	
	public Type evaluate(){
		return result;
	}

}