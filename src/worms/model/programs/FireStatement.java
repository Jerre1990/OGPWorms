package worms.model.programs;

import worms.gui.game.IActionHandler;

public class FireStatement implements Statement {

	private IActionHandler ah;
	private Program p;
	private Expression yield;

	public FireStatement(Expression yield){
		this.yield = yield;
	}
	
	@Override
	public void execute() {
		ah.fire(p.getWorm(), (int) ((DoubleType) yield.evaluate()).getValue());
	}

}
