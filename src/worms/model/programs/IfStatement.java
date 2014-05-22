package worms.model.programs;

public class IfStatement implements Statement{
	private Statement s1;	
	private Statement s2;
	private Expression c;
	
	public IfStatement(Expression c, Statement s1, Statement s2){
		this.s1 = s1;
		this.s2 = s2;
		this.c = c;
	}
	
	@Override
	public void execute(){
		if (((BooleanType) c.evaluate()).getValue())
			s1.execute();
		else
			s2.execute();
	}
}
