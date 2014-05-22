package worms.model.programs;

import java.util.Map;

import worms.gui.game.IActionHandler;

public class PrintStatement extends Statement{
	
	private IActionHandler ah;
	private Expression e;

	public PrintStatement(IActionHandler ah, Expression e){
		this.ah = ah;
		this.e = e;
	}
	
	@Override
	public void execute(Map<String, Type> context) {
		if(e.evaluate(context) instanceof WormEntityType){
		ah.print(((WormEntityType)e.evaluate(context)).getValue().toString());
		}
		else if(e.evaluate(context) instanceof FoodEntityType){
			ah.print(((FoodEntityType)e.evaluate(context)).getValue().toString());
				}
			else if (e.evaluate(context) instanceof BooleanType){
				ah.print(((BooleanType)e.evaluate(context)).getValue()+"");
				}
				else ah.print(((DoubleType)e.evaluate(context)).getValue()+"");
	}


}
