package worms.model.programs;

public class MulExpression implements Expression {
    Expression leftOperand;
    Expression rightOperand;
    public MulExpression (Expression left, Expression right) { 
        leftOperand = left; 
        rightOperand = right;
    }
 
    public Type evaluate()  { 	
        return ((DoubleType) leftOperand).Mul((DoubleType) rightOperand);
    }
}
