package worms.model.programs;

import worms.gui.game.IActionHandler;

public class PrintStatement implements Statement{
	
	private IActionHandler ah;
	private String message;

	public PrintStatement(String message){
	}
	
	@Override
	public void execute() {
		ah.print(this.message);
	}
}
