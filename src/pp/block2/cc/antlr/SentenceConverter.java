package pp.block2.cc.antlr;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;

import org.antlr.v4.runtime.tree.*;
import pp.block2.cc.*;
import pp.block2.cc.ll.Grammar;
import pp.block2.cc.ll.Grammars;
import pp.block2.cc.ll.Sentence;

import java.util.HashMap;
import java.util.Set;

public class SentenceConverter 
		extends SentenceBaseListener implements Parser {
	/** Factory needed to create terminals of the {@link Sentence}
	 * grammar. See {@link pp.block2.cc.ll.SentenceParser} for
	 * example usage. */
	private final SymbolFactory fact;
	private AST finalAST;
	private ParseTreeProperty<AST> asts;
	private Grammar grammar;
	private int errorCount;


	public SentenceConverter() {
		this.fact = new SymbolFactory(Sentence.class);
	}

	@Override
	public AST parse(Lexer lexer) throws ParseException {
		this.grammar = Grammars.makeSentence();
		this.asts = new ParseTreeProperty<>();
		this.errorCount = 0;

		SentenceParser parser = new SentenceParser(new CommonTokenStream(lexer));
		ParseTree tree = parser.sentence();
		new ParseTreeWalker().walk(this, tree);

		if (errorCount > 0) {
			throw new ParseException("Actual node didn't match expected node.");
		}

		return this.finalAST;
	}
	
	// From here on overwrite the listener methods
	// Use an appropriate ParseTreeProperty to
	// store the correspondence from nodes to ASTs


	@Override
	public void exitSentence(SentenceParser.SentenceContext ctx) {
		computeAST(ctx, "Sentence");
		this.finalAST = getAST(ctx);
	}

	@Override
	public void exitSubject(SentenceParser.SubjectContext ctx) {
		computeAST(ctx, "Subject");
	}

	@Override
	public void exitModifier(SentenceParser.ModifierContext ctx) {
		computeAST(ctx, "Modifier");
	}

	@Override
	public void exitObject(SentenceParser.ObjectContext ctx) {
		computeAST(ctx, "Object");
	}

	@Override
	public void visitTerminal(TerminalNode node) {
		String name = fact.getTerminal(node.getSymbol().getType()).getName();
		AST terminal = new AST(new Term(node.getSymbol().getType(), name), node.getSymbol());
		setAST(node, terminal);
	}

	@Override
	public void visitErrorNode(ErrorNode node) {
		this.errorCount++;
	}

	public void computeAST(ParseTree node, String name) {
		AST result = new AST(new NonTerm(name));
		for (int i = 0; i < node.getChildCount(); i++) {
			result.addChild(getAST(node.getChild(i)));
		}
		setAST(node, result);
	}

	private AST getAST(ParseTree node) {
		return asts.get(node);
	}


	public void setAST(ParseTree tree, AST ast) {
		this.asts.put(tree, ast);
	}
}
