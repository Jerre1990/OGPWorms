package worms.model.programs;

public class BooleanType implements Type {
	
private boolean value;
	
	public BooleanType(boolean value){
		this.value = value;
	}
	
	
	public boolean getValue() {
		return value;
	}

}