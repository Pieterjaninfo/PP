lexer grammar Quotations;

CITATION: QUOTATION (~'"' | QUOTATION QUOTATION)* QUOTATION;

//fragment ALPHABET: (LETTER | DIGIT | PUNCTUATION | SPACE);
//fragment LETTER: ('a'..'z' | 'A'..'Z');
//fragment DIGIT: ('0'..'9');
//fragment PUNCTUATION: (',' | '.'| '!' | '?' | '\'' | '"');
//fragment PUNCTUATION: (',' | '.'| '!' | '?' | '\'');
//fragment SPACE: (' ' | '\t' | '\r' | '\n');
fragment QUOTATION: '"';
