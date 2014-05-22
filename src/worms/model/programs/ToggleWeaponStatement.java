package worms.model.programs;

import worms.gui.game.IActionHandler;

public class ToggleWeaponStatement implements Statement{
	
	private IActionHandler ah;
	private Program p;

	public ToggleWeaponStatement(){
	}
	
	@Override
	public void execute() {
		ah.toggleWeapon(p.getWorm());
	}
	
}
