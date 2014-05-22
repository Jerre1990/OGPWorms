package worms.model.programs;

public class EntityExpression implements Expression {
	EntityType entity;
	
	public EntityExpression(EntityType e){
		this.entity = e;
	}
	
	@Override
	public Type evaluate() {
		return entity;
	}
}
