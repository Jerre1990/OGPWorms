package worms.model;


import static org.junit.Assert.*;

import java.util.Random;

import org.junit.*;

import worms.util.Util;


/**
 * 
 * A class collecting tests for the class of worms.
 * 
 * @version 1.0
 * @author Jonas Thys & Jeroen Reinenbergh
 * 
 *
 */

public class WormsTest {

	private static World world1;
	
	private static Random random;
	
	private static Worm worm1,worm2,worm3,worm4;
		
	private  static Food food1,food2; 
	
	private static boolean[][] map;
	
	@Before
	public void setUpMutableFixture() throws Exception {
		random = new Random();
		map = new boolean[100][100];
		for (int i = 0; i<map.length;i++){
			for (int u = 0; u<map[0].length;u++){
				if (i <= 4)
					map[i][u] = false;	
				else if (i >= map.length - 5){
					if ((u < 30) || (u > 69))
						map[i][u] = false;
				}
				else if ((u <= 4) || (u >= map[0].length - 5))
					map[i][u] = false;
			}
		}
		world1 = new World(1000, 1000, map, random);
		food1 = new Food(new Position(790,45.001));
		worm1 = new Worm(new Position(800,60.001), 1, (Math.PI / 2), "Ricky");
		worm2 = new Worm(new Position(999,999), 1, (Math.PI / 2), "Rambo");
		worm4 = new Worm(new Position(900,450), 1, (Math.PI / 4), "Rambo");
		world1.addAsGameObject(food1);
		world1.addAsGameObject(worm1);
		world1.addAsGameObject(worm2);
	}

	@BeforeClass
	public static void setUpImmutableFixture() throws Exception {
		random = new Random();
		map = new boolean[100][100];
		for (int i = 0; i<map.length;i++){
			for (int u = 0; u<map[0].length;u++){
				if (i <= 4)
					map[i][u] = false;	
				else if (i >= map.length - 5){
					if ((u < 30) || (u > 69))
						map[i][u] = false;
				}
				else if ((u <= 4) || (u >= map[0].length - 5))
					map[i][u] = false;
			}
		}
		world1 = new World(1000, 1000, map, random);
		food2 = new Food(new Position(100,45.001));
		worm3 = new Worm(new Position(750,60.001), 1, 0, "Ricky");
		world1.addAsGameObject(food2);
		world1.addAsGameObject(worm3);
	}

@Test
public void constructor_LegalCase() throws Exception {
	Worm myWorm = new Worm(new Position(100,60.001), 1, 0, "Franck \"The tanck\" 'O Riley");
	assertEquals("Franck \"The tanck\" 'O Riley",myWorm.getName());
	assertEquals(1,myWorm.getRadius(),Util.DEFAULT_EPSILON);
	assertEquals(0,myWorm.getDirection(),Util.DEFAULT_EPSILON);
	assertEquals(100,myWorm.getX(),Util.DEFAULT_EPSILON);
	assertEquals(60.001,myWorm.getY(),Util.DEFAULT_EPSILON);
}

@Test (expected = IllegalArgumentException.class)
public void constructor_NameWithoutCapitalLetter() throws Exception {
	new Worm(new Position(100,60.001), 1, 0, "joske");
}

@Test (expected = IllegalArgumentException.class)
public void constructor_NameWithInvalidCharacters() throws Exception {
	new Worm(new Position(100,60.001), 1, 0, "Joske!");
}

@Test (expected = IllegalArgumentException.class)
public void constructor_RadiusTooSmall() throws Exception {
	new Worm(new Position(100,60.001), 0.1, 0, "Franck \"The tanck\" 'O Riley");
}

@Test (expected = IllegalArgumentException.class)
public void constructor_RadiusNotANumber() throws Exception {
	new Worm(new Position(100,60.001), Double.NaN, 0, "Franck \"The tanck\" 'O Riley");
}

@Test (expected = IllegalArgumentException.class)
public void constructor_XCoordinateNotANumber() throws Exception {
	new Worm(new Position(Double.NaN,60.001), 1, 0, "Franck \"The tanck\" 'O Riley");
}

@Test (expected = IllegalArgumentException.class)
public void constructor_YCoordinateNotANumber() throws Exception {
	new Worm(new Position(100,Double.NaN), 1, 0, "Franck \"The tanck\" 'O Riley");
}

@Test
public void setName_LegalCase() throws Exception {
	worm1.setName("Wormpje");
	assertEquals("Wormpje", worm1.getName());
}

@Test (expected = IllegalArgumentException.class)
public void setName_NoCapitalLetter() throws Exception {
	worm3.setName("wormpje");
}

@Test (expected = IllegalArgumentException.class)
public void setName_InvalidCharacters() throws Exception {
	worm3.setName("Wormpje!");
}

@Test
public void setRadius_LegalCase_Bigger() throws Exception {
	worm1.setRadius(3);
	assertEquals(3, worm1.getRadius(), Util.DEFAULT_EPSILON);
	assertTrue(worm1.canHaveAsRadius(worm1.getRadius()));
	assertTrue(worm1.getNumberOfActionPoints() >= 0);
	assertTrue(worm1.getNumberOfActionPoints() <= worm1.getMaxNumberOfActionPoints());
	assertTrue(worm1.getNumberOfHitPoints() >= 0);
	assertTrue(worm1.getNumberOfHitPoints() <= worm1.getMaxNumberOfActionPoints());
}

@Test
public void setRadius_LegalCase_Smaller() throws Exception {
	worm1.setRadius(0.75);
	assertEquals(0.75, worm1.getRadius(), Util.DEFAULT_EPSILON);
	assertTrue(worm1.canHaveAsRadius(worm1.getRadius()));
	assertTrue(worm1.getNumberOfActionPoints() >= 0);
	assertTrue(worm1.getNumberOfActionPoints() <= worm1.getMaxNumberOfActionPoints());
	assertTrue(worm1.getNumberOfHitPoints() >= 0);
	assertTrue(worm1.getNumberOfHitPoints() <= worm1.getMaxNumberOfActionPoints());
}

@Test
public void setRadius_LegalCase_MaxAmountOfActionAndHitPointsTooBig() throws Exception {
	worm1.setRadius(Integer.MAX_VALUE);
	assertEquals((Integer.MAX_VALUE), worm1.getRadius(), Util.DEFAULT_EPSILON);
	assertTrue(worm1.canHaveAsRadius(worm1.getRadius()));
	assertEquals(Integer.MAX_VALUE, worm1.getMaxNumberOfActionPoints());
	assertTrue(worm1.getNumberOfActionPoints() >= 0);
	assertTrue(worm1.getNumberOfActionPoints() <= worm1.getMaxNumberOfActionPoints());
	assertEquals(Integer.MAX_VALUE, worm1.getMaxNumberOfHitPoints());
	assertTrue(worm1.getNumberOfHitPoints() >= 0);
	assertTrue(worm1.getNumberOfHitPoints() <= worm1.getMaxNumberOfActionPoints());
}

@Test (expected = IllegalArgumentException.class)
public void setRadius_IllegalCase_Infinity() throws Exception {
	worm1.setRadius(Double.POSITIVE_INFINITY);
}

@Test (expected = IllegalArgumentException.class)
public void setRadius_RadiusTooSmall() throws Exception {
	worm3.setRadius(0.1);
}

@Test (expected = IllegalArgumentException.class)
public void setRadius_RadiusNotANumber() throws Exception {
	worm3.setRadius(Double.NaN);
}

@Test
public void getMass() {
	assertEquals(4448.4951974831465, worm1.getMass(), Util.DEFAULT_EPSILON);
}

@Test
public void canMove_LegalCaseTrue() throws Exception {
	assertTrue(worm1.canMove());	
}

@Test
public void canJump_LegalCaseTrue() throws Exception {
	assertTrue(worm1.canJump(0.02));	
}

@Test
public void isPassablePosition_LegalCaseTrue() throws Exception {
	assertTrue(world1.isPassable(worm4.getPosition(), worm4.getRadius()));	
}

/**
@Test
public void canMove_LegalCaseFalse() throws Exception {
	assertFalse(worm3.canMove(2000));
}

@Test (expected = IllegalArgumentException.class)
public void canMove_NegativeNumberOfSteps() throws Exception {
	assertFalse(worm3.canMove(-1));
}

@Test
public void activeMove_LegalCase() throws Exception {
	worm1.activeMove(5);
	assertEquals(-9.9533855557, worm1.getX(), Util.DEFAULT_EPSILON);
	assertEquals(0.964425198, worm1.getY(), Util.DEFAULT_EPSILON);
	assertEquals(35578, worm1.getNumberOfActionPoints(), Util.DEFAULT_EPSILON);
}

@Test (expected = IllegalArgumentException.class)
public void activeMove_NegativeNumberOfSteps() throws Exception {
	worm3.activeMove(-5);
}

@Test (expected = UnsupportedOperationException.class)
public void activeMove_CannotMove() throws Exception {
	worm3.activeMove(2000);
}

@Test
public void canTurn_LegalCaseTrue() {
	assertTrue(worm3.canTurn(2));	
}

@Test
public void canTurn_LegalCaseFalse() {
	worm1.jump();
	assertFalse(worm1.canTurn(2));	
}

@Test
public void activeTurn_LegalCase() {
	worm1.activeTurn(1.5);
	assertEquals(4.545, worm1.getDirection(), Util.DEFAULT_EPSILON);
	assertEquals(35573, worm1.getNumberOfActionPoints());
	assertTrue(worm1.getDirection() >= 0);
	assertTrue(worm1.getDirection() < (Math.PI * 2));
}

@Test
public void activeTurn_CannotTurn() {
	worm1.jump();
	assertEquals(3.045, worm1.getDirection(), Util.DEFAULT_EPSILON);
	assertEquals(0, worm1.getNumberOfActionPoints());
	assertTrue(worm1.getDirection() >= 0);
	assertTrue(worm1.getDirection() < (Math.PI * 2));
}

@Test
public void jumpTime(){
	assertEquals(1.2704540289466788, worm3.jumpTime(), Util.DEFAULT_EPSILON);
}

@Test
public void jumpStep(){
	assertEquals(8.999883205192992, worm3.jumpStep(1.0)[0], Util.DEFAULT_EPSILON);
	assertEquals(5.326124001484975, worm3.jumpStep(1.0)[1], Util.DEFAULT_EPSILON);
}

@Test
public void Jump_LegalCase() throws Exception {
	worm1.jump();
	assertEquals(-1.073007217, worm1.getX(), Util.DEFAULT_EPSILON);
	assertEquals(0, worm1.getNumberOfActionPoints());
	assertEquals(0, worm1.getY(), Util.DEFAULT_EPSILON);	
}

@Test (expected = UnsupportedOperationException.class)
public void Jump_CannotJump_FacedUpwards() throws Exception {
	worm4.jump();
}

@Test (expected = UnsupportedOperationException.class)
public void Jump_CannotJump_FacedDownwards() throws Exception {
	worm2.jump();
}*/

}
