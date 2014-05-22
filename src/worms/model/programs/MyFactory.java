package worms.model.programs;


import java.util.List;

import worms.gui.game.IActionHandler;
import worms.model.World;
import worms.model.programs.ProgramFactory.ForeachType;

public class MyFactory implements ProgramFactory<Expression,Statement,Type>{
	
	private String programText;
	private IActionHandler handler;
	private ProgramParser parser;
	
	public MyFactory(String programText, IActionHandler handler){
		this.programText = programText;
		this.handler = handler;
		this.parser = new ProgramParser(this);
	}

	public Expression createDoubleLiteral(int line, int column, double doubleType) {
		return new DoubleExpression(doubleType);
	}
	
	public Expression createBooleanLiteral(int line, int column, boolean booleanExpression){
		return new BooleanExpression(booleanExpression);
	}
	
	public Expression createAnd(int line, int column,  Expression e1, Expression e2) throws ExpressionException{
		return new AndExpression(e1,e2);
	}
	
	
	
	public Expression createOr(int line, int column,  Expression e1, Expression e2){
		return new OrExpression(e1,e2);
		}
	
	
	public Expression createNot(int line, int column, Expression e){
		return new NotExpression(e);
	}
	
	public Expression createNull(int line, int column){
		return null;
	}

	public Expression createSelf(int line, int column){
		return new SelfExpression();
	}
	
	public Expression createGetX(int line, int column, Expression e){
		return new GetXExpression(e);
	}
	
	public Expression createGetY(int line, int column, Expression e){
		return new GetYExpression(e);
	}
	
	public Expression createGetDir(int line, int column, Expression e){
		return new GetDirExpression(e);
	}
	
	public Expression createGetRadius(int line, int column, Expression e){
		return new GetRadiusExpression(e);
	}
	
	public Expression createGetAP(int line, int column, Expression e) {
		return new GetAPExpression(e);
	}

	public Expression createGetMaxAP(int line, int column, Expression e) {
		return new GetMaxAPExpression(e);
	}

	public Expression createGetHP(int line, int column, Expression e){
		return new GetHPExpression(e);
	}
	
	public Expression createGetMaxHP(int line, int column, Expression e){
		return new GetMaxHPExpression(e);
	}
	
	
	
	public Expression createSameTeam(int line, int column, Expression e){
		return new SameTeamExpression(e);
	}
	
	public Expression createSearchObj(int line, int column, Expression e){
		return new SearchObjExpression(e);
	}

	public Expression createAdd(int line, int column, Expression e1, Expression e2) {
		return new AddExpression(e1,e2);
	}

	public Expression createIsWorm(int line, int column, Expression e) {
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
	
	public Statement createTurn(int line, int column, Expression angle) { 
		return new TurnStatement(handler, angle);		
	}
	
	public Statement createMove(int line, int column){
		return new MoveStatement(handler);
	}
	
	public Statement createJump(int line, int column){
		return new JumpStatement(handler);
	}
	
	public Statement createToggleWeap(int line, int column){
		return new ToggleWeaponStatement(handler);
	}
	
	public Statement createFire(int line, int column, Expression yield){
		return new FireStatement(handler, yield);
	}
	
	public Statement createSkip(int line, int column){
		return new SkipStatement(handler);
	}
	
	public Statement createAssignment(int line, int column, String variableName, Expression rhs){
		return new AssignmentStatement(variableName, rhs);
	}
	
	public Statement createIf(int line, int column, Expression condition, Statement then, Statement otherwise){
		return new IfStatement(condition, then, otherwise);	
	}
	
	public Statement createWhile(int line, int column, Expression condition, Statement body){
		return new WhileStatement(condition, body);
	}
	
	public Statement createForeach(int line, int column, ForeachType type, String variableName, Statement body){
		return new ForeachStatement(type, variableName, body);		
	}
	
	public Statement createSequence(int line, int column, List<Statement> statements){
		return new SequenceStatement(statements);
	}
	
	public Statement createPrint(int line, int column, Expression e){
		return new PrintStatement(handler, e);
	}

}
