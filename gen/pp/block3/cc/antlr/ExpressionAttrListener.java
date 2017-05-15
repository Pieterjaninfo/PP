// Generated from /home/pieterjan/IdeaProjects/CompilerConstruction/src/pp/block3/cc/antlr/ExpressionAttr.g4 by ANTLR 4.7
package pp.block3.cc.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionAttrParser}.
 */
public interface ExpressionAttrListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExpressionAttrParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ExpressionAttrParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionAttrParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ExpressionAttrParser.ExprContext ctx);
}