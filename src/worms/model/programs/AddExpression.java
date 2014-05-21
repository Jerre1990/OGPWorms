package worms.model.programs;

import java.util.Map;

public class AddExpression implements Expression{
    Expression leftOperand;
    Expression rightOperand;
    public AddExpression(Expression left, Expression right) { 
        leftOperand = left; 
        rightOperand = right;
    }
 
    public Type evaluate()  {
    	
        return leftOperand.evaluate().add(rightOperand.evaluate());
    }
}
