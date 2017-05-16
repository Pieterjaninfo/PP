// Generated from /home/pieterjan/IdeaProjects/CompilerConstruction/src/pp/block3/cc/tabular/Tabular.g4 by ANTLR 4.7
package pp.block3.cc.tabular;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TabularLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BEGINTABLE=1, ENDTABLE=2, TABLEENTRY=3, COMMENT=4, ENDROW=5, AND=6;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"BEGINTABLE", "ENDTABLE", "TABLEENTRY", "COMMENT", "ENDROW", "AND", "ALLIGNMENT", 
		"NEWLINE", "ALPHANUM"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, "'&'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "BEGINTABLE", "ENDTABLE", "TABLEENTRY", "COMMENT", "ENDROW", "AND"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public TabularLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Tabular.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\bk\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\38\n\3\f"+
		"\3\16\3;\13\3\3\3\7\3>\n\3\f\3\16\3A\13\3\3\4\6\4D\n\4\r\4\16\4E\3\5\3"+
		"\5\6\5J\n\5\r\5\16\5K\3\5\6\5O\n\5\r\5\16\5P\3\6\3\6\3\6\3\6\3\6\3\7\3"+
		"\7\3\b\3\b\6\b\\\n\b\r\b\16\b]\3\b\3\b\3\t\5\tc\n\t\3\t\3\t\3\n\6\nh\n"+
		"\n\r\n\16\ni\2\2\13\3\3\5\4\7\5\t\6\13\7\r\b\17\2\21\2\23\2\3\2\5\7\2"+
		"\"\"\60\60\62;C\\c|\5\2eenntt\7\2\17\17\"\"\62;C\\c|\2o\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\3\25\3\2\2"+
		"\2\5(\3\2\2\2\7C\3\2\2\2\tG\3\2\2\2\13R\3\2\2\2\rW\3\2\2\2\17Y\3\2\2\2"+
		"\21b\3\2\2\2\23g\3\2\2\2\25\26\7^\2\2\26\27\7d\2\2\27\30\7g\2\2\30\31"+
		"\7i\2\2\31\32\7k\2\2\32\33\7p\2\2\33\34\7}\2\2\34\35\7v\2\2\35\36\7c\2"+
		"\2\36\37\7d\2\2\37 \7w\2\2 !\7n\2\2!\"\7c\2\2\"#\7t\2\2#$\7\177\2\2$%"+
		"\3\2\2\2%&\5\17\b\2&\'\5\21\t\2\'\4\3\2\2\2()\7^\2\2)*\7g\2\2*+\7p\2\2"+
		"+,\7f\2\2,-\7}\2\2-.\7v\2\2./\7c\2\2/\60\7d\2\2\60\61\7w\2\2\61\62\7n"+
		"\2\2\62\63\7c\2\2\63\64\7t\2\2\64\65\7\177\2\2\659\3\2\2\2\668\7\"\2\2"+
		"\67\66\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:?\3\2\2\2;9\3\2\2\2<>\5"+
		"\21\t\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@\6\3\2\2\2A?\3\2\2\2B"+
		"D\5\23\n\2CB\3\2\2\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\b\3\2\2\2GI\7\'\2"+
		"\2HJ\t\2\2\2IH\3\2\2\2JK\3\2\2\2KI\3\2\2\2KL\3\2\2\2LN\3\2\2\2MO\5\21"+
		"\t\2NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2Q\n\3\2\2\2RS\7^\2\2ST\7^"+
		"\2\2TU\3\2\2\2UV\5\21\t\2V\f\3\2\2\2WX\7(\2\2X\16\3\2\2\2Y[\7}\2\2Z\\"+
		"\t\3\2\2[Z\3\2\2\2\\]\3\2\2\2][\3\2\2\2]^\3\2\2\2^_\3\2\2\2_`\7\177\2"+
		"\2`\20\3\2\2\2ac\7\17\2\2ba\3\2\2\2bc\3\2\2\2cd\3\2\2\2de\7\f\2\2e\22"+
		"\3\2\2\2fh\t\4\2\2gf\3\2\2\2hi\3\2\2\2ig\3\2\2\2ij\3\2\2\2j\24\3\2\2\2"+
		"\13\29?EKP]bi\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}