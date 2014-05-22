package worms.model.programs;

import java.util.Map;

public class AndExpression implements Expression{
    Expression leftOperand;
    Expression rightOperand;
    public AndExpression(Expression left, Expression right) { 
        leftOperand = left; 
        rightOperand = right;
    }
 
    public Type evaluate(Map<String,Type> context)  { 	
        return ((BooleanType) leftOperand.evaluate(context)).and((BooleanType) rightOperand.evaluate(context));
    }
}
