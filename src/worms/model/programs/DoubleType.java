package worms.model.programs;

import java.lang.Math;


public class DoubleType implements Type {
	
	private double value;
	
	public DoubleType(double value){
		this.value = value;
	}
	
	public DoubleType add(DoubleType e2){
		return new DoubleType(e2.getValue() + this.value);
		
	}
	
	public DoubleType Subtract(DoubleType e2){
		return new DoubleType(e2.getValue() - this.value);
		
	}
	
	public DoubleType Mul(DoubleType e2){
		return new DoubleType(e2.getValue() * this.value);
		
	}
	
	public DoubleType Division(DoubleType e2){
		return new DoubleType(e2.getValue() / this.value);
		
	}
	
	public double getValue() {
		return value;
	}
	
	public DoubleType sqrt() {
		return new DoubleType(Math.sqrt(this.value));
	}
	
	public DoubleType toSin(){
		return new DoubleType(Math.sin(this.value));
	}
	
public DoubleType toCos(){
	return new DoubleType(Math.cos(this.value));
	}

}
