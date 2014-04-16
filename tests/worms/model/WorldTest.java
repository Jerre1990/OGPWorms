package worms.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.*;

import worms.util.Util;


/**
 * 
 * A class collecting tests for the class of game worlds.
 * 
 * @version 1.0
 * @author Jonas Thys & Jeroen Reinenbergh
 * 
 *
 */

public class WorldTest {

	private static World world1, world2, world3;
	
	private static boolean[][] map, map2, map3, map4;
	
	private static Random random = new Random(54);

	/**
	 * Set up a mutable test fixture
	 * 
	 * @post 	The variable world1 references the following new world:
	 *		 	world1 has a width of 10.36, a height of 15.877, is based on rectangular map of 50 by 60 and has a random number generator.
	 */
	@Before
	public void setUpMutableFixture() throws Exception {
		map = new boolean[50][60];
		for (int i = 0; i<=map.length-1;){
			for (int u = 0; u<map[0].length;)
				if (map[i][u] = (map[1][6] ||map[5][46] ||map[3][25] ||map[7][3] ||map[49][2] ||map[3][0] ||map[45][37]))
					map[i][u] = false;
				else map[i][u] = true;
		}			
		world1 = new World(10.36, 15.877, map, random);
	}



	/**
	 * Set up an immutable test fixture
	 * 
	 * @post	The variables world2 and world3 reference the following new worlds respectively:
	 *		 	world2 has a width of 25.58, a height of 30.56, is based on rectangular map of 46 by 62 and has a random number generator.
	 *		 	world3 has a width of 18.62, a height of 13.457, is based on cubic map of 10 by 10 and has a random number generator.
	 */
	@BeforeClass
	public static void setUpImmutableFixture() throws Exception {
		map2 = new boolean[46][62];
		for (int i = 0; i<=map2.length-1;){
			for (int u = 0; u<map2[0].length;)
				if (map2[i][u] = (map2[8][9] ||map2[5][6] ||map2[31][5] ||map2[17][13] ||map2[0][12] ||map2[43][30] ||map2[4][37]))
					map2[i][u] = false;
				else map2[i][u] = true;
		}	
		map3 = new boolean[10][10];
		for (int i = 0; i<=map3.length-1;){
			for (int u = 0; u<map3[0].length;)
				if (map3[i][u] = (map3[8][6] ||map3[3][4] ||map3[0][5] ||map3[1][8] ||map3[7][7] ||map3[4][2] ||map3[5][3]))
					map3[i][u] = false;
				else map3[i][u] = true;
		}
		world2 = new World(25.58, 30.56, map2, random);
		world3 = new World(18.62, 13.457, map3, random);
	}	
	
	@Test
	public void constructor_LegalCase() throws Exception {
		World myWorld = new World(24.36,40.36,map4, random);
		assertEquals(24.36, myWorld.getWidth(), Util.DEFAULT_EPSILON);
		assertEquals(40.36, myWorld.getHeight(), Util.DEFAULT_EPSILON);
		assertArrayEquals(map4, myWorld.getPassableMap());
		 for (int i=0; i>map4.length; i++) {
		assertTrue(Arrays.equals(map4[i], myWorld.getPassableMap()[i]));
		}
		assertEquals(random, myWorld.getRandom());
	}
	
	@Test
	public void setWidth_LegalCase() throws Exception {
		map2 = new boolean[46][62];
		for (int i = 0; i<=map2.length-1;){
			for (int u = 0; u<map2[0].length;)
				if (map2[i][u] = (map2[8][9] ||map2[5][6] ||map2[31][5] ||map2[17][13] ||map2[0][12] ||map2[43][30] ||map2[4][37]))
					map2[i][u] = false;
				else map2[i][u] = true;
		}
		world1.setPassableMap(map2);
		assertArrayEquals(map2, world1.getPassableMap());
	}
}
