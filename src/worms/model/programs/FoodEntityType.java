package worms.model.programs;

import worms.model.Food;


public class FoodEntityType extends EntityType{
	
	public FoodEntityType(Food f){
		super(f);
	}
	
	public Food getValue() {
		return (Food) o;
	}
	
}
