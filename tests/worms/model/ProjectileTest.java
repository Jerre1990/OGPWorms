package worms.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import worms.util.Util;


/**
 * 
 * A class collecting tests for the class of projectiles
 * 
 * @version 1.0
 * @author Jonas Thys & Jeroen Reinenbergh
 * 
 *
 */

public class ProjectileTest {

	
	
	
	private static Projectile projectile1; 
	
	private static World world1;
	

	
	
	

	/**
	 * Set up a mutable test fixture
	 * 
	 * @post 	The variable projectile1 references the following new projectile:
	 *		 	world1 has a width of 10.36, a height of 15.877, is based on rectangular map of 50 by 60 and has a random number generator.
	 */
	@Before
	public void setUpMutableFixture() throws Exception {
		projectile1 = new Projectile(new Position(4,7), 1.25,1,1000);
		Random random = new Random();
		int mountainWidth = 8;
		boolean[][] map = new boolean[99][79];
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
		
		world1 = new World(25,36,map, random);
		world1.addAsGameObject(projectile1);
		
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
		
	}
	
	@Test
	public void constructor_LegalCase() throws Exception {
		Projectile myProjectile = new Projectile(new Position(2,9), 1.75,1,4.12);
		assertEquals(1, myProjectile.getRadius(), Util.DEFAULT_EPSILON);
		assertEquals(1.75, myProjectile.getDirection(), Util.DEFAULT_EPSILON);
		assertEquals(7.02, myProjectile.getInitialForce(), Util.DEFAULT_EPSILON);
		assertEquals(2, myProjectile.getX(), Util.DEFAULT_EPSILON);
		assertEquals(9, myProjectile.getY(), Util.DEFAULT_EPSILON);
		
	}
	
	@Test
	public void mass_LegalCase(){
		assertEquals(4448.495197, projectile1.getMass(), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void initialVelocity_LegalCase(){
		assertEquals(0.112397559, projectile1.initialVelocity(), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void jumpStepOnXAxis_LegalCase(){
		assertEquals(4.00070883, projectile1.jumpStepOnXAxis(0.02), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void jumpStepOnYAxis_LegalCase(){
		assertEquals(7.000171941, projectile1.jumpStepOnYAxis(0.02), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void jumpTime_LegalCase(){
		assertEquals(7.000171941, projectile1.jumpStepOnYAxis(0.02), Util.DEFAULT_EPSILON);
	}
	

}
