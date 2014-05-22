package worms.model.programs;

import java.util.Map;

public class NotExpression {
    Expression operand;
    public NotExpression(Expression e) { 
        operand = e;
    }
 
    public Type evaluate(Map<String,Type> context)  { 	
        return ((BooleanType) operand.evaluate(context)).not();
    }
}
