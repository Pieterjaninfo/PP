-- Haskell session 6 of block 3

module Tokenizer where 
{- ===========================================================================
Contains Tokenizer for tokenizing String -> [(Alphabet,String)] using fsa's.
=========================================================================== -}


import FP_TypesEtc

data FsaState = Q0 | Q1 | Q2 | Q3 
--Numbers
fsaNUM :: FsaState -> String -> String -> ((Alphabet,String), String)
fsaNUM _ "" ys = ((Nmbr,ys), "")
fsaNUM Q0 (x:xs) ys
    | x == '~' = fsaNUM Q1 xs (ys ++ [x])
    | isDigit x = fsaNUM Q2 xs (ys ++ [x])
    | otherwise = error "illegal state fsaNUM Q0"

fsaNUM Q1 (x:xs) ys
    | isDigit x = fsaNUM Q2 xs (ys ++ [x])
    | otherwise = error "illegal state fsaNUM Q1"

fsaNUM Q2 (x:xs) ys
    | isDigit x = fsaNUM Q2 xs (ys ++ [x])
    | x == '.' = fsaNUM Q3 xs (ys ++ [x])
    | otherwise = ((Nmbr,ys), x:xs)

fsaNUM Q3 (x:xs) ys
    | isDigit x = fsaNUM Q3 xs (ys ++ [x])
    | otherwise = ((Nmbr,ys), x:xs)

--Variables (Identifiers)
fsaID :: FsaState -> String -> String -> ((Alphabet,String), String)
fsaID _ "" ys = checkReservedWords (ys,"")
fsaID Q0 (x:xs) ys
    | isAlpha x = fsaID Q1 xs (ys ++ [x])
    | otherwise = error "Illegal state fsaID Q0"

fsaID Q1 (x:xs) ys
    | isAlphanum x = fsaID Q1 xs (ys ++ [x])
    | otherwise = checkReservedWords (ys,x:xs)

checkReservedWords (token,rest)
    | token == "if" = ((If,token), rest)
    | token == "repeat" = ((Repeat,token), rest)
    | token == "then" = ((Then,token), rest)
    | token == "else" = ((Else,token), rest)
    | otherwise = ((Var,token), rest)


--Operators
fsaOP :: FsaState -> String -> String -> ((Alphabet,String), String)
fsaOP _ "" ys = ((Op,ys),"")
fsaOP Q0 (x:xs) ys
    | isOperator x = fsaOP Q1 xs (ys ++ [x])
    | otherwise = error "illegal state fsaOP Q0"

fsaOP Q1 (x:xs) ys
    | isOperator x = fsaOP Q1 xs (ys ++ [x])
    | otherwise = ((Op,ys), x:xs)

--Brackets
fsaBR :: FsaState -> String -> String -> ((Alphabet,String), String)
fsaBR _ "" ys = ((Bracket,ys),"")
fsaBR Q0 (x:xs) ys
    | isBracket x = fsaBR Q1 xs (ys ++ [x])
    | otherwise = error "illegal state fsaBR Q0"

fsaBR Q1 (x:xs) ys = ((Bracket,ys),x:xs)


testNUM xs = fsaNUM Q0 xs ""
testID xs = fsaID Q0 xs ""
testOP xs = fsaOP Q0 xs ""
testBR xs = fsaBR Q0 xs ""

isDigit x = elem x ['0'..'9']
isAlpha x = elem x ['a'..'z']
isAlphanum x = isDigit x || isAlpha x
isBracket x = elem x "(){}"
isOperator x = elem x "+-*/^=<>"

tokenizer :: String -> [(Alphabet,String)] 
tokenizer [] = []
tokenizer l@(x:xs)
    | isDigit x || x == '~' = numToken : tokenizer numRest
    | isAlpha x             = idToken : tokenizer idRest
    | isOperator x          = opToken : tokenizer opRest
    | isBracket x           = brToken : tokenizer brRest
    | otherwise = error "Invalid character not recognized!"
    where (numToken, numRest) = fsaNUM Q0 l ""
          (idToken, idRest)   = fsaID Q0 l ""
          (opToken, opRest)   = fsaOP Q0 l ""
          (brToken, brRest)   = fsaBR Q0 l ""


tokentest = tokenizer "if(5==5)var=12.3"




