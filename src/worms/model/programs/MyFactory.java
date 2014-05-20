package worms.model.programs;


import worms.model.World;

public class MyFactory {

	public Expression createDoubleLiteral(double doubleExpression) {
		return new DoubleExpression(doubleExpression);
	}
	
	public Expression createBooleanLiteral(boolean booleanExpression){
		return new BooleanExpression(booleanExpression);
	}
	
	public Expression createAnd( Expression e1, Expression e2) throws ExpressionException{
		if (e1 instanceof BooleanExpression && e2 instanceof BooleanExpression){
			Boolean boolean1 = (Boolean) e1.evaluate();
			Boolean boolean2 = (Boolean) e2.evaluate();
			if (boolean1 && boolean2){
				return new BooleanExpression(true);
			}
			else return new BooleanExpression(false);
			}
		else throw new ExpressionException("Invalid Expression");
	}
	
	public Expression createOr( Expression e1, Expression e2){
		if (e1 instanceof BooleanExpression && e2 instanceof BooleanExpression){
			Boolean boolean1 = (Boolean) e1.evaluate();
			Boolean boolean2 = (Boolean) e2.evaluate();
			if (boolean1 || boolean2){
				return new BooleanExpression(true);
			}
			else return new BooleanExpression(false);
			}
		else throw new ExpressionException("Invalid Expression");
		}
		
	public Expression createNot(Expression e){
		if (e instanceof BooleanExpression){
			BooleanExpression boolean1 = (BooleanExpression) e.evaluate();
			return new BooleanExpression(boolean1.getContrary());
		}
		else throw new ExpressionException("Invalid Expression");
	}
	
	public Expression createNull(){
		return null;
	}

	public Expression createSelf(){
		return new WormExpression(world.getActiveWorm());
	}
	
	public Expression createGetX(int line, int column, Expression e){
		if(! (e instanceof WormExpression)){
			throw new IllegalArgumentException();
		
		}
		WormExpression wormExpression = (WormExpression) e;
		return new DoubleExpression(wormExpression.getX());
	}
	
	public Expression createGetY(int line, int column, Expression e){
		if(! (e instanceof WormExpression)){
			throw new IllegalArgumentException();
		
		}
		WormExpression wormExpression = (WormExpression) e;
		return new DoubleExpression(wormExpression.getY());
	
	}
	
	public Expression createGetRadius(int line, int column, Expression e){
		if(! (e instanceof WormExpression)){
			throw new IllegalArgumentException();
		
		}
		WormExpression wormExpression = (WormExpression) e;
		return new DoubleExpression(wormExpression.getRadius());
	
	
	}
	
	public Expression createGetDir(int line, int column, Expression e){
		if(! (e instanceof WormExpression)){
			throw new IllegalArgumentException();
		
		}
		WormExpression wormExpression = (WormExpression) e;
		return new DoubleExpression(wormExpression.getDirection());
	
	
	}

	
	
	public Expression createGetAP(int line, int column, Expression e) {
		if(! (e instanceof WormExpression)){
			throw new IllegalArgumentException();
		
		}
		WormExpression wormExpression = (WormExpression) e;
		return new DoubleExpression(wormExpression.getAP());
		
		
	}

	public Expression createGetMaxAP(int line, int column, Expression e){
		if(! (e instanceof WormExpression)){
			throw new IllegalArgumentException();
		
		}
		WormExpression wormExpression = (WormExpression) e;
		return new DoubleExpression(wormExpression.getMaxAP());
	
	
	}

	
	public Expression createGetHP(int line, int column, Expression e){
		if(! (e instanceof WormExpression)){
			throw new IllegalArgumentException();
		
		}
		WormExpression wormExpression = (WormExpression) e;
		return new DoubleExpression(wormExpression.getHP());
	
	
	}

	
	public Expression createGetMaxHP(int line, int column, Expression e){
		if(! (e instanceof WormExpression)){
			throw new IllegalArgumentException();
		
		}
		WormExpression wormExpression = (WormExpression) e;
		return new DoubleExpression(wormExpression.getMaxHP());
	
	}
	
	public Expression createSameTeam(int line, int column, Expression e){
		if (! (e instanceof WormExpression)){
			throw new ExpressionException("Invalid Expression");
		}
		else return new BooleanExpression(((WormExpression) e).getTeam() == ((WormExpression) this.createSelf()).getTeam());
	}
	
	public Expression createSearchObj(int line, int column, Expression e){
		if (! (e instanceof DoubleExpression)){
			throw new ExpressionException("Invalid Expression");
		}
		else ((WormExpression) this.createSelf()).searchNearestObjectInGivenDirection(((Double) e.evaluate()));
	}

	public Expression createAdd(Expression e1, Expression e2) {
		if(e1 instanceof DoubleExpression && e2 instanceof DoubleExpression){
			Double double1 = (Double) e1.evaluate();
			Double double2 = (Double) e2.evaluate();
			return new DoubleExpression(double1 + double2);
		}
		else throw new ExpressionException("Invalid Expression");
	}

	public Expression createIsWorm(Expression e) {
		if(e instanceof WormExpression)
			return new BooleanExpression(true);
		return new BooleanExpression(false);
	}

}
