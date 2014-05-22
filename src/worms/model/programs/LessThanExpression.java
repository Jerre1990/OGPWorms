package worms.model.programs;

public class LessThanExpression implements Expression {
	
	Expression e1,e2;
	
	public LessThanExpression(Expression e1, Expression e2){
		this.e1 = e1;
		this.e2 = e2;
	
	}
	
	public Type evaluate(){
		return new BooleanType(((DoubleType) e1.evaluate()).getValue() < ((DoubleType) e2.evaluate()).getValue());
	}

}
