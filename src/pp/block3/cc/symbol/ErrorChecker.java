package pp.block3.cc.symbol;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ErrorChecker extends DeclUseBaseListener {

    private List<String> errors;
    private SymbolTable table;

    public List<String> checkErrors(String text) {
        this.errors = new ArrayList<>();
        this.table = new MySymbolTable();
        Lexer lexer = null;
        try {
            lexer = new DeclUseLexer(CharStreams.fromFileName(text));
        } catch (IOException e) {
            System.err.println("Couldn't load the file: " + text);
            e.printStackTrace();
        }
        DeclUseParser parser = new DeclUseParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.program();
        new ParseTreeWalker().walk(this, tree);

        // Print the found errors
        System.out.println("File " + text + ": " + errors);
        return errors;
    }

    @Override
    public void enterProgram(DeclUseParser.ProgramContext ctx) {
        table.openScope();
    }

    @Override
    public void exitProgram(DeclUseParser.ProgramContext ctx) {
        table.closeScope();
    }

    @Override
    public void enterSeriesUnit(DeclUseParser.SeriesUnitContext ctx) {
        table.openScope();
    }

    @Override
    public void exitSeriesUnit(DeclUseParser.SeriesUnitContext ctx) {
        try {
            table.closeScope();
        } catch (RuntimeException e) {
            Token token = ctx.getStart();
            String errormsg = String.format("Tried closing non-existing scope at line %d:%d",
                    token.getLine(), token.getCharPositionInLine());
            errors.add(errormsg);
        }
    }


    @Override
    public void enterDecUnit(DeclUseParser.DecUnitContext ctx) {
        if (!table.add(ctx.getText().substring(2))) {
            Token token = ctx.getStart();
            String errormsg = String.format("Variable '%s' already declared in scope at line %d:%d",
                    ctx.getText(), token.getLine(), token.getCharPositionInLine());
            errors.add(errormsg);
        }
    }

    @Override
    public void enterUseUnit(DeclUseParser.UseUnitContext ctx) {
        String id = ctx.getText();
        if (!table.contains(id.substring(2))) {
            Token token = ctx.getStart();
            String errormsg = String.format("Variable '%s' used out of scope at line %d:%d",
                    id, token.getLine(), token.getCharPositionInLine());
            errors.add(errormsg);
        }
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        Token token = node.getSymbol();
        String errormsg = "Parser error occured at: " + token.getLine() + ":" + token.getCharPositionInLine() + ".";
        this.errors.add(errormsg);
    }

    public static void main(String[] args) {
        ErrorChecker errorChecker = new ErrorChecker();
        String path = "src/pp/block3/cc/symbol/";
        errorChecker.checkErrors(path + "prog1");
        errorChecker.checkErrors(path + "prog2");
        errorChecker.checkErrors(path + "prog3");
        errorChecker.checkErrors(path + "prog4");
    }
}
