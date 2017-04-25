package pp.block1.cc.test;

import static pp.block1.cc.dfa.State.ID6_DFA;
import static pp.block1.cc.dfa.State.LALA_DFA;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import pp.block1.cc.dfa.MyScanner;
import pp.block1.cc.dfa.Scanner;
import pp.block1.cc.dfa.State;

/** Test class for Scanner implementation. */
public class ScannerTest {
	private Scanner myGen = new MyScanner();

	@Test
	public void testID6() {
		this.dfa = ID6_DFA;
		yields("", true);
		yields("a12345", true, "a12345");
		yields("a12345AaBbCc", true,"a12345", "AaBbCc");

		yields("a12345b12345c12345", true,"a12345", "b12345", "c12345");
		yields("a1234$AaBbCc", false);
		yields("a12345AaBbC%", false);
		yields("a12345AaBbCc%", false);
		yields("%a12345AaBbCc", false);
		yields("a12345%AaBbCc", false);
	}

	@Test
    public void testLALA() {
	    this.dfa = LALA_DFA;
	    yields("La", true, "La");
	    yields("LaLa", true, "LaLa");
	    yields("LaLaLa", true, "LaLa", "La");
    }

	private void yields(String word, boolean correct, String... tokens) {
		List<String> result = this.myGen.scan(this.dfa, word);

		if (!correct) {
		    Assert.assertEquals(null, result);
		} else {

			if (result == null) {
				Assert.fail(String.format("Word '%s' is erroneously rejected by %s", word, this.dfa));
			}
			Assert.assertEquals(tokens.length, result.size());
			for (int i = 0; i < tokens.length; i++) {
				Assert.assertEquals(tokens[i], result.get(i));
			}
		}
	}

	private State dfa;
}
