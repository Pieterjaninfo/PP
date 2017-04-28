package pp.block1.cc.test;

import org.junit.Test;
import pp.block1.cc.antlr.id6;

public class id6Test {
	private static LexerTester tester = new LexerTester(id6.class);

	@Test
	public void succeedingTest() {
		tester.correct("a12345");
		tester.correct("z1b23d");
		tester.correct("G420GG");
		tester.wrong("123456");
	}

	@Test
	public void noSpacesBetweenKeywordsTest() {
		// the following is perfectly fine, so claiming it's wrong will fail
		tester.yields("a13245", id6.ID);
		tester.yields("a13245G12AcB", id6.ID, id6.ID);
	}
}
