package worms.model.programs;

import java.util.Map;

import worms.gui.game.IActionHandler;

public class MoveStatement extends Statement{
	
	private IActionHandler ah;

	public MoveStatement(IActionHandler ah){
		this.ah = ah;
	}

	@Override
	public void execute(Map<String, Type> context) {
		ah.move(((WormEntityType) context.get("self")).getValue());
		
	}
	
}
