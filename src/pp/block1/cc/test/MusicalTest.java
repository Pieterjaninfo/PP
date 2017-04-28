package pp.block1.cc.test;

import org.junit.Test;
import pp.block1.cc.antlr.Musical;

public class MusicalTest {
	private static LexerTester tester = new LexerTester(Musical.class);

	@Test
	public void succeedingTest() {
		tester.correct("La");
		tester.correct("Laaa");
		tester.correct("Li");
		tester.correct("LaLiLaLiLa");
		tester.correct("Laaaa  LiLa");
		tester.wrong("L");
		tester.wrong("la");
		tester.wrong("li");
		tester.wrong("aLa");
	}

	@Test
	public void noSpacesBetweenKeywordsTest() {
		// the following is perfectly fine, so claiming it's wrong will fail
		tester.yields("La", Musical.SONG);
	}
}
