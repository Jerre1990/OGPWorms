package worms.model.programs;

public class BooleanType implements Type {
	
private boolean value;
	
	public BooleanType(boolean value){
		this.value = value;
	}
	
	public boolean getValue() {
		return value;
	}
	
	public BooleanType and(BooleanType b2){
		return new BooleanType(b2.getValue() && this.value);
	}
	
	public BooleanType or(BooleanType b2){
		return new BooleanType(b2.getValue() || this.value);
	}
	
	public BooleanType not(){
		return new BooleanType(!this.value);
	}

}