package worms.model.programs;

import java.util.Map;

import worms.gui.game.IActionHandler;

public class ToggleWeaponStatement extends Statement{
	
	private IActionHandler ah;

	public ToggleWeaponStatement(IActionHandler ah){
		this.ah = ah;
	}
	
	@Override
	public void execute(Map<String, Type> context) {
		ah.toggleWeapon(((WormEntityType) context.get("self")).getValue());
	}
	
}
