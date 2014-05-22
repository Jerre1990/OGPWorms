package worms.model.programs;

import java.util.Map;

public class OrExpression implements Expression {
    Expression leftOperand;
    Expression rightOperand;
    
    public OrExpression(Expression left, Expression right) { 
        leftOperand = left; 
        rightOperand = right;
    }
 
    public Type evaluate(Map<String,Type> context)  { 	
        return ((BooleanType) leftOperand.evaluate(context)).or((BooleanType) rightOperand.evaluate(context));
    }
}
