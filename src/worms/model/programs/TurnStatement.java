package worms.model.programs;

import java.util.Map;

import worms.gui.game.IActionHandler;

public class TurnStatement extends Statement{

	private Expression angle;
	private IActionHandler ah;

	public TurnStatement(IActionHandler ah, Expression angle){
		this.angle = angle;
		this.ah = ah;
	}
	
	@Override
	public void execute(Map<String, Type> context) {
		ah.turn(((WormEntityType) context.get("self")).getValue(), ((DoubleType) angle.evaluate(context)).getValue());
	}

}
