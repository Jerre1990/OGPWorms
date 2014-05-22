package worms.model.programs;

import java.util.List;
import java.util.Map;

public class SequenceStatement extends Statement{
	private List<Statement> statements;	
	
	public SequenceStatement(List<Statement> statements){
		this.statements = statements;
	}
	
	@Override
	public void execute(Map<String, Type> context){
		for (Statement statement : statements){
			statement.execute(context);
		}
	}
}
