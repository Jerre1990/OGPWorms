package worms.model.programs;


public class AddExpression implements Expression{
    Expression leftOperand;
    Expression rightOperand;
    public AddExpression(Expression left, Expression right) { 
        leftOperand = left; 
        rightOperand = right;
    }
 
    public Type evaluate()  { 	
        return ((DoubleType) leftOperand).add((DoubleType) rightOperand);
    }
}
