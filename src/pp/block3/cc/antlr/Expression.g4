grammar Expression;

import ExpressionVocab;

expr : expr POW expr    # pow
     | expr PLUS expr   # plus
     | expr EQ expr     # equality
     | LPAR expr RPAR   # par
     | NUM              # number
     | BOOL             # boolean
     | STR              # string
     ;
