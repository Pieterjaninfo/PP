package pp.block2.cc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import pp.block2.cc.NonTerm;
import pp.block2.cc.Symbol;
import pp.block2.cc.Term;
import pp.block2.cc.ll.*;

public class LLCalcTest {
	private Grammar sentenceG = Grammars.makeSentence();
	// Define the non-terminals
	private NonTerm subj = sentenceG.getNonterminal("Subject");
	private NonTerm obj = sentenceG.getNonterminal("Object");
	private NonTerm sent = sentenceG.getNonterminal("Sentence");
	private NonTerm mod = sentenceG.getNonterminal("Modifier");
	// Define the terminals
	private Term adj = sentenceG.getTerminal(Sentence.ADJECTIVE);
	private Term noun = sentenceG.getTerminal(Sentence.NOUN);
	private Term verb = sentenceG.getTerminal(Sentence.VERB);
	private Term end = sentenceG.getTerminal(Sentence.ENDMARK);
	// Now add the last rule, causing the grammar to fail
	private Grammar sentenceXG = Grammars.makeSentence();
	{    sentenceXG.addRule(mod, mod, mod);
	}
	private LLCalc sentenceXLL = createCalc(sentenceXG);

	// If then else grammar
	private Grammar ifG = Grammars.makeIf();
	private NonTerm stat = ifG.getNonterminal("Stat");
	private NonTerm elsePart = ifG.getNonterminal("ElsePart");
	private Term assign = ifG.getTerminal(If.ASSIGN);
	private Term iff = ifG.getTerminal(If.IF);
	private Term expr = ifG.getTerminal(If.COND);
	private Term then = ifG.getTerminal(If.THEN);
	private Term elsee = ifG.getTerminal(If.ELSE);
	private Term eof = Symbol.EOF;
	private Term empty = Symbol.EMPTY;
	private LLCalc ifLL = createCalc(ifG);

	// L grammar
	private Grammar LG = Grammars.makeL();
	private NonTerm l = LG.getNonterminal("L");
	private NonTerm r = LG.getNonterminal("R");
	private NonTerm r2 = LG.getNonterminal("R'");
	private NonTerm q = LG.getNonterminal("Q");
	private NonTerm q2 = LG.getNonterminal("Q'");
	private Term a = LG.getTerminal(L.A);
	private Term b = LG.getTerminal(L.B);
	private Term c = LG.getTerminal(L.C);
	private LLCalc LLL = createCalc(LG);

	/** Tests the LL-calculator for the If grammar. (2-CC.1) */
	@Test
	public void testIfLL1() {
		assertFalse(ifLL.isLL1());
	}
	@Test
	public void testIfFirst() {
		Map<Symbol, Set<Term>> first = ifLL.getFirst();
		assertEquals(set(assign, iff), first.get(stat));
		assertEquals(set(elsee, empty), first.get(elsePart));
	}
	@Test
	public void testIfFollow() {
		Map<NonTerm, Set<Term>> follow = ifLL.getFollow();
		assertEquals(set(eof, elsee), follow.get(stat));
		assertEquals(set(eof, elsee), follow.get(elsePart));
	}
	@Test
	public void testIfFirstp() {
		Map<Rule, Set<Term>> firstp = ifLL.getFirstp();
		assertEquals(set(assign), firstp.get(ifG.getRules(stat).get(0)));
		assertEquals(set(iff), firstp.get(ifG.getRules(stat).get(1)));
		assertEquals(set(elsee), firstp.get(ifG.getRules(elsePart).get(0)));
		assertEquals(set(eof, empty, elsee), firstp.get(ifG.getRules(elsePart).get(1)));
	}

	/** Tests the LL-calculator for the L grammar. (2-CC.2) */
	@Test
	public void testLLL() {
		assertTrue(LLL.isLL1());
	}
	@Test
	public void testLFirst() {
		Map<Symbol, Set<Term>> first = LLL.getFirst();
		assertEquals(set(a,b,c), first.get(l));
		assertEquals(set(a,c), first.get(r));
		assertEquals(set(b,empty), first.get(r2));
		assertEquals(set(b), first.get(q));
		assertEquals(set(b,c), first.get(q2));
	}
	@Test
	public void testLFollow() {
		Map<NonTerm, Set<Term>> follow = LLL.getFollow();
		assertEquals(set(eof), follow.get(l));
		assertEquals(set(a), follow.get(r));
		assertEquals(set(a), follow.get(r2));
		assertEquals(set(b), follow.get(q));
		assertEquals(set(b), follow.get(q2));
	}
	@Test
	public void testLFirstp() {
		Map<Rule, Set<Term>> firstp = LLL.getFirstp();
		List<Rule> lRules = LG.getRules(l);
		List<Rule> rRules = LG.getRules(r);
		List<Rule> r2Rules = LG.getRules(r2);
		List<Rule> qRules = LG.getRules(q);
		List<Rule> q2Rules = LG.getRules(q2);
		assertEquals(set(a,c), firstp.get(lRules.get(0)));
		assertEquals(set(b), firstp.get(lRules.get(1)));

		assertEquals(set(a), firstp.get(rRules.get(0)));
		assertEquals(set(c), firstp.get(rRules.get(1)));

		assertEquals(set(b), firstp.get(r2Rules.get(0)));
		assertEquals(set(empty, a), firstp.get(r2Rules.get(1)));

		assertEquals(set(b), firstp.get(qRules.get(0)));

		assertEquals(set(b), firstp.get(q2Rules.get(0)));
		assertEquals(set(c), firstp.get(q2Rules.get(1)));
	}


	/** Tests the LL-calculator for the Sentence grammar. */
	@Test
	public void testSentenceOrigLL1() {
		// Without the last (recursive) rule, the grammar is LL-1
		assertTrue(createCalc(sentenceG).isLL1());
	}

	@Test
	public void testSentenceXFirst() {
		Map<Symbol, Set<Term>> first = sentenceXLL.getFirst();
		assertEquals(set(adj, noun), first.get(sent));
		assertEquals(set(adj, noun), first.get(subj));
		assertEquals(set(adj, noun), first.get(obj));
		assertEquals(set(adj), first.get(mod));
	}
	
	@Test
	public void testSentenceXFollow() {
		// FOLLOW sets
		Map<NonTerm, Set<Term>> follow = sentenceXLL.getFollow();
		assertEquals(set(Symbol.EOF), follow.get(sent));
		assertEquals(set(verb), follow.get(subj));
		assertEquals(set(end), follow.get(obj));
		assertEquals(set(noun, adj), follow.get(mod));
	}
	
	@Test
	public void testSentenceXFirstPlus() {
		// Test per rule
		Map<Rule, Set<Term>> firstp = sentenceXLL.getFirstp();
		List<Rule> subjRules = sentenceXG.getRules(subj);
		assertEquals(set(noun), firstp.get(subjRules.get(0)));
		assertEquals(set(adj), firstp.get(subjRules.get(1)));
	}
	
	@Test
	public void testSentenceXLL1() {
		assertFalse(sentenceXLL.isLL1());
	}

	/** Creates an LL1-calculator for a given grammar. */
	private LLCalc createCalc(Grammar g) {
		return new MyLLCalc(g);
	}

	@SuppressWarnings("unchecked")
	private <T> Set<T> set(T... elements) {
		return new HashSet<>(Arrays.asList(elements));
	}
}
