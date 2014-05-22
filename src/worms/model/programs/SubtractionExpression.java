package worms.model.programs;

import java.util.Map;

public class SubtractionExpression implements Expression{
    Expression leftOperand;
    Expression rightOperand;
    
    public SubtractionExpression(Expression left, Expression right) { 
        leftOperand = left; 
        rightOperand = right;
    }
 
    public Type evaluate(Map<String,Type> context)  { 	
        return ((DoubleType) leftOperand).subtract((DoubleType) rightOperand);
    }
}
