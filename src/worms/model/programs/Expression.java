package worms.model.programs;

public interface Expression {
	public Type evaluate(Map<String, Type> context);
}
