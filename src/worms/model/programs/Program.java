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
		Map<String, Type> context = new HashMap<String, Type>();
		context.add(new WormEntityType(worm));
		programTree.execute(context);
	}

}
