package worms.model.programs;

public class SinExpression implements Expression {
	
    Expression toSin;
    
    public SinExpression(Expression sin) { 
        toSin = sin;
    }
 
    public Type evaluate()  { 	
        return ((DoubleType) toSin).toSin();
    }
}