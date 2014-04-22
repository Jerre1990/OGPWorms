package worms.model;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
	
	private static Random random;
	
	private static Worm worm1,worm2,worm3;
	
	
	private static Weapon weapon;
	
	private  static Food food1, food2, food3; 
	
	
	
	private static Projectile projectile1; 
	
	private static boolean[][] map, map2, map3, map4;
	
	
	

	/**
	 * Set up a mutable test fixture
	 * 
	 * @post 	The variable world1 references the following new world:
	 *		 	world1 has a width of 10.36, a height of 15.877, is based on rectangular map of 50 by 60 and has a random number generator.
	 */
	@Before
	public void setUpMutableFixture() throws Exception {
		random = new Random();
		int mountainWidth = 8;
		map = new boolean[99][79];
		for (int i = 0; i<map.length;i++){
			for (int u = 0; u<map[0].length;u++){
				if (i == 0){
				map[i][u] = false;	
				}
				else 
						if (i < u % mountainWidth || i == u){
							map[i][u] = false;
						}
				else map[i][u] = true;
			}
			if(i< mountainWidth){
			mountainWidth = mountainWidth -1;
			}
		}
		world1 = new World(10.36, 15.877, map, random);
		
		food1 = new Food(new Position(8,9));
		food2 = new Food(new Position(6,7));
		food3 = new Food(new Position(3,4));
		worm1 = new Worm(new Position(75,84), 3, 4.5, "Ricky");
		worm2 = new Worm(new Position(94,2), 1.4, 3.03, "Ash");
		worm3 = new Worm(new Position(53,1.22), 1.7, 0.3, "Octo");
		projectile1 = new Projectile(new Position(52,2), 2.03,5.36, 2.5);
		weapon = new Weapon("Rifle", 1, 5, 7, 8.3, true);
		world1.addAsGameObject(food1);
		world1.addAsGameObject(worm1);
		world1.addAsGameObject(projectile1);
		world1.addAsGameObject(worm2);
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
		int mountainWidth = 8;
		map2 = new boolean[99][79];
		for (int i = 0; i<map2.length;i++){
			for (int u = 0; u<map2[0].length;u++){
				if (i == 0){
					map2[i][u] = false;	
				}
				else 
						if (i < u % mountainWidth || i == u){
							map2[i][u] = false;
						}
				else map2[i][u] = true;
			}
			if(i< mountainWidth){
			mountainWidth = mountainWidth -1;
			}
		}	
		mountainWidth = 8;
		map3 = new boolean[99][79];
		for (int i = 0; i<map3.length;i++){
			for (int u = 0; u<map3[0].length;u++){
				if (i == 0){
					map3[i][u] = false;	
				}
				else 
						if (i < u % mountainWidth || i == u){
							map3[i][u] = false;
						}
				else map3[i][u] = true;
			}
			if(i< mountainWidth){
			mountainWidth = mountainWidth -1;
			}
		}
		world2 = new World(25.58, 30.56, map2, random);
		world3 = new World(18.62, 13.457, map3, random);
		
	}
	
	

	@Test
	public void constructor_LegalCase() throws Exception {
		World myWorld = new World(24.36,40.36,map, random);
		assertEquals(24.36, myWorld.getWidth(), Util.DEFAULT_EPSILON);
		assertEquals(40.36, myWorld.getHeight(), Util.DEFAULT_EPSILON);
		assertArrayEquals(map, myWorld.getPassableMap());
		 for (int i=0; i>map.length; i++) {
		assertTrue(Arrays.equals(map[i], myWorld.getPassableMap()[i]));
		}
	}

	@Test
	public void isPassable_LegalCaseTrue(){
		assertTrue(world1.isPassable(new Position(3,4), .05));
	}
	
	@Test
	public void isPassable_LegalCaseFalse(){
		assertFalse(world1.isPassable(new Position(0.1603737,0.1255), .05));
	}
	
	@Test
	public void isPassable_LegalCaseTruee(){
		assertTrue(world1.isPassable(new Position(0.261,0.02),0.1));
	}
	
	@Test
	public void isPassable_LegalCaseFalsee(){
		assertFalse(world1.isPassable(new Position(0.261,0.02),0.11));
	}
	
	@Test
	public void isAdjacentToImpassableTerrain_LegalCaseTrue(){
		assertTrue(world1.isAdjacentToImpassableTerrain(new Position(0.261,0.02),0.1));
	}
	
	@Test
	public void checkIsLocated(){
		Position position = new Position(3,4);
		assertTrue(world1.isLocatedInWorld(position, 0.05));
	}
	
	@Test
	public void test1(){
		assertEquals(world1.addRandomWorm().isAlive(), true);
	}
	
	@Test
	public void test3(){
		world1.addAsGameObject(new Food(new Position(5,7)));
		System.out.println(world1.getAllObjectsFrom(Food.class.getName()).size());
		assertEquals(world1.getAllObjectsFrom(Food.class.getName()).size(), 1);
	}
	
	@Test
	public void getAllObjectsFrom_LegalCaseWorms(){
		List<GameObject> result = new ArrayList<GameObject>();
		result.add(worm1);
		result.add(worm2);
		assertArrayEquals(result.toArray(), world1.getAllObjectsFrom(Worm.class.getName(), world1.getObjects()).toArray());
	}
	
	@Test
	public void getAllObjectsFrom_LegalCaseFood(){
		List<GameObject> result = new ArrayList<GameObject>();
		result.add(food1);
		assertArrayEquals(result.toArray(), world1.getAllObjectsFrom(Food.class.getName(), world1.getObjects()).toArray());
	}
	
	@Test
	public void getAllObjectsFrom_LegalCaseProjectile(){
		List<GameObject> result = new ArrayList<GameObject>();
		result.add(projectile1);
		assertArrayEquals(result.toArray(), world1.getAllObjectsFrom(Projectile.class.getName(), world1.getObjects()).toArray());
	}
	
}
	