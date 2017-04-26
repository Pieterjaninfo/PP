package pp.block1.cc.dfa;

import java.util.ArrayList;
import java.util.List;

/**
 * Greedy scanner that scans the given string and returns all tokens
 */
public class MyScanner implements Scanner {

    @Override
    public List<String> scan(State start, String text) {
        State state = start;
        List<String> tokens = new ArrayList<>();
        char[] characters = text.toCharArray();

        String current_token = "";
        for (Character character : characters) {
            if (!state.hasNext(character)) {
                System.out.println("Text: " + text + " has no tokens");
                return null;
            }
            state = state.getNext(character);
            current_token += character;

            // If accepting then add the token and reset the DFA
            if (state.isAccepting()) {
                tokens.add(current_token);
                state = start;
                current_token = "";
            }
        }
        System.out.println("Text: " + text + " Tokens: " + tokens.toString());
        return tokens;
    }
}
