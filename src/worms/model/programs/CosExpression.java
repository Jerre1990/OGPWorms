package worms.model.programs;

public class CosExpression implements Expression {
	
    Expression toCos;
    
    public CosExpression(Expression cos) { 
    	toCos = cos;
    }
 
    public Type evaluate()  { 	
        return ((DoubleType) toCos).toCos();
    }
}
