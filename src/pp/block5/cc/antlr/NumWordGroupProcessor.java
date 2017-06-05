package pp.block5.cc.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pp.block5.cc.ErrorListener;
import pp.block5.cc.ParseException;

/** Prettyprints a (number, word)-sentence and adds the numbers. */
public class NumWordGroupProcessor extends NumWordGroupBaseListener {
	public static void main(String[] args) {
		NumWordGroupProcessor grouper = new NumWordGroupProcessor();
		if (args.length == 0) {
			process(grouper, "1sock2shoes 3 holes");
			process(grouper, "3 strands 10 blocks 11 weeks 15 credits");
			process(grouper, "1 2 3");
			process(grouper, "1	PJ 2BEAST 4MEirL 62 lol");
		} else {
			for (String text : args) {
				process(grouper, text);
			}
		}
	}

	private static void process(NumWordGroupProcessor grouper, String text) {
		try {
			System.out.printf("Processing '%s':%n", text);
			int result = grouper.group(text);
			System.out.println("Total: " + result);
		} catch (ParseException exc) {
			exc.print();
		}
	}

	/** Mapping from parse tree nodes to (sums of) numbers. */
	private ParseTreeProperty<Integer> values;

	/** Groups a given sentence and prints it to stdout.
	 * Returns the sum of the numbers in the sentence.
	 */
	public int group(String text) throws ParseException {
		CharStream chars = CharStreams.fromString(text);
		ErrorListener listener = new ErrorListener();
		Lexer lexer = new NumWordGroupLexer(chars);
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);
		TokenStream tokens = new CommonTokenStream(lexer);
		NumWordGroupParser parser = new NumWordGroupParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(listener);
		ParseTree tree = parser.sentence();
		listener.throwException();
		this.values = new ParseTreeProperty<>();
		new ParseTreeWalker().walk(this, tree);
		return this.values.get(tree);
	}

	// Override the listener methods


	@Override
	public void exitSentence(NumWordGroupParser.SentenceContext ctx) {
		int total = 0;
		for (int i = 0; i < ctx.children.size() - 1; i++) {
			total += values.get(ctx.getChild(i));
		}
		values.put(ctx, total);
		System.out.println("");
	}

	@Override
	public void exitNumber(NumWordGroupParser.NumberContext ctx) {
		int val = Integer.parseInt(ctx.NUMBER().getText());
		values.put(ctx, val);
		System.out.printf("%d ", val);
	}

	@Override
	public void exitWord(NumWordGroupParser.WordContext ctx) {
		System.out.print(ctx.WORD().toString());
	}

	@Override
	public void exitGroup(NumWordGroupParser.GroupContext ctx) {
		values.put(ctx, values.get(ctx.number()));
		System.out.print(", ");
	}

	@Override
	public void exitPenultimateGroup(NumWordGroupParser.PenultimateGroupContext ctx) {
		values.put(ctx, values.get(ctx.number()));
		System.out.print(" and ");
	}

	@Override
	public void exitLastGroup(NumWordGroupParser.LastGroupContext ctx) {
		values.put(ctx, values.get(ctx.number()));
	}


}
