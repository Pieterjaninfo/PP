grammar DeclUse;

//@header{package pp.block3.cc.symbol;}

program : '(' series ')' ;
series  : unit* ;
unit    : decl              #decUnit
        | use               #useUnit
        | '(' series ')'    #seriesUnit
        ;

decl    : 'D:' ID ;
use     : 'U:' ID ;

ID : [a-zA-Z]+;
WS : [ \t\n\r]+ -> skip;
