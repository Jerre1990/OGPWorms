package worms.model;

import java.awt.IllegalComponentStateException;
import java.util.Collection;
import java.util.Random;

public class Facade implements IFacade {

	@Override
	public void addEmptyTeam(World world, String newName) throws ModelException{
		try{
		world.addAsTeam(new Team(newName));
		}
		catch(IllegalArgumentException exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public void addNewFood(World world) {
		world.addRandomFood();
	}

	@Override
	public void addNewWorm(World world) {
		world.addRandomWorm();
	}

	@Override
	public boolean canFall(Worm worm) {
		return worm.canFall();
	}

	@Override
	public boolean canMove(Worm worm) {
		return worm.canMove();
	}

	@Override
	public boolean canTurn(Worm worm, double angle) {
		return worm.canTurn(angle);
	}

	@Override
	public Food createFood(World world, double x, double y) {
		Food newSnack = new Food(new Position(x,y));
		world.addAsGameObject(newSnack);
		return newSnack;
	}

	@Override
	public World createWorld(double width, double height,
			boolean[][] passableMap, Random random) {
		return new World(width, height, passableMap, random);
	}

	@Override
	public Worm createWorm(World world, double x, double y, double direction,
			double radius, String name) throws ModelException{
		Worm newWorm;
		try{
		newWorm = new Worm(new Position(x,y), radius, direction, name);
		world.addAsGameObject(newWorm);
		}
		catch(IllegalArgumentException exc){
			throw new ModelException(exc.getMessage());
		}
		return newWorm;
	}

	@Override
	public void fall(Worm worm) throws ModelException{
		try{
		worm.fall();
		}
		catch(UnsupportedOperationException exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public int getActionPoints(Worm worm) {
		return worm.getNumberOfActionPoints();
	}

	@Override
	public Projectile getActiveProjectile(World world) {
		return world.getActiveProjectile();
	}

	@Override
	public Worm getCurrentWorm(World world) {
		return world.getActiveWorm();
	}

	@Override
	public Collection<Food> getFood(World world) {
		return world.getAllFood();
	}

	@Override
	public int getHitPoints(Worm worm) {
		return worm.getNumberOfHitPoints();
	}

	@Override
	public double[] getJumpStep(Projectile projectile, double t) {
		return projectile.jumpStep(t);
	}

	@Override
	public double[] getJumpStep(Worm worm, double t) {
		return worm.jumpStep(t);
	}

	@Override
	public double getJumpTime(Projectile projectile, double timeStep) {
		return projectile.jumpTime(timeStep);
	}

	@Override
	public double getJumpTime(Worm worm, double timeStep) {
		return worm.jumpTime(timeStep);
	}

	@Override
	public double getMass(Worm worm) {
		return worm.getMass();
	}

	@Override
	public int getMaxActionPoints(Worm worm) {
		return worm.getMaxNumberOfActionPoints();
	}

	@Override
	public int getMaxHitPoints(Worm worm) {
		return worm.getMaxNumberOfHitPoints();
	}

	@Override
	public double getMinimalRadius(Worm worm) {
		return worm.getLowerBoundOfRadius();
	}

	@Override
	public String getName(Worm worm) {
		return worm.getName();
	}

	@Override
	public double getOrientation(Worm worm) {
		return worm.getDirection();
	}

	@Override
	public double getRadius(Food food) {
		return food.getRadius();
	}

	@Override
	public double getRadius(Projectile projectile) {
		return projectile.getRadius();
	}

	@Override
	public double getRadius(Worm worm) {
		return worm.getRadius();
	}

	@Override
	public String getSelectedWeapon(Worm worm) {
		return worm.getSelectedWeaponsName();
	}

	@Override
	public String getTeamName(Worm worm) {
		Team team = worm.getTeam();
		if(team == null)
			return "";
		else return team.getName();
	}

	@Override
	public String getWinner(World world) {
		return world.getWinner();
	}

	@Override
	public Collection<Worm> getWorms(World world) {
		return world.getAllLiveWorms();
	}

	@Override
	public double getX(Food food) {
		return food.getX();
	}

	@Override
	public double getX(Projectile projectile) {
		return projectile.getX();
	}

	@Override
	public double getX(Worm worm) {
		return worm.getX();
	}

	@Override
	public double getY(Food food) {
		return food.getY();
	}

	@Override
	public double getY(Projectile projectile) {
		return projectile.getY();
	}

	@Override
	public double getY(Worm worm) {
		return worm.getY();
	}

	@Override
	public boolean isActive(Food food) {
		return (food.getWorld() != null);
	}

	@Override
	public boolean isActive(Projectile projectile) {
		return (projectile.getWorld() != null);
	}

	@Override
	public boolean isAdjacent(World world, double x, double y, double radius) {
		return world.isAdjacentToImpassableTerrain(new Position(x,y), radius);
	}

	@Override
	public boolean isAlive(Worm worm) {
		return worm.isAlive();
	}

	@Override
	public boolean isGameFinished(World world) {
		return world.isFinished();
	}

	@Override
	public boolean isImpassable(World world, double x, double y, double radius) {
		return !world.isPassable(new Position(x,y), radius);
	}

	@Override
	public void jump(Projectile projectile, double timeStep) throws ModelException{
		try{
		projectile.jump(timeStep);
		}
		catch(UnsupportedOperationException exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public void jump(Worm worm, double timeStep) throws ModelException{
		try{
		worm.jump(timeStep);
		}
		catch(UnsupportedOperationException exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public void move(Worm worm) throws ModelException{
		try{
		worm.move();
		}
		catch(UnsupportedOperationException exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public void rename(Worm worm, String newName) throws ModelException{
		try{
		worm.setName(newName);
		}
		catch(IllegalArgumentException exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public void selectNextWeapon(Worm worm) {
		worm.selectNextWeapon();
	}

	@Override
	public void setRadius(Worm worm, double newRadius) throws ModelException{
		try{
		worm.setRadius(newRadius);
		}
		catch(IllegalArgumentException exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public void shoot(Worm worm, int yield) throws ModelException{
		try{
		worm.shoot(yield);
		}
		catch(UnsupportedOperationException exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public void startGame(World world) throws ModelException{
		try{
			world.startGame();
		}
		catch(IllegalComponentStateException exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public void startNextTurn(World world) {
		world.startNextTurn();
	}

	@Override
	public void turn(Worm worm, double angle) {
		worm.turn(angle);
	}

}
