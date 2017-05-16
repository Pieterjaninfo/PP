package pp.block3.cc.tabular;


import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

public class MyErrorListener extends BaseErrorListener {
    List<String> errors = new ArrayList<>();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        String errormsg = String.format("line %d:%d %s", line, charPositionInLine, msg);
        errors.add(errormsg);
    }

    public List<String> getErrors() {
        return errors;
    }
}
