package worms.model.programs;

import java.util.Map;

import worms.gui.game.IActionHandler;

public class JumpStatement extends Statement{

	private IActionHandler ah;

	public JumpStatement(IActionHandler ah){
		this.ah = ah;
	}

	@Override
	public void execute(Map<String, Type> context) {
		ah.jump(((WormEntityType) context.get("self")).getValue());
		
	}

}
