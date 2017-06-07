{-# LANGUAGE FlexibleInstances, DeriveGeneric, DeriveAnyClass #-}
import FPPrac.Trees             -- Contains now also the function toRoseTree. Re-install it!
import GHC.Generics             -- Necessary for correct function of FPPrac
import FP_Grammar
import FP_TypesEtc              -- Extend the file TypesEtc with your own alphabet
import FP_ParserGen (parse)     -- Touching this file leaves you at your own devices
import Tokenizer

--Exercise 3
data Tree = Node Alphabet [Tree]
          | Leaf (Alphabet,String)
          deriving (Eq,Show,Generic,ToRoseTree)
{-
data ParseTree  = PLeaf Token
                | PNode Alphabet [ParseTree]
                | PError ParseTree [Alphabet] Alphabet String Int
                deriving (Eq,Show,Generic,ToRoseTree)
-}

ppt :: ParseTree -> Tree
ppt (PLeaf token) = Leaf token
ppt (PNode alpha [t1]) = ppt t1
ppt (PNode alpha trees) = Node alpha (map ppt trees)

pp :: Tree -> RoseTree
pp (Leaf (a,b)) = RoseNode (show b) []
pp (Node alpha trees) = RoseNode (show alpha) (map pp trees)

showppt str = showRoseTree $ toRoseTree $ ppt $ parse grammar Expr (tokenizer str)
showpp str = showRoseTree $ pp $ ppt $ parse grammar Stmnt (tokenizer str)

--Exercise 4
{-  4b
    Calc EQ -> returns 0/1
    na condition -> before begin of 'then' -> skip to else      JUMP 
                    before begin of 'else  -> skip to remainder CONDJUMP
    when conditions are met for the jump, it will either only increase PC by one, or by
    enough to skip the entire desired segment. 
-}
