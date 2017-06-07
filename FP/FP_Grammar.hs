{-# LANGUAGE FlexibleInstances, DeriveGeneric, DeriveAnyClass #-}
        -- Necessary for function toRoseTree

module FP_Grammar where

{- ===========================================================================
Contains example grammar + examples of test definitions
NOTE: Compiler directives above
=========================================================================== -}

import FPPrac.Trees             -- Contains now also the function toRoseTree. Re-install it!
import GHC.Generics             -- Necessary for correct function of FPPrac

import FP_TypesEtc              -- Extend the file TypesEtc with your own alphabet
import FP_ParserGen (parse)     -- Touching this file leaves you at your own devices
import Tokenizer                -- You'll have to write a file for tokenizing yourself


-- ================================================================================================
-- Example grammar, to illustrate the structure of the definition of the grammar as a function
--      (where the Alphabet is in the file TypesEtc.hs)

grammar :: Grammar

grammar nt = case nt of

        Expr    -> [[ lBracket, Expr, Op, Expr, rBracket ]
                   ,[ If, Expr, Then, Expr, Else, Expr   ]
                   ,[ Nmbr                               ]
                   ,[ Var                                ]]

        Nmbr    -> [[ nmbr                               ]]

        Op      -> [[ op                                 ]]
        
        Var     -> [[ var                                ]]  


        Stmnt   -> [[ Var, eq, Expr                      ]
                   ,[ Repeat, Expr, Stmnt'               ]]

        Stmnt'  -> [[ Stmnt, Stmnt'                      ]
                   ,[ Stmnt                              ]]

        Repeat  -> [[ repeat'                            ]]
        If      -> [[ iff                                ]]
        Then    -> [[ thenn                              ]]
        Else    -> [[ elsee                              ]]        

-- shorthand names can be handy, such as:
--lBracket  = Terminal "("           -- Terminals WILL be shown in the parse tree
--rBracket  = Terminal ")"

-- alternative for brackets (try both variants and compare the parse trees):
lBracket  = Symbol "("          -- Symbols will NOT be shown in the parse tree.
rBracket  = Symbol ")"

nmbr      = SyntCat Nmbr
op        = SyntCat Op
var       = SyntCat Var
eq        = Terminal "="
repeat'   = SyntCat Repeat
iff       = SyntCat If
thenn     = SyntCat Then
elsee     = SyntCat Else

-- ===============================================================================================
-- TESTING: example expression: "((10+20)*30)"
-- ===============================================================================================

-- Result of tokenizer (to write yourself) should be a tokenList like here.
-- NOTE: a lexer is needed (i.e., a function that maps a String to a tuple (Alphabet,String).
--                         (opens e.g. the possibility to differentiate between variables and keywords)

tokenList = [ (Bracket, "(" )
            , (Bracket, "(" )
            , (Nmbr   , "10")
            , (Op     , "+" )
            , (Nmbr   , "20")
            , (Bracket, ")" )
            , (Op     , "*" )
            , (Nmbr   , "30")
            , (Bracket, ")" )
            ]
tokenList' = tokenizer "((10+20)*a)"
-- Parse this token list with a call to the function parse, with
--      - grammar: the name of the grammar above
--      - Expr: the start-nonterminal of the grammar above
--      - tokenList: the token list above
parseTree0 = parse grammar Expr tokenList'

-- prpr: for pretty-printing the parsetree, including error messages
testTxt    = prpr parseTree0

-- showTree + toRoseTree: for graphical representation in browser
testGr     = showRoseTree $ toRoseTree parseTree0


ex1 = "((10+20)*30)"                                    --expr
ex1b= "((10+b)*a)"                                      --expr
ex2 = "a=(10+20)"                                       --stmnt
ex3 = "repeat(10+20)a=(10+20)"                          --smnt
ex3b= "repeat(10+20)a=(10+20)b=10c=12"                  --stmnt
ex4 = "if(10==(5+5))then(30+(10*2))else((1-1)*10)"      --expr
ttxt str = prpr $ parse grammar Stmnt (tokenizer str)
etxt str = prpr $ parse grammar Expr (tokenizer str)
tgr str = showRoseTree $ toRoseTree $ parse grammar Stmnt (tokenizer str)
egr str = showRoseTree $ toRoseTree $ parse grammar Expr (tokenizer str)





