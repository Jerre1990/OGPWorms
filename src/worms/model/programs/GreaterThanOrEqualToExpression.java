package worms.model.programs;

public class GreaterThanOrEqualToExpression implements Expression {
	
	Expression e1,e2;
	
	public GreaterThanOrEqualToExpression(Expression e1, Expression e2){
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public Type evaluate(){
		return new BooleanType((((DoubleType) e1.evaluate()).getValue() > ((DoubleType) e2.evaluate()).getValue()) ||(((DoubleType) e1.evaluate()).getValue() == ((DoubleType) e2.evaluate()).getValue()) );
	}

}
