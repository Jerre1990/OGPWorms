package worms.model.programs;

public class AndExpression implements Expression{
    Expression leftOperand;
    Expression rightOperand;
    public AndExpression(Expression left, Expression right) { 
        leftOperand = left; 
        rightOperand = right;
    }
 
    public Type evaluate()  { 	
        return ((BooleanType) leftOperand.evaluate()).and((BooleanType) rightOperand.evaluate());
    }
}
