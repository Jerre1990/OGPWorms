package worms.model.programs;

import java.util.Map;

public class MulExpression implements Expression {
    Expression leftOperand;
    Expression rightOperand;
    
    public MulExpression (Expression left, Expression right) { 
        leftOperand = left; 
        rightOperand = right;
    }
 
    public Type evaluate(Map<String,Type> context)  { 	
        return ((DoubleType) leftOperand).mul((DoubleType) rightOperand);
    }
}
