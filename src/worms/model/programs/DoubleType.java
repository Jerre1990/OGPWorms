package worms.model.programs;


public class DoubleType implements Type {
	
	private double value;
	
	public DoubleType(double value){
		this.value = value;
	}
	
	public DoubleType add(DoubleType e2){
		return new DoubleType(e2.getValue() + this.value);
		
	}
	
	public double getValue() {
		return value;
	}

}
