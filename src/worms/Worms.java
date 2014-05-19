package worms;

import java.util.Random;

import worms.gui.GUIOptions;
import worms.gui.WormsGUI;
import worms.model.Facade;
import worms.model.Food;
import worms.model.Position;
import worms.model.World;
import worms.model.Worm;

public class Worms {

	public static void main(String[] args) {
		new WormsGUI(new Facade(), parseOptions(args)).start();
/*		Random random = new Random();
		int width = 1000;
		int height = 1000;
		boolean[][] map = new boolean[height][width];
		int widthOfImpassableTerrain = 30;
		int heightOfImpassableTerrain = 30;
		int widthOfPassableHole = 200;
		for (int i = 0; i<map.length;i++){
			for (int u = 0; u<map[0].length;u++){
				if ((i >= heightOfImpassableTerrain) && (i <= map.length - 1 - heightOfImpassableTerrain) && (u >= widthOfImpassableTerrain) || (u <= map[0].length - 1 - widthOfImpassableTerrain))
					map[i][u] = true;	
				if (i > map.length - 1 - heightOfImpassableTerrain){
					if (u < widthOfPassableHole)
						map[i][u] = true;
				}
			}
		}
		World world1 = new World(20, 20, map, random);
		boolean isPassable = false;
		boolean isAdjacent = false;
		double i = 0;
		while (!isPassable && i<6){
			isPassable = world1.isPassable(new Position(6,i), 1);
			i = i + 0.001;
		}
		System.out.println(i);
		isAdjacent = world1.isAdjacentToImpassableTerrain(new Position(6,i), 3);
		while (!isAdjacent && i<10){
			isAdjacent = world1.isAdjacentToImpassableTerrain(new Position(6,i), 3);
			i = i + 0.001;
		}
		System.out.println(i);
		System.out.println(world1.isPassable(new Position(6,5), 1));
		System.out.println(world1.isPassable(new Position(6,0.2), 1));
*/	}
	private static GUIOptions parseOptions(String[] args) {
		GUIOptions options = new GUIOptions();

		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if ("-window".equals(arg)) {
				options.disableFullScreen = true;
			} else if ("-seed".equals(arg)) {
				long randomSeed = Long.parseLong(args[++i]);
				options.randomSeed = randomSeed;
			} else if ("-clickselect".equals(arg)) {
				options.enableClickToSelect = true;
			} else if ("-program".equals(arg)) {
				String program = args[++i];
				options.programFile = program;
			}
		}

		return options;
	}
}
