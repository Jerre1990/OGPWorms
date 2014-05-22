package worms.model.programs;

public class DivisionExpression implements Expression {
    Expression leftOperand;
    Expression rightOperand;
    public DivisionExpression (Expression left, Expression right) { 
        leftOperand = left; 
        rightOperand = right;
    }
 
    public Type evaluate()  { 	
        return ((DoubleType) leftOperand).Division((DoubleType) rightOperand);
    }
}