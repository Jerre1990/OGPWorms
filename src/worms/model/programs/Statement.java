package worms.model.programs;

import java.util.HashMap;
import java.util.Map;

public abstract class Statement {
	
	public abstract void execute(Map<String, Type> context);
	 
	protected void updateContext(Map<String, Type> context, Map<String, Type> scopeContext) {
		for(String key : context.keySet()) {
			context.put(key, scopeContext.get(key));
		}
	}
 
	protected void executeWithScope(Statement statement, Map<String, Type> context) {
		Map<String, Type> scopeContext = new HashMap<String, Type>(context);
		statement.execute(scopeContext);
		updateContext(context, scopeContext);
	}
}
