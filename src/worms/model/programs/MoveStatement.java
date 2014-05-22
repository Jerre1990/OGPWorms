package worms.model.programs;

import worms.gui.game.IActionHandler;

public class MoveStatement implements Statement{
	
	private IActionHandler ah;
	private Program p;

	public MoveStatement(){
	}
	
	@Override
	public void execute() {
		ah.move(p.getWorm());
	}
	
}
