package worms.model.programs;

public class SubtractionExpression implements Expression{
    Expression leftOperand;
    Expression rightOperand;
    public SubtractionExpression(Expression left, Expression right) { 
        leftOperand = left; 
        rightOperand = right;
    }
 
    public Type evaluate()  { 	
        return ((DoubleType) leftOperand).Subtract((DoubleType) rightOperand);
    }
}
