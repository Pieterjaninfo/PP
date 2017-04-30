// Generated from /home/pieterjan/IdeaProjects/CompilerConstruction/src/pp/block1/cc/antlr/Musical.g4 by ANTLR 4.7
package pp.block1.cc.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Musical extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SONG=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"SONG", "LA", "LI"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SONG"
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


	public Musical(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Musical.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\3)\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\23\n\2\3\3\3\3"+
		"\6\3\27\n\3\r\3\16\3\30\3\3\7\3\34\n\3\f\3\16\3\37\13\3\3\4\3\4\3\4\3"+
		"\4\7\4%\n\4\f\4\16\4(\13\4\2\2\5\3\3\5\2\7\2\3\2\2\2+\2\3\3\2\2\2\3\22"+
		"\3\2\2\2\5\24\3\2\2\2\7 \3\2\2\2\t\23\5\5\3\2\n\13\5\5\3\2\13\f\5\5\3"+
		"\2\f\23\3\2\2\2\r\16\5\5\3\2\16\17\5\5\3\2\17\20\5\5\3\2\20\21\5\7\4\2"+
		"\21\23\3\2\2\2\22\t\3\2\2\2\22\n\3\2\2\2\22\r\3\2\2\2\23\4\3\2\2\2\24"+
		"\26\7N\2\2\25\27\7c\2\2\26\25\3\2\2\2\27\30\3\2\2\2\30\26\3\2\2\2\30\31"+
		"\3\2\2\2\31\35\3\2\2\2\32\34\7\"\2\2\33\32\3\2\2\2\34\37\3\2\2\2\35\33"+
		"\3\2\2\2\35\36\3\2\2\2\36\6\3\2\2\2\37\35\3\2\2\2 !\7N\2\2!\"\7k\2\2\""+
		"&\3\2\2\2#%\7\"\2\2$#\3\2\2\2%(\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\'\b\3\2\2"+
		"\2(&\3\2\2\2\7\2\22\30\35&\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}