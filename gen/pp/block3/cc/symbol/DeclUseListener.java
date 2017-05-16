// Generated from /home/pieterjan/IdeaProjects/CompilerConstruction/src/pp/block3/cc/symbol/DeclUse.g4 by ANTLR 4.7
package pp.block3.cc.symbol;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DeclUseParser}.
 */
public interface DeclUseListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DeclUseParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(DeclUseParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link DeclUseParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(DeclUseParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link DeclUseParser#series}.
	 * @param ctx the parse tree
	 */
	void enterSeries(DeclUseParser.SeriesContext ctx);
	/**
	 * Exit a parse tree produced by {@link DeclUseParser#series}.
	 * @param ctx the parse tree
	 */
	void exitSeries(DeclUseParser.SeriesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decUnit}
	 * labeled alternative in {@link DeclUseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterDecUnit(DeclUseParser.DecUnitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decUnit}
	 * labeled alternative in {@link DeclUseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitDecUnit(DeclUseParser.DecUnitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code useUnit}
	 * labeled alternative in {@link DeclUseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUseUnit(DeclUseParser.UseUnitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code useUnit}
	 * labeled alternative in {@link DeclUseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUseUnit(DeclUseParser.UseUnitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code seriesUnit}
	 * labeled alternative in {@link DeclUseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterSeriesUnit(DeclUseParser.SeriesUnitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code seriesUnit}
	 * labeled alternative in {@link DeclUseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitSeriesUnit(DeclUseParser.SeriesUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link DeclUseParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(DeclUseParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link DeclUseParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(DeclUseParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link DeclUseParser#use}.
	 * @param ctx the parse tree
	 */
	void enterUse(DeclUseParser.UseContext ctx);
	/**
	 * Exit a parse tree produced by {@link DeclUseParser#use}.
	 * @param ctx the parse tree
	 */
	void exitUse(DeclUseParser.UseContext ctx);
}