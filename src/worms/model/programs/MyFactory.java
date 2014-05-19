package worms.model.programs;

public class MyFactory  {

	Expression createDoubleLiteral(double d) {
		return new DoubleExpression(d);
	}


	Expression createAdd(Expression e1, Expression e2) {
		// test of e1 en e2 doubles zijn he
		return new DoubleExpression(e1.evaluate() + e1.evaluate());
	}

	Expression createGetAP(int line, int column, Expression e) {
		if( e instanceof WormExpression)
			throw new IllegalArgumentException();
		return new Expression() {
			public Expression evaluate() {
				return e.getAP();
			}
		};
	}

	Expression createIsWorm(Expression e) {
		if(e instanceof WormExpression)
			return new BooleanExpression(true);
		return new BooleanExpression(false);
	}

}
