package worms.model.programs;

public class WhileStatement implements Statement{
	
	private Statement body;
	private Expression condition;
	
	public WhileStatement(Expression condition, Statement body){
		this.body = body;
		this.condition = condition;
	}
	
	@Override
	public void execute(){
		while (((BooleanType) condition.evaluate()).getValue()){
			body.execute();
		}
	}
	
}
