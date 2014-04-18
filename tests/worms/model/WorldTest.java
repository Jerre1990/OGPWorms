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
	
	private static Random random = new Random();
	

	
	private static boolean[][] map, map2, map3, map4;
	
	//private static Random random, random2,random3;
	
	
	private static GameObject object1, object2, object3, object4, object5, object6;

	/**
	 * Set up a mutable test fixture
	 * 
	 * @post 	The variable world1 references the following new world:
	 *		 	world1 has a width of 10.36, a height of 15.877, is based on rectangular map of 50 by 60 and has a random number generator.
	 */
	@Before
	public void setUpMutableFixture() throws Exception {
		int mountainWidth = 8;
		map = new boolean[99][79];
		for (int i = 0; i<map.length;){
			for (int u = 0; u<map[0].length;){
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
		
		world1.addAsGameObject(object1);
		world1.addAsGameObject(object2);
		world1.addAsGameObject(object3);
		world1.addAsGameObject(object4);
		world1.addAsGameObject(object5);
		
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
	public void hasProperGameObjects_LegalCaseFalseNullReference() {
		world1.addAsGameObject(null);
		assertFalse(world1.hasProperGameObjects());
	}
	
	@Test 
	public void hasProperGameObjects_LegalCaseFalseWorldTerminated() {
		world1.terminate();
		assertFalse(world1.hasProperGameObjects());
	}
	
	@Test 
	public void hasProperGameObjects_LegalCaseFalseObjectInOtherWorld() {
		object5.setWorld(world2);
		assertFalse(world1.hasProperGameObjects());
	}
	
	@Test 
	public void hasProperGameObjects_LegalCaseFalseObjectTwoTimesInWorld() {
		world1.addAsGameObject(object5);
		assertFalse(world1.hasProperGameObjects());
	}
	
	@Test 
	public void hasProperGameObjects_LegalCaseFalseObjectNotReferencingWorld() {
		object5.setWorld(null);
		assertFalse(world1.hasProperGameObjects());
	}
	
	@Test 
	public void canHaveAsGameObject_LegalCaseTrue() {
		assertTrue(world1.canHaveAsGameObject(object1));
	}
	
	@Test 
	public void canHaveAsGameObject_LegalCaseFalseNullReference() {
		assertFalse(world1.canHaveAsGameObject(null));
	}
	
	@Test 
	public void canHaveAsGameObject_LegalCaseFalseWorldTerminated() {
		world1.terminate();
		assertFalse(world1.canHaveAsGameObject(object1));
	}
	
	@Test 
	public void canHaveAsGameObject_LegalCaseFalseObjectInOtherWorld() {
		object1.setWorld(world2);
		assertFalse(world1.canHaveAsGameObject(object1));
	}
	
	@Test 
	public void canHaveAsGameObject_LegalCaseFalseTwoTimesInWorld() {
		world1.addAsGameObject(object5);
		assertFalse(world1.canHaveAsGameObject(object1));
	}
	
	@Test 
	public void hasAsGameObject_LegalCaseTrue() {
		assertTrue(world1.hasAsGameObject(object5));
	}
	
	@Test 
	public void hasAsGameObject_LegalCaseFalse() {
		assertFalse(world1.hasAsGameObject(object6));
	}
	
	@Test 
	public void removeAsGameObject_LegalCase() throws Exception {
		world1.removeAsGameObject(object5);
		assertFalse(world1.hasAsGameObject(object5));
		assertEquals(object5.getWorld(), world1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeAsGameObject_ObjectNotInWorld() throws Exception {
		world1.removeAsGameObject(object6);
	}
	
	@Test 
	public void terminate_LegalCase() {
		GameObject[] objects = world1.getObjects().toArray(new GameObject[5]);
		for (int i = 0; i<5;i++){
			assertTrue(objects[i].equals(null));
		}
		assertTrue(world1.isTerminated());
	}
	
	@Test 
	public void isTerminated_LegalCaseTrue() {
		world1.terminate();
		assertTrue(world1.isTerminated());
	}
	
	@Test 
	public void isTerminated_LegalCaseFalse() {
		assertFalse(world1.isTerminated());
	}
	
	@Test 
	public void addAsGameObject_LegalCaseTrue() throws Exception {
		world1.addAsGameObject(object6);
		assertTrue(world1.hasAsGameObject(object6));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addAsGameObject_NullReference() throws Exception {
		world1.addAsGameObject(null);
		assertFalse(world1.hasAsGameObject(null));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addAsGameObject_WorldTerminated() throws Exception {
		world1.terminate();
		world1.addAsGameObject(object6);
		assertFalse(world1.hasAsGameObject(object6));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addAsGameObject_ObjectInOtherWorld() throws Exception {
		object6.setWorld(world2);
		world1.addAsGameObject(object6);
		assertFalse(world1.hasAsGameObject(object6));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addAsGameObject_TwoObjectsInOtherWorld() throws Exception {
		object5.setWorld(world1);
		world1.addAsGameObject(object5);
		assertEquals(world1.getNbGameObjects(), 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addAsGameObject_GameStartedAddWorm() throws Exception {
		world1.startGame();
		object6 = (Worm) object6;
		world1.addAsGameObject(object6);
		assertFalse(world1.hasAsGameObject(object6));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addAsGameObject_GameStartedAddFood() throws Exception {
		world1.startGame();
		object6 = (Food) object6;
		world1.addAsGameObject(object6);
		assertFalse(world1.hasAsGameObject(object6));
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
	

	@Test 
	public void isPassable_LegalCaseTrue() {
		assertTrue(world1.isPassable(new Position((16),(6)), 0 ));
	}
	
	@Test 
	public void isPassable_LegalCaseFalse() {
		assertTrue(world1.isPassable(new Position((0),(6)), 0 ));
	}
	
}
