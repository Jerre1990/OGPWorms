package worms.model;

import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;

public class Team extends Identifiable{
	
	public Team(String name) {
		super(name);
	}

	@Override
	protected boolean isValidName(String name) {
		return name.matches("[A-Z]"+"[A-Za-z]+");
	}

	public void addWorm(Worm worm){
		
	}
	
	public List<Worm> queryLiveWorms(){
		
	}
	
}
