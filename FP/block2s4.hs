-- Haskell session 4 of block 2
import FPPrac.Trees
-- Exercise 1
--a
data BinTree a b = LeafBin b
                 | NodeBin a (BinTree a b) (BinTree a b)
                 deriving Show

--b
data Unit = Unit
instance Show Unit where
    show Unit = ""

type Tree1a = BinTree Integer Integer
tree1a = NodeBin 5 (LeafBin 2) (LeafBin 7)

type Tree1b = BinTree (Int, Int) (Int, Int)
tree1b = NodeBin (5,5) (LeafBin (2,2)) (LeafBin (7,7))

type Tree1c = BinTree Unit Integer
tree1c = NodeBin Unit (LeafBin 2) (LeafBin 7)

type Tree4 = BinTree Integer Unit
tree4 = NodeBin 5 (LeafBin Unit) (LeafBin Unit)

--c
ppBin :: (Show a, Show b) => BinTree a b -> RoseTree
ppBin (LeafBin n) = RoseNode (show n) []
ppBin (NodeBin n left right) = RoseNode (show n) [ppBin left, ppBin right]


-- Exercise 2
--a
expr1 = "2"
expr2 = "(1+2)"
expr3 = "(9+(3*5))"
expr4 = "((3*5)+9)"
expr5 = "((6+y)*(x+4))"

data NT = E | O
parse :: NT -> [Char] -> (BinTree Char Int, [Char])
parse E (x:xs)
    | x == '(' = (NodeBin n t0 t2, r3)
    | x `elem` ['0'..'9'] = (LeafBin (read [x]), xs)
    | x == ')' = (LeafBin 0, xs)
    | otherwise = error "Invalid call to parse expression!"
    where (t0, r0) = parse E xs
          (NodeBin n left right, r1) = parse O r0
          (t2, r2) = parse E r1
          (t3, r3) = parse E r2

parse O (x:xs)
    | x `elem` ['+', '-', '*', '/', '^'] = (NodeBin x (LeafBin 0) (LeafBin 0), xs)
    | otherwise = error "Invalid call to parse operator!"

--b
parse2 :: NT -> [Char] -> (BinTree Char (Either Int Char), [Char])
parse2 E (x:xs)
    | x == '(' = (NodeBin n t0 t2, r3)
    | x `elem` ['0'..'9'] = (LeafBin (Left (read [x])), xs)
    | x `elem` ['a'..'z'] = (LeafBin (Right x), xs)
    | x == ')' = (LeafBin (Left 0), xs)
    | otherwise = error "Invalid call to parse expression!"
    where (t0, r0) = parse2 E xs
          (NodeBin n left right, r1) = parse2 O r0
          (t2, r2) = parse2 E r1
          (t3, r3) = parse2 E r2

parse2 O (x:xs)
    | x `elem` ['+','-','*','/','^'] = (NodeBin x (LeafBin (Left 0)) (LeafBin (Left 0)), xs)
    | otherwise = error "Invalid call to parse operator!"



-- Exercise 3
--a
data FsaState = Q0 | Q1 | Q2 | Q3
    deriving Show

--b
--Numbers
fsaNUM :: FsaState -> String -> String -> (String, String)
fsaNUM _ "" ys = (ys, "")
fsaNUM Q0 (x:xs) ys
    | x == '~' = fsaNUM Q1 xs (ys ++ [x])
    | elem x ['0'..'9'] = fsaNUM Q2 xs (ys ++ [x])
    | otherwise = error "illegal state fsaNUM Q0"

fsaNUM Q1 (x:xs) ys
    | elem x ['0'..'9'] = fsaNUM Q2 xs (ys ++ [x])
    | otherwise = error "illegal state fsaNUM Q1"

fsaNUM Q2 (x:xs) ys
    | elem x ['0'..'9'] = fsaNUM Q2 xs (ys ++ [x])
    | x == '.' = fsaNUM Q3 xs (ys ++ [x])
    | otherwise = (ys, x:xs)

fsaNUM Q3 (x:xs) ys
    | elem x ['0'..'9'] = fsaNUM Q3 xs (ys ++ [x])
    | otherwise = (ys, x:xs)

--ID
fsaID :: FsaState -> String -> String -> (String, String)
fsaID _ "" ys = (ys,"")
fsaID Q0 (x:xs) ys
    | elem x ['a'..'z'] = fsaID Q1 xs (ys ++ [x])
    | otherwise = error "Illegal state fsaID Q0"

fsaID Q1 (x:xs) ys
    | elem x ['a'..'z'] || elem x ['0'..'9'] = fsaID Q1 xs (ys ++ [x])
    | otherwise = (ys, x:xs)

--Operators
fsaOP :: FsaState -> String -> String -> (String, String)
fsaOP _ "" ys = (ys,"")
fsaOP Q0 (x:xs) ys
    | elem x "<>=*+-/^" = fsaOP Q1 xs (ys ++ [x])
    | otherwise = error "illegal state fsaOP Q0"

fsaOP Q1 (x:xs) ys
    | elem x "<>=*+" = fsaOP Q1 xs (ys ++ [x])
    | otherwise = (ys, x:xs)

--Brackets
fsaBR :: FsaState -> String -> String -> (String, String)
fsaBR _ "" ys = (ys,"")
fsaBR Q0 (x:xs) ys
    | x == '('= fsaBR Q1 xs (ys ++ [x])
    | x == ')'= fsaBR Q1 xs (ys ++ [x])
    | otherwise = error "illegal state fsaBR Q0"
{-
fsaBR Q1 (x:xs) ys
    | x == '(' = fsaBR Q1 xs (ys ++ [x])
    | x == ')' = fsaBR Q1 xs (ys ++ [x])
    | otherwise = (ys,x:xs)
-}
fsaBR Q1 (x:xs) ys = (ys,x:xs)

--Whitespace
fsaWHITE :: FsaState -> String -> String -> (String, String)
fsaWHITE _ "" ys = (ys,"")
fsaWHITE Q0 (x:xs) ys
    | x == ' ' = fsaWHITE Q1 xs (ys ++ [x])
    | otherwise = error "illegal state fsaWHITE Q0"

fsaWHITE Q1 (x:xs) ys
    | x == ' ' = fsaWHITE Q1 xs (ys ++ [x])
    | otherwise = (ys,x:xs)

testNUM xs = fsaNUM Q0 xs ""
testID xs = fsaID Q0 xs ""
testOP xs = fsaOP Q0 xs ""
testBR xs = fsaBR Q0 xs ""
testWHITE xs = fsaWHITE Q0 xs ""


--c
tokenizer :: String -> [String] 
tokenizer [] = []
tokenizer l@(x:xs)
    | elem x ['0'..'9'] || x == '~' = numToken : tokenizer numRest
    | elem x ['a'..'z']             = idToken : tokenizer idRest
    | elem x "<>=*/+-^"             = opToken : tokenizer opRest
    | elem x "()"                   = brToken : tokenizer brRest
    | x == ' '                      = whiteToken : tokenizer whiteRest
    | otherwise = error "Invalid character not recognized!"
    where (numToken, numRest) = fsaNUM Q0 l ""
          (idToken, idRest) = fsaID Q0 l ""
          (opToken, opRest) = fsaOP Q0 l ""
          (brToken, brRest) = fsaBR Q0 l ""
          (whiteToken, whiteRest) = fsaWHITE Q0 l ""

scantest = tokenizer "123.3hello69<=~44 ()"



-- Exercise 4
ex4 = "( (x* 69) +    (~123.5 / y))"
ex4b = "( (x* 69) +    (123.5 / y))"

readf :: (Fractional a, Read a) => String -> a
readf l@(x:xs)
    | x == '~' = -1 * read xs
    | otherwise = read l + 0.0

parse4 :: (Fractional a, Read a) => String -> (BinTree String (Either a String), [String])
parse4 xs = parse' E (removeWhite $ tokenizer xs)

removeWhite :: [String] -> [String]
removeWhite [] = []
removeWhite (xs:xss)
    | head xs == ' ' = removeWhite xss
    | otherwise = xs : removeWhite xss

parse' :: (Fractional a, Read a) => NT -> [String] -> (BinTree String (Either a String), [String])
parse' E (xs:xss)
    | head xs == '(' = (NodeBin n t0 t2, r3)
    | head xs `elem` ['0'..'9'] || head xs == '~' = (LeafBin (Left (readf xs)), xss)
    | head xs `elem` ['a'..'z'] = (LeafBin (Right xs), xss)
    | head xs == ')' = (LeafBin (Left 0.0), xss)
    | otherwise = error "Invalid call to parse expression!"
    where (t0, r0) = parse' E xss
          (NodeBin n left right, r1) = parse' O r0
          (t2, r2) = parse' E r1
          (t3, r3) = parse' E r2

parse' O (xs:xss)
    | head xs `elem` "+-*/^" = (NodeBin xs (LeafBin (Left 0.0)) (LeafBin (Left 0.0)), xss)
    | otherwise = error "Invalid call to parse operator!"



-- Exercise 5
val "x" = 20
val "y" = 10

op :: (Floating a) => String -> (a->a->a)
op x
    | x == "+" = (+)
    | x == "-" = (-)
    | x == "*" = (*)
    | x == "/" = (/)
    | x == "^" = (**)
    | x == "**" = (**)


eval (NodeBin n left right) = (op n) (eval left) (eval right)
eval (LeafBin (Left x)) = x
eval (LeafBin (Right x)) = val x


ex5 = "(10+5)"
ex5b = "( (x* 69) +    (123.5 ^ y))"
ex5c = "( (x*(30+39)) +    (123.5 ** y))"
--ex4b = "( (x* 69) +    (123.5 / y))" = 1392.25

testeval xs = eval $ fst $ parse4 xs

