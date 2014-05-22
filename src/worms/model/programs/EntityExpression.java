package worms.model.programs;

import java.util.Map;

public class EntityExpression implements Expression {
	EntityType entity;
	
	public EntityExpression(EntityType e){
		this.entity = e;
	}
	
	@Override
	public Type evaluate(Map<String,Type> context) {
		return entity;
	}
}
