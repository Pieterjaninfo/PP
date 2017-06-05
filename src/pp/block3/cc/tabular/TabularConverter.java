package pp.block3.cc.tabular;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class TabularConverter extends TabularBaseListener {
    private boolean errorFound;
    MyErrorListener errorListener;

    private StringBuilder tablebuilder;


    public void generateTable(String text) {        //USE STRINGBUILDER INSTEAD
        this.tablebuilder = new StringBuilder();
        this.errorFound = false;
        errorListener = new MyErrorListener();
        Lexer lexer = null;
        try {
            lexer = new TabularLexer(CharStreams.fromFileName(text));
            lexer.removeErrorListeners();
            lexer.addErrorListener(errorListener);
        } catch (IOException e) {
            System.err.println("Couldn't load the file: " + text);
            e.printStackTrace();
        }
        TabularParser parser = new TabularParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        ParseTree tree = parser.start();
        new ParseTreeWalker().walk(this, tree);

        if (errorFound) {
            System.err.println("Error occured parsing the tree of file " + text.substring(25));
            System.out.println("Errors: " + errorListener.getErrors());
        }
        else { System.out.println("HTML table: \n" + tablebuilder.toString()); writeFile(text, tablebuilder.toString()); }
    }

    private void writeFile(String filepath, String text) {
        try (PrintWriter out = new PrintWriter(filepath + ".html")) {
            out.print(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enterStart(TabularParser.StartContext ctx) {
        tablebuilder.append("<html>\n<body>\n<table border=\"1\">\n");
    }

    @Override
    public void exitStart(TabularParser.StartContext ctx) {
        tablebuilder.append("</table>\n</body>\n</html>\n");
    }

    @Override
    public void enterTablerow(TabularParser.TablerowContext ctx) {
        tablebuilder.append("<tr>\n");
    }

    @Override
    public void exitTablerow(TabularParser.TablerowContext ctx) {
        tablebuilder.append("</tr>\n");
    }

    @Override
    public void enterTableentry(TabularParser.TableentryContext ctx) {
        tablebuilder.append("\t<td>" + ctx.getText() + "</td>\n");
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        this.errorFound = true;
    }

    public static void main(String[] args) {
        TabularConverter tabularConverter = new TabularConverter();
        tabularConverter.generateTable("src/pp/block3/cc/tabular/tabular-1.tex");
        tabularConverter.generateTable("src/pp/block3/cc/tabular/tabular-2.tex");
        tabularConverter.generateTable("src/pp/block3/cc/tabular/tabular-3.tex");
        tabularConverter.generateTable("src/pp/block3/cc/tabular/tabular-4.tex");
        tabularConverter.generateTable("src/pp/block3/cc/tabular/tabular-5.tex");
    }
}
