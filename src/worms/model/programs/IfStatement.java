package worms.model.programs;

import java.util.Map;

public class IfStatement extends Statement{
	private Statement s1;	
	private Statement s2;
	private Expression c;
	
	public IfStatement(Expression c, Statement s1, Statement s2){
		this.s1 = s1;
		this.s2 = s2;
		this.c = c;
	}
	
	@Override
	public void execute(Map<String,Type> context){
		if (((BooleanType) c.evaluate(context)).getValue())
			executeWithScope(s1, context);
		else
			executeWithScope(s2, context);
	}
}
