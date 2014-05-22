package worms.model.programs;

import java.util.Map;

public interface Expression {
	public Type evaluate(Map<String,Type> context);
}