package worms.model.programs;

import java.util.Map;

public class AssignmentStatement extends Statement {
	
		String name;
		Expression rhs;
		public AssignmentStatement(String name, Expression rhs) {
			this.name = name;
			this.rhs = rhs;
		}
	 
		public void execute(Map<String, Type> context) {
			context.put(name, rhs.evaluate(context));
		}
	 
}
