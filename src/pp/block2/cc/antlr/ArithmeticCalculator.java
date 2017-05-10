package pp.block2.cc.antlr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.*;
import pp.block2.cc.ParseException;

public class ArithmeticCalculator extends ArithmeticBaseListener {

	private ParseTreeProperty<Double> values;
	private boolean foundError;
	private double result;

	public void calculate(String text) {
		this.values = new ParseTreeProperty<>();
		this.result = 0;
		this.foundError = false;
		Lexer lexer = new ArithmeticLexer(CharStreams.fromString(text));
		ArithmeticParser parser = new ArithmeticParser(new CommonTokenStream(lexer));
		ParseTree tree = parser.start();
		new ParseTreeWalker().walk(this, tree);

		if (foundError) { System.err.println("Expected token did not match actual token in expression: " + text); }
		else {System.out.println(text + " = " + result); }
	}

	@Override
	public void exitStart(ArithmeticParser.StartContext ctx) {
		if (!foundError) { this.result = getValue(ctx.getChild(0)); }
	}

	@Override
	public void exitAddExpr(ArithmeticParser.AddExprContext ctx) {
		if (!foundError) {
			double new_value = getValue(ctx.getChild(0)) + getValue(ctx.getChild(2));
			setValue(ctx, new_value);
		}
	}

	@Override
	public void exitMulExpr(ArithmeticParser.MulExprContext ctx) {
		if (!foundError) {
			double new_value = getValue(ctx.getChild(0)) * getValue(ctx.getChild(2));
			setValue(ctx, new_value);
		}
	}

	@Override
	public void exitPowExpr(ArithmeticParser.PowExprContext ctx) {
		if (!foundError) {
			double new_value = Math.pow(getValue(ctx.getChild(0)), getValue(ctx.getChild(2)));
			setValue(ctx, new_value);
		}
	}

	@Override
	public void exitSubExpr(ArithmeticParser.SubExprContext ctx) {
		if (!foundError) {
			double new_value = getValue(ctx.getChild(0)) - getValue(ctx.getChild(2));
			setValue(ctx, new_value);
		}
	}


	@Override
	public void exitNumExpr(ArithmeticParser.NumExprContext ctx) {
		if (!foundError) { setValue(ctx, getValue(ctx.getChild(0))); }
	}

	@Override
	public void visitTerminal(TerminalNode node) {
		if (!foundError && node.getSymbol().getType() == 5) {
			setValue(node, (double) Integer.parseInt(node.getSymbol().getText()));
		}
	}


	@Override
	public void visitErrorNode(ErrorNode node) {
		this.foundError = true;
	}


	private Double getValue(ParseTree node) {
		return this.values.get(node);
	}
	private void setValue(ParseTree node, Double value) {
		this.values.put(node, value);
	}

	public static void main(String[] args) {
		ArithmeticCalculator counter = new ArithmeticCalculator();
		counter.calculate("5--3*2+1^4");
		counter.calculate("3*4+7^54");
		counter.calculate("3**2");		//SHOULD AND DOES FAIL
		counter.calculate("-8^45-123*-42^2");
		counter.calculate("69*420^2-1337+3");
	}
}
