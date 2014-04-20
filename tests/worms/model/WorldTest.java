package worms.model;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
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
	public void setWidth_LegalCase() throws Exception {
		world1.setPassableMap(map2);
	}
	
	/**
	@Test 
	public void isValidWidth_LegalCaseTrue() {
		assertTrue(world1.isValidWidth(5.5));
	}
	
	@Test 
	public void isValidWidth_LegalCaseFalse() {
		assertFalse(world1.isValidWidth(-10));
	}
	
	@Test 
	public void isValidHeight_LegalCaseTrue() {
		assertTrue(world1.isValidHeight(7.65));
	}
	
	@Test 
	public void isValidHeight_LegalCaseFalse() {
		assertFalse(world1.isValidHeight(-20.3));
	}
	*/
	
	@Test 
	public void hasProperGameObjects_LegalCaseTrue() {
		assertTrue(world1.hasProperGameObjects());
	}
	
	@Test 
	public void hasProperGameObjects_LegalCaseFalseObjectInOtherWorld() {
		food1.setWorld(world2);
		assertFalse(world1.hasProperGameObjects());
	}
	
	
	
	@Test 
	public void canHaveAsGameObject_LegalCaseTrue() {
		assertTrue(world1.canHaveAsGameObject(food3));
	}
	
	@Test 
	public void canHaveAsGameObject_LegalCaseFalseNullReference() {
		assertFalse(world1.canHaveAsGameObject(null));
	}	
	
	@Test 
	public void canHaveAsGameObject_LegalCaseFalseObjectInOtherWorld() {
		food3.setWorld(world2);
		assertFalse(world1.canHaveAsGameObject(food3));
	}
	
	@Test 
	public void canHaveAsGameObject_LegalCaseFalseTwoTimesInWorld() {
		assertFalse(world1.canHaveAsGameObject(food1));
	}
	
	@Test 
	public void hasAsGameObject_LegalCaseTrue() {
		assertTrue(world1.hasAsGameObject(food1));
	}
	
	@Test 
	public void hasAsGameObject_LegalCaseFalse() {
		assertFalse(world1.hasAsGameObject(food3));
	}
	
	@Test 
	public void removeAsGameObject_LegalCase() throws Exception {
		world1.removeAsGameObject(food1);
		assertFalse(world1.hasAsGameObject(food1));
		assertEquals(food1.getWorld(), world1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeAsGameObject_ObjectNotInWorld() throws Exception {
		world1.removeAsGameObject(food3);
	}
	
	
	@Test 
	public void addAsGameObject_LegalCaseTrue() throws Exception {
		world1.addAsGameObject(food3);
		assertTrue(world1.hasAsGameObject(food3));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addAsGameObject_NullReference() throws Exception {
		world1.addAsGameObject(null);
		assertFalse(world1.hasAsGameObject(null));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addAsGameObject_ObjectInOtherWorld() throws Exception {
		food3.setWorld(world2);
		world1.addAsGameObject(food3);
		assertFalse(world1.hasAsGameObject(food3));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addAsGameObject_TwoObjectsInOtherWorld() throws Exception {
		food3.setWorld(world1);
		world1.addAsGameObject(food3);
		assertEquals(world1.getNbGameObjects(), 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addAsGameObject_GameStartedAddWorm() throws Exception {
		world1.startGame();
		world1.addAsGameObject(worm3);
		assertFalse(world1.hasAsGameObject(worm3));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addAsGameObject_GameStartedAddFood() throws Exception {
		world1.startGame();
		world1.addAsGameObject(food3);
		assertFalse(world1.hasAsGameObject(food3));
	}
	
	
	@Test 
	public void isLocatedInWorld_LegalCaseTrue() throws Exception {
		assertTrue(world1.isLocatedInWorld(new Position((5),(6)), 0));
	}
	
	@Test 
	public void isLocatedInWorld_LegalCaseFalseNegativeCoordinate() {
		assertFalse(world1.isLocatedInWorld(new Position((-5),(6)), 0));
	}
	
	@Test 
	public void isLocatedInWorld_LegalCaseFalseTooLarge() {
		assertFalse(world1.isLocatedInWorld(new Position((856),(6)), 0));
	}
	
	
	@Test 
	public void getPixelCoordinates_LegalCase() {
		assertEquals(world1.getPixelCoordinates(new Position((5),(6))),"[38][39]" );
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getPixelCoordinates_NotLocatedInWorld() throws Exception {
		assertEquals(world1.getPixelCoordinates(new Position((-5),(6))),"[38][39]" );
	}
	/**
	@Test 
	public void isPassable_LegalCaseTrue() {
		assertTrue(world1.isPassable(new Position((16),(6)), 0 ));
	}
	
	@Test 
	public void isPassable_LegalCaseFalse() {
		assertTrue(world1.isPassable(new Position((0),(6)), 0 ));
	}
	*/
}
	