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
        Map<Symbol, Set<Term>> first = new HashMap<>();
        List<Rule> rules = grammar.getRules();

        for (Term T : grammar.getTerminals()) {
            HashSet<Term> set = new HashSet<>();
            set.add(T);
            first.put(T, set);
        }

        for (Symbol NT : grammar.getNonterminals()) {
            first.put(NT, new HashSet<>());
        }

        boolean change_made = true;
        while (change_made) {
            change_made = false;

            for (Rule rule : rules) {
                List<Symbol> betas = rule.getRHS();

                Set<Term> rhs = new HashSet<>(first.get(betas.get(0)));
                rhs.remove(Symbol.EMPTY);

                int i = 0;
                int k = betas.size();
                while (first.get(betas.get(i)).contains(Symbol.EMPTY) && i < k - 1) {
                    rhs.addAll(first.get(betas.get(i + 1)));
                    rhs.remove(Symbol.EMPTY);
                    i++;
                }
                if (i == k - 1 && first.get(betas.get(k-1)).contains(Symbol.EMPTY)) {
                    rhs.add(Symbol.EMPTY);
                }
                Set<Term> terminals = first.get(rule.getLHS());
                change_made = change_made || terminals.addAll(rhs);
                first.put(rule.getLHS(), terminals);
            }
        }
        return first;
    }


    @Override
    public Map<NonTerm, Set<Term>> getFollow() {
        Map<NonTerm, Set<Term>> follow = new HashMap<>();
        for (NonTerm NT : grammar.getNonterminals()) {
            follow.put(NT, new HashSet<>());
        }

        Set<Term> start_set = follow.get(grammar.getStart());
        start_set.add(Symbol.EOF);
        follow.put(grammar.getStart(), start_set);

        Map<Symbol, Set<Term>> first = getFirst();

        boolean change_made = true;
        while (change_made) {
            change_made = false;

            for (Rule rule : grammar.getRules()) {
                Set<Term> trailer = new HashSet<>(follow.get(rule.getLHS()));
                List<Symbol> betas = rule.getRHS();
                int k = betas.size() - 1;
                for (int i = k; i >= 0; i--) {
                    Symbol beta = betas.get(i);
                    if (beta instanceof NonTerm) {
                        Set<Term> new_set = new HashSet<>(follow.get(beta));
                        change_made = change_made || new_set.addAll(trailer);
                        follow.put((NonTerm) beta, new_set);

                        if (first.get(beta).contains(Symbol.EMPTY)) {
                            trailer.addAll(first.get(beta));
                            trailer.remove(Symbol.EMPTY);
                        } else {
                            trailer = new HashSet<>(first.get(beta));
                        }
                    } else {
                        trailer = new HashSet<>(first.get(beta));
                    }
                }
            }
        }
        return follow;
    }

    @Override
    public Map<Rule, Set<Term>> getFirstp() {
        Map<Rule, Set<Term>> firstp = new HashMap<>();

        Map<Symbol, Set<Term>> first = getFirst();
        Map<NonTerm, Set<Term>> follow = getFollow();

        for (Rule rule : grammar.getRules()) {
            Symbol beta = rule.getRHS().get(0);

            if (!first.get(beta).contains(Symbol.EMPTY)) {
                firstp.put(rule, first.get(beta));
            } else {
                Set<Term> terms = new HashSet<>(first.get(beta));
                terms.addAll(follow.get(rule.getLHS()));
                firstp.put(rule, terms);
            }
        }
        return firstp;
    }

    @Override
    public boolean isLL1() {
        Map<Rule, Set<Term>> firstp = getFirstp();
        HashMap<NonTerm, Set<Term>> temp = new HashMap<>();

        for (Map.Entry<Rule, Set<Term>> entry : firstp.entrySet()) {
            NonTerm NT = entry.getKey().getLHS();
            if (!temp.containsKey(NT)) {
                temp.put(NT, entry.getValue());
            } else {
                for (Term term : entry.getValue()) {
                    if (temp.get(NT).contains(term)) {
                        return false;
                    }
                }
                Set<Term> temp_set = new HashSet<>(temp.get(NT));
                temp_set.addAll(entry.getValue());
                temp.put(NT, temp_set);
            }
        }
        return true;
    }

}
