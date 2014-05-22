package worms.model.programs;

public class isFoodExpression implements Expression {
	
	BooleanType isFood;
	
	public isFoodExpression(Expression e){
		if (e.evaluate() instanceof FoodEntityType){
			isFood = new BooleanType(true);
		}
		else isFood = new BooleanType(false);
		
	}

	@Override
	public Type evaluate() {
		return isFood;
	}

}
