package worms.model.programs;


import worms.gui.game.IActionHandler;
import worms.model.World;

public class MyFactory implements ProgramFactory{
	
	private String programText;
	private IActionHandler handler;
	private ProgramParser parser;
	
	public MyFactory(String programText, IActionHandler handler){
		this.programText = programText;
		this.handler = handler;
		this.parser = new ProgramParser(this);
	}

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
		return new GetXExpression((EntityExpression) e);
	}
	
	public Expression createGetY(int line, int column, Expression e){
		if(! (e instanceof EntityExpression)){
			throw new ExpressionException("");
		
		}
		return new GetYExpression((EntityExpression) e);
	}
	
	public Expression createGetDir(int line, int column, Expression e){
		if(! (e instanceof EntityExpression)){
			throw new ExpressionException("");
		
		}
		return new GetDirExpression((EntityExpression) e);
	}
	
	public Expression createGetRadius(int line, int column, Expression e){
		if(! (e instanceof EntityExpression)){
			throw new ExpressionException("");
		
		}
		return new GetRadiusExpression((EntityExpression) e);
	}
	

	
	
	public Expression createGetAP(int line, int column, Expression e) {
		if(! (e instanceof EntityExpression)){
			throw new ExpressionException("");
		
		}
		return new GetAPExpression((EntityExpression) e);
	}

	public Expression createGetMaxAP(int line, int column, Expression e) {
		if(! (e instanceof EntityExpression)){
			throw new ExpressionException("");
		
		}
		return new GetMaxAPExpression((EntityExpression) e);
	}

	public Expression createGetHP(int line, int column, Expression e){
		if(! (e instanceof EntityExpression)){
			throw new ExpressionException("");
		
		}
		return new GetHPExpression((EntityExpression) e);
	}
	
	public Expression createGetMaxHP(int line, int column, Expression e){
		if(! (e instanceof EntityExpression)){
			throw new ExpressionException("");
		
		}
		return new GetMaxHPExpression((EntityExpression) e);
	}
	
	
	
	public Expression createSameTeam(int line, int column, Expression e){
		if (! (e.evaluate() instanceof WormEntityType)){
			throw new ExpressionException("Invalid Expression");
		}
		else return new BooleanExpression(((WormEntityType) e.evaluate()).getTeam() == ((WormEntityType) this.createSelf().evaluate()).getTeam());
	}
	
	public Expression createSearchObj(int line, int column, Expression e){
		if (! (e instanceof DoubleExpression)){
			throw new ExpressionException("Invalid Expression");
		}
		else return new EntityExpression ( new EntityType(((WormEntityType) this.createSelf().evaluate()).searchNearestObjectInGivenDirection(((DoubleType) e.evaluate()))));
	}

	public Expression createAdd(Expression e1, Expression e2) {
		if(e1 instanceof DoubleExpression && e2 instanceof DoubleExpression){
			return new AddExpression(e1, e2);
		}
		else throw new ExpressionException("Invalid Expression");
	}

	public Expression createIsWorm(Expression e) {
			return new IsWormExpression(e);
	}
	
	public Expression createIsFood(int line, int column, Expression e){
		return new isFoodExpression(e);
	}
	
	public Expression createLessThan(int line, int column, Expression e1, Expression e2){
		return new LessThanExpression(e1, e2);
	}
	
	public Expression createGreaterThan(int line, int column, Expression e1, Expression e2){
		return new GreaterThanExpression(e1, e2);
		}
	
	public Expression createLessThanOrEqualTo(int line, int column, Expression e1, Expression e2){
		return new LessThanOrEqualToExpression(e1, e2);
	}
	
	public Expression createGreaterThanOrEqualTo(int line, int column, Expression e1, Expression e2){
		return new GreaterThanOrEqualToExpression(e1,e2);
	}
	
	public Expression createEquality(int line, int column, Expression e1, Expression e2){
		return new EqualityExpression(e1,e2);
	}
	
	public Expression createInequality(int line, int column, Expression e1, Expression e2){
		return new InequalityExpression(e1,e2);
		
	}
	
	public Expression createSubtraction(int line, int column, Expression e1, Expression e2){
		return new SubtractionExpression(e1,e2);
	}
	
	public Expression createMul(int line, int column, Expression e1, Expression e2){
		return new MulExpression(e1,e2);
	}
	
	public Expression createDivision(int line, int column, Expression e1, Expression e2){
		return new DivisionExpression(e1,e2);
	}
	
	public Expression createSqrt(int line, int column, Expression e){
		return new SqrtExpression(e);
		
	}
	
	public Expression createSin(int line, int column, Expression e){
		return new SinExpression(e);
	}
	
	public Expression createCos(int line, int column, Expression e){
	return new CosExpression(e);
	}
	
	public Expression createVariableAccess(int line, int column, String name){
		return new VariableAccesExpression(name);
	}
	
	public DoubleType createDoubleType(){
		return new DoubleType();
		
	}
	
	public BooleanType createBooleanType(){
		return new BooleanType();
	}
	
	public EntityType createEntityType(){
		return new EntityType();
	}

}
