package worms.model.programs;

import worms.gui.game.IActionHandler;

public class JumpStatement implements Statement{

	private IActionHandler ah;
	private Program p;

	public JumpStatement(){
	}
	
	@Override
	public void execute() {
		ah.jump(p.getWorm());
	}

}
