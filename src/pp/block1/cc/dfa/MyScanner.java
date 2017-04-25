package pp.block1.cc.dfa;

import java.util.ArrayList;
import java.util.List;
import static pp.block1.cc.dfa.State.ID6_DFA;

/**
 * Created by pieterjan on 25-4-17.
 */
public class MyScanner implements Scanner {

    public static void main(String[] args) {
        MyScanner scanner = new MyScanner();
        State dfa;
        dfa = ID6_DFA;
        String text = "i1234#A12345";
        scanner.scan(dfa, text);
    }

    @Override
    public List<String> scan(State start, String text) {
        State state = start;
        List<String> tokens = new ArrayList<>();
        char characters[] = text.toCharArray();

        String current_token = "";
        for (Character character : characters) {
            if (!state.hasNext(character)) {
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
