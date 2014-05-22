package worms.model.programs;

public class SqrtExpression implements Expression{
	
    Expression toSqrt;
    
    public SqrtExpression(Expression Sqrt) { 
    	toSqrt = Sqrt;
    }
 
    public Type evaluate()  { 	
        return ((DoubleType) toSqrt).sqrt();
    }
}
