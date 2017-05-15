package pp.block3.cc.antlr;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class ExpressionCalculator extends ExpressionBaseListener {
	/** Map storing the val attribute for all parse tree nodes. */
	private ParseTreeProperty<Type> vals;
	private boolean foundError;

	/** Initialises the calculator before using it to walk a tree. */
	public void init() {
		this.vals = new ParseTreeProperty<>();
		this.foundError = false;
	}

	@Override
	public void exitPow(ExpressionParser.PowContext ctx) {
		Type val1 = getValue(ctx.expr(0));
		Type val2 = getValue(ctx.expr(1));

		if (val1 == Type.NUM && val2 == Type.NUM) {
			setValue(ctx, Type.NUM);
		} else if (val1 == Type.STR && val2 == Type.NUM) {
			setValue(ctx, Type.STR);
		} else {
			setValue(ctx, Type.ERR);
		}


	}

	@Override
	public void exitPlus(ExpressionParser.PlusContext ctx) {
		if (getValue(ctx.expr(0)) == getValue(ctx.expr(1))) {
			setValue(ctx, getValue(ctx.expr(0)));
		} else {
			setValue(ctx, Type.ERR);
		}
	}

	@Override
	public void exitEquality(ExpressionParser.EqualityContext ctx) {
		if (getValue(ctx.expr(0)) == getValue(ctx.expr(1))) {
			setValue(ctx, Type.BOOL);
		} else {
			setValue(ctx, Type.ERR);
		}
	}

	@Override
	public void exitPar(ExpressionParser.ParContext ctx) {
		setValue(ctx, getValue(ctx.expr()));
	}

	@Override
	public void exitNumber(ExpressionParser.NumberContext ctx) {
		setValue(ctx, Type.NUM);
	}
	@Override
	public void exitBoolean(ExpressionParser.BooleanContext ctx) {
		setValue(ctx, Type.BOOL);
	}

	@Override
	public void exitString(ExpressionParser.StringContext ctx) {
		setValue(ctx, Type.STR);
	}

	@Override
	public void visitErrorNode(ErrorNode node) {
		foundError = true;
	}

	private void setValue(ParseTree node, Type val) {
		this.vals.put(node, val);
	}
	public Type getValue(ParseTree node) {
		return this.vals.get(node);
	}


}
