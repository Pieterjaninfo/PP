package pp.block1.cc.dfa;

import java.util.ArrayList;
import java.util.List;

/**
 * Greedy scanner that scans the given string and returns all tokens
 */

public class GreedyScanner implements Scanner {

    private boolean debug = true;

    @Override
    public List<String> scan(State start, String text) {
        State state = start;
        List<String> tokens = new ArrayList<>();
        char[] characters = text.toCharArray();

        String current_token = "";
        String last_token = "";
        int last_i = 0;
        int i = 0;
        if (debug) System.out.println("Entered word: " + text);
        while (i < characters.length) {
            Character character = characters[i];
            if (debug) System.out.printf("Char: %s, current_token: %s, last_token: %s i:%d last_i:%d\n",
                    character, current_token, last_token, i, last_i);

            if (!last_token.equals("") && !state.hasNext(character)) {
                if (debug) System.out.println("Current token went dead, resetting to previous token");
                tokens.add(last_token);
                state = start;
                current_token = "";
                last_token = "";
                i = last_i + 1;
            } else if (!state.hasNext(character)) {
                if (debug) System.out.println("Text: " + text + " has no tokens");
                return null;
            }
            state = state.getNext(character);
            current_token += character;

            // If accepting add token to current token
            if (state.isAccepting()) {
                last_token = current_token;
                last_i = i;
            }
            i++;
            if (i == characters.length && !state.isAccepting() && !last_token.equals("")) {
                if (debug) System.out.println("Current token has no future, resetting to previous token");
                tokens.add(last_token);
                state = start;
                current_token = "";
                last_token = "";
                i = last_i + 1;
            }
        }
        if (state.isAccepting() && !current_token.equals("")) {
            tokens.add(current_token);
        }
        if (debug) System.out.println("Text: " + text + " Tokens: " + tokens.toString());
        return tokens;
    }
}
