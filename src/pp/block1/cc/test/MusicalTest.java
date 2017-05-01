package pp.block1.cc.test;

import org.junit.Test;
import pp.block1.cc.antlr.Musical;

public class MusicalTest {
	private static LexerTester tester = new LexerTester(Musical.class);

	@Test
	public void succeedingTest() {
		tester.correct("La");
		tester.correct("Laaa    ");
		tester.correct("Laaa      Laa ");
		tester.correct("Laa Laaa LaaaaaaaaaaaaaaaaaaaaaaaaLi   ");

		tester.wrong("LaLaLi");
		tester.wrong("Laa LaaaLi   ");
		tester.wrong("LaLiLa");
		tester.wrong("LiLaLa");
		tester.wrong("Li");
		tester.wrong("LaLiLaLiLa");
		tester.wrong("Laaaa  LiLa");
		tester.wrong("L");
		tester.wrong("la");
		tester.wrong("li");
		tester.wrong("aLa");
	}

	@Test
	public void keywordsTest() {
		// the following is perfectly fine, so claiming it's wrong will fail
		tester.yields("La", Musical.LA);
		tester.yields("LaLa", Musical.LALA);
		tester.yields("LaLaLaLi", Musical.LALALALI);
//
		tester.yields("LaLaLa", Musical.LALA, Musical.LA);
		tester.yields("LaLaLaLaLaLi", Musical.LALA, Musical.LALALALI);
	}
}
