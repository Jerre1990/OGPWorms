package worms.model;

public class Facade implements IFacade {

	@Override
	public Worm createWorm(double x, double y, double direction, double radius, String name) throws ModelException {
		try{
			return new Worm(name,radius,direction,x,y);
		}
		catch (Throwable exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public boolean canMove(Worm worm, int nbSteps) throws ModelException {
		try{
			return worm.canMove(nbSteps);
		}
		catch (Throwable exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public void move(Worm worm, int nbSteps) throws ModelException {
		try{
			worm.activeMove(nbSteps);
		}
		catch (Throwable exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public boolean canTurn(Worm worm, double angle) {
		return worm.canTurn(angle);
	}

	@Override
	public void turn(Worm worm, double angle) {
		worm.activeTurn(angle);
	}

	@Override
	public void jump(Worm worm) throws ModelException {
		try{
			worm.jump();
		}
		catch (Throwable exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public double getJumpTime(Worm worm) {
		return worm.jumpTime();
	}

	@Override
	public double[] getJumpStep(Worm worm, double t) {
		return worm.jumpStep(t);
	}

	@Override
	public double getX(Worm worm) {
		return worm.getX();
	}

	@Override
	public double getY(Worm worm) {
		return worm.getY();
	}

	@Override
	public double getOrientation(Worm worm) {
		return worm.getDirection();
	}

	@Override
	public double getRadius(Worm worm) {
		return worm.getRadius();
	}

	@Override
	public void setRadius(Worm worm, double newRadius) throws ModelException {
		try{
			worm.setRadius(newRadius);
		}
		catch (Throwable exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public double getMinimalRadius(Worm worm) {
		return worm.getLowerBoundOfRadius();
	}

	@Override
	public int getActionPoints(Worm worm) {
		return worm.getNumberOfActionPoints();
	}

	@Override
	public int getMaxActionPoints(Worm worm) {
		return worm.getMaxNumberOfActionPoints();
	}

	@Override
	public String getName(Worm worm) {
		return worm.getName();
	}

	@Override
	public void rename(Worm worm, String newName) throws ModelException {
		try{
			worm.setName(newName);
		}
		catch (Throwable exc){
			throw new ModelException(exc.getMessage());
		}
	}

	@Override
	public double getMass(Worm worm) {
		return worm.getMass();
	}

}
