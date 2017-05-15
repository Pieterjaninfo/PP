grammar ExpressionAttr;

import ExpressionVocab;

expr returns [ pp.block3.cc.antlr.Type val ]
    : e0=expr POW e1=expr
      { $val = ($e0.val != Type.BOOL && $e1.val == Type.NUM ) ? $e0.val : Type.ERR; }
    | e0=expr PLUS e1=expr
      { $val = ($e0.val == $e1.val) ? $e0.val : Type.ERR; }
    | e0=expr EQ e1=expr
      { $val = ($e0.val == $e1.val) ? Type.BOOL : Type.ERR; }
    | LPAR e=expr RPAR
      { $val = $e.val; }
    | NUM
      { $val = Type.NUM; }
    | BOOL
      { $val = Type.BOOL; }
    | STR
      { $val = Type.STR; }
    ;
