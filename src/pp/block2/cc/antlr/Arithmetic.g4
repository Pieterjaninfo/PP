grammar Arithmetic;

// Parser rules
start: expr;
expr: <assoc=right> expr '^' expr   #powExpr
    | expr '*' expr                 #mulExpr
    | expr '-' expr                 #subExpr
    | expr '+' expr                 #addExpr
    | NUM                           #numExpr
    ;


// Lexer rules
NUM: DIGIT+ | '-' DIGIT+;
fragment DIGIT: '0'..'9';
