lexer grammar ExpressionVocab;

//@header{package pp.block3.cc.antlr;}

POW    : '^';
PLUS   : '+';
EQ     : '=';
LPAR   : '(';
RPAR   : ')';

NUM    : ('0'..'9')+;
BOOL   : 'true' | 'false';
STR    : ('a'..'z')+;

// ignore whitespace
WS : [ \t\n\r] -> skip;
