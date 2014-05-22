package worms.model.programs;

import java.util.Map;

import worms.model.Worm;

public class Program {
	
	Statement programTree;
	Map<String, Type> globals;
	
	public Program(Statement programTree, Map<String, Type> globals){
		this.programTree = programTree;
		this.globals=globals;
		
	}
	
	public void execute(Worm worm){
		programTree.execute();
		
	}

}
