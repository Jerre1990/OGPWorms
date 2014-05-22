package worms.model.programs;

public class OrExpression {
    Expression leftOperand;
    Expression rightOperand;
    public OrExpression(Expression left, Expression right) { 
        leftOperand = left; 
        rightOperand = right;
    }
 
    public Type evaluate()  { 	
        return ((BooleanType) leftOperand.evaluate()).or((BooleanType) rightOperand.evaluate());
    }
}
