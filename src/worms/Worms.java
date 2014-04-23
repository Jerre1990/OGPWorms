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
		/**new WormsGUI(new Facade(), parseOptions(args)).start();
		Random random = new Random();
		boolean [][] map = new boolean[100][100];
		for (int i = 0; i<map.length;i++){
			for (int u = 0; u<map[0].length;u++){
				map[i][u] = true;
				if (i <= 4)
					map[i][u] = false;	
				else if (i >= map.length - 5){
					if ((u < 30) || (u > 69))
						map[i][u] = false;
				}
				if ((u <= 4) || (u >= map[0].length - 5))
					map[i][u] = false;
			}
		}
		World world1 = new World(1000, 1000, map, random);
		double y = 0;
		while ((y < 60) && !world1.isPassable(new Position(150,y), 0.2)){
			y += 0.001;
		}
		System.out.println(y);
		while ((y < 60) && !world1.isAdjacentToImpassableTerrain(new Position(150,y), 1)){
			y += 0.001;
		}
		System.out.println(y);*/
		Worm worm3 = new Worm(new Position(750,60.001), 0.1, 0, "Ricky");
		double text = worm3.getRadius();
		
		System.out.println(text);
	}

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
			}
		}

		return options;
	}
}
