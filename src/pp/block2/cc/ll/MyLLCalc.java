package pp.block2.cc.ll;

import pp.block2.cc.NonTerm;
import pp.block2.cc.Symbol;
import pp.block2.cc.Term;

import java.util.*;

/**
 * A LL Calc implementation.
 */
public class MyLLCalc implements LLCalc {

    private Grammar grammar;

    public MyLLCalc(Grammar g) {
        this.grammar = g;
    }

    @Override
    public Map<Symbol, Set<Term>> getFirst() {
        //Initialize First sets
        Map<Symbol, Set<Term>> result = new HashMap<>();
        List<Rule> rules = grammar.getRules();
        for (Rule rule : rules) {
            result.put(rule.getLHS(), new HashSet<>());
        }

        for (Rule rule : rules) { // rule: A -> beta

        }


        return result;
    }


    @Override
    public Map<NonTerm, Set<Term>> getFollow() {
        return null;
    }

    @Override
    public Map<Rule, Set<Term>> getFirstp() {
        return null;
    }

    @Override
    public boolean isLL1() {
        return false;
    }


















    /*
    @Override
    public Map<Symbol, Set<Term>> getFirst() {
        Map<Symbol, Set<Term>> result = new HashMap<>();
        List<Rule> rules = grammar.getRules();
        for (Rule rule : rules) {
            result.put(rule.getLHS(), new HashSet<>());
        }
        boolean changes_made = false;
        do {
            changes_made = false;
            for (Rule rule : rules) {
                NonTerm lhs = rule.getLHS();
                List<Symbol> rhs = rule.getRHS();

                Symbol beta = rhs.get(0);
                if (beta instanceof Term) {
                    // First symbol in rhs is a terminal
                    if (!result.get(lhs).contains(beta)) {
                        //did not have symbol yet
                        changes_made = true;
                        result.get(lhs).add((Term) beta);
                    }
                } else {
                    // First symbol in rhs is a non-terminal
                    for (Term term : result.get(beta)) {
                        if (!result.get(lhs).contains(term)) {
                            //did not have symbol yet
                            changes_made = true;
                            result.get(lhs).add(term);
                        }
                    }
                }
            }
        } while (changes_made);

        return result;
    }*/
}
