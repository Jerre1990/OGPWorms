package worms.model.programs;

import java.util.Map;

public class ForeachStatement extends Statement{

	Type type;
	String variable;
	Statement body;
	
	public ForeachStatement(Type type, String variable, Statement body){
		this.type = type;
		this.variable = variable;
		this.body = body;
	}

	@Override
	public void execute(Map<String, Type> context) {
		for () {
			executeWithScope(body, context);
		}
	}
}
