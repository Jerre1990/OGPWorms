package worms.model.programs;

import java.util.Map;

public class DivisionExpression implements Expression {
    Expression leftOperand;
    Expression rightOperand;
    public DivisionExpression (Expression left, Expression right) { 
        leftOperand = left; 
        rightOperand = right;
    }
 
    public Type evaluate(Map<String,Type> context)  { 	
        return ((DoubleType) leftOperand).division((DoubleType) rightOperand);
    }
}