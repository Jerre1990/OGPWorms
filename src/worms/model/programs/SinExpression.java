package worms.model.programs;

import java.util.Map;

public class SinExpression implements Expression {
	
    Expression toSin;
    
    public SinExpression(Expression sin) { 
        toSin = sin;
    }
 
    public Type evaluate(Map<String,Type> context)  { 	
        return ((DoubleType) toSin).toSin();
    }
}