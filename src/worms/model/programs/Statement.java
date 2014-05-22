package worms.model.programs;

import java.util.Map;

public interface Statement {
	public void execute(Map<String,Type> map);
}
