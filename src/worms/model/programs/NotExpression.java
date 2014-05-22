package worms.model.programs;

public class NotExpression {
    Expression operand;
    public NotExpression(Expression e) { 
        operand = e;
    }
 
    public Type evaluate()  { 	
        return ((BooleanType) operand.evaluate()).not();
    }
}
