package worms.model.programs;


import worms.model.World;

public class MyFactory {

	public Expression createDoubleLiteral(double doubleType) {
		return new DoubleExpression(doubleType);
	}
	
	public Expression createBooleanLiteral(boolean booleanExpression){
		return new BooleanExpression(booleanExpression);
	}
	
	public Expression createAnd( Expression e1, Expression e2) throws ExpressionException{
		if (e1 instanceof BooleanExpression && e2 instanceof BooleanExpression){
			BooleanType boolean1 = (BooleanType) e1.evaluate();
			BooleanType boolean2 = (BooleanType) e2.evaluate();
			if (boolean1.getValue() && boolean2.getValue()){
				return new BooleanExpression(true);
			}
			else return new BooleanExpression(false);
			}
		else throw new ExpressionException("Invalid Expression");
	}
	
	
	
	public Expression createOr( Expression e1, Expression e2){
		if (e1 instanceof BooleanExpression && e2 instanceof BooleanExpression){
			BooleanType boolean1 = (BooleanType) e1.evaluate();
			BooleanType boolean2 = (BooleanType) e2.evaluate();
			if (boolean1.getValue() || boolean2.getValue()){
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
		return new SelfExpression();
	}
	
	public Expression createGetX(int line, int column, Expression e){
		if(! (e instanceof EntityExpression)){
			throw new ExpressionException("");
		
		}
		return new GetXExpression(e);
	}
	
	public Expression createGetY(int line, int column, Expression e){
		if(! (e instanceof EntityExpression)){
			throw new ExpressionException("");
		
		}
		return new GetYExpression(e);
	}
	
	public Expression createGetDir(int line, int column, Expression e){
		if(! (e instanceof EntityExpression)){
			throw new ExpressionException("");
		
		}
		return new GetDirExpression(e);
	}
	
	public Expression createGetRadius(int line, int column, Expression e){
		if(! (e instanceof EntityExpression)){
			throw new ExpressionException("");
		
		}
		return new GetRadiusExpression(e);
	}
	

	
	
	public Expression createGetAP(int line, int column, Expression e) {
		if(! (e instanceof EntityExpression)){
			throw new ExpressionException("");
		
		}
		return new GetAPExpression(e);
	}

	public Expression createGetMaxAP(int line, int column, Expression e) {
		if(! (e instanceof EntityExpression)){
			throw new ExpressionException("");
		
		}
		return new GetMaxAPExpression(e);
	}

	public Expression createGetHP(int line, int column, Expression e){
		if(! (e instanceof EntityExpression)){
			throw new ExpressionException("");
		
		}
		return new GetHPExpression(e);
	}
	
	public Expression createGetMaxHP(int line, int column, Expression e){
		if(! (e instanceof EntityExpression)){
			throw new ExpressionException("");
		
		}
		return new GetMaxHPExpression(e);
	}
	
	public Expression createGetMaxHP(int line, int column, Expression e){
		if(! (e instanceof WormExpression)){
			throw new IllegalArgumentException();
		
		}
		WormExpression wormExpression = (WormExpression) e;
		return new DoubleExpression(wormExpression.getMaxHP());
	
	}
	
	public Expression createSameTeam(int line, int column, Expression e){
		if (! (e.evaluate() instanceof WormEntityType)){
			throw new ExpressionException("Invalid Expression");
		}
		else return new BooleanExpression(((WormEntityType) e.evaluate()).getTeam() == ((WormExpression) this.createSelf()).getTeam());
	}
	
	public Expression createSearchObj(int line, int column, Expression e){
		if (! (e instanceof DoubleExpression)){
			throw new ExpressionException("Invalid Expression");
		}
		else ((WormExpression) this.createSelf()).searchNearestObjectInGivenDirection(((Double) e.evaluate()));
	}

	public Expression createAdd(Expression e1, Expression e2) {
		if(e1 instanceof DoubleExpression && e2 instanceof DoubleExpression){
			return new AddExpression(e1, e2);
		}
		else throw new ExpressionException("Invalid Expression");
	}

	public Expression createIsWorm(Expression e) {
		if(e instanceof WormExpression)
			return new BooleanExpression(true);
		return new BooleanExpression(false);
	}
	
	public Expression createMultiply(){
		
	}

}
