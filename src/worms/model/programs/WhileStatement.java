package worms.model.programs;

import java.util.Map;

public class WhileStatement extends Statement{
	
	private Statement body;
	private Expression condition;
 
	public WhileStatement(Expression condition, Statement body){
		this.body = body;
		this.condition = condition;
	}
 
	public void execute(Map<String,Type> map){
		while (((BooleanType) condition.evaluate(map)).getValue()) {
			executeWithScope(body, map);
		}
	}
	
}
