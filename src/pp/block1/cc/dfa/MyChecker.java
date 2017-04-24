package pp.block1.cc.dfa;

import static pp.block1.cc.dfa.State.ID6_DFA;

import pp.block1.cc.dfa.Checker;
import pp.block1.cc.dfa.State;

/**
 * Checks whether the string fits the DFA: [a-zA-Z][0-9a-zA-Z]{5}
 */
public class MyChecker implements Checker {

    public static void main(String[] args) {
        MyChecker checker = new MyChecker();
        State dfa;
        dfa = ID6_DFA;
        String word = "i12345";
        checker.accepts(dfa, word);
    }

    @Override
    public boolean accepts(State state, String word) {
        char characters[] = word.toCharArray();
        for (Character character : characters) {
            //System.out.printf("char: %s current state: %d is_accepting: %s\n", character, state.getNumber(), state.isAccepting());
            if (!state.hasNext(character)) {
                return false;
            }
            state = state.getNext(character);
        }
        return state.isAccepting();


    }
}
