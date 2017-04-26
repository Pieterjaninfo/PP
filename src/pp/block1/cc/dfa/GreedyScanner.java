package pp.block1.cc.dfa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pieterjan on 26-4-17.
 */
public class GreedyScanner implements Scanner {
    @Override
    public List<String> scan(State start, String text) {
        State state = start;
        List<String> tokens = new ArrayList<>();
        char[] characters = text.toCharArray();

        String current_token = "";
        String last_token = "";
        int last_i = 0;
        int i = 0;
        System.out.println("Entered word: " + text);
        while (i < characters.length) {
            Character character = characters[i];
            System.out.printf("Char: %s, current_token: %s, last_token: %s i:%d last_i:%d\n",
                    character, current_token, last_token, i, last_i);

            if (!last_token.equals("") && !state.hasNext(character)) {
                System.out.println("Current token went dead, resetting to previous token");
                tokens.add(last_token);
                state = start;
                current_token = "";
                last_token = "";
                i = last_i + 1;
            } else if (!state.hasNext(character)) {
                System.out.println("Text: " + text + " has no tokens");
                return null;
            }
            state = state.getNext(character);
            current_token += character;

            // If accepting add token to current token
            System.out.printf("char: %s state:%d\n", character, state.getNumber());
            if (state.isAccepting()) {
                last_token = current_token;
                last_i = i;
            }
            i++;
            if (i == characters.length && !state.isAccepting() && !last_token.equals("")) {
                System.out.println("Current token has no future, resetting to previous token");
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
        System.out.println("Text: " + text + " Tokens: " + tokens.toString());
        return tokens;
    }
}
