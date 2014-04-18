package worms.model;

import java.util.ArrayList;
import java.util.Arrays;
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
		List<Worm> livingWorms = new ArrayList<Worm>();
		for (Worm worm: worms){
			if (worm.isAlive())
				livingWorms.add(worm);
		}
		return livingWorms;
	}
	
	public Team determineWinningTeam(World world){
		boolean[] teamsCheck = new boolean [world.getTeams().size()];
		int i = 0;
		for (Team team: world.getTeams()){
			for (Worm worm: worms) {
				if (worm.isAlive())
					teamsCheck[i] = false;
					i = i +1;
					break;				
			}
		}
		int counter = 0;
		Team winner = null;
		for (int u = 0; u <world.getTeams().size();u++){
			if (teamsCheck[i] == false){
				counter = counter + 1;
				
			}
		}
		int hasAWinner = 0;
		if (counter ==1) {
			hasAWinner = 1;
		}
		 return ()
			
	}
	
	List<Worm> worms = new ArrayList<Worm>();
	
	
	public void terminate(){
		this.isTerminated = true;
		worms.clear();
		for (Worm worm: this.worms) {
			if (worm.isAlive()) {
				worm.setTeam(null);
				this.worms.remove(worm);
			}
		}
	}
	
	public boolean isTerminated() {
		return isTerminated;
	}
	
	boolean isTerminated;
	
	public boolean canHaveAsWorm(Worm worm){
		if (worm == null || isTerminated)
		return false;
		else return true;
	}
	
	public boolean hasAsWorm(Worm worm){
		return (this.worms.contains(worm));
	}
	
	public boolean hasProperWorms(){
		for (Worm worm: this.worms) {
			if (! canHaveAsWorm(worm) || worm.getTeam() != this )
				return false;
			
				
		}
		return true;
	}
	
	public void addAsWorm(Worm worm) throws IllegalArgumentException {
		if (! canHaveAsWorm(worm) || worm.getTeam() != null)
			throw new IllegalArgumentException("Not a valid worm");
		else 	this.worms.add(worm);
				worm.setTeam(this);
	}
	
	public void removeAsWorm(Worm worm){
		if (hasAsWorm(worm)){
			this.worms.remove(worm);
			worm.setTeam(null);
		}
	}
	
}
