package worms.model.programs;

import java.util.Map;

import worms.gui.game.IActionHandler;

public class FireStatement extends Statement {

	private IActionHandler ah;
	private Expression yield;

	public FireStatement(IActionHandler ah, Expression yield){
		this.yield = yield;
		this.ah = ah;
	}
	
	@Override
	public void execute(Map<String, Type> context) {
		ah.fire(((WormEntityType) context.get("self")).getValue(), (int) ((DoubleType) yield.evaluate(context)).getValue());
	}
}
