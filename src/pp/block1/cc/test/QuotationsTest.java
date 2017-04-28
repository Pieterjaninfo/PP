package pp.block1.cc.test;

import org.junit.Test;
import pp.block1.cc.antlr.Quotations;

public class QuotationsTest {
	private static LexerTester tester = new LexerTester(Quotations.class);

	@Test
	public void succeedingTest() {
		tester.correct("\"hello\"");
		tester.correct("\"hello\"\"whatssup\"");
		tester.correct("\"hello, how are you doing?\"\"I'm doing fine? Yes I am!...\"");
		tester.wrong("hello");
		tester.wrong(("\"hello"));
		tester.wrong(("hello\""));
		tester.wrong("\"hello\"\"wasup");
		tester.wrong("\"hello\"wasup\"");
		tester.wrong("\"Hello\"what");
	}

	@Test
	public void noSpacesBetweenKeywordsTest() {
		// the following is perfectly fine, so claiming it's wrong will fail
		tester.yields("\"teststring\"", Quotations.CITATION);
	}
}
