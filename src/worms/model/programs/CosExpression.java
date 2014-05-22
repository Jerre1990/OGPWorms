package worms.model.programs;

import java.util.Map;

public class CosExpression implements Expression {
	
    Expression toCos;
    
    public CosExpression(Expression cos) { 
    	toCos = cos;
    }
 
    public Type evaluate(Map<String,Type> context)  { 	
        return ((DoubleType) toCos).toCos();
    }
}
