package worms.model.programs;

public class ForeachStatement implements Statement{

	Type type;
	String variable;
	private Statement body;
	
	public ForeachStatement(Type type, String variable, Statement body){
		this.type = type;
		this.variable = variable;
		this.body = body;
	}
	
	@Override
	public void execute(){
		while (((BooleanType) condition.evaluate()).getValue()){
			body.execute();
		}
	}

}
