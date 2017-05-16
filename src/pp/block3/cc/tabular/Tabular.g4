grammar Tabular;

// Parser rules
start: COMMENT? BEGINTABLE tablerow+ ENDTABLE;
tablerow: tableentry? ('&' tableentry?)* ENDROW;
tableentry: TABLEENTRY;

// Lexer rules
BEGINTABLE: '\\begin{tabular}' ALLIGNMENT NEWLINE;
ENDTABLE: '\\end{tabular}' ' '* NEWLINE*;
TABLEENTRY: ALPHANUM+;
COMMENT: '%' [a-zA-Z0-9 .]+ NEWLINE+;

ENDROW: '\\\\' NEWLINE;
AND: '&';
fragment ALLIGNMENT: '{' ('l' | 'c' | 'r')+ '}';
fragment NEWLINE: '\r'? '\n';
fragment ALPHANUM: [a-zA-Z0-9 \r]+;
