package worms.model.programs;

import java.util.Map;

import worms.gui.game.IActionHandler;

public class SkipStatement extends Statement{
	
	IActionHandler ah;
	
	public SkipStatement(IActionHandler ah){
		this.ah = ah;
		
	}
	
	@Override
	public void execute(Map<String, Type> context) {		
	}
}
