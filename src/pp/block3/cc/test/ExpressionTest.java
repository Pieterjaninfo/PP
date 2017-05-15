package pp.block3.cc.test;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import pp.block3.cc.antlr.*;
import pp.block3.cc.antlr.ExpressionAttrParser.ExprContext;

import static org.junit.Assert.assertEquals;

public class ExpressionTest {
	private final ParseTreeWalker walker = new ParseTreeWalker();
	private final ExpressionCalculator calc = new ExpressionCalculator();

	@Test
	public void test() {
		test(Type.NUM, "2");
		test(Type.NUM, "4+8");
		test(Type.NUM, "(2+23)");
		test(Type.NUM, "(2^23)");

		test(Type.BOOL, "true");
		test(Type.BOOL, "false");
		test(Type.BOOL, "(false)");
		test(Type.BOOL, "(2+23) = (4^23)");
		test(Type.BOOL, "aa = bb");
		test(Type.BOOL, "true = false");

		test(Type.STR, "abc");
		test(Type.STR, "(abc)");
		test(Type.STR, "abc^22");
		test(Type.STR, "abc + def");

		test(Type.ERR, "2 = abc");
		test(Type.ERR, "2 = true");
		test(Type.ERR, "abc = false");

		test(Type.ERR, "1 ^ true");
		test(Type.ERR, "1 ^ abc");
		test(Type.ERR, "true ^ abc");
		test(Type.ERR, "true ^ 1");
		test(Type.ERR, "true ^ false");

		test(Type.ERR, "true + 1");
		test(Type.ERR, "true + abc");
		test(Type.ERR, "abc + 1");
	}

	private void test(Type expected, String expr) {
		assertEquals(expected, parseExpressionAttr(expr).val);
		ParseTree tree = parseExpression(expr);
		this.calc.init();
		this.walker.walk(this.calc, tree);
		assertEquals(expected, this.calc.getValue(tree));
	}

	private ParseTree parseExpression(String text) {
		CharStream chars = CharStreams.fromString(text);
		Lexer lexer = new ExpressionLexer(chars);
		TokenStream tokens = new CommonTokenStream(lexer);
		ExpressionParser parser = new ExpressionParser(tokens);
		return parser.expr();
	}

	private ExprContext parseExpressionAttr(String text) {
		CharStream chars = CharStreams.fromString(text);
		Lexer lexer = new ExpressionAttrLexer(chars);
		TokenStream tokens = new CommonTokenStream(lexer);
		ExpressionAttrParser parser = new ExpressionAttrParser(tokens);
		return parser.expr();
	}


}
