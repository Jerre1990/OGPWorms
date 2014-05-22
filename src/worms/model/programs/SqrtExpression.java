package worms.model.programs;

import java.util.Map;

public class SqrtExpression implements Expression{
	
    Expression toSqrt;
    
    public SqrtExpression(Expression Sqrt) { 
    	toSqrt = Sqrt;
    }
 
    public Type evaluate(Map<String,Type> context)  { 	
        return ((DoubleType) toSqrt).sqrt();
    }
}
