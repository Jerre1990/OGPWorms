package worms.model.programs;

import java.util.List;

public class SequenceStatement implements Statement{
	private List<Statement> statements;	
	
	public SequenceStatement(List<Statement> statements){
		this.statements = statements;
	}
	
	@Override
	public void execute(){
		for (Statement statement : statements){
			statement.execute();
		}
	}
}
