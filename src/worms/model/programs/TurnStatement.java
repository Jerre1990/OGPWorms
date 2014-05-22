package worms.model.programs;

import worms.gui.game.IActionHandler;

public class TurnStatement implements Statement{

	private IActionHandler ah;
	private Program p;
	private Expression angle;

	public TurnStatement(Expression angle){
		this.angle = angle;
	}
	
	@Override
	public void execute() {
		ah.turn(p.getWorm(), ((DoubleType) angle.evaluate()).getValue());
	}

}
